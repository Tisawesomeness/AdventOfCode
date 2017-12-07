package com.tisawesomeness.advent;

import java.util.Scanner;

public class D5P2 {

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
			int orig = maze[pos]; //Save original instruction value
			if (maze[pos] >= 3) { //Change instruction
				maze[pos]--;
			} else {
				maze[pos]++;
			}
			pos += orig; //Follow instruction
			steps++;
		}
		System.out.println(steps);
	}

}
