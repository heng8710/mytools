package my.javascript;

import java.nio.file.Path;
import java.nio.file.Paths;

import my.relativepath.RelativeFile;

public class Test002 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "E:/eclipsews/workspace001/globalsetting/src/main/resources";
		String s2 = "E:/eclipsews/workspace001/globalsetting/src/main/resources/a/b/c/d/another.js";
		RelativeFile r = new RelativeFile(Paths.get(s2), Paths.get(s), ".js");
		System.out.println(r);
	}

}
