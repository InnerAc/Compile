package com.innerac.compile.lexical;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author innerac
 *	词法分析类
 *	用于对一段语句进行词法分析，需要使用种码字典树来进行分析
 */
public class Analysis {
	
	TireTreeCode ttc = new TireTreeCode();
	
	public Analysis(){
		try {
			ttc.buildTree();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输入一段语句，完成词法分析
	 * @param testString	待分析的语句
	 */
	public void startAnalysis(String testString){
		int N = testString.length();
		
		//本次分析的首指针
		int stdex = 0;
		//是否进行变量或数字判断
		boolean isVar = false;
		
		NodeCode nodeCode = ttc.tireRoot;
		char c,c2;
		for(int i=0;i<N;i++){
			c = testString.charAt(i);
			//遇到空格且不为数字和变量，不会出现这种情况
			if(c == ' ' && !isVar){
				nodeCode = ttc.tireRoot;
				stdex = i+1;
				continue;
			}
			//遇到空格且正在进行数字或变量的分析
			if(c == ' ' && isVar){
				String tmpString = testString.substring(stdex, i);
				//判断该串是否为数字
				if(isNumeric(tmpString)){
					System.out.print("(11,"+tmpString+") ");
				}else{
					System.out.print("(10,"+tmpString+") ");
				}
				isVar = false;
				nodeCode = ttc.tireRoot;
				stdex = i+1;
				continue;
			}
			//判断是否可以进行进一步词法分析(字典树是否可以向下进行)
			if(nodeCode.judge(c)){
				//可以向下进行
				nodeCode = nodeCode.next(c);
				//判断是否为终结符
				if(nodeCode.isEnd() == true){
					//为终结符，向后读取一位
					try{
						c2 = testString.charAt(i+1);
						//下一位仍然可以进行分析，继续进行，不进行归约
						if(nodeCode.judge(c2)){
							continue;
						}
						//当前单词符号为保留字，则后面如果不是空格或左括号便不进行归约
						if(nodeCode.getCode() <= 6 && (c2 != ' ' && c2 != '(')){
							continue;
						}
					}catch(StringIndexOutOfBoundsException e){
					}
					if(isVar){
						//正在进行单词分析便同时输出两个单词
						int len = nodeCode.getValue().length();
						String tmpString = testString.substring(stdex, i-len+1);
						if(isNumeric(tmpString)){
							System.out.print("(11,"+tmpString+") ");
						}else{
							System.out.print("(10,"+tmpString+") ");
						}
						isVar = false;
						System.out.print("("+nodeCode.getCode()+","+nodeCode.getValue()+") ");
					}else{
						System.out.print("("+nodeCode.getCode()+","+nodeCode.getValue()+") ");
					}
					nodeCode = ttc.tireRoot;
					stdex = i+1;
				}
			}else{
				//无法向下进行，该单词为变量或这数字
				isVar = true;
				nodeCode = ttc.tireRoot;
			}
			
		}
	}
	/**
	 * 判断是否为数字
	 * 仅仅判断是否为整形
	 * @param str	待判断的字符串
	 * @return	如果为数字则返回true
	 */
	public boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
}
