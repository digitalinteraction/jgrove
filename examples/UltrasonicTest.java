import grovepi.Constants;
import grovepi.GrovePi;
import grovepi.Pin;
import grovepi.sensors.UltrasonicRangerSensor;

/**
 * @author Dan Jackson, Newcastle University, 2015.
 */
public class UltrasonicTest {

	public static void main(String[] args) {
		final UltrasonicTest test = new UltrasonicTest();
		test.run();
	}

	private final GrovePi grovePi;
	private final UltrasonicRangerSensor ultrasonic;

	private UltrasonicTest() {
		grovePi = new GrovePi();
		ultrasonic = grovePi.getDeviceFactory().createUltraSonicSensor(Pin.DIGITAL_PIN_4);
		run();
	}
	
	public void run() {
		for(;;) {
			int value = ultrasonic.getDistance();
            System.out.println("" + value + " com");
		}
	}

}
