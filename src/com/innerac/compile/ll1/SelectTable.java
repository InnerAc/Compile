package com.innerac.compile.ll1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author innerac
 *	表达式文法预测分析表
 *	文法G[S]
 *		S->AT
 *		A->BU
 *		T->+AT|$
 *		U->*BU|$
 *		B->(S)|m
 */
public class SelectTable {
	private static char itoc[] = {'S','A','T','U','B','+','*','m','(',')','#'};
	private static Map<Character, Integer> ctoi = new HashMap<Character, Integer>();
	private static String[][] table = new String[5][6];
	/**
	 * 建立预测分析表
	 */
	public static void init(){
		ctoi.put('S',0);
		ctoi.put('A',1);
		ctoi.put('T',2);
		ctoi.put('U',3);
		ctoi.put('B',4);
		ctoi.put('+',5);
		ctoi.put('*',6);
		ctoi.put('m',7);
		ctoi.put('(',8);
		ctoi.put(')',9);
		ctoi.put('#',10);
		
		table[0][2] = "AT";
		table[0][3] = "AT";
		table[1][2] = "BU";
		table[1][3] = "BU";
		table[2][0] = "+AT";
		table[2][4] = "$";
		table[2][5] = "$";
		table[3][0] = "$";
		table[3][1] = "*BU";
		table[3][4] = "$";
		table[3][5] = "$";
		table[4][2] = "m";
		table[4][3] = "(S)";
	}
	/**
	 * 查预测分析表
	 * @param n	左部
	 * @param t	转移子
	 * @return	文法右部
	 */
	public static String getNext(char n,char t){
		int x = ctoi.get(n);
		int y = ctoi.get(t) - 5;
		return table[x][y];
	}
	/**
	 * 判断是否为终结符
	 * @param c	待判断字符
	 * @return	若为终结符则返回true
	 */
	public static boolean isVT(char c){
		if(ctoi.get(c) > 4)return true;
		else return false;
	}
}
