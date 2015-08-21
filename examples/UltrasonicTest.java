import grovepi.Constants;
import grovepi.GrovePi;
import grovepi.Pin;
import grovepi.sensors.Led;
import grovepi.sensors.RotaryAngleSensor;

/**
 * @author Dan Jackson, Newcastle University, 2015.
 */
public class UltrasonicTest {

	public UltrasonicRangerSensor(GrovePi device, int pin) {
		this.device = device;
		this.pin = pin;
	}
	
    public int getDistance() {
        byte[] buffer = new byte[] {COMMAND_ADDRESS, (byte)pin, Constants.UNUSED, Constants.UNUSED};
        device.getDirectAccess().write(buffer);
//jgrove.common.Delay.milliseconds(200);	// C# version doesn't do this
        device.getDirectAccess().read(buffer);
        return ((buffer[1] & 0xFF) << 8) + (buffer[2] & 0xFF);
    }	
	
	public static void main(String[] args) {
		final UltrasonicTest test = new UltrasonicTest();
		test.run();
	}

	private final GrovePi grovePi;
	private final UltrasonicRangerSensor ultrasonic;

	private UltrasonicTest() {
		grovePi = new GrovePi();
		ultrasonic = grovePi.getDeviceFactory().createUltraSonicSensor(Pin.ANALOG_PIN_0);
		run();
	}
	
	public void run() {
		for(;;) {
			int value = ultrasonic.getDistance();
            System.out.println("" + value + " com");
		}
	}

}
