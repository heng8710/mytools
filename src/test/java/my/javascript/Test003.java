package my.javascript;

import java.nio.file.Path;
import java.nio.file.Paths;

import my.relativepath.RelativeFileSearcher;

public class Test003 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path root = Paths.get( "E:/eclipsews/workspace001/globalsetting/src/main/resources");
		RelativeFileSearcher t = new RelativeFileSearcher(root, ".js");
		
	}

}
