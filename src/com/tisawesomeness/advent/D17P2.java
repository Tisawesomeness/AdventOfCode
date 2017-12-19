package com.tisawesomeness.advent;

import java.util.Scanner;

public class D17P2 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		
		int steps = Integer.valueOf(input);
		int current = 0;
		int size = 1;
		int val = 0;
		for (int i = 1; i <= 50000000; i++) {
			current += steps;
			if (current >= size) current %= size;
			size++;
			if (current == 0) val = i;
			current++;
		}
		System.out.println(val);
	}

}
