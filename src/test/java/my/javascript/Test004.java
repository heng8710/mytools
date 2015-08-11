package my.javascript;

import java.nio.file.Paths;
import java.util.Arrays;

import com.google.common.base.Objects;

public class Test004 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JsNameSpace j = new JsNameSpace(Paths.get("E:/eclipsews/workspace001/globalsetting/src/main/resources"));
		Object o = j.get("another['xx']");
		System.out.println(o);
		
		o = j.get("global.mail");
		System.out.println(o);
		
		
		
		o = j.get("global.jjfly");
		System.out.println(o);	
	}

}
