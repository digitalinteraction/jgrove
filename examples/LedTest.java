import grovepi.GrovePi;
import grovepi.Pin;
import grovepi.sensors.Led;
import grovepi.common.Delay;

/**
 * @author Dan Jackson, Newcastle University, 2015.
 */
public class LedTest {

	public static void main(String[] args) {
		GrovePi grovePi = new GrovePi();
		Led led = grovePi.getDeviceFactory().createLed(Pin.DIGITAL_PIN_3);
		for(;;) {
			led.setState(true);
			Delay.milliseconds(250);
			led.setState(false);
			Delay.milliseconds(250);
		}
	}

}
