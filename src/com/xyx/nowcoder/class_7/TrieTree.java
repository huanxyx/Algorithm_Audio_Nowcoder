package com.xyx.nowcoder.class_7;

/**
 * 字典树的实现
 * @author huan
 * @date 2018年7月1日
 */
public class TrieTree {
	
	/*
	 * 结点类，存储着路径
	 */
	private static class TrieNode {
		TrieNode nexts[];					//下一个结点，路径代表着字符
		int path;							//经过该节点的字符串的个数
		int end;							//以该节点为终点的字符串的个数

		public TrieNode() {
			nexts = new TrieNode[26];
			path = 0;
			end = 0;
		}
 	}
	
	//根节点
	private TrieNode root;

	public TrieTree() {
		root = new TrieNode();
	}
	
	/*
	 * 插入一个字符串
	 */
	public void insert(String str) {
		if (str == null)
			return ;
		
		char[] strArr = str.toCharArray();
		
		TrieNode cur = root;
		for (int i = 0; i < strArr.length; i++) {
			int index = mapping(strArr[i]);
			if (cur.nexts[index] == null) {
				cur.nexts[index] = new TrieNode();
			}
			cur = cur.nexts[index];
			cur.path++;
		}
		cur.end++;
	}
	
	/*
	 * 查看一个字符串是否出现过
	 */
	public boolean search(String str) {
		return count(str) > 0;
	}
	
	/*
	 * 删除一个字符串
	 */
	public void delete(String str) {
		//先判断是否有这个字符串
		if (search(str)) {
			char[] strArr = str.toCharArray();

			TrieNode cur = root;
			for (int i = 0; i < strArr.length; i++) {
				int index = mapping(strArr[i]);
				if (--cur.nexts[index].path == 0) {
					cur.nexts[index] = null;
					return ;
				}
				cur = cur.nexts[index];
			}
			cur.end--;
		}
	}
	
	/*
	 * 查看一个字符串出现的次数
	 */
	public int count(String str) {
		if (str == null)
			return 0;
		
		char[] strArr = str.toCharArray();
		
		TrieNode cur = root;
		for (char ch : strArr) {
			int index = mapping(ch);
			if (cur.nexts[index] == null)
				return 0;
			cur = cur.nexts[index];
		}
		return cur.end;
	}
	
	/*
	 * 查看包含该前缀字符串的个数
	 */
	public int preCount(String str) {
		if (str == null)
			return 0;
		
		char[] strArr = str.toCharArray();
		
		TrieNode cur = root;
		for (char ch : strArr) {
			int index = mapping(ch);
			if (cur.nexts[index] == null)
				return 0;
			cur = cur.nexts[index];
		}
		return cur.path;
	}
	
	//当前字符的下标映射
	private static int mapping(char ch) {
		return ch - 'a';
	}
	
	//test
	public static void main(String[] args) {
		TrieTree trie = new TrieTree();
		System.out.println(trie.count("zuo"));
		trie.insert("zuo");
		System.out.println(trie.count("zuo"));
		trie.delete("zuo");
		System.out.println(trie.count("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		System.out.println(trie.count("zuo"));
		trie.delete("zuo");
		System.out.println(trie.count("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		trie.delete("zuoa");
		System.out.println(trie.count("zuoa"));
		System.out.println(trie.preCount("zuo"));
	}

}
