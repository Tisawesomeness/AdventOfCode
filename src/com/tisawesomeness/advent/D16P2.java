package com.tisawesomeness.advent;

import java.util.ArrayList;
import java.util.Scanner;

public class D16P2 {
	
	private static char[] programs = new char[]{
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'
	};
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		
		ArrayList<String> seen = new ArrayList<String>();
		while (!seen.contains(new String(programs))) {
			seen.add(new String(programs));
			for (String s : input.split(",")) {
				switch (s.charAt(0)) {
				case 's':
					int len = Integer.valueOf(s.substring(1));
					char[] temp = new char[len];
					for (int i = 0; i < len; i++) temp[i] = programs[16 + i - len]; //Save last n values in temp
					for (int i = 15; i >= len; i--) programs[i] = programs[i - len]; //Move other values to front
					for (int i = 0; i < len; i++) programs[i] = temp[i]; //Set first n values to temp
					break;
				case 'x':
					int xA = Integer.valueOf(s.substring(1, s.indexOf("/")));
					int xB = Integer.valueOf(s.substring(s.indexOf("/") + 1));
					swap(xA, xB);
					break;
				case 'p':
					int pA = indexOf(s.charAt(1));
					int pB = indexOf(s.charAt(3));
					swap(pA, pB);
				}
			}
		}
		System.out.println(seen.get(1000000000 % seen.size()));
	}
	
	private static void swap(int x, int y) {
		char temp = programs[x];
		programs[x] = programs[y];
		programs[y] = temp;
	}
	
	private static int indexOf(char c) {
		for (int i = 0; i < 16; i++) {
			if (programs[i] == c) return i;
		}
		return -1;
	}

}
