package jgrove;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

/**
 * Core class to work with the GrovePi board.
 * 
 * Use this class together with the {@link GroveSensors} class to create
 * instances for the sensors connected to your GrovePi:
 * 
 * <pre>
 * GrovePi grovePi = new GrovePi();
 * GroveSensors sensors = new GroveSensors(grovePi);
 * Ultrasonic ultrasonic = sensors.createUltrasonic(4);
 * Button button = sensors.createButton(3);
 * </pre>
 * 
 * @author Johannes Bergmann
 */
public class GrovePi {

	// Arduino I2C Address
	private static final byte ADDRESS = 0x04;

	private static final int DIGITAL_READ_COMMAND = 1;
	private static final int DIGITAL_WRITE_COMMAND = 2;

	private static final byte PIN_MODE_COMMAND = 5;
	private static final byte PIN_MODE_OUTPUT = 1;
	private static final byte PIN_MODE_INPUT = 0;

	private static final byte RTC_GET_TIME_COMMAND = 30;

	private static final byte UNUSED = 0;

	private final I2CDevice device;

	public GrovePi() throws IOException {
		final I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
		device = bus.getDevice(GrovePi.ADDRESS);
	}

	// --- Factory methods for creating different kinds of pin implementations

	// --- Other operations

	public byte[] getRtcTime() throws IOException {

		writeI2c(RTC_GET_TIME_COMMAND, UNUSED, UNUSED, UNUSED);
		sleep(100);
		return readI2c(4);
	}

	public DigitalInputPin createDigitalInputPin(int pin) throws IOException {
		return new DigitalInputPin(this, pin);
	}

	public DigitalOutputPin createDigitalOutputPin(int pin) throws IOException {
		return new DigitalOutputPin(this, pin);
	}

	public I2cPin createI2cPin(int pin) throws IOException {
		return new I2cPin(this, pin);
	}

	// --- Operations used by the pin implementations

	void setPinModeInput(int pin) throws IOException {
		setPinMode(pin, PIN_MODE_INPUT);
	}

	void setPinModeOutput(int pin) throws IOException {
		setPinMode(pin, PIN_MODE_OUTPUT);
	}

	private void setPinMode(int pin, int pinModeInputOrOutput)
			throws IOException {
		writeI2c(PIN_MODE_COMMAND, pin, pinModeInputOrOutput, UNUSED);
	}

	int digitalRead(int pin) throws IOException {
		writeI2c(DIGITAL_READ_COMMAND, pin, UNUSED, UNUSED);
		return device.read() & 0xff;
	}

	void digitalWrite(int pin, int value) throws IOException {
		writeI2c(DIGITAL_WRITE_COMMAND, pin, value, UNUSED);
	}

	byte[] readI2c(int numberOfBytes) throws IOException {
		byte[] buffer = new byte[numberOfBytes];
		device.read(1, buffer, 0, buffer.length);
		return buffer;
	}

	void writeI2c(int... bytes) throws IOException {
		// Convert array: int[] to byte[]
		final ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			byteBuffer.put((byte) bytes[i]);
		}
		device.write(1, byteBuffer.array(), 0, byteBuffer.limit());
	}

	public static void sleep(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Base class for a pin of the GrovePi.
	 * 
	 * The implementations support different kinds interactions with this pin
	 * (e.g. reading digital values only with {@link DigitalInputPin}.
	 * 
	 * @author Johannes Bergmann
	 */
	protected static class Pin {

		protected final GrovePi grovePi;
		protected final int pin;

		protected Pin(GrovePi grovePi, int pin) throws IOException {
			this.grovePi = grovePi;
			this.pin = pin;
		}
	}
}
