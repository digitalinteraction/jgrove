package grovepi;

/*
public class PinMode
{
	public static final byte INPUT = 0;
	public static final byte OUTPUT = 1;
	//public static final byte COMMAND = 5;	// [dgj] Added this
}
*/

public enum PinMode
{
	// [dgj] Why aren't more pins defined?
	INPUT(0),
	OUTPUT(1),
	//COMMAND(5)		// [dgj] Added this
	;
	
	final int value;
	
	PinMode(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
