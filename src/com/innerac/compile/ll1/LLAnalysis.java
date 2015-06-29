package com.innerac.compile.ll1;

import java.util.Stack;

/**
 * @author innerac
 *	LL1文法分析
 */
public class LLAnalysis {

	public LLAnalysis(){
		SelectTable.init();
	}
	/**
	 * 判断是否属于某文法
	 * @param str	待判断字符串
	 */
	public void start(String str){
		//输出表头
		System.out.print("Step\t");
		System.out.printf("%-30s", "Stack");
		System.out.printf("%-30s", "String");
		System.out.println("Rule");
		
		Stack<Character> stack = new Stack<Character>();
		stack.push('#');
		stack.push('S');
		int index = 0;
		int n = 0;
		char c,s;
		char cs[];
		String csString = null;
		int ant = 0;
		try{
			//LL1文法分析过程
			while(stack.size() > 1){
				System.out.print((++ant)+"\t");
				System.out.printf("%-30s", stack.toString());
				c = str.charAt(index);
				s = stack.pop();		
				System.out.printf("%-30s", str.substring(index));
				if(s == c){
					System.out.println("Match "+c);
					index++;
					continue;
				}
				csString = SelectTable.getNext(s, c);
				System.out.println(s + "->"+ csString);
				if(csString != "$"){
					n = csString.length();
					cs = csString.toCharArray();
					for(int i=n-1;i>=0;i--){
						stack.push(cs[i]);
					}
				}
			}
			System.out.print((++ant)+"\t");
			System.out.printf("%-30s", stack.toString());
			System.out.printf("%-30s", str.substring(index));
			System.out.println("Accept");
		}catch(Exception e){
			System.out.println("Error");
		}
	}
}
