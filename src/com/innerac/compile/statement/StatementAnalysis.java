package com.innerac.compile.statement;

public class StatementAnalysis {

	Statement statement;
	public StatementAnalysis(){
		statement = new Statement();
	}
	public void start(String str){
		String ans = "";
		String tmp = "";
		char cs[];
		int N = str.length();
		cs = str.toCharArray();
		char c;
		
		ans = str.substring(0, 5);
		if(!"const".equals(ans)){
			System.out.println(ans);
			System.err.println("It is not a constant declaration statement! \nPlease input a string again!");
		}
		int i =0;
		for(i=0;i<N;i++){
			if(cs[i] == ' ' ){
				break;
			}
		}
		i++;
		ans = "";
		while(i<N){
			c = cs[i];
			if(c != '='){
				ans += c;
				i++;
				continue;
			} else {
				if(!statement.judgeName(ans)){
					System.err.println(ans+"(Wrong! It is not a identifier!)");
				}else{
					System.out.print(ans);
				}
				ans = "";
				i++;
				c = cs[i];
				for(i++;i<N;i++){
					if(cs[i] != ',')
						ans += cs[i];
					else
						break;
				}
				if(c == '\''){
					//char
					tmp = ans.substring(0,ans.length()-1);
					if(statement.judgeChar(tmp)){
						System.out.println("(char,'"+tmp+"')");
					}else{
						System.err.println("(Woring!)");
					}
					ans ="";
					i++;
					continue;
				}else if(c == '\"'){
					//String
					tmp = ans.substring(0,ans.length()-1);
					if(statement.judgeString(tmp)){
						System.out.println("(string,\""+tmp+"\")");
					}else{
						System.err.println("(Woring!)");
					}
					ans ="";
					i++;
					continue;
				}else{
					//int or float
				}
			}
		}
	}
}
