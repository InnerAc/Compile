package com.innerac.compile.lexical;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author innerac
 *	字典树码表
 *	该类用于通过文件读取保留字(单词符号)和种别码，然后建立一颗字典树。
 *	该字典树用来进行词法分析
 */
public class TireTreeCode {

	
	/**
	 * 字典树根，为了方便设置为public，理论来说应该设置为private
	 */
	public  NodeCode tireRoot;
	
	private  int nodeId = 0;
	private  String keyString = null;
	private  int value = 0;
	
	/**
	 * 建立字典树，通过实例化的方法来调用该函数建立一颗用于词法分析的字典树
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public void buildTree() throws FileNotFoundException{
		
		String tablePath = System.getProperty("user.dir")+"/src/raw/codetable";
		
		FileInputStream is=new FileInputStream(tablePath);
		InputStreamReader ir=new InputStreamReader(is);
        BufferedReader in =new BufferedReader(ir);
        
        String s;
        String[] input = null;
        tireRoot = new NodeCode(nodeId++, "");
        try {
			while((s=in.readLine()) !=null){
				input = s.split("	");
				keyString = input[0];
				value = Integer.valueOf(input[1]);
				findNode();
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	private  NodeCode createNode(int kid){
		NodeCode nodeCode = new NodeCode(nodeId++, keyString.substring(0, kid));
		if(kid == keyString.length()){
			nodeCode.setCode(value);
//			System.out.println("isEnd! "+nodeCode.getValue());
			return nodeCode;
		}
		return nodeCode;
	}
	/**
	 * 建树过程
	 * 如果存在子节点便向下走，如果不存在则创建子节点
	 */
	public void findNode(){
		NodeCode nc = tireRoot;
		int kid = 0;
		char c = keyString.charAt(kid);
		int N = keyString.length();
		while(kid < N){
			c = keyString.charAt(kid);
			if(!nc.judge(c)){
				nc.addkeys();
				nc.nextMap.put(c, createNode(kid+1));
			}
			nc = nc.nextMap.get(c);
			kid++;
		}
		
 	}
	/**
	 * 得到建树的节点数，用于调试信息
	 * @return 节点总数
	 */
	public int getNodeId(){
		return nodeId;
	}
}
