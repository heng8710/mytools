package my.relativepath;

import java.io.File;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;

/**
 * 按相对路径搜索
 */
public class RelativeFileSearcher {

	public final String suffix;
	public final Path rootPath;
	/**
	 * 有序的，顺序是按路径来排列。深度优先；同级的话，就按照 字符串的顺序来比较
	 */
	private final SortedSet<RelativeFile> targetFiles = new TreeSet<>(new Comparator<RelativeFile>(){
		@Override
		public int compare(final RelativeFile o1, final RelativeFile o2) {
			 return ComparisonChain.start().compare(o2.pathNodeList.size(), o1.pathNodeList.size()).compare(o1.path, o2.path).result();
		}
	});

	/**
	 * @param root 必须是一个目录
	 * @param suffix 后缀名，例如【.js】
	 */
	public RelativeFileSearcher(final Path root, final String suffix) {
		if(root == null){
			throw new IllegalArgumentException("root is null");
		}
		final Path realRoot = root.normalize().toAbsolutePath();
		if(!realRoot.toFile().exists() || !realRoot.toFile().isDirectory()){
			throw new IllegalArgumentException(String.format("root=[%s] not exist or not a directory", root));
		}
		if(Strings.isNullOrEmpty(suffix) || !suffix.startsWith(".")){
			throw new IllegalArgumentException(String.format("suffix=[%s] 不正确", suffix));
		}
		this.suffix = suffix;
		this.rootPath = realRoot;
		init0(rootPath);
		//debug
		System.out.println(targetFiles);
	}

	public Path search(final String relativePath){
		if(!RelativeFile.mayBeRelativePath(relativePath)){
			throw new IllegalArgumentException(String.format("path=[%s] is not a relative path", relativePath));
		}
		final RelativeFile target = new RelativeFile(rootPath.resolve(relativePath + suffix).normalize().toAbsolutePath(), rootPath, suffix);
		if (targetFiles.contains(target)){
			return target.filePath;
		}
		return null;
	}
	
	
	/**
	 * TODO 还可以有边界条件地遍历
	 * @param visitor
	 */
	public void depthFirstTraverse(final Visitor visitor){
		for(final RelativeFile i: targetFiles){
			visitor.visit(i);
		}
	}
	
	
	
	
	public static interface Visitor{
		public void visit(RelativeFile path);
	}
	
	void init0(final Path path){
		if(!path.toFile().isDirectory()){
			throw new IllegalArgumentException(String.format("path=[%s] 必须是目录", path));
		}
		for(final File f: path.toFile().listFiles()){
			if(f.isDirectory()){
				init0(f.toPath());
			}else{
				if(f.getName().endsWith(suffix)){
					final RelativeFile rf = new RelativeFile(f.toPath(), rootPath, suffix);
					targetFiles.add(rf);
				}
			}
		}
	}
	
}
