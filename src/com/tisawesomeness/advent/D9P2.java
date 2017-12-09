package com.tisawesomeness.advent;

import java.util.Scanner;

public class D9P2 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		
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
					else if (!escaped) sum++;
				} else if (c == '<') garbage = true;
			}
		}
		System.out.println(sum);
	}

}
