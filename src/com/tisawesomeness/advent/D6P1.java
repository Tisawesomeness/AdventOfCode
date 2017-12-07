package com.tisawesomeness.advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class D6P1 {
	
	private static ArrayList<int[]> prev = new ArrayList<int[]>();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		
		//Create array of blocks
		String[] nums = input.split("\\t");
		int[] blocks = new int[nums.length];
		for (int i = 0; i < blocks.length; i++) {
			blocks[i] = Integer.valueOf(nums[i]);
		}
		while (!seen(blocks)) { //Loop until current block list matches a previous one
			prev.add(blocks.clone());
			//Find highest item in array
			int target = 0;
			for (int i = 0; i < blocks.length; i++) {
				if (blocks[i] > blocks[target]) target = i;
			}
			//Distribute blocks
			int dist = blocks[target];
			blocks[target] = 0;
			while (dist > 0) {
				target++;
				if (target >= blocks.length) target = 0;
				blocks[target]++;
				dist--;
			}
		}
		System.out.println(prev.size());
	}
	
	private static boolean seen(int[] blocks) {
		for (int[] arr : prev) {
			if (Arrays.equals(blocks, arr)) return true;
		}
		return false;
	}

}
