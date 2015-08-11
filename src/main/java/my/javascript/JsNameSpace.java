package my.javascript;

import java.nio.file.Path;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import my.relativepath.RelativeFileTraveller;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;

/**
 * 实现了一种简单的方式，让不同的js文件（必须在root目录之下；必须以【.js】作为文件名后缀）之内可以（顺序先按文件夹深度优先，然后再按文件名字符串比较顺序）跨越不同的js文件之间互相引用。<br/>
 * 各自有独立的命名空间。<br/>
 * 引用的命名方式，前半段表示文件夹和js文件：是用【.】点，特指文件夹层次【这个是扩展的部分】，后半段表示属性：就用js原来自带的方式即可。<br/>
 * 目前只测试了文件夹、文件名字只在[-a-zA-Z0-9_]的情况，建议不要超过这个界限。---特别 是文件夹名字中最好不要带【.】（因为.有表示文件夹目录层次的意思了）<br/>
 * @author heng
 */
public class JsNameSpace {

	private final RelativeFileTraveller searcher; //待扩展
	private final Path root;
	
	private final ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");//引擎，同时也保存了js的上下文属性值
	
	
	public JsNameSpace(final Path root) {
		if(root == null || !root.toFile().exists() || !root.toFile().isDirectory()){
			throw new IllegalArgumentException(String.format("path=[%s] 不是正确的目录", root));
		}
		this.root = root.normalize().toAbsolutePath();
		this.searcher = new RelativeFileTraveller(this.root, ".js");
//		try {
//			engine.eval("var g = {};");
//		} catch (ScriptException e) {
//			throw new IllegalStateException();
//		}
		
//	    final String str = (String)engine.eval("JSON.stringify("+jsObjStr+");");
		init();
	}
	
	/**
	 * @param path ：如果要引用[k/another.js]里面的xx.yy['zz']属性，可以这样：【k.another.xx.yy['zz']】、【k.another['xx'].yy.zz】、……
	 * @return
	 * @throws Exception
	 */
	public String get(final String path) throws Exception{
		if(Strings.isNullOrEmpty(path)){
			throw new IllegalArgumentException("path不能为空");
		}
		//如果要算上[xx]这种数组写法的引用的话，做不了
//		final List<String> relativePathNodeList = Splitter.on(".").omitEmptyStrings().trimResults().splitToList(path);
//		if(relativePathNodeList.size() == 0){
//			throw new IllegalArgumentException();
//		}
//		String currentName = relativePathNodeList.get(0);
//		if((Boolean)engine.eval(String.format("typeof(%s) == 'undefined'", currentName))){
//			//不存在
//			return null;
//		}
//		for(int i=1, last=relativePathNodeList.size()-1; i< last; i++){
//			currentName = currentName + "." + relativePathNodeList.get(i);
//			final boolean hasNotDefined = (Boolean)engine.eval(String.format("typeof(%s) == 'undefined'", currentName));
//			if(hasNotDefined){
//				//不存在
//				return null;
//			}
//		}
//		currentName = currentName + "." + relativePathNodeList.get(relativePathNodeList.size()-1);
//		final boolean hasNotDefined = (Boolean)engine.eval(String.format("typeof(%s) == 'undefined'", currentName));
//		if(hasNotDefined){
//			//不存在
//			return null;
//		}
		try {
			return  (String)engine.eval(String.format("JSON.stringify(%s)", path));
		} catch (final Exception e) {
			throw new IllegalArgumentException(String.format("path=[%s] 找不到对应的属性值", path), e);
		}
	}
	
	public void init(){
		searcher.traverseLeaves((path)->{
			try {
				final String jsContent = Files.toString(path.filePath.toFile(), Charsets.UTF_8);
				recursiveDefine(engine, path.pathNodeList);
				engine.eval(String.format("%s = %s;", path.path.replaceAll("/", "."), jsContent));
			} catch (Exception e) {
				throw new IllegalStateException(String.format("在js引擎中声明js文件=[%s]失败", path.filePath), e);
			}
		});
	}
	
	
	private static void recursiveDefine(final ScriptEngine engine, final List<String> relativePathNodeList) throws Exception{
		if(relativePathNodeList.size() == 0){
			throw new IllegalArgumentException();
		}
		try {
			//TODO 全局变量的表现？？？？
			//应该不会与js文件内部的属性相冲突，如果重复了，应该也是读取的内部的属性，而不是全局变量。
			//不知道还没有没必要给全局变量加一个var
			String currentName = relativePathNodeList.get(0);
			if((Boolean)engine.eval(String.format("typeof(%s) == 'undefined'", currentName))){
				engine.eval(String.format("var %s = {};", currentName));
			}
			for(int i=1, size=relativePathNodeList.size(); i< size; i++){
				currentName = currentName + "." + relativePathNodeList.get(i);
				final boolean hasNotDefined = (Boolean)engine.eval(String.format("typeof(%s) == 'undefined'", currentName));
				if(hasNotDefined){
					engine.eval(String.format("%s = {};", currentName));
				}
			}
		} catch (Exception e) {
			throw new IllegalStateException("定义js文件失败", e);
		}
	}
	
}
