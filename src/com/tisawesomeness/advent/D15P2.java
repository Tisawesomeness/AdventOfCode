package com.tisawesomeness.advent;

import java.util.Scanner;

public class D15P2 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		String[] lines = input.split("\\n");
		long a = Long.valueOf(lines[0].substring(24));
		long b = Long.valueOf(lines[1].substring(24));
		int count = 0;
		for (int i = 0; i < 5000000; i++) {
			while (true) {
				a = (a * 16807) % 2147483647;
				if (a % 4 == 0) break;
			}
			while (true) {
				b = (b * 48271) % 2147483647;
				if (b % 8 == 0) break;
			}
			String binaryA = binary16(a);
			String binaryB = binary16(b);
			if (binaryA.equals(binaryB)) count++;
		}
		System.out.println(count);
	}
	
	private static String binary16(long num) {
		String binary = Long.toBinaryString(num);
		while (binary.length() < 16) binary = "0" + binary;
		return binary.substring(binary.length() - 16);
	}

}
