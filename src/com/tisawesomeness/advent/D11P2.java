package com.tisawesomeness.advent;

import java.util.Scanner;

public class D11P2 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		
		//Follow directions in input
		int x = 0;
		int y = 0;
		int highest = 0;
		for (String s : input.split(",")) {
			switch (s) {
				case "nw": x--; if (Math.abs(x) % 2 == 0) y++; break;
				case "n": y++; break;
				case "ne": x++; if (Math.abs(x) % 2 == 0) y++; break;
				case "sw": x--; if (Math.abs(x) % 2 == 1) y--; break;
				case "s": y--; break;
				case "se": x++; if (Math.abs(x) % 2 == 1) y--;
			}
			int dist = distance(x, y);
			if (dist > highest) highest = dist;
		}
		System.out.println(highest);
	}
	
	private static int distance(int x, int y) {
		//Go to x = 0
		int count = 0;
		while (x != 0) {
			if (x > 0) {
				if (y > 0) {
					x--; if (Math.abs(x) % 2 == 1) y--; //sw
				} else {
					x--; if (Math.abs(x) % 2 == 0) y++; //nw
				}
			} else {
				if (y > 0) {
					x++; if (Math.abs(x) % 2 == 1) y--; //se
				} else {
					x++; if (Math.abs(x) % 2 == 0) y++; //ne
				}
			}
			count++;
		}
		//Go to y = 0
		while (y != 0) {
			if (y > 0) {
				y--; //s
			} else {
				y++; //n
			}
			count++;
		}
		return count;
	}

}
