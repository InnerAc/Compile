package com.innerac.compile.ll1;

import java.util.HashMap;
import java.util.Map;

public class SelectTable {
	static char itoc[] = {'S','A','T','U','B','+','*','m','(',')','#'};
	public static Map<Character, Integer> ctoi = new HashMap<Character, Integer>();
	public static String[][] table = new String[5][6];
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
		table[3][5] = "$";
		table[4][2] = "m";
		table[4][3] = "(S)";
	}
	public static String getNext(char n,char t){
		return table[ctoi.get(n)][ctoi.get(t)-5];
	}
	public static boolean isVT(char c){
		if(ctoi.get(c) > 4)return true;
		else return false;
	}
}
