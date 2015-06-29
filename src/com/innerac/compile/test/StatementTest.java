package com.innerac.compile.test;

import org.junit.Test;

import com.innerac.compile.statement.StatementAnalysis;

public class StatementTest {

	@Test
	public void testChar(){
		String test = "const count=10,sum=81.5,char1='ff',string1=\"hj\t dsaf\",max=169";
		StatementAnalysis sa = new StatementAnalysis();
		sa.start(test);
	}
}
