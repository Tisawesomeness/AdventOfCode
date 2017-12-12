package com.tisawesomeness.advent;

import java.util.Scanner;

public class D10P1 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		
		int[] list = new int[256];
		for (int i = 0; i < 256; i++) list[i] = i;
		int pos = 0;
		int skip = 0;
		for (String s : input.split(",")) {
			int length = Integer.valueOf(s);
			reverse(list, pos, length);
			pos = correct(pos + length + skip);
			skip++;
		}
		System.out.println(list[0] * list[1]);
	}
	
	private static int[] reverse(int[] arr, int start, int length) {
		for (int i = 0; i < length / 2; i++) {
			int temp = arr[correct(start + i)];
			arr[correct(start + i)] = arr[correct(start + length - i - 1)];
			arr[correct(start + length - i - 1)] = temp;
		}
		return arr;
	}
	
	private static int correct(int i) {
		return i < 256 ? i : i % 256;
	}

}
