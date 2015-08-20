package jgrove.sensors;

import java.io.IOException;

import jgrove.DigitalOutputPin;
import jgrove.GrovePi;

public class Switcher {

	private final DigitalOutputPin pin;
	private boolean on;

	public Switcher(DigitalOutputPin pin) throws IOException {
		this(pin, false);
	}

	public Switcher(DigitalOutputPin pin, boolean on) throws IOException {
		this.pin = pin;
		// Initial device switch
		setOn(on);
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) throws IOException {
		this.on = on;
		pin.write(on ? 1 : 0);
	}

	public void toggle() throws IOException {
		setOn(!isOn());
	}

	public static void main(String[] args) throws IOException {
		final Switcher switcher = new Switcher(
				new GrovePi().createDigitalOutputPin(2));
		System.out.println("Switch on");
		switcher.setOn(true);
		GrovePi.sleep(1000);
		System.out.println("Switch off");
		switcher.setOn(false);
		GrovePi.sleep(1000);
		System.out.println("Toggle");
		for (int i = 0; i < 10; i++) {
			switcher.toggle();
			System.out.println(switcher.isOn() ? "on" : "off");
			GrovePi.sleep(500);
		}
	}
}
