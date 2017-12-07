package com.tisawesomeness.advent;

import java.util.Scanner;

public class D5P1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		//Create array representing maze
		String[] lines = input.split("\\n");
		int[] maze = new int[lines.length];
		for (int i = 0; i < maze.length; i++) {
			maze[i] = Integer.valueOf(lines[i]);
		}
		int pos = 0;
		int steps = 0;
		while (pos >= 0 && pos < maze.length) { //While in bounds
			maze[pos]++; //Increment jump instruction
			pos += maze[pos] - 1; //Follow instruction
			steps++;
		}
		System.out.println(steps);
	}

}
