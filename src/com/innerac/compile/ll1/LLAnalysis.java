package com.innerac.compile.ll1;

import java.util.Stack;

public class LLAnalysis {

	public void start(String str){
		
		Stack<Character> stack = new Stack<Character>();
		
		stack.push('S');
		int index = 0;
		char c,s;
		String csString = null;
		while(!stack.empty()){
			c = str.charAt(index);
			s = stack.pop();		
			csString = SelectTable.getNext(s, c);
			
			output();
			
			for(int i=0;i<csString.length();i++){
				stack.push(csString.charAt(i));
			}
		}
		
	}
	public void output(){
		
	}
}
