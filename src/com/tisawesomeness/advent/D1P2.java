package com.tisawesomeness.advent;

import java.util.Scanner;

public class D1P2 {
	
	private static String input = null;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		input = scan.nextLine();
		scan.close();
		
		int sum = 0;
		for (int i = 0; i < input.length(); i++) {
			int digit = getDigit(i);
			if (digit == getDigit(i + input.length() / 2)) { //If digit matches the digit halfway around
				sum += digit;
			}
		}
		System.out.println(sum);
	}
	
	private static int getDigit(int digit) {
		if (digit >= input.length()) digit -= input.length(); //Loop to beginning of input
		if (digit < 0) digit += input.length(); //Loop to end of input
		return Integer.valueOf(String.valueOf(input.charAt(digit))); //Return requested digit
	}

}
