package jgrove;

import java.io.IOException;

import jgrove.GrovePi.Pin;

/**
 * A pin on the GrovePi board for a digital Grove device.
 * 
 * The Grove device attached to this pin is not an I2C device. The GrovePi uses
 * I2C communication to instruct the built in Arduino to read or write data to a
 * GrovePi pin.
 * 
 * @author Johannes Bergmann
 */
public class I2cPin extends Pin {

	I2cPin(GrovePi grovePi, int pin) throws IOException {
		super(grovePi, pin);
	}

	public byte[] read(int numberOfBytes) throws IOException {
		return grovePi.readI2c(numberOfBytes);
	}

	public void write(int... bytes) throws IOException {
		grovePi.writeI2c(bytes);
	}

	public void writeCommand(int command) throws IOException {
		write(command, pin, 0, 0);
	}

	public void sleep(int msec) throws IOException {
		GrovePi.sleep(msec);
	}

}
