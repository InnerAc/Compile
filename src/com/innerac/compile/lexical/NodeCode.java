package com.innerac.compile.lexical;

import java.util.HashMap;
import java.util.Map;

public class NodeCode {
	int nodeId = 0;
	String nodeValue = null;
	boolean isEnd = false;
	int code = 0;
	int keys = 0;
	public NodeCode(int id,String value) {
		this.nodeId = id;
		this.nodeValue = value;
	}
	public void setCode(int code){
		isEnd = true;
		this.code = code;
	}
	public void addkeys(){
		keys++;
	}

	Map<Character, NodeCode> nextMap = new HashMap<Character, NodeCode>();
	
	public NodeCode next(char c){
		return nextMap.get(c);
	}
	
	public boolean isEnd(){
		return isEnd;
	}
	public String getValue(){
		return nodeValue;
	}
	public int getCode(){
		return code;
	}
	public int getId(){
		return nodeId;
	}
	public int getKeys(){
		return keys;
	}
	public boolean judge(char c){
		if(nextMap.get(c) == null){
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "NodeCode [nodeId=" + nodeId + ", nodeValue=" + nodeValue
				+ ", isEnd=" + isEnd + ", code=" + code + ", keys=" + keys
				+ ", nextMap=" + nextMap + "]";
	}

	
	
	
}
