package com.tisawesomeness.advent;

import java.util.HashMap;
import java.util.Scanner;

public class D8P1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		HashMap<String, Integer> registers = new HashMap<String, Integer>();
		for (String line : input.split("\\n")) {
			String[] words = line.split(" ");
			//First number in if statement
			int compare = 0;
			if (registers.containsKey(words[4])) {
				compare = registers.get(words[4]);
			} else {
				registers.put(words[4], 0);
			}
			//Second number in if statement
			int compareTo = Integer.valueOf(words[6]);
			//Execute if statement
			boolean result = false;
			switch (words[5]) {
				case "==": result = compare == compareTo; break;
				case "!=": result = compare != compareTo; break;
				case "<": result = compare < compareTo; break;
				case ">": result = compare > compareTo; break;
				case "<=": result = compare <= compareTo; break;
				case ">=": result = compare >= compareTo;
			}
			if (result) {
				//Register to modify
				int value = 0;
				if (registers.containsKey(words[0])) {
					value = registers.get(words[0]);
				} else {
					registers.put(words[0], 0);
				}
				//Increase or decrease
				if (words[1].equals("inc")) {
					value += Integer.valueOf(words[2]);
				} else {
					value -= Integer.valueOf(words[2]);
				}
				registers.put(words[0], value);
			}
		}
		
		int highest = Integer.MIN_VALUE;
		for (String key : registers.keySet()) {
			int value = registers.get(key);
			if (value > highest) highest = value;
		}
		System.out.println(highest);
	}

}
