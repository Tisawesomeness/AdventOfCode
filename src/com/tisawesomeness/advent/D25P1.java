package com.tisawesomeness.advent;

import java.util.HashMap;

public class D25P1 {

	public static void main(String[] args) {
		HashMap<Integer, Boolean> tape = new HashMap<Integer, Boolean>();
		int cursor = 0;
		int state = 0;
		for (int i = 0; i < 12683008; i++) {
			switch (state) {
			case 0:
				if (tape.containsKey(cursor)) {
					tape.put(cursor, false);
					cursor--;
					state = 1;
				} else {
					tape.put(cursor, true);
					cursor++;
					state = 1;
				}
				break;
			case 1:
				if (tape.containsKey(cursor)) {
					tape.put(cursor, false);
					cursor++;
					state = 4;
				} else {
					tape.put(cursor, true);
					cursor--;
					state = 2;
				}
				break;
			case 2:
				if (tape.containsKey(cursor)) {
					tape.put(cursor, false);
					cursor--;
					state = 3;
				} else {
					tape.put(cursor, true);
					cursor++;
					state = 4;
				}
				break;
			case 3:
				if (tape.containsKey(cursor)) {
					tape.put(cursor, true);
					cursor--;
					state = 0;
				} else {
					tape.put(cursor, true);
					cursor--;
					state = 0;
				}
				break;
			case 4:
				if (tape.containsKey(cursor)) {
					tape.put(cursor, false);
					cursor++;
					state = 5;
				} else {
					tape.put(cursor, false);
					cursor++;
					state = 0;
				}
				break;
			case 5:
				if (tape.containsKey(cursor)) {
					tape.put(cursor, true);
					cursor++;
					state = 0;
				} else {
					tape.put(cursor, true);
					cursor++;
					state = 4;
				}
			}
		}
		int sum = 0;
		for (int i : tape.keySet()) {
			if (tape.get(i)) sum++;
		}
		System.out.println(sum);
	}

}
