package my.javascript.file;

import java.nio.file.Path;

import my.relativepath.RelativeFile;

public class JsFile extends RelativeFile{

	private static final String suffix = ".js";
	
	public JsFile(final Path filePath, final Path root) {
		super(filePath, root, suffix);
	}

	
	
	
	
}
