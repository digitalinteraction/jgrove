package grovepi;

import java.io.IOException;

import grovepi.common.Delay;


/**
 * GrovePi+ board.
 * 
 * @author Dan Jackson (originally based on code by Johannes Bergmann, but rewritten following C# implementation)
 */
public class GrovePi {
	
    private static class Command
    {
		public static final byte DIGITAL_READ = 1;
		public static final byte DIGITAL_WRITE = 2;
		public static final byte ANALOG_READ = 3;
		public static final byte ANALOG_WRITE = 4;
		public static final byte PIN_MODE = 5;
		public static final byte VERSION = 8;
    };

	private final GrovePiI2CDevice device;
	public GrovePiI2CDevice getDirectAccess() { return device; }    
	
	public GrovePi() {
		this(GrovePiI2CDevice.getInstanceRuntimeExecption());
	}
    
	public GrovePi(GrovePiI2CDevice device) {
		this.device = device;
	}
	
	
	// [dgj] Added this -- makes more sense than the C# way of making sensors
	private GroveDeviceFactory deviceFactory = null;
	public GroveDeviceFactory getDeviceFactory() {
		if (deviceFactory == null) {
			deviceFactory = new GroveDeviceFactory(this);
		}
		return deviceFactory;
	}
	
	

    public String getFirmwareVersion() throws IOException
    {
    	byte[] buffer = new byte[] { Command.VERSION, Constants.UNUSED, Constants.UNUSED, Constants.UNUSED };
        getDirectAccess().read(buffer);
        return "" + buffer[1] + buffer[2] + "." + buffer[3] + "";
    }	
	

    public int digitalRead(int pin)
    {
        byte[] buffer = new byte[] { (byte)Command.DIGITAL_READ, (byte)pin, Constants.UNUSED, Constants.UNUSED};
        getDirectAccess().write(buffer);
Delay.milliseconds(100);	// C# version doesn't do this
        int value = getDirectAccess().read();
	System.out.print("<pin-" + pin + "<=" + value + ">");
	return value;
    }

    public void digitalWrite(int pin, int value)
    {
    	byte[] buffer = new byte[] { (byte)Command.DIGITAL_WRITE, (byte)pin, (byte)value, Constants.UNUSED};
System.out.print("<pin-" + pin + "=>" + value + ">");
    	getDirectAccess().write(buffer);
    }

    public int analogRead(int pin)
    {
    	byte[] buffer = new byte[] { (byte)Command.ANALOG_READ, (byte)pin, Constants.UNUSED, Constants.UNUSED, Constants.UNUSED};
    	getDirectAccess().write(buffer);
Delay.milliseconds(100);	// C# version doesn't do this
        getDirectAccess().read(buffer);
        return Byte.toUnsignedInt(buffer[1])*256 + (int)buffer[2];
    }

    public void analogWrite(int pin, int value)
    {
    	byte[] buffer = new byte[] {(byte)Command.ANALOG_WRITE, (byte)pin, (byte)value, Constants.UNUSED};
    	getDirectAccess().write(buffer);
    }

    public void pinMode(int pin, PinMode mode)
    {
    	byte[] buffer = new byte[] {(byte)Command.PIN_MODE, (byte)pin, (byte)mode.getValue(), Constants.UNUSED};
    	getDirectAccess().write(buffer);
System.out.println("DEBUG: Mode for pin " + pin + " = " + mode + " (" + mode.getValue() + ").");
    }
	
}
