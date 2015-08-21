package grovepi.sensors;

import grovepi.GrovePi;
import grovepi.PinMode;

public class Led extends Sensor {

	public Led(GrovePi device, int pin) {
		super(device, pin, PinMode.OUTPUT);
	}

}
