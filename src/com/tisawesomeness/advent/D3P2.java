package com.tisawesomeness.advent;

import java.util.HashMap;
import java.util.Scanner;

public class D3P2 {
	
	static HashMap<Coordinate, Integer> field;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		scan.close();
		
		field = new HashMap<Coordinate, Integer>(); //Map of values to coordinates
		field.put(new Coordinate(0, 0), 1); //Starting value
		int x = 0; //X coordinate
		int y = 0; //Y coordinate
		int i = 0; //Current value iterator
		
		while (true) {
			i++;
			int level = (int) (Math.sqrt((i - 0.75) / 4) - 0.25 + 1); //A measure of how many times to move in one direction
			int reduced = (int) (i - 4 * Math.pow(level - 0.75, 2) - 0.25); //Reduces i to a sequence of integers incrementing from 0 based on level
			int sum = 0;
			if (reduced < 2 * level - 1) { //Right quadrant, move right, then add up surrounding spaces
				x++;
				sum += get(x - 1, y);
				sum += get(x - 1, y + 1);
				sum += get(x, y + 1);
				sum += get(x + 1, y + 1);
			} else if (reduced < 4 * level - 2) { //Up quadrant
				y++;
				sum += get(x, y - 1);
				sum += get(x - 1, y - 1);
				sum += get(x - 1, y);
				sum += get(x - 1, y + 1);
			} else if (reduced < 6 * level - 2) { //Left quadrant
				x--;
				sum += get(x + 1, y);
				sum += get(x + 1, y - 1);
				sum += get(x, y - 1);
				sum += get(x - 1, y - 1);
			} else if (reduced < 8 * level - 2) { //Down quadrant
				y--;
				sum += get(x, y + 1);
				sum += get(x + 1, y + 1);
				sum += get(x + 1, y);
				sum += get(x + 1, y - 1);
			}
			field.put(new Coordinate(x, y), sum); //Add a new value
			if (sum > input) {
				System.out.println(sum);
				break;
			}
		}
	}
	
	//HashMap get function with default
	private static int get(int x, int y) {
		Coordinate key = new Coordinate(x, y);
		return field.containsKey(key) ? field.get(key) : 0;
	}
	
	//Coordinate class, two coordinates are equal if their x values and y values are equal
	private static class Coordinate {
		private int x;
		private int y;
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
	    public boolean equals(Object o) {
	        Coordinate coord = (Coordinate) o;
	        return x == coord.x && y == coord.y;
	    }
		@Override
	    public int hashCode() {
			return (x << 16) + y;
	    }
	}

}
