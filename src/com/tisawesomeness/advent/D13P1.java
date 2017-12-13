package com.tisawesomeness.advent;

import java.util.Scanner;

public class D13P1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		int severity = 0;
		for (String line : input.split("\\n")) {
			int depth = Integer.valueOf(line.substring(0, line.indexOf(":")));
			int range = Integer.valueOf(line.substring(line.indexOf(" ") + 1));
			//2r - 2 is the amount of time a scanner takes to complete one cycle
			if (depth % (2 * range - 2) == 0) severity += depth * range;
		}
		System.out.println(severity);
	}

}
