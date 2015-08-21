import grovepi.GrovePi;
import grovepi.Pin;
import grovepi.sensors.ButtonSensor;

/**
 * @author Dan Jackson, Newcastle University, 2015.
 */
public class ButtonTest {
	public static void main(String[] args) {
		GrovePi grovePi = new GrovePi();
		ButtonSensor button = grovePi.getDeviceFactory().createButtonSensor(Pin.DIGITAL_PIN_4);
		for(;;) {
            System.out.print(button.isPressed() ? 1 : 0);
		}
	}
}
