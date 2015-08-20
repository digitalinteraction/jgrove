package jgrove.demo;

import java.io.IOException;

import jgrove.GrovePi;
import jgrove.sensors.i2c.RgbLcd;

public class RgbLcdTest {

	public static void main(String[] args) throws IOException {
		final RgbLcdTest test = new RgbLcdTest();
		if (args.length > 0) {
			test.testGreeting(args);
		} else {
			test.run();
		}
	}

	private final RgbLcd lcd;

	private RgbLcdTest() throws IOException {
		lcd = new RgbLcd();
	}

	public void run() throws IOException {
		testGreeting(new String[0]);
		testDisplayOnOff();
		testMove();
		testColors();
		testCursor();
		lcd.shutdown();
	}

	public void testGreeting(String[] args) throws IOException {
		String text = "Hello\nGrovePi";
		int red = 0;
		int green = 255;
		int blue = 0;

		if (args.length > 0) {
			text = args[0];
		}
		if (args.length > 1) {
			String[] rgb = args[1].split(",");
			red = Integer.parseInt(rgb[0]);
			green = Integer.parseInt(rgb[1]);
			blue = Integer.parseInt(rgb[2]);
		}

		System.out.println("Sending text: " + text);
		lcd.setText(text);
		lcd.setColor(red, green, blue);
		GrovePi.sleep(1000);
	}

	public void testDisplayOnOff() throws IOException {
		lcd.setText("Display on/off");
		GrovePi.sleep(500);
		lcd.setColor(100, 100, 100);
		GrovePi.sleep(1000);
		lcd.display(false);
		GrovePi.sleep(1000);
		lcd.display(true);
		GrovePi.sleep(1000);
	}

	public void testMove() throws IOException {
		final String text = "A long, long text which is scrolled left and right...";
		lcd.setText(text);
		for (int i = 0; i < 16; i++) {
			lcd.moveLeft();
			GrovePi.sleep(100);
		}
		for (int i = 0; i < 16; i++) {
			lcd.moveRight();
			GrovePi.sleep(100);
		}
	}

	public void testCursor() throws IOException {
		lcd.setText("Cursor on\n");
		lcd.cursor(true);
		GrovePi.sleep(1000);
		lcd.setText("Blinking Cursor\n");
		lcd.cursorBlink(true);
		GrovePi.sleep(1000);
		lcd.setText("Blinking off\n");
		lcd.cursorBlink(false);
		GrovePi.sleep(1000);
		lcd.setText("Cursor off\n");
		lcd.cursor(false);
		GrovePi.sleep(1000);
	}

	public void testColors() throws IOException {
		lcd.setText("Red");
		for (int color = 0; color <= 255; color++) {
			lcd.setColor(color, 0, 0);
			GrovePi.sleep(5);
		}
		lcd.setText("Green");
		for (int color = 0; color <= 255; color++) {
			lcd.setColor(0, color, 0);
			GrovePi.sleep(5);
		}
		lcd.setText("Blue");
		for (int color = 0; color <= 255; color++) {
			lcd.setColor(0, 0, color);
			GrovePi.sleep(5);
		}
	}

}
