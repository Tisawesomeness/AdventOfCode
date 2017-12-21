package com.tisawesomeness.advent;

import java.util.Scanner;

public class D20P2 {

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
		Particle[] buffer = new Particle[lines.length];
		for (int i = 0; i < lines.length; i++) {
			int[][] struct = new int[3][3]; //I don't want to create nine separate variables again
			String[] words = lines[i].split(" ");
			for (int i2 = 0; i2 < words.length; i2++) {
				String[] nums = words[i2].split(",");
				struct[i2][0] = Integer.valueOf(nums[0].substring(3));
				struct[i2][1] = Integer.valueOf(nums[1]);
				struct[i2][2] = Integer.valueOf(nums[2].substring(0, nums[2].length() - 1));
			}
			buffer[i] = new Particle(
				struct[0][0], struct[0][1], struct[0][2],
				struct[1][0], struct[1][1], struct[1][2],
				struct[2][0], struct[2][1], struct[2][2]
			);
		}
		
		//Simulate 1000 ticks
		for (int i = 0; i < 1000; i++) {
			for (Particle p : buffer) {
				if (p != null) p.tick();
			}
			//Set any colliding particles to null
			for (int i2 = 0; i2 < buffer.length; i2++) {
				Particle p = buffer[i2];
				if (p == null) continue;
				boolean remove = false;
				for (int i3 = buffer.length - 1; i3 > i2; i3--) {
					Particle p2 = buffer[i3];
					if (p2 == null) continue;
					if (p.pX == p2.pX && p.pY == p2.pY && p.pZ == p2.pZ) {
						remove = true;
						buffer[i3] = null;
					}
				}
				if (remove) buffer[i2] = null;
			}
		}
		int sum = 0;
		for (Particle p : buffer) {
			if (p != null) sum++;
		}
		System.out.println(sum);
	}
	
	private static class Particle {
		private int pX;
		private int pY;
		private int pZ;
		private int vX;
		private int vY;
		private int vZ;
		private int aX;
		private int aY;
		private int aZ;
		public Particle(int pX, int pY, int pZ, int vX, int vY, int vZ, int aX, int aY, int aZ) {
			this.pX = pX;
			this.pY = pY;
			this.pZ = pZ;
			this.vX = vX;
			this.vY = vY;
			this.vZ = vZ;
			this.aX = aX;
			this.aY = aY;
			this.aZ = aZ;
		}
		public void tick() {
			vX += aX;
			vY += aY;
			vZ += aZ;
			pX += vX;
			pY += vY;
			pZ += vZ;
		}
	}

}
