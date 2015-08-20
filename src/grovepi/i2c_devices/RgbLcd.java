package grovepi.i2c_devices;

import java.io.IOException;
import java.nio.charset.Charset;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

/**
 * @author Johannes Bergmann
 */
public class RgbLcd {

	private static final int DISPLAY_RGB_ADDR = 0x62;
	private static final int DISPLAY_TEXT_ADDR = 0x3e;

	// Commands
	private static final int CLEAR_DISPLAY_CMD = 0x01;
	private static final int CONTROL_DISPLAY_CMD = 0x08;
	private static final int ONE_LINE_CMD = 0x20;
	private static final int TWO_LINES_CMD = 0x28;
	private static final int SHIFT_TEXT_CMD = 0x10;

	// Flags for controlling display
	private static final int DISPLAY_ON = 0x04;
	private static final int CURSOR_ON = 0x02;
	private static final int BLINK_ON = 0x01;

	// Flags for shifting text
	private static final int TEXT_MOVE = 0x08;
	private static final int MOVE_RIGHT = 0x04;
	private static final int MOVE_LEFT = 0x00;

	private final I2CDevice lightDevice;
	private final I2CDevice textDevice;

	private int displayState = 0;

	public RgbLcd() throws IOException {
		final I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
		lightDevice = bus.getDevice(DISPLAY_RGB_ADDR);
		textDevice = bus.getDevice(DISPLAY_TEXT_ADDR);
	}

	public void setColor(int r, int g, int b) throws IOException {

		lightDevice.write(0, (byte) 0);
		lightDevice.write(1, (byte) 0);
		lightDevice.write(0x08, (byte) 0xaa);
		lightDevice.write(4, (byte) r);
		lightDevice.write(3, (byte) g);
		lightDevice.write(2, (byte) b);
	}

	private void command(int cmd) throws IOException {
		textDevice.write(0x80, (byte) cmd);
	}

	private void displayCommand(int prop, boolean on) throws IOException {
		displayState = (on ? displayState | prop : displayState & ~prop);
		command(CONTROL_DISPLAY_CMD | displayState);
	}

	public void oneLine() throws IOException {
		command(ONE_LINE_CMD);
	}

	public void twoLines() throws IOException {
		command(TWO_LINES_CMD);
	}

	/**
	 * Activates or deactivates the display (the text, not the backlight).
	 */
	public void display(boolean on) throws IOException {
		displayCommand(DISPLAY_ON, on);
	}

	/**
	 * Shows or hides the underline cursor.
	 */
	public void cursor(boolean show) throws IOException {
		displayCommand(CURSOR_ON, show);
	}

	public void cursorBlink(boolean blink) throws IOException {
		displayCommand(BLINK_ON, blink);
	}

	public void moveLeft() throws IOException {
		command(SHIFT_TEXT_CMD | TEXT_MOVE | MOVE_LEFT);
	}

	public void moveRight() throws IOException {
		command(SHIFT_TEXT_CMD | TEXT_MOVE | MOVE_RIGHT);
	}

	public void setText(String text) throws IOException {
		clearText();
		display(true);
		twoLines();
		int count = 0;
		int row = 0;
		for (byte c : text.getBytes(Charset.forName("US-ASCII"))) {
			if (c == '\n' || count == 16) {
				count = 0;
				row += 1;
				if (row == 2) {
					break;
				}
				command(0xc0);
				if (c == '\n') {
					continue;
				}
			}
			count += 1;
			textDevice.write(0x40, c);
		}
	}

	public void clearText() throws IOException {
		command(CLEAR_DISPLAY_CMD); // clear display
	}

	/**
	 * Clear text and switches light off.
	 */
	public void shutdown() throws IOException {
		clearText();
		setColor(0, 0, 0);
	}
}