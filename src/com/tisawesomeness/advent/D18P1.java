package com.tisawesomeness.advent;

import java.util.HashMap;
import java.util.Scanner;

public class D18P1 {
	
	//Using long because 32 bits overflows into the negatives
	private static HashMap<Character, Long> registers = new HashMap<Character, Long>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		String[] lines = input.split("\\n");
		Long last = 0L;
		for (int i = 0; i < lines.length; i++) {
			char c = lines[i].charAt(4);
			boolean escape = false;
			switch (lines[i].substring(0, 3)) {
			case "snd":
				last = registers.get(lines[i].charAt(4));
				break;
			case "set":
				registers.put(c, parse(lines[i]));
				break;
			case "add":
				registers.put(c, registers.getOrDefault(c, 0L) + parse(lines[i]));
				break;
			case "mul":
				registers.put(c, registers.getOrDefault(c, 0L) * parse(lines[i]));
				break;
			case "mod":
				registers.put(c, registers.getOrDefault(c, 0L) % parse(lines[i]));
				break;
			case "rcv":
				if (registers.getOrDefault(c, 0L) != 0) escape = true;
				break;
			case "jgz":
				if (registers.getOrDefault(c, 0L) > 0) i += parse(lines[i]) - 1;
			}
			if (escape) break;
		}
		System.out.println(last);
	}
	
	private static long parse(String s) {
		s = s.substring(6);
		char c = s.charAt(0);
		if (c >= 'a' && c <= 'z') {
			if (!registers.containsKey(c)) registers.put(c, 0L);
			return registers.get(c);
		}
		return Integer.valueOf(s);
	}

}
