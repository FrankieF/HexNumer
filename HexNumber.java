/**
 * @author frankie fasola
 * 7/19/2016
 * This class represents a hexadecimal number, allowing for basic mathematical operations
 * and conversion from hex to decimal and decimal to hex.
 */
package v1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fasolaf5
 *
 */
public class HexNumber {
	
	/**
	 * The hex representation of the number.
	 */
	private String hexValue;
	/**
	 * The decimal value of the number.
	 */
	private long decimalValue;
	
	/**
	 * Possible digits in a hex number, used when converting.
	 */
	public static final String DIGITS = "0123456789ABCDEF";
	
	/**
	 * Constructor to make a HexNumber from a valid hexadecimal string.
	 * @param hexValue - the hex value.
	 * @throws InvalidHexNumberException - when the hexValue contains invalid digits.
	 */
	public HexNumber(String hexValue) throws InvalidHexNumberException {
		HexNumber.isHex(hexValue); // check if valid hex number
		decimalValue = HexNumber.decimalValue(hexValue); // parse and store in long
		this.hexValue = hexValue; // set hexValue
	}
	
	/**
	 * Constructor to make a HexNumber from a long.
	 * @param hexValue - the hex value.
	 * @throws InvalidHexNumberException - when the decimalValue contains invalid digits.
	 */
	public HexNumber(long decimalValue) throws InvalidHexNumberException {
		HexNumber.isHex(HexNumber.hexValue(decimalValue)); // parse and check if valid hex number
		hexValue = HexNumber.hexValue(decimalValue); // parse and store in our string
		this.decimalValue = decimalValue; // set decimalValue
	}
	
	/** 
	 * Standard getter method; returns the hexadecimal value (base 16) representation of an instance this class.
	 * @return hexValue
	 */
	public String getHexValue() {
		return hexValue;
	}
	
	/**
	 * Standard getter method; returns the decimal value (base 10) representation of an instance this class.
	 * @return
	 */
	public long getDecimalValue() {
		return decimalValue;
	}
	
	/**
	 * Adds on hexadecimal number to another.
	 * @param hex the HexNumber to add to the instance of this class. 
	 * @return a new HexNumber after adding the instance and hex.
	 */
	public HexNumber add(HexNumber hex) {
		long value = decimalValue + hex.getDecimalValue();
		return new HexNumber(value);
	}
	
	/**
	 * Divides on hexadecimal number by another.
	 * @param hex - the HexNumber to divide this instance by.
	 * @return a new HexNumber after dividing the instance and hex.
	 */
	public HexNumber divide(HexNumber hex) {
		long value = decimalValue / hex.getDecimalValue();
		return new HexNumber(value);
	}
	
	/**
	 * Returns true for valid Hexadecimal strings.
	 * @param potenialHexString - Hexadecimal number to check.
	 * @return True for valid hex strings.
	 */
	public static boolean isHex(String potenialHexString) throws InvalidHexNumberException {
		boolean isHexNumber = false;
		String regularExpression = "^[-]?[a-fA-F\\d]*$"; // The possible values for hex numbers
		Pattern pattern = Pattern.compile(regularExpression); // create a pattern to create a matcher
		Matcher matcher = pattern.matcher(potenialHexString); // create a matcher to check our string
		isHexNumber = matcher.matches(); // check if the string was valid
		
		if(!isHexNumber)
			throw new InvalidHexNumberException(potenialHexString);
		else
			return isHexNumber;
	}
	
	/**
	 * Multiplies one hexadecimal number to another.
	 * @param hex The HexNumber to multiply by this instance.
	 * @return a new HexNumber after multiplying the instance and hex.
	 */
	public HexNumber multiply(HexNumber hex) {
		long value = decimalValue * hex.getDecimalValue();
		return new HexNumber(value);
	}
	
	/**
	 * Subtracts on hexadecimal number from another.
	 * @param hex - The HexNumber to subtract from this.
	 * @return a new HexNumber after subtracting hex from this instance.
	 */
	public HexNumber subtract(HexNumber hex) {
		long value = decimalValue - hex.getDecimalValue();
		return new HexNumber(value);
	}
	
	/**
	 * Returns the hexString for a hexadecimal number. 
	 * @return hexValue.
	 */
	public String toString() {
		return hexValue;
	}
	
	/***
	 * This method takes in a string value and converts it to a decimal value.
	 * @param decimalValue - The string of a hex number.
	 * @return - the decimal value of the hex string
	 */
	public static long decimalValue(String hexValue) throws InvalidHexNumberException {        
		/** We need to check if the value passed in is in hex format
		 * ex. "1hkh8" is not valid
		 * if the string is in the acceptable format, we continue
		 * otherwise throw an exception
		 */
		HexNumber.isHex(hexValue);

		boolean isNegative = false;

		// Store the characters in the string into a character array for quick access
		char[] decimals = hexValue.toUpperCase().toCharArray();
		// The value of our hex number
		int val = 0;
		int i = 0;
		if(decimals[0] == '-') {
			i++;
			isNegative = true;
		}
		for (; i < hexValue.length(); i++) {
			    char c = decimals[i]; // Get the character in the array
			    int d = DIGITS.indexOf(c); // Get the hex value of our character 
			    val = 16*val + d; // Multiple the value * 16 to move over one place to the left, then add our number
		}        
		return isNegative ? -val : val;
    }

	/**
	 * Converts a decimal value into a hexadecimal string.
	 * @param decimalValue
	 * @return a String representation of the deicmalValue in hexadecimal (base 16).
	 */
    public static String hexValue(long decimalValue) {
		boolean isNegative = false;
		if (decimalValue == 0) // the decimalValue is 0 so return 0
			return "0";
		/**
		 * If the number is negative set the negative flag
		 * then make the number positive to convert it
		 */
		else if (decimalValue < 0) {
			isNegative = true;
			decimalValue = -decimalValue;
		}

		String hexValue = "";
		/**
		 * Mod the number by 16, then find the digit in our String Digit.
		 * Add that digit to our hexValue, use integer division to
		 * divide the decimalValue by 16.
		 */
		while (decimalValue > 0) {
		    int digit = (int)(decimalValue % 16);
		    hexValue = DIGITS.charAt(digit) + hexValue;
		    decimalValue /= 16;
		}
		// if the number passed in was negative add '-' to the front of the string.
		return isNegative ?  hexValue = "-" + hexValue :hexValue;
    }
	
}
