package com.innerac.compile.lexical;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TableCode {
	static public Map<String, String> codetable = new HashMap<String, String>();
	@SuppressWarnings("resource")
	static public void buildTable() throws FileNotFoundException{
		String tablePath = System.getProperty("user.dir")+"/src/raw/codetable";
		
		FileInputStream is=new FileInputStream(tablePath);
		InputStreamReader ir=new InputStreamReader(is);
        BufferedReader in =new BufferedReader(ir);
        
        String s;
        String[] input = null;
        String key = null;
        String value = null;
        
        
        try {
			while((s=in.readLine()) !=null){
				input = s.split("	");
				key = input[0];
				value = input[1];
				codetable.put(key, value);
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
