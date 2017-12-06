package com.tisawesomeness.advent;

import java.util.Scanner;

public class D3P1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		scan.close();
		
		int ring = (int) (Math.sqrt(input - 1) + 1) / 2 + 1; //Find the current "ring"
		int dist = 2 * ring - 2; //Distance between two corners of the ring
		int close = (input + ring - 2) % dist; //How close the input is to the midpoint between two corners
		if (close > dist / 2) close = dist - close; //Correct close variable if it's too big
		System.out.println(ring + close - 1); //Add ring and close variables to get Manhattan distance
	}

}
