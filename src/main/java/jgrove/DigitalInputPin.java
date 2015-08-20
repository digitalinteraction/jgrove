package jgrove;

import java.io.IOException;

import jgrove.GrovePi.Pin;

/**
 * A pin on the GrovePi board which will be used for reading digital values
 * only.
 * 
 * @author Johannes Bergmann
 */
public class DigitalInputPin extends Pin {

	DigitalInputPin(GrovePi grovePi, int pin) throws IOException {
		super(grovePi, pin);
		grovePi.setPinModeInput(pin);
	}

	public int read() throws IOException {
		return grovePi.digitalRead(pin);
	}
}
