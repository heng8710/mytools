package my.javascript;

import java.nio.file.Path;

import my.relativepath.RelativeFileTraveller;

public class JsRuntimeEnv {

	private RelativeFileTraveller searcher;
	private Path root;
	
	
	public JsRuntimeEnv(final Path root) {
		if(root == null || !root.toFile().exists() || !root.toFile().isDirectory()){
			throw new IllegalArgumentException(String.format("path=[%s] 不是正确的目录", root));
		}
		this.root = root.normalize().toAbsolutePath();
		this.searcher = new RelativeFileTraveller(this.root, ".js");
	}
	
	
	
}
