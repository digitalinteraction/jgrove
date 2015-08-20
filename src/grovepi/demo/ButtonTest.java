package grovepi.demo;

import java.io.IOException;

import grovepi.GrovePi;
import grovepi.Pin;
import grovepi.sensors.ButtonSensor;
import grovepi.sensors.Led;

public class ButtonTest {

	public static void main(String[] args) throws IOException {
		final ButtonTest test = new ButtonTest();
		test.run();
	}

	private final GrovePi grovePi;
	private final ButtonSensor button;
	private final Led led;

	private ButtonTest() {
		grovePi = new GrovePi();
		button = grovePi.getDeviceFactory().createButtonSensor(Pin.DIGITAL_PIN_3);
		led = grovePi.getDeviceFactory().createLed(Pin.DIGITAL_PIN_2);
		run();
	}
	
	public void run() {
		for (;;) {
			boolean buttonPressed = button.isPressed();
			if (buttonPressed) {
				System.out.println("PRESSED");
				led.setState(true);
			} else {
				System.out.println("RELEASED");
				led.setState(false);
			}
		}
		
	}

}
