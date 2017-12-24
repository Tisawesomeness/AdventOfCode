package com.tisawesomeness.advent;

public class D23P2 {

	public static void main(String[] args) {
		//My code ran practically forever (unlucky input?) so I simplified the assembly code
		
		int h = 0;
		for (int b = 106700; b <= 123700; b += 17) {
			boolean f = true;
			for (int d = 2; d < b; d++) {
				if (b % d == 0) {
                    f = false;
                    break;
                }
			}
			if (!f) h++;
		}
		System.out.println(h);
	}

}
