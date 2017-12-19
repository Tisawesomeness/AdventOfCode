package com.tisawesomeness.advent;

import java.util.ArrayList;
import java.util.Scanner;

public class D17P1 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		
		ArrayList<Integer> spinlock = new ArrayList<Integer>();
		spinlock.add(0);
		int steps = Integer.valueOf(input);
		int current = 0;
		for (int i = 1; i <= 2017; i++) {
			current += steps;
			if (current >= spinlock.size()) current = current % spinlock.size();
			spinlock.add(current + 1, i);
			current++;
		}
		System.out.println(spinlock.get(current + 1));
		System.out.println(spinlock.get(1));
	}

}
