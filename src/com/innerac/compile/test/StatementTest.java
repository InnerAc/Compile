package com.innerac.compile.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import com.innerac.compile.statement.StatementAnalysis;

public class StatementTest {

	@Test
	public void testChar(){
		String test = "const count=10, sum=81.5, char1='ff',string1=\"hj\t dsaf\",max=169";

		StatementAnalysis sa = new StatementAnalysis();
		String inputPath = System.getProperty("user.dir")+"/src/inputfile/inputfile";
		
		FileInputStream is = null;
		try {
			is = new FileInputStream(inputPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStreamReader ir=new InputStreamReader(is);
        BufferedReader in =new BufferedReader(ir);
        try {
			while((test=in.readLine()) !=null){

				sa.start(test);
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
