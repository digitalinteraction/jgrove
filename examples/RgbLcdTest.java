import grovepi.i2c_devices.RgbLcd;
import grovepi.common.Delay;

/**
 * @author Johannes Bergmann
 */
public class RgbLcdTest {

	public static void main(String[] args) {
		final RgbLcdTest test = new RgbLcdTest();
		if (args.length > 0) {
			test.testGreeting(args);
		} else {
			test.run();
		}
	}

	private final RgbLcd lcd;

	private RgbLcdTest() {
		lcd = new RgbLcd();
	}

	public void run() {
		testGreeting(new String[0]);
		testDisplayOnOff();
		testMove();
		testColors();
		testCursor();
		lcd.shutdown();
	}

	public void testGreeting(String[] args) {
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
		Delay.milliseconds(1000);
	}

	public void testDisplayOnOff() {
		lcd.setText("Display on/off");
		Delay.milliseconds(500);
		lcd.setColor(100, 100, 100);
		Delay.milliseconds(1000);
		lcd.display(false);
		Delay.milliseconds(1000);
		lcd.display(true);
		Delay.milliseconds(1000);
	}

	public void testMove() {
		final String text = "A long, long text which is scrolled left and right...";
		lcd.setText(text);
		for (int i = 0; i < 16; i++) {
			lcd.moveLeft();
			Delay.milliseconds(100);
		}
		for (int i = 0; i < 16; i++) {
			lcd.moveRight();
			Delay.milliseconds(100);
		}
	}

	public void testCursor() {
		lcd.setText("Cursor on\n");
		lcd.cursor(true);
		Delay.milliseconds(1000);
		lcd.setText("Blinking Cursor\n");
		lcd.cursorBlink(true);
		Delay.milliseconds(1000);
		lcd.setText("Blinking off\n");
		lcd.cursorBlink(false);
		Delay.milliseconds(1000);
		lcd.setText("Cursor off\n");
		lcd.cursor(false);
		Delay.milliseconds(1000);
	}

	public void testColors() {
		lcd.setText("Red");
		for (int color = 0; color <= 255; color++) {
			lcd.setColor(color, 0, 0);
			Delay.milliseconds(5);
		}
		lcd.setText("Green");
		for (int color = 0; color <= 255; color++) {
			lcd.setColor(0, color, 0);
			Delay.milliseconds(5);
		}
		lcd.setText("Blue");
		for (int color = 0; color <= 255; color++) {
			lcd.setColor(0, 0, color);
			Delay.milliseconds(5);
		}
	}

}
