package com.innerac.compile.lexical;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TireTreeCode {

	public  NodeCode tireRoot;
	
	private  int nodeId = 0;
	private  String keyString = null;
	private  int value = 0;
	
	@SuppressWarnings("resource")
	public  void buildTree() throws FileNotFoundException{
		
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
	public  int getNodeId(){
		return nodeId;
	}
}
