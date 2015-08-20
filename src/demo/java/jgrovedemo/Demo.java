package jgrovedemo;

import java.io.IOException;

import jgrove.GrovePi;
import jgrove.GroveSensors;
import jgrove.sensors.Button;
import jgrove.sensors.Ultrasonic;
import jgrove.sensors.i2c.RgbLcd;

/**
 * Code for the Raspberry Pi and GrovePi.
 */
public class Demo {

	private final GrovePi grovePi;
	private final Button button;
	//private final Ultrasonic ultrasonic;
	//private final RgbLcd lcd;

	public static void main(String[] args) throws Exception {
		new Demo().run();
	}

	public Demo() throws IOException {
		grovePi = new GrovePi();
		GroveSensors sensors = new GroveSensors(grovePi);
		button = sensors.createButton(3);
		//ultrasonic = sensors.createUltrasonic(4);
		//lcd = new RgbLcd();
	}
	
	private void run() throws Exception {
		
		for (;;) {
			boolean buttonPressed = button.isPressed();
			if (buttonPressed) {
				System.out.println("PRESSED\n");
			} else {
				System.out.println("RELEASED\n");
			}
			GrovePi.sleep(500);
		}
		
		/*
		lcd.shutdown();
		lcd.clearText();
		lcd.setColor(0, 200, 0);
		lcd.setText("Hello\nWorld!");
		lcd.display(true);
		GrovePi.sleep(1000);
		lcd.shutdown();
		*/
	}


}
