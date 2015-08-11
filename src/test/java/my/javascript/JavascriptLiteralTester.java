package my.javascript;

import java.io.File;

import com.google.common.base.Charsets;
import com.google.common.io.Files;


/**
 *  与js字面量有关
 */
public final class JavascriptLiteralTester {

	public static void main(String... args) throws Exception{
		String s = jsonStrToObjLiteral();
		System.out.println(s);
	}
	
	
	public static String objToJsonStr() throws Exception{
		return JavascriptLiteral.jsObjToJson(Files.toString(new File("E:/eclipsews/workspace001/mytools/src/test/resources/another.js"), Charsets.UTF_8));
	}
	
	
	
	public static String jsonStrToObjLiteral(){
		return JavascriptLiteral.jsonToObjLiteral("{\"sqlite_file_folder\":\"/data/sqlite/log/\",\"sname\":\"aagjj\",\"num\":998,\"xx\":{\"mao\":[\"xiaohuan\",\"xiaohei\",\"paojiao\",{\"nangua\":[1,3,7777,[666,999]]}]},\"mail\":{\"host\":\"smtp.aaaaaa.com\",\"user\":\"3333333@qq.com\",\"password\":\"2222222\"}}");
	}
	
}
