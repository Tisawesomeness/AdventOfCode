package com.tisawesomeness.advent;

import java.math.BigInteger;
import java.util.Scanner;

public class D14P2 {
	
	private static int[][] matrix = new int[128][128];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		
		for (int i = 0; i < 128; i++) {
			String hash = hash(input + "-" + i);
			String binary = new BigInteger(hash, 16).toString(2);
			while (binary.length() < 128) binary = "0" + binary;
			for (int i2 = 0; i2 < 128; i2++) {
				matrix[i][i2] = binary.charAt(i2) == '0' ? 0 : 1;
			}
		}
		
		int regions = 0;
		for (int i = 0; i < 128; i++) {
			for (int i2 = 0; i2 < 128; i2++) {
				if (matrix[i][i2] == 1) {
					floodFill(i2, i);
					regions++;
				}
			}
		}
		System.out.println(regions);
	}
	
	private static String hash(String input) {
		int[] list = new int[256];
		for (int i = 0; i < 256; i++) list[i] = i;
		int pos = 0;
		int skip = 0;
		int count = 0;
		char[] lengths = (input + (char) 17 + (char) 31 + "I/" + (char) 23).toCharArray();
		while (count < 64) {
			count++;
			for (int length : lengths) {
				reverse(list, pos, length);
				pos = correct(pos + length + skip);
				skip++;
			}
		}
		String hash = "";
		for (int i = 0; i < 16; i++) {
			int dense = 0;
			for (int i2 = 0; i2 < 16; i2++) {
				dense ^= list[i * 16 + i2];
			}
			hash += String.format("%02x", dense);
		}
		return hash;
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
	
	private static void floodFill(int x, int y) {
		if (matrix[y][x] == 1) {
			matrix[y][x] = -1;
			if (y > 0) floodFill(x, y - 1);
			if (x < 127) floodFill(x + 1, y);
			if (y < 127) floodFill(x, y + 1);
			if (x > 0) floodFill(x - 1, y);
		}
	}

}
