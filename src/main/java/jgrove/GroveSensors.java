package jgrove;

import java.io.IOException;

import jgrove.sensors.Button;
import jgrove.sensors.Switcher;
import jgrove.sensors.SwitcherInverse;
import jgrove.sensors.Ultrasonic;

public class GroveSensors {

	private final GrovePi grovePi;

	public GroveSensors(GrovePi grovePi) {
		this.grovePi = grovePi;
	}

	// --- Factory methods for sensors

	public Ultrasonic createUltrasonic(int pin) throws IOException {
		return new Ultrasonic(grovePi.createI2cPin(pin));
	}

	public Button createButton(int pin) throws IOException {
		return new Button(grovePi.createDigitalInputPin(pin));
	}

	public Switcher createSwitcher(int pin, boolean inititialStateOn)
			throws IOException {
		return new Switcher(grovePi.createDigitalOutputPin(pin),
				inititialStateOn);
	}

	public Switcher createSwitcherInverse(int pin, boolean inititialStateOn)
			throws IOException {
		return new SwitcherInverse(grovePi.createDigitalOutputPin(pin),
				inititialStateOn);
	}
}
