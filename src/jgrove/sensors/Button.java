package jgrove.sensors;

import java.io.IOException;

import jgrove.DigitalInputPin;
import jgrove.GrovePi;

public class Button {

	private final DigitalInputPin pin;

	public Button(DigitalInputPin pin) throws IOException {
		this.pin = pin;
	}

	public boolean isPressed() throws IOException {
		return pin.read() == 1;
	}

	public static void main(String[] args) throws IOException {
		final Button button = new Button(new GrovePi().createDigitalInputPin(3));
		for (int i = 0; i < 40; i++) {
			System.out.println(button.isPressed());
			GrovePi.sleep(500);
		}
	}
}
