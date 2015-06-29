package com.innerac.compile.test;

import java.io.FileNotFoundException;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.junit.Test;

import com.innerac.compile.lexical.Analysis;
import com.innerac.compile.lexical.NodeCode;
import com.innerac.compile.lexical.TableCode;
import com.innerac.compile.lexical.TireTreeCode;

public class CompileTest {

	@Test
	public void testPath(){
		System.out.println(System.getProperty("user.dir")+"/src/raw/codetable");
	}
	
	@Test
	public void testBuildTable(){
		try {
			TableCode.buildTable();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(TableCode.codetable.get("begin"));
	}
	
	@Test
	public void testBuildTree(){
		TireTreeCode ttc = new TireTreeCode();
		try {
			ttc.buildTree();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String testString = "begin letter(letter|digit)*=digitdigit*;if letter(letter|digit)*>digitdigit* then letter(letter|digit)*:=digitdigit**letter(letter|digit)*+digitdigit*/digitdigit*;end#";
		int N = testString.length();
		NodeCode nodeCode = ttc.tireRoot;
		char c;
//		System.out.println(nodeCode.toString());
		//nodeCode.next('b').getId();
		for(int i=0;i<N;i++){
			c = testString.charAt(i);
			if(c == ' '){
				nodeCode = ttc.tireRoot;
				continue;
			}
			nodeCode = nodeCode.next(c);
			if(nodeCode.isEnd() == true){
				System.out.print("("+nodeCode.getCode()+","+nodeCode.getValue()+") ");
				nodeCode = ttc.tireRoot;
			}
		}
	}

	@Test
	public void testAnalysis(){
		Analysis analysis = new Analysis();
		
//		String testString = "><";
		String testString = "begin begins:=9;"
				+ "if(ifs<>0) then x:=2*x+1/3;end#";
		analysis.startAnalysis(testString);
		
	}
	
}
