package com.tisawesomeness.advent;

import java.util.Scanner;

public class D9P1 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		
		int level = 0;
		int sum = 0;
		boolean garbage = false;
		boolean escaped = false;
		for (char c : input.toCharArray()) {
			if (escaped) {
				escaped = false;
		 	} else {
				if (c == '!') escaped = true;
				if (garbage) {
					if (c == '>') garbage = false;
				} else {
					if (c == '<') garbage = true;
					if (c == '{') level++;
					if (c == '}') {
						sum += level;
						level--;
					}
				}
			}
		}
		System.out.println(sum);
	}

}
