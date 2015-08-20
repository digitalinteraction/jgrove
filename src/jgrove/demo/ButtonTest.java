package jgrove.demo;

import java.io.IOException;

import jgrove.GrovePi;
import jgrove.GroveSensors;
import jgrove.sensors.Button;

public class ButtonTest {

	public static void main(String[] args) throws IOException {
		final ButtonTest test = new ButtonTest();
		test.run();
	}

	private final GrovePi grovePi;
	private final GroveSensors sensors;
	private final Button button;

	private ButtonTest() throws IOException {
		grovePi = new GrovePi();
		sensors = new GroveSensors(grovePi);
		button = sensors.createButton(3);
	}
	
	public void run() throws IOException {
		for (;;) {
			boolean buttonPressed = button.isPressed();
			if (buttonPressed) {
				System.out.println("PRESSED");
			} else {
				System.out.println("RELEASED");
			}
		}
		
	}

}
