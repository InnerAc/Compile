package com.innerac.compile.test;

import org.junit.Test;

import com.innerac.compile.ll1.LLAnalysis;

public class LL1Test {

	@Test
	public void testLL1(){
		LLAnalysis llAnalysis = new LLAnalysis();
		String string = "m+m*m#";
		llAnalysis.start(string);
	}
}
