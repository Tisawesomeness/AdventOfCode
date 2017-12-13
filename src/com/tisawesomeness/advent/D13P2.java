package com.tisawesomeness.advent;

import java.util.HashMap;
import java.util.Scanner;

public class D13P2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		//Create HashMap representing firewall to speed up program
		HashMap<Integer, Integer> firewall = new HashMap<Integer, Integer>();
		for (String line : input.split("\\n")) {
			int depth = Integer.valueOf(line.substring(0, line.indexOf(":")));
			int range = Integer.valueOf(line.substring(line.indexOf(" ") + 1));
			firewall.put(depth, range);
		}
		
		int delay = -1;
		boolean fail = true;
		while (fail) {
			delay++;
			fail = false;
			for (int depth : firewall.keySet()) {
				//2r - 2 is the amount of time a scanner takes to complete one cycle
				if ((depth + delay) % (2 * firewall.get(depth) - 2) == 0) {
					fail = true;
					break;
				}
			}
		}
		System.out.println(delay);
	}

}
