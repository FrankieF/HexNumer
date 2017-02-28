package v1;

public class HexDriver {
	
	public static void main(String[] args) {
		long i = HexNumber.decimalValue("-A");
		String s = HexNumber.hexValue(-10);
		System.out.println(i + ".. " + s);
		
		HexNumber hex1 = new HexNumber(-10);
		HexNumber hex2 = new HexNumber(27);
		HexNumber hex3 = hex1.multiply(hex2);
		System.out.println(hex3 + " " + hex3.getDecimalValue());
		
		HexNumber a = new HexNumber("A");
		HexNumber forty = new HexNumber(40);
		HexNumber four = new HexNumber(4);
		HexNumber fourteen = new HexNumber(14);
		HexNumber two = new HexNumber(2);
		
		HexNumber test = two.multiply(a.add(forty.divide(four).subtract(fourteen)));
		
		System.out.println(test + " " + test.getDecimalValue());
	}
	
	

}
