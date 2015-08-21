package grovepi.sensors;

import grovepi.GrovePi;
import grovepi.PinMode;

public class Relay extends Sensor {

	public Relay(GrovePi device, int pin) {
		super(device, pin, PinMode.OUTPUT);
	}

}
