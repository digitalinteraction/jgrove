package grovepi.sensors;

import grovepi.GrovePi;
import grovepi.PinMode;

public class Buzzer extends Sensor {

	public Buzzer(GrovePi device, int pin) {
		super(device, pin, PinMode.OUTPUT);
	}

}
