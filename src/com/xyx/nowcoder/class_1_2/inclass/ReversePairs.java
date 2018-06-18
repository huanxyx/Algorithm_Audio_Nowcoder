package com.xyx.nowcoder.class_1_2.inclass;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆序对问题
 * 		在一个数组中， 左边的数如果比右边的数大， 则折两个数构成一个逆序对， 请打印所有逆序对。
 * @author huan
 * @date 2018年6月7日
 */
public class ReversePairs {
	private static int[] help;
	
	public static class ReversePair {
		int left;
		int right;
		
		public ReversePair(int left, int right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "ReversePair [left=" + left + ", right=" + right + "]";
		}
		
		
	}
	public static List<ReversePair> reversePair(int[] arr) {
		help = new int[arr.length];
		List<ReversePair> pairList = new ArrayList<ReversePair>();
		if (arr == null || arr.length == 0)
			return pairList;
		mergeSort(arr, 0, arr.length-1, pairList);
		return pairList;
	}
	
	private static void mergeSort(int[] arr, int low, int hig, List<ReversePair> pairList) {
		if (low == hig)
			return ;
		int mid = low + ((hig - low) >> 1);
		mergeSort(arr, low, mid, pairList);
		mergeSort(arr, mid+1, hig, pairList);
		merge(arr, low, mid, hig, pairList);
	}
	
	private static void merge(int[] arr, int low, int mid, int hig, List<ReversePair> pairList) {
		int left = low;
		int right = mid + 1;
		int pos = low;
		
		while (left <= mid && right <= hig) {
			if (arr[left] > arr[right]) {
				for (int i = left; i <= mid; i++) {
					pairList.add(new ReversePair(arr[i], arr[right]));
				}
			}
			help[pos++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
		}
		while (left <= mid)
			help[pos++] = arr[left++];
		while (right <= hig) 
			help[pos++] = arr[right++];
		
		for (int i = low; i <= hig; i++) 
			arr[i] = help[i];
	}	
}
