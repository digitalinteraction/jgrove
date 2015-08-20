package grovepi;

import grovepi.sensors.AccelerometerSensor;
import grovepi.sensors.ButtonSensor;
import grovepi.sensors.Led;
import grovepi.sensors.UltrasonicRangerSensor;

public class GroveDeviceFactory {

	protected final GrovePi device;
	
	public GroveDeviceFactory(GrovePi device) {
		this.device = device;
	}
	
    //public Relay createRelay(int pin);
	
    public Led createLed(int pin) {
    	return new Led(device, pin);
    }
    
    //public TemperatureAndHumiditySensor createTemperatureAndHumiditySensor(int pin, Model model);
	
	public UltrasonicRangerSensor createUltraSonicSensor(int pin) {
    	return new UltrasonicRangerSensor(device, pin);
    }
    
    public AccelerometerSensor createAccelerometerSensor(int pin) {
    	return new AccelerometerSensor(device, pin);
	}
    
    //public RealTimeClock createRealTimeClock(int pin);
    //public LedBar createBuildLedBar(int pin);
    //public FourDigitDisplay createFourDigitDisplay(int pin);
    //public ChainableRgbLed createChainableRgbLed(int pin);
    //public RotaryAngleSensor createRotaryAngleSensor(int pin);
    //public Buzzer createBuzzer(int pin);
    //public SoundSensor createSoundSensor(int pin);
    //public LightSensor createLightSensor(int pin);
    
    public ButtonSensor createButtonSensor(int pin) {
    	return new ButtonSensor(device, pin);
    }
    
    //public RgbLcdDisplay createRgbLcdDisplay();
    //public RgbLcdDisplay createRgbLcdDisplay(int rgbAddress, int textAddress);
	
}

