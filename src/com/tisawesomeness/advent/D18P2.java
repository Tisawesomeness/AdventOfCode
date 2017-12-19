package com.tisawesomeness.advent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class D18P2 {
	
	private static Program prog0 = new Program(0);
	private static Program prog1 = new Program(1);
	private static String[] lines;
	private static int count;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
		    String line = scan.nextLine();
		    if (line.isEmpty()) break;
		    input += line + "\n";
		}
		scan.close();
		
		lines = input.split("\\n");
		prog0.run();
	}
	
	private static class Program {
		
		//Using long because 32 bits overflows into the negatives
		private HashMap<Character, Long> registers = new HashMap<Character, Long>();
		private boolean zero = false;
		private int pos = 0;
		private LinkedList<Long> queue = new LinkedList<Long>();
		
		public Program(int id) {
			if (id == 0) zero = true;
			registers.put('p', (long) id);
		}
		
		public void run() {
			while (pos < lines.length) {
				char c = lines[pos].charAt(4);
				boolean done = false;
				switch (lines[pos].substring(0, 3)) {
				case "snd":
					if (zero) {
						prog1.add(registers.get(lines[pos].charAt(4)));
					} else {
						count++;
						prog0.add(registers.get(lines[pos].charAt(4)));
					}
					break;
				case "set":
					registers.put(c, parse(lines[pos]));
					break;
				case "add":
					registers.put(c, registers.getOrDefault(c, 0L) + parse(lines[pos]));
					break;
				case "mul":
					registers.put(c, registers.getOrDefault(c, 0L) * parse(lines[pos]));
					break;
				case "mod":
					registers.put(c, registers.getOrDefault(c, 0L) % parse(lines[pos]));
					break;
				case "rcv":
					if (queue.size() > 0) {
						registers.put(c, queue.poll());
					} else {
						done = true;
					}
					break;
				case "jgz":
					if (parse(lines[pos], true) > 0) pos += parse(lines[pos]) - 1;
				}
				if (done) {
					if (zero) {
						if (prog1.queue.size() > 0) {
							prog1.run();
							return;
						}
					} else {
						if (prog0.queue.size() > 0) {
							prog0.run();
							return;
						}
					}
					System.out.println(count);
					return; //We don't want this method to be recursive
				}
				pos++;
			}
		}
		
		public void add(long l) {
			queue.add(l);
		}
		
		private long parse(String s) {
			return parse(s, false);
		}
		private long parse(String s, boolean first) {
			s = first ? s.split(" ")[1] : s.substring(6); //jgz 1 3 y u do dis
			char c = s.charAt(0);
			if (c >= 'a' && c <= 'z') {
				if (!registers.containsKey(c)) registers.put(c, 0L);
				return registers.get(c);
			}
			return Integer.valueOf(s);
		}
		
	}

}
