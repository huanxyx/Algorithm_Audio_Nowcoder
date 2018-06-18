package com.xyx.nowcoder.exercise_3_4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗收留所
 * 问题链接：https://www.nowcoder.com/questionTerminal/6235a76b1e404f748f7c820583125c50
 * @author huan
 * @date 2018年6月18日
 */
public class CatDogAsylum {
	
	public ArrayList<Integer> asylum(int[][] opes) {
		DogCatAsylum asylum = new DogCatAsylum();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int[] ope : opes) {
			if (ope[0] == 1) {
				asylum.enter(ope[1]);
			} else if (ope[0] == 2) {
				int num = 0;
				if (ope[1] == 0)
					num = asylum.takeInAAnimate();
				else if (ope[1] == 1)
					num = asylum.takeInADog();
				else if (ope[1] == -1)
					num = asylum.takeInACat();
				if (num != 0)
					result.add(num);
			}
		}
		return result;
	}

	public static class Animate {
		long time;
		int type;

		public Animate(long time, int type) {
			this.time = time;
			this.type = type;
		}
	}

	public static class DogCatAsylum {

		private Queue<Animate> dogQ;
		private Queue<Animate> catQ;
		private long time;

		public DogCatAsylum() {
			dogQ = new LinkedList<Animate>();
			catQ = new LinkedList<Animate>();
			time = 0;
		}

		/**
		 * 收养一个动物
		 * 
		 * @param type
		 */
		public void enter(int type) {
			if (type > 0)
				dogQ.offer(new Animate(time++, type));
			else if (type < 0)
				catQ.offer(new Animate(time++, type));
		}

		/**
		 * 收养一只猫
		 */
		public int takeInACat() {
			if (catQ.isEmpty())
				return 0;
			return catQ.poll().type;
		}

		/**
		 * 收养一只猫
		 */
		public int takeInADog() {
			if (dogQ.isEmpty())
				return 0;
			return dogQ.poll().type;
		}

		/**
		 * 收养一只动物
		 * 
		 * @return
		 */
		public int takeInAAnimate() {
			if (dogQ.isEmpty() && catQ.isEmpty())
				return 0;
			else if (dogQ.isEmpty()) {
				return catQ.poll().type;
			} else if (catQ.isEmpty()) {
				return dogQ.poll().type;
			} else {
				if (dogQ.peek().time < catQ.peek().time) {
					return dogQ.poll().type;
				} else {
					return catQ.poll().type;
				}
			}
		}
	}
}