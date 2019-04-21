package com.skyworthbox.parserHtml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class KeyUtil {
	private static ScriptEngine engine = null;
	static {
		ScriptEngineManager m = new ScriptEngineManager();
		engine = m.getEngineByName("JavaScript");
	}
	
	public static void main(String[] args) {
		try {
			String vjkl5Val = "4175195ff283e9201c218ad9f02610c8e41ad557";
			getKey(vjkl5Val);
		} catch (IOException | ScriptException | NoSuchMethodException  e) {
			e.printStackTrace();
		}
	}

	public static String getKey(String vjkl5Val) throws IOException, ScriptException, NoSuchMethodException {
		PipedReader prd = new PipedReader();
		PipedWriter pwt = new PipedWriter(prd);
		// 设置执行结果内容的输出流
		engine.getContext().setWriter(pwt);
		// js文件的路径
		String path = Thread.currentThread().getClass().getResource("/").getPath();
		System.out.println(path);
		
		Reader reader = eaderJsFileList(path + "/md5.js", path + "/base64.js",
				path + "/Lawyee.CPWSW.ListExtend.js"
				);
		engine.eval(reader);
		
		//NashornScriptEngine
	    Invocable inv=(Invocable)engine;
	    System.err.println(inv);
	    
	    Object setVal = inv.invokeFunction("getCookie", vjkl5Val);
	    
	    Object result = inv.invokeFunction("getKey");
	    System.out.println(result);
	    
		pwt.close();
		prd.close();
		
		return result.toString();
	}
	
	public static Reader eaderJsFileList(String... fileNames) throws IOException {		
		File file1 = new File(fileNames[0]);
		File file2 = new File(fileNames[1]);
		File file3 = new File(fileNames[2]);
		
		FileInputStream reader1 = new FileInputStream(file1);
		FileInputStream reader2 = new FileInputStream(file2);
		FileInputStream reader3 = new FileInputStream(file3);
		
		List<FileInputStream> c = new ArrayList<FileInputStream>();
		c.add(reader1);
		c.add(reader2);
		c.add(reader3);
		
		Enumeration<FileInputStream> e = Collections.enumeration(c);
		SequenceInputStream sequenceInputStream = new SequenceInputStream(e);
		
		InputStreamReader inputReader = new InputStreamReader(sequenceInputStream);
//		reader1.close();
//		reader2.close();
		return inputReader;
	}

}
