package com.tisawesomeness.advent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class D12P2 {
	
	private static ArrayList<ArrayList<Integer>> programs = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		//Represent list of programs with pipes in 2d array
		for (String line : input.split("\\n")) {
			String[] words = line.split(" ");
			ArrayList<Integer> pipes = new ArrayList<Integer>();
			for (int i = 2; i < words.length; i++) {
				pipes.add(Integer.valueOf(words[i].replace(",", "")));
			}
			programs.add(pipes);
		}
		//Count number of groups
		ArrayList<Integer> seen = new ArrayList<Integer>();
		int groups = 0;
		for (int i = 0; i < programs.size(); i++) {
			if (!seen.contains(i)) {
				seen.addAll(findGroup(i, new HashSet<Integer>()));
				groups++;
			}
		}
		System.out.println(groups);
	}
	
	private static Set<Integer> findGroup(int program, Set<Integer> seen) {
		for (int i : programs.get(program)) {
			if (!seen.contains(i)) {
				seen.add(i);
				seen.addAll(findGroup(i, seen));
			}
		}
		return seen;
	}

}
