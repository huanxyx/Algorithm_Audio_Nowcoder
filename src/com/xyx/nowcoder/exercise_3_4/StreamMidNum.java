package com.xyx.nowcoder.exercise_3_4;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 找到数据流中的中位数
 * 问题链接：https://www.nowcoder.com/questionTerminal/9be0172896bd43948f8a32fb954e1be1
 * @author huan
 * @date 2018年6月17日
 */
public class StreamMidNum {
	private int count = 0;
	private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	});

	/*
	 * 每次操作都保证了大根堆所有元素都小于小根堆的元素，
	 * 保证了若是总量为奇数个，小根堆元素数目比大根堆大一（意味着小根堆最小的元素就是中位数）
	 * 保证了若是总量为偶数个，小根堆和大根堆的元素数目相同
	 */
	public void Insert(Integer num) {
		if (count % 2 == 0) {
			//1.当总数为偶数个时，新加入的元素，应该进入大根堆
			//然后经过大根堆筛选一个进入小根堆
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
		} else {
			//2.当总数为奇数个时，新加入的元素，应该进入小根堆
			//然后经过小根堆筛选一个进入大根堆
			minHeap.offer(num);
			maxHeap.offer(minHeap.poll());
		}
		count++;
	}
	
	//获取中位数
	public Double GetMedian() {
		if (count % 2 == 0) 
			return new Double((minHeap.peek() + maxHeap.peek())) / 2;
		else 
			return (double)minHeap.peek();
	}

}
