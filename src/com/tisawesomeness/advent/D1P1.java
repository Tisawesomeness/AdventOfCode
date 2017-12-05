package com.tisawesomeness.advent;

import java.util.Scanner;

public class D1P1 {

	private static String input = null;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		input = scan.nextLine();
		scan.close();
		
		int sum = 0;
		for (int i = 0; i < input.length(); i++) {
			int digit = getDigit(i);
			if (digit == getDigit(i + 1)) {
				sum += digit;
			}
		}
		System.out.println(sum);
	}
	
	private static int getDigit(int digit) {
		if (digit >= input.length()) digit = 0;
		return Integer.valueOf(String.valueOf(input.charAt(digit)));
	}

}
