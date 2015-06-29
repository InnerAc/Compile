package com.innerac.compile.test;

import org.junit.Test;

import com.innerac.compile.statement.StatementAnalysis;

public class StatementTest {

	@Test
	public void testChar(){
		StatementAnalysis sa = new StatementAnalysis();
		sa.start("const char='@',string=\"ahhhhh\"");
	}
}
