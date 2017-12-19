package com.tisawesomeness.advent;

import java.util.Scanner;

public class D19P2 {

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
		int x = lines[0].indexOf('|');
		int y = 0;
		int dir = 2; //0 north 1 east 2 south 3 west
		int steps = 0;
		while (true) {
			char c = lines[y].charAt(x);
			if (c == '+') {
				if (dir != 2 && y > 0 && lines[y - 1].charAt(x) == '|') {
					dir = 0;
					y--;
				} else if (dir != 3 && x < lines[y].length() - 1 && lines[y].charAt(x + 1) == '-') {
					dir = 1;
					x++;
				} else if (dir != 0 && y < lines.length - 1 && lines[y + 1].charAt(x) == '|') {
					dir = 2;
					y++;
				} else {
					dir = 3;
					x--;
				}
			} else {
				if (c == ' ') break;
				switch (dir) {
				case 0: y--; break;
				case 1: x++; break;
				case 2: y++; break;
				case 3: x--;
				}
			}
			steps++;
		}
		System.out.println(steps++);
	}

}
