package com.tisawesomeness.advent;

import java.util.HashMap;
import java.util.Scanner;

public class D22P2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		//Create list of infected tiles
		HashMap<Coordinate, State> infected = new HashMap<Coordinate, State>();
		String[] lines = input.split("\\n");
		for (int y = 0; y < lines.length; y++) {
			for (int x = 0; x < lines[y].length(); x++) {
				if (lines[y].charAt(x) == '#') infected.put(new Coordinate(x, y), State.INFECTED);
			}
		}
		
		//Simulate virus
		int x = lines.length / 2;
		int y = lines[0].length() / 2;
		int dir = 0;
		int count = 0;
		for (int i = 0; i < 10000000; i++) {
			Coordinate c = new Coordinate(x, y);
			if (infected.containsKey(c)) {
				switch (infected.get(c)) {
				case WEAKENED:
					infected.put(c, State.INFECTED);
					count++;
					break;
				case INFECTED:
					dir++;
					if (dir > 3) dir = 0;
					infected.put(c, State.FLAGGED);
					break;
				case FLAGGED:
					dir += 2;
					if (dir > 3) dir -= 4;
					infected.remove(c);
				}
			} else {
				dir--;
				if (dir < 0) dir = 3;
				infected.put(c, State.WEAKENED);
			}
			switch (dir) {
			case 0: y--; break;
			case 1: x++; break;
			case 2: y++; break;
			case 3: x--;
			}
		}
		System.out.println(count);
	}
	
	private static class Coordinate {
		private int x;
		private int y;
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
	    public boolean equals(Object o) {
	        Coordinate c = (Coordinate) o;
	        return x == c.x && y == c.y;
	    }
		@Override
	    public int hashCode() {
			return (x << 16) + y;
	    }
	}
	
	private static enum State {
		WEAKENED,
		INFECTED,
		FLAGGED;
	}

}
