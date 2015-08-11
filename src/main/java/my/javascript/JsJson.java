package my.javascript;

import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * js对象与json之间的转换。基本上是只能是【名词】。<br/>
 * 在有js引擎的地方，就可以用。<br/>
 * 有两种方法可以操作（get、set）： <br/>
 * 一是以json格式，get、set属性， 这在每个语言中都有各自的<br/>
 * 二是把名词对象（json字符串）拿到js引擎中，然后添加js操作代码（这需要再在js中跑JSON.parse(...jsonstr...)） <br/>
 */
public class JsJson {

	
	/**
	 * @param jsObjStr : js的对象，任何对象也可以（boolean、number、String、null、undefined、数组、对象）
	 * @return : json字符串 
	 */
	public static String jsObjToJson(final String jsObjStr){
		try {
			final ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
		    final String str = (String)engine.eval("JSON.stringify("+jsObjStr+");");
			return str;
		} catch (Exception e) {
			throw new IllegalStateException(String.format("解析js对象失败=[%s]失败", jsObjStr) , e);
		}
	}
	
	
	/**
	 * 以格式化了的json输出（这可能用于人类编辑，展示）
	 * js对象语法包含了json。
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String toPrettyJson(final Map<String, Object> o) throws Exception{
		if(o == null){
			throw new IllegalArgumentException();
		}
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o);
	}
	
	public static String toJson(final Map<String, Object> o) throws Exception{
		if(o == null){
			throw new IllegalArgumentException();
		}
		return new ObjectMapper().writeValueAsString(o);
	}
	
	
	/**
	 * 以格式化了的json输出（这可能用于人类编辑，展示）
	 * js对象语法包含了json。
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String toPrettyJson(final List<Object> o) throws Exception{
		if(o == null){
			throw new IllegalArgumentException();
		}
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o);
	}
	
	
	public static String toJson(final List<Object> o) throws Exception{
		if(o == null){
			throw new IllegalArgumentException();
		}
		return new ObjectMapper().writeValueAsString(o);
	}
	
	
//	public static String objToJson(final String o) throws Exception{
//		if(o == null){
//			throw new IllegalArgumentException();
//		}
//		final ObjectMapper mapper = new ObjectMapper();
//		final Map tmp = mapper.readValue(o, Map.class);
//		return mapper.writeValueAsString(tmp);
//	}
	
	/**
	 * 以格式化了的json输出（这可能用于人类编辑，展示）
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String objToPrettyJson(final String o) throws Exception{
		if(o == null){
			throw new IllegalArgumentException();
		}
		final ObjectMapper mapper = new ObjectMapper();
		final Map tmp = mapper.readValue(o, Map.class);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tmp);
	}
	
	
//	public static String arrayToJson(final String o) throws Exception{
//		if(o == null){
//			throw new IllegalArgumentException();
//		}
//		final ObjectMapper mapper = new ObjectMapper();
//		final List tmp = mapper.readValue(o, List.class);
//		return mapper.writeValueAsString(tmp);
//	}
	
	/**
	 * 以格式化了的json输出（这可能用于人类编辑，展示）
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String arrayToPrettyJson(final String o) throws Exception{
		if(o == null){
			throw new IllegalArgumentException();
		}
		final ObjectMapper mapper = new ObjectMapper();
		final List tmp = mapper.readValue(o, List.class);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tmp);
	}
}
