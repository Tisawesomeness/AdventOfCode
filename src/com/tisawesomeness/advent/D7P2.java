package com.tisawesomeness.advent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class D7P2 {
	
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
			//Extract name, weight, and children of each program from input
			String[] words = lines[i].split(" ");
			int weight = Integer.valueOf(words[1].substring(1, words[1].length() - 1));
			if (words.length > 3) {
				String[] children = new String[words.length - 3];
				for (int i2 = 3; i2 < words.length; i2++) {
					children[i2 - 3] = words[i2].replace(",", "");
				}
				programs[i] = new Program(words[0], weight, children);
			} else {
				programs[i] = new Program(words[0], weight);
			}
		}
		
		//Go to parent program until root is reached
		Program parent = programs[0];
		Program current = null;
		while (parent != null) {
			current = parent;
			parent = getParent(current);
		}
		
		//Go to next unbalanced program in tree until end is reached
		Program prev = null;
		while (current != null) {
			prev = current;
			current = getUnbalanced(convert(current.children));
		}
		Program unbalanced = getParent(prev);
		
		//Find common weight in list of children
		Program[] children = convert(unbalanced.children);
		ArrayList<Integer> weights = new ArrayList<Integer>();
		for (Program child : children) {
			int weight = getWeight(child);
			if (weights.contains(weight)) {
				//Subtract children's weight to get what the weight should be
				System.out.println(weight - getWeight(search(prev.children[0])) * prev.children.length);
				break;
			} else {
				weights.add(weight);
			}
		}
		
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
	
	private static Program search(String search) {
		for (int i = 0; i < programs.length; i++) {
			if (!programs[i].name.equals(null) && programs[i].name.equals(search)) {
				return programs[i];
			}
		}
		return null;
	}
	
	private static Program getUnbalanced(Program[] children) {
		
		if (children.length < 2) {
			return null;
		} else if (children.length == 2) {
			if (getWeight(children[0]) == getWeight(children[1])) {
				return null;
			} else {
				if (getUnbalanced(convert(children[0].children)) == null) {
					return children[1];
				} else {
					return children[0];
				}
			}
		
		} else {
			
			//HashMap of weight to frequency
			HashMap<Integer, Integer> frequency = new HashMap<Integer, Integer>();
			for (Program child : children) {
				int weight = getWeight(child);
				if (frequency.containsKey(weight)) {
					frequency.put(weight, frequency.get(weight) + 1);
				} else {
					frequency.put(weight, 0);
				}
			}
			if (frequency.size() <= 1) return null;
			//Find lowest frequency
			int lowestFrequency = Integer.MAX_VALUE;
			int weightOfLowest = 0;
			for (int weight : frequency.keySet()) {
				if (frequency.get(weight) < lowestFrequency) {
					lowestFrequency = frequency.get(weight);
					weightOfLowest = weight;
				}
			}
			//Find program from weight
			for (Program p : children) {
				if (getWeight(p) == weightOfLowest) {
					return p;
				}
			}
			return null;
		}
	}
	
	private static int getWeight(Program program) {
		int sum = program.weight;
		if (program.children != null) {
			sum = program.weight;
			for (String s : program.children) {
				sum += getWeight(search(s));
			}
		}
		return sum;
	}
	
	private static Program[] convert(String[] programs) {
		Program[] result = new Program[programs.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = search(programs[i]);
		}
		return result;
	}
	
	private static class Program {
		String name;
		int weight;
		String[] children;
		public Program(String name, int weight) {
			this.name = name;
			this.weight = weight;
		}
		public Program(String name, int weight, String[] children) {
			this.name = name;
			this.weight = weight;
			this.children = children;
		}
	}

}
