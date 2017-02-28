package v1;

import java.time.LocalDate;

public class InvalidHexNumberException extends RuntimeException {

	//Serial number
	private static final long serialVersionUID = 1L;
	private String invalidHexValue = null;
	private LocalDate time = null;
	
	public InvalidHexNumberException(String invalidHexValue) {
		this.time = LocalDate.now();
		this.invalidHexValue = invalidHexValue;
		printErrorMessage();
	}
	
	public InvalidHexNumberException(Throwable cause, String invalidHexValue) {
		super(cause);
		this.time = LocalDate.now();
		this.invalidHexValue = invalidHexValue;
		printErrorMessage();
	}
	
	public void printErrorMessage() {
		System.err.println(invalidHexValue + " is not a valid hexadecimal number!\nError occured at: " + time);
		this.printStackTrace();
	}
}
