package com.innerac.compile.statement;

/**
 * @author innerac
 *	说明语句的词法分析
 *	输入一段语句，进行词法分析，并且进行错误检查
 */
public class StatementAnalysis {

	Statement statement;
	public StatementAnalysis(){
		statement = new Statement();
	}
	/**
	 * 开始进行词法分析
	 * @param str	待分析的字符串
	 */
	public void start(String str){
		String ans = "";
		String tmp = "";
		char cs[];
		int N = str.length();
		cs = str.toCharArray();
		char c;
		
		int i =0;
		//获取第一个空格前的字符串，来判断是否为保留字
		for(i=0;i<N;i++){
			if(cs[i] == ' ' ){
				break;
			}else{
				ans += cs[i];
			}
		}
		if(!"const".equals(ans)){
			System.err.println("It is not a constant declaration statement! \nPlease input a string again!");
		}
		
		i++;
		ans = "";
		while(i<N){
			//每个赋值语句都为一个循环
			c = cs[i];
			if(c != '='){
				if(c != ' ')
					ans += c;
				i++;
				continue;
			} else {
				//得到变量名，判断变量名是否合法
				if(!statement.judgeName(ans)){
					System.err.println(ans+"(Wrong! It is not a identifier!)");
				}else{
					System.out.print(ans);
				}
				ans = "";
				i++;
				c = cs[i];
				for(;i<N;i++){
					if(cs[i] != ',')
						ans += cs[i];
					else
						break;
				}
				//获取到'='后的字符串，根据引号来判断类型，并且进行合法检验
				if(c == '\''){
					//char
					tmp = ans.substring(1,ans.length()-1);
					if(statement.judgeChar(tmp)){
						System.out.println("(char,'"+tmp+"')");
					}else if(statement.errnChar(str)){
						System.out.println("(Woring ! There are more than one char in '"+ tmp +"')");
					}else if(statement.erroChar(str)){
						System.out.println("(Woring ! There are should not include \\t \\r \\n \\f !");
					}else{
						System.out.println("(Woring ! Maybe this is a string)");
					}
					ans ="";
					i++;
					continue;
				}else if(c == '\"'){
					//String
					tmp = ans.substring(1,ans.length()-1);
					if(statement.judgeString(tmp)){
						System.out.println("(string,\""+tmp+"\")");
					}else{
						System.out.println("(Woring! String should't be error please reboot!)");
					}
					ans ="";
					i++;
					continue;
				}else{
					//int or float
					tmp = ans;
					if(statement.judgeInt(tmp)){
						System.out.println("(Integer,"+tmp+")");
					}else if(statement.judgeFloat(tmp)){
						System.out.println("(Float,"+tmp+")");
					}else if(statement.err0Num(str)){
						System.out.println("(Woring! The num can't be started with 0 .");
					}else if(statement.judgeString(str)){
						System.out.println("(Woring! The string should be contained by \" .)");
					}else{
						System.out.println("(Woring! Unknown error)");
					}
					ans ="";
					i++;
					continue;
				}
			}
		}
	}
}
