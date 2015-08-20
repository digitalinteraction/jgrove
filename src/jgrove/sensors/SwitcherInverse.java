package jgrove.sensors;

import java.io.IOException;

import jgrove.DigitalOutputPin;

/**
 * A switcher which returns "on" when device returns 0 "off" when device returns
 * 1.
 * 
 * @author Johannes Bergmann
 */
public class SwitcherInverse extends Switcher {

	public SwitcherInverse(DigitalOutputPin pin) throws IOException {
		super(pin);
	}

	public SwitcherInverse(DigitalOutputPin pin, boolean on) throws IOException {
		super(pin, !on);
	}

	@Override
	public boolean isOn() {
		return !super.isOn();
	}

	@Override
	public void setOn(boolean on) throws IOException {
		super.setOn(!on);
	}

}
