package com.tisawesomeness.advent;

public class D23P1 {

	public static void main(String[] args) {
		//My code ran practically forever (unlucky input?) so I simplified the assembly code
		
		int b = 67;
		int c = 67;
		int d = 0;
		int e = 0;
		int g = 0;
		int count = 0;
		while (true) {
			d = 2;
			int i = 0; while (g != 0 || i == 0) { i++;
				e = 2;
				int i2 = 0; while (g != 0 || i2 == 0) { i2++;
					g = d;
					g *= e; count++;
					g -= b;
					e++;
					g = e;
					g -= b;
				}
				d++;
				g = d;
				g -= b;
			}
			g = b;
			g -= c;
			if (g == 0) break;
			b += 17;
		}
		System.out.println(count);
	}

}
