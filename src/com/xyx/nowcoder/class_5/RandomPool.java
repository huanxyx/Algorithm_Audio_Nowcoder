package com.xyx.nowcoder.class_5;

import java.util.HashMap;

/**
 * 实现一个数据结构保证插入，删除，随机选择其中的数的时间复杂度都是O(1)：
 * 			通过一个Map记录每一个数据的编号
 * 			通过一个Map记录每个编号的对应的数据
 * 			当需要随机的时候，随机一个编号，然后获取其对应的数据
 * 			当删除一个元素的时候，将拥有最后编号的元素的编号修改成应该删除元素的编号，
 * 			并将总的最大编号减一
 * @author huan
 * @date 2018年6月17日
 */
public class RandomPool<Key> {
	private HashMap<Key, Integer> keyIndexMap;
	private HashMap<Integer, Key> indexKeyMap;
	private int count;							//记录当前容器里面有多少个元素
	
	public RandomPool() {
		keyIndexMap = new HashMap<Key, Integer>();
		indexKeyMap = new HashMap<Integer, Key>();
		count = 0;
	}
	
	/**
	 * 添加一个元素
	 * @param data
	 */
	public void add(Key data) {
		if (keyIndexMap.containsKey(data))
			return ;
		keyIndexMap.put(data, count);
		indexKeyMap.put(count, data);
		count++;
	}
	
	/**
	 * 随机获取一个元素
	 * @return
	 */
	public Key random() {
		if (count == 0)
			return null;
		int index = (int)(Math.random() * count);		//0 ~ count-1
		Key data = indexKeyMap.get(index);
		return data;
	}
	
	/**
	 * 删除一个元素
	 * @param data
	 */
	public void remove(Key deletedata) {
		if (!keyIndexMap.containsKey(deletedata))
			return ;
		//最后一个元素的编号
		int lastIndex = --count;
		//获取应该删除元素的编号
		int deleteIndex = keyIndexMap.get(deletedata);
		//获取最后一个元素
		Key lastData = indexKeyMap.get(lastIndex);
		//将删除元素删除
		keyIndexMap.remove(deletedata);
		//同时修改最后一个元素的编号为删除元素的编号
		keyIndexMap.put(lastData, deleteIndex);
		indexKeyMap.put(deleteIndex, lastData);
		indexKeyMap.remove(lastIndex);
	}
	
	public static void main(String[] args) {
		RandomPool<String> pool = new RandomPool<String>();
		pool.add("zuo");
		pool.add("cheng");
		pool.add("yun");
		System.out.println(pool.random());
		System.out.println(pool.random());
		System.out.println(pool.random());
		System.out.println(pool.random());
		System.out.println(pool.random());
		System.out.println(pool.random());
	}
}
