package grovepi.sensors;

import grovepi.GrovePi;
import grovepi.PinMode;

/** @author Dan Jackson, Newcastle University, 2015. */
public class ButtonSensor extends Sensor {

	public ButtonSensor(GrovePi device, int pin) {
		super(device, pin, PinMode.INPUT);
	}

	// [dgj] Added
	public boolean isPressed() {
		return getState();
	}
	
}
