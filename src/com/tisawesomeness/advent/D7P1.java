package com.tisawesomeness.advent;

import java.util.Scanner;

public class D7P1 {
	
	private static Program[] programs;

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
		programs = new Program[lines.length];
		for (int i = 0; i < programs.length; i++) {
			//Extract name and children of each program from input
			String[] words = lines[i].split(" ");
			if (words.length > 3) {
				String[] children = new String[words.length - 3];
				for (int i2 = 3; i2 < words.length; i2++) {
					children[i2 - 3] = words[i2].replace(",", "");
				}
				programs[i] = new Program(words[0], children);
			} else {
				programs[i] = new Program(words[0]);
			}
		}
		
		//Go to parent program until root is reached
		Program parent = programs[0];
		Program current = null;
		while (parent != null) {
			current = parent;
			parent = getParent(current);
		}
		System.out.println(current.name);
	}
	
	private static Program getParent(Program current) {
		for (int i = 0; i < programs.length; i++) {
			if (programs[i].children != null && contains(programs[i].children, current.name)) {
				return programs[i];
			}
		}
		return null;
	}
	
	private static boolean contains(String[] arr, String str) {
		for (String s : arr) {
			if (str.equals(s)) return true;
		}
		return false;
	}
	
	private static class Program {
		String name;
		String[] children;
		public Program(String name) {
			this.name = name;
		}
		public Program(String name, String[] children) {
			this.name = name;
			this.children = children;
		}
	}

}
