package com.tisawesomeness.advent;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class D4P1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		int count = 0;
		for (String s : input.split("\\n")) {
			if (allUnique(s.split(" "))) count++; //If password is valid
		}
		System.out.println(count);
	}
	
	//Tests if every word is unique, by using properties of the Set class
	public static boolean allUnique(String[] arr) {
		Set<String> lump = new HashSet<String>();
	  	for (String s : arr) {
	  		if (lump.contains(s)) return false;
		  	lump.add(s);
	  	}
	  	return true;
	}

}
