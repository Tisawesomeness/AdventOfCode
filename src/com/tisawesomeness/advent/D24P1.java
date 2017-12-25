package com.tisawesomeness.advent;

import java.util.ArrayList;
import java.util.Scanner;

public class D24P1 {

	private static ArrayList<Component> components = new ArrayList<Component>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		//Create list of components
		for (String line : input.split("\\n")) {
			String[] nums = line.split("/");
			components.add(new Component(Integer.valueOf(nums[0]), Integer.valueOf(nums[1])));
		}
		System.out.println(highestBridge(new ArrayList<Component>()));
	}
	
	private static int highestBridge(ArrayList<Component> used) {
		int highest = 0;
		int out = used.size() == 0 ? 0 : used.get(used.size() - 1).out;
		//Iterate over every component not in the used list
		for (Component c : components) {
			if (!used.contains(c)) {
				ArrayList<Component> newUsed = new ArrayList<Component>(used); //Don't modify current list
				//If two components match, reverse order if necessary
				if (out == c.in) {
					newUsed.add(c);
				} else if (out == c.out) {
					newUsed.add(new Component(c.out, c.in));
				} else continue;
				int current = highestBridge(newUsed); //Recursively find strongest bridge
				if (current > highest) highest = current;
			}
		}
		//Add current component to total
		int in = used.size() == 0 ? 0 : used.get(used.size() - 1).in;
		highest += in + out;
		return highest;
	}
	
	private static class Component {
		int in;
		int out;
		public Component(int in, int out) {
			this.in = in;
			this.out = out;
		}
		@Override
		public boolean equals(Object o) {
			Component c = (Component) o;
			return (this.in == c.in && this.out == c.out) || (this.in == c.out && this.out == c.in);
		}
	}

}
