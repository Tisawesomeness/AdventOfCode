package com.tisawesomeness.advent;

import java.util.ArrayList;
import java.util.Scanner;

public class D21P1 {
	
	private static String input;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		//Starting pattern
		boolean[] start = new boolean[]{
			false, true, false,
			false, false, true,
			true, true, true
		};
		
		//Five iterations
		boolean[] s1 = enhance(start);
		boolean[] s2 = assemble(enhance(split(s1)));
		ArrayList<boolean[]> s3 = enhance(split(s2));
		boolean[] s4 = assemble(enhance(s3));
		boolean[] s5 = assemble(enhance(split(s4)));
		//Find number of "on" cells
		int sum = 0;
		for (boolean b : s5) {
			if (b) sum++;
		}
		System.out.println(sum);
	}
	
	//Assembles many grids into one large grid
	private static boolean[] assemble(ArrayList<boolean[]> fractal) {
		//Variables to make calculation easier
		int sqrtSize = (int) Math.sqrt(fractal.size()); //Width of entire grid
		int len = fractal.get(0).length; //Amount of cells in a single grid
		int sqrtLen = (int) Math.sqrt(len); //Width of a single grid
		boolean[] output = new boolean[len * fractal.size()]; //Output grid
		int size = (int) Math.sqrt(output.length); //Width of output grid
		for (int i = 0; i < fractal.size(); i++) { //Loop over each input grid
			int x = (i % sqrtSize) * sqrtLen; //Starting x value in output grid
			int y = i * sqrtLen / size * sqrtLen; //Starting y value in output grid
			for (int i2 = 0; i2 < len; i2++) { //Loop over each value in current input grid
				int x2 = i2 % sqrtLen; //Current x value of input grid
				int y2 = i2 / sqrtLen; //Current y value of input grid
				output[size * (y + y2) + x + x2] = fractal.get(i)[i2]; //Map input to output
			}
		}
		return output;
	}
	
	//Splits an even-length grid into many 2x2 grids
	private static ArrayList<boolean[]> split(boolean[] grid) {
		int size = (int) Math.sqrt(grid.length); //Width of grid
		ArrayList<boolean[]> fractal = new ArrayList<boolean[]>();
		for (int i = 0; i < grid.length / 4; i++) { //Loop over each 2x2 area
			int x = (i % (size / 2)) * 2; //Starting x value
			int y = i * 2 / size * 2; //Starting y value
			//Extract values to a 2x2 grid
			fractal.add(new boolean[]{
				grid[size * y + x], grid[size * y + x + 1],
				grid[size * (y + 1) + x], grid[size * (y + 1) + x + 1]
			});
		}
		return fractal;
	}
	
	//Enhances a grid using the enhancement rule
	private static boolean[] enhance(boolean[] grid) {
		String pattern = tryGrid(grid);
		if (pattern != null) return toGrid(pattern);
		pattern = tryGrid(flip(grid));
		return toGrid(pattern);
	}
	
	//Enhances every grid in the provided list
	private static ArrayList<boolean[]> enhance(ArrayList<boolean[]> fractal) {
		ArrayList<boolean[]> list = new ArrayList<boolean[]>();
		for (boolean[] b : fractal) list.add(enhance(b));
		return list;
	}
	
	//Tries to find a rule for every rotation of a grid
	private static String tryGrid(boolean[] grid) {
		boolean[] test = grid;
		for (int i = 0; i < 4; i++) {
			String pattern = findRule(toText(test));
			if (pattern != null) return pattern;
			if (i < 3) test = rotate(test);
		}
		return null;
	}
	
	private static boolean[] rotate(boolean[] grid) {
		if (grid.length == 4) {
			return new boolean[]{
				grid[2], grid[0],
				grid[3], grid[1]
			};
		} else {
			return new boolean[]{
				grid[6], grid[3], grid[0],
				grid[7], grid[4], grid[1],
				grid[8], grid[5], grid[2]
			};
		}
	}
	
	private static boolean[] flip(boolean[] grid) {
		if (grid.length == 4) {
			return new boolean[]{
				grid[1], grid[0],
				grid[3], grid[2]
			};
		} else {
			return new boolean[]{
				grid[2], grid[1], grid[0],
				grid[5], grid[4], grid[3],
				grid[8], grid[7], grid[6]
			};
		}
	}
	
	//Converts boolean array to string
	private static String toText(boolean[] grid) {
		String output = "";
		for (int i = 0; i < grid.length; i++) {
			output += grid[i] ? "#" : ".";
			//Put slashes in string
			if (i != grid.length - 1) { //No slash at end of string
				int size = (int) Math.sqrt(grid.length);
				if (i % size == size - 1) output += "/";
			}
		}
		return output;
	}
	
	//Converts string to boolean array
	private static boolean[] toGrid(String s) {
		if (s == null) return null;
		s = s.replaceAll("/", "");
		boolean[] grid = new boolean[s.length()];
		for (int i = 0; i < grid.length; i++) {
			grid[i] = s.charAt(i) == '#' ? true : false;
		}
		return grid;
	}

	//Searches the input for the provided rule key
	private static String findRule(String key) {
		for (String s : input.split("\\n")) {
			String[] words = s.split(" ");
			if (key.equals(words[0])) return words[2];
		}
		return null;
	}

}
