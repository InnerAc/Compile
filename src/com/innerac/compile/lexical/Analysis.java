package com.innerac.compile.lexical;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analysis {
	
	TireTreeCode ttc = new TireTreeCode();
	
	public Analysis(){
		try {
			ttc.buildTree();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void startAnalysis(String testString){
		int N = testString.length();
		
		int stdex = 0;
		boolean isVar = false;
		
		NodeCode nodeCode = ttc.tireRoot;
		char c,c2;
		for(int i=0;i<N;i++){
			c = testString.charAt(i);
			if(c == ' ' && !isVar){
				nodeCode = ttc.tireRoot;
				stdex = i+1;
				continue;
			}
			if(c == ' ' && isVar){
				String tmpString = testString.substring(stdex, i);
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
			if(nodeCode.judge(c)){
				nodeCode = nodeCode.next(c);
				if(nodeCode.isEnd() == true){
					try{
						c2 = testString.charAt(i+1);
						if(nodeCode.judge(c2)){
							continue;
						}
					}catch(StringIndexOutOfBoundsException e){
					}
					
					if(isVar){
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
				isVar = true;
				nodeCode = ttc.tireRoot;
			}
			
		}
	}
	public boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
}
