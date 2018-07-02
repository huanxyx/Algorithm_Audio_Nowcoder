package com.xyx.nowcoder.exercise_7_8;

import java.util.ArrayList;

/**
 * 问题：哈诺塔问题
 * 问题链接：https://www.nowcoder.com/questionTerminal/7d6cab7d435048c4b05251bf44e9f185
 * @author huan
 * @date 2018年7月2日
 */
public class Hanoi {

    public ArrayList<String> getSolution(int n) {
        ArrayList<String> result = new ArrayList<String>();
    	
        hanoi(n, "left", "mid", "right", result);
        return result;
    }
    
    public void hanoi(int n, String from, String help, String to, ArrayList<String> result) {
    	if (n == 1)
    		result.add("move from " + from + " to " + to);
    	else {
    		hanoi(n - 1, from, to, help, result);
    		result.add("move from " + from + " to " + to);
    		hanoi(n - 1, help, from, to, result);
    	}
    } 

}
