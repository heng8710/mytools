package my.javascript;

import java.nio.file.Path;
import java.nio.file.Paths;

import my.relativepath.RelativeFileTraveller;

public class Test003 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path root = Paths.get( "E:/eclipsews/workspace001/globalsetting/src/main/resources");
		RelativeFileTraveller t = new RelativeFileTraveller(root, ".js");
		
	}

}

