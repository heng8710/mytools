package my.javascript;



/**
 *  与js字面量有关。【注意：这里的字面量不能处理属性顺序严格的情况；建议属性名字不要超出[-a-zA-Z0-9_]的情况】 <br/>
 *  这是设想一的某一种数据交互模式： 以json作为传输格式，从前端交互到后台业务，从后台业务到存储持久。<br/>
 *  js的角色可以出现在2个地方：前端交互js，后端业务的js<br/>
 *  js字面量与json格式的转化只能是单向【json属于其中一种js字面量，但是js字面量可以有多种表达】：从js字面量转化为json<br/>
 *  js字面量的一般使用场景： 前端交互js；后端	（前端）用来作为单纯的配置式的js字面量（因为有层次，而且可以写注释）。<br/>
 */
public final class JavascriptLiteral {

	
	
	
	
	
//	/**
//	 * @param jsonStr: json字符串
//	 * @return : js字面量字符串
//	 */
//	public static String jsonToObjLiteral(final String jsonStr){
//		try {
//			final ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
////		    final String str = (String)engine.eval("JSON.parse("+jsonStr+");");
//			final String str = (String)engine.eval("JSON.stringify('"+jsonStr+"');");
//			return str;
//		} catch (Exception e) {
//			throw new IllegalStateException(String.format("解析js json失败=[%s]失败", jsonStr) , e);
//		}
//	}
	
}
