package grovepi;

public class Pin
{
	public static final int ANALOG_PIN_0 = 0;
	public static final int DIGITAL_PIN_2 = 2;
	public static final int DIGITAL_PIN_3 = 3;
	public static final int DIGITAL_PIN_4 = 4;
	// [dgj] Why aren't more pins defined?
}

/*
public enum Pin
{
	ANALOG_PIN_0(0),
	DIGITAL_PIN_2(2),
	DIGITAL_PIN_3(3),
	DIGITAL_PIN_4(4),
	// [dgj] Why aren't more pins defined?
	;
	
	final int value;
	
	Pin(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
*/
