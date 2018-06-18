package com.xyx.nowcoder.class_1_2.inclass;

/**
 * 堆排序
 * @author huan
 * @date 2018年6月8日
 */
public class HeapSort {
	public static void sort(int[] arr) {
		// 将数组转换为堆
		for (int i = 0; i < arr.length; i++) {
			insert(arr, i);
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			//取出前i个数最大的数放在最后一个位置。
			int max = delMax(arr, i);		
			arr[i] = max;
		}
	}
	
	/*
	 * 前置条件：arr中[0,pos-1]已经构成了一个堆
	 * 将pos位置的元素插入堆中
	 */
	private static void insert(int[] arr, int pos) {
		swim(arr, pos);
	}
	
	//对一个大小为lastPos的堆进行删除操作
	private static int delMax(int[] arr, int lastPos) {
		int res = arr[0];
		swap(arr, lastPos, 0);
		sink(arr, 0, lastPos - 1);
		return res;
	}
	
	private static void swim(int[] arr, int pos) {
		while (arr[pos] > arr[(pos - 1) / 2]) {
			swap(arr, pos, (pos - 1)/2);
			pos = (pos - 1) / 2;
		}
	}
	
	private static void sink(int[] arr, int pos, int size) {
		int nextPos = 2 * pos + 1;
		while (nextPos <= size) {
			if (nextPos < size && arr[nextPos] < arr[nextPos+1])
				nextPos++;
			if (arr[nextPos] > arr[pos])
				swap(arr, nextPos, pos);
			else 
				break;
			pos = nextPos;
			nextPos = pos * 2 + 1;
		}
	}
	
	private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}
	
	//test
	public static void main(String[] args) {
		
		int[] arr = generateRandomArray(1000, 100);
		sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
