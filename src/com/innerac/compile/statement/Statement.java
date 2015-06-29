package com.innerac.compile.statement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statement {
	
	/**
	 * 判断是否为正常的变量名，以a-z_开头
	 * @param str 待判断字符串
	 * @return 若为真返回true
	 */
	public boolean judgeName(String str){
		String rule = "[a-z_][\\w_]*";
		return isJudge(rule, str);
	}
	/**
	 * 判断是否为整型
	 * @param str 待判断字符串
	 * @return 若为真返回true
	 */
	public boolean judgeInt(String str){
		String rule = "((\\+|\\-)?[1-9][0-9]*|0)";
		return isJudge(rule, str);
	}
	/**
	 * 判断是否为浮点型
	 * @param str 待判断字符串
	 * @return 若为真返回true
	 */
	public boolean judgeFloat(String str){
		String rule = "(\\+|\\-)?([1-9][0-9]*|0)\\.[0-9]*";
		return isJudge(rule, str);
	}
	/**
	 * 判断是否为字符型
	 * @param str 待判断字符串
	 * @return 若为真返回true
	 */
	public boolean judgeChar(String str){
		String rule = "[\\S.]";
		return isJudge(rule, str);
	}
	/**
	 * 判断是否为字符串
	 * @param str 待判断字符串
	 * @return 若为真返回true
	 */
	public boolean judgeString(String str){
		String rule = ".*";
		return isJudge(rule, str);
	}
	
	//Error
	/**
	 * 判断数字是否以0为前缀
	 * @param str 待判断字符串
	 * @return 若为真返回true
	 */
	public boolean err0Num(String str){
		String rule = "(\\+|\\-)?([0-9]+)(\\.[0-9]*)?";
		return isJudge(rule, str);
	}
	/**
	 * 判断字符是否为多个
	 * @param str 待判断字符串
	 * @return 若为真返回true
	 */
	public boolean errnChar(String str){
		String rule = "[\\S.]+";
		return isJudge(rule, str);
	}
	/**
	 * 判断字符是否含有\\[trnf]
	 * @param str 待判断字符串
	 * @return 若为真返回true
	 */
	public boolean erroChar(String str){
		String rule = "[.]";
		return isJudge(rule, str);
	}
	private boolean isJudge(String rule,String str){ 
		   Pattern pattern = Pattern.compile(rule); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
	
//	public static void main(String arg[]){
//		Statement statement = new Statement();
//		if(statement.err0Num("+0")){
//			System.out.println("true");
//		}
//	}
}
