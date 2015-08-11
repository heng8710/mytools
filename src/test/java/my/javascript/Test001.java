package my.javascript;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import com.google.common.collect.Iterators;

public class Test001 {

	public static void main(String[] args) throws URISyntaxException {
		URI u0 = new File("D:/proxy/ccproxy").toURI();
		
		
		URI u = new File("D:/proxy/ccproxy/CCProxy/web/../..").toURI();
		Path p = Paths.get(u).normalize().toAbsolutePath().relativize(Paths.get(u0)).normalize();
		System.out.println(p);
		
		System.out.println(Paths.get(u).normalize().toAbsolutePath().startsWith(Paths.get(u0)));
		
		System.out.println("--");
		System.out.println(Paths.get(u).normalize().toAbsolutePath());
		System.out.println(Iterators.toString(Paths.get(u).normalize().toAbsolutePath().iterator()));
		Object it = Paths.get(u).normalize().toAbsolutePath().iterator();
		System.out.println("==");
		Iterator i = Paths.get(u).normalize().toAbsolutePath().iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
		
	}

}
