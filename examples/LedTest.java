import grovepi.GrovePi;
import grovepi.Pin;
import grovepi.sensors.Led;

/**
 * @author Dan Jackson, Newcastle University, 2015.
 */
public class LedTest {

	public static void main(String[] args) {
		GrovePi grovePi = new GrovePi();
		Led led = grovePi.getDeviceFactory().createLed(Pin.DIGITAL_PIN_3);
		for(;;) {
			led.setState(true);
			grovepi.common.Delay(250);
			led.setState(false);
			grovepi.common.Delay(250);
		}
	}

}
