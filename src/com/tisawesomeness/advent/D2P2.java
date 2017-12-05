package com.tisawesomeness.advent;

import java.util.Scanner;

public class D2P2 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		int sum = 0;
		String[] lines = input.split("\\n");
		for (String s : lines) {
			String[] numStr = s.split("[^0-9]");
			int[] nums = new int[numStr.length];
			for (int i = 0; i < nums.length; i++) {
				nums[i] = Integer.valueOf(numStr[i]);
			}
			sum += findDivision(nums);
		}
		System.out.println(sum);
	}
	
	private static int findDivision(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int i2 = i + 1; i2 < nums.length; i2++) {
				if ((double) nums[i] / nums[i2] % 1 == 0) return nums[i] / nums[i2];
				if ((double) nums[i2] / nums[i] % 1 == 0) return nums[i2] / nums[i];
			}
		}
		return 0;
	}

}
