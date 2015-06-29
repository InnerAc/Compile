package com.innerac.compile.lexical;

import java.util.HashMap;
import java.util.Map;

/**
 * @author innerac
 *	种别码字典树节点
 *	包含属性：
 *	nodeId	节点id
 *	nodeValue	该节点含有的字符串
 *	isEnd	终结符标识
 *	code	种别码
 *	keys	含有子树数量
 *	nextMap	子树映射表
 */
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
	/**
	 * 标识为终结符
	 * @param code	种别码
	 */
	public void setCode(int code){
		isEnd = true;
		this.code = code;
	}
	/**
	 * 【统计】增加一颗子树
	 */
	public void addkeys(){
		keys++;
	}

	Map<Character, NodeCode> nextMap = new HashMap<Character, NodeCode>();
	

	/**
	 * 得到子节点
	 * @param c	字符c
	 * @return	下一个节点
	 */
	public NodeCode next(char c){
		return nextMap.get(c);
	}
	
	/**
	 * 判断是否为终结符
	 * @return	若为终结符则为true
	 */
	public boolean isEnd(){
		return isEnd;
	}
	/**
	 * 获取单词符号
	 * @return 单词符号
	 */
	public String getValue(){
		return nodeValue;
	}
	/**
	 * 获取种别码
	 * @return	种别码
	 */
	public int getCode(){
		return code;
	}
	/**
	 * 获取ID
	 * @return ID
	 */
	public int getId(){
		return nodeId;
	}
	/**
	 * 获取子树数量
	 * @return	子树数量
	 */
	public int getKeys(){
		return keys;
	}
	/**
	 * 判断是否含有边为 c 的出度
	 * @param c	字符c
	 * @return	如果有返回true
	 */
	public boolean judge(char c){
		if(nextMap.get(c) == null){
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NodeCode [nodeId=" + nodeId + ", nodeValue=" + nodeValue
				+ ", isEnd=" + isEnd + ", code=" + code + ", keys=" + keys
				+ ", nextMap=" + nextMap + "]";
	}

	
	
	
}
