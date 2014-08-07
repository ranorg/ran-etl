package com.ranorg.etl.misc;

public class HexUtil {
	
	public static int hex2decimal(String s) {
		String digits = "0123456789ABCDEF";
		s = s.toUpperCase();
		int val = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}
		return val;
	}

	// precondition: d is a nonnegative integer
	public static String decimal2hex(int d) {
		String digits = "0123456789ABCDEF";
		if (d == 0)
			return "0";
		String hex = "";
		while (d > 0) {
			int digit = d % 16; // rightmost digit
			hex = digits.charAt(digit) + hex; // string concatenation
			d = d / 16;
		}
		return hex;
	}

	public static void main(String[] args) {
		int decimal = hex2decimal("2F31");
		System.out.println(decimal);

//		String hex = decimal2hex(95);
//		System.out.println(hex);
	}
}
