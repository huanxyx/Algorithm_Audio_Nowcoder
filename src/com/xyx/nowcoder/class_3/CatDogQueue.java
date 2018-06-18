package com.xyx.nowcoder.class_3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列
 * @author huan
 * @date 2018年6月12日
 */
public class CatDogQueue {
	public static String PET_TYPE_DOG = "Dog";
	public static String PET_TYPE_CAT = "Cat";
	
	public static class Pet {
		private String type;
		
		public Pet(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		} 
	}
	
	public static class Cat extends Pet {
		public Cat() {
			super(PET_TYPE_CAT);
		}
	}
	
	public static class Dog extends Pet {
		public Dog() {
			super(PET_TYPE_DOG);
		}
	}
	
	public static class PetEnter {
		private Pet pet;
		private long count;
		
		public PetEnter(Pet pet, long count) {
			this.pet = pet;
			this.count = count;
		}
		
		public Pet getPet() {
			return pet;
		}
		
		public long getCount() {
			return count;
		}
		
		public String getEnterPetType() {
			return this.pet.getType();
		}
	}
	
	private Queue<PetEnter> dogQueue;
	private Queue<PetEnter> catQueue;
	private long count;
	
	public CatDogQueue() {
		dogQueue = new LinkedList();
		catQueue = new LinkedList();
		count = 0;
	}
	
	public Dog pollDog() {
		if (this.isDogEmpty())
			throw new RuntimeException("不存在狗!");
		return (Dog) dogQueue.poll().getPet();
	}
	
	public Cat pollCat() {
		if (this.isCatEmpty())
			throw new RuntimeException("不存在猫!");
		return (Cat) catQueue.poll().getPet();
	}
	
	public Pet pollAll() {
		if (this.isEmpty()) 
			throw new RuntimeException("队列为空!");
		else if (dogQueue.isEmpty())
			return catQueue.poll().getPet();
		else if (catQueue.isEmpty())
			return dogQueue.poll().getPet();
		else if (dogQueue.peek().getCount() < catQueue.peek().getCount())
			return dogQueue.poll().getPet();
		else 
			return catQueue.poll().getPet();
	}
	
	public void offer(Pet pet) {
		if (PET_TYPE_CAT.equals(pet.getType()))
			catQueue.offer(new PetEnter(pet, count++));
		else if (PET_TYPE_DOG.equals(pet.getType()))
			dogQueue.offer(new PetEnter(pet, count++));
		else 
			throw new RuntimeException("猫狗队列中不能容纳该宠物!");
	}
	
	public boolean isEmpty() {
		return catQueue.isEmpty() && dogQueue.isEmpty();
	}
	
	public boolean isDogEmpty() {
		return dogQueue.isEmpty();
	}
	
	public boolean isCatEmpty() {
		return catQueue.isEmpty();
	}
	
	public static void main(String[] args) {
		CatDogQueue test = new CatDogQueue();

		Pet dog1 = new Dog();
		Pet cat1 = new Cat();
		Pet dog2 = new Dog();
		Pet cat2 = new Cat();
		Pet dog3 = new Dog();
		Pet cat3 = new Cat();

		test.offer(dog1);
		test.offer(cat1);
		test.offer(dog2);
		test.offer(cat2);
		test.offer(dog3);
		test.offer(cat3);

		test.offer(dog1);
		test.offer(cat1);
		test.offer(dog2);
		test.offer(cat2);
		test.offer(dog3);
		test.offer(cat3);

		test.offer(dog1);
		test.offer(cat1);
		test.offer(dog2);
		test.offer(cat2);
		test.offer(dog3);
		test.offer(cat3);
//		while (!test.isDogEmpty()) {
//			System.out.println(test.pollDog().getType());
//		}
		while (!test.isEmpty()) {
			System.out.println(test.pollAll().getType());
		}
	}
		
}
