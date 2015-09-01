package my.json;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;

import conf.attribute.ArrayAttribute;
import conf.attribute.Attribute;
import conf.attribute.AttributeChainParser;
import conf.attribute.IAttribute;

public final class JsonGetter {

	
	/**
	 * 目前只能解析下面几种情况的js属性：
	 * 1. '.' [-a-zA-Z0-9_]+
	 * 2. '[' [0-9]+ ']'
	 * @param objFromJsObject
	 * @param path
	 * @return
	 */
	public static Integer getIntegerByPath(final Map<String, Object> map, String path){
		final Object r = getByPath(map, path);
		if(r instanceof Number){
			return Integer.valueOf(r.toString());
		}
		throw new IllegalArgumentException("不是number类型");
	}
	
	
	/**
	 * 目前只能解析下面几种情况的js属性：
	 * 1. '.' [-a-zA-Z0-9_]+
	 * 2. '[' [0-9]+ ']'
	 * @param objFromJsObject
	 * @param path
	 * @return
	 */
	public static String getStringByPath(final Map<String, Object> map, String path){
		final Object r = getByPath(map, path);
		return r != null? r.toString(): null;
	}
	
	
	/**
	 * 目前只能解析下面几种情况的js属性：
	 * 1. '.' [-a-zA-Z0-9_]+
	 * 2. '[' [0-9]+ ']'
	 * @param objFromJsObject
	 * @param path
	 * @return
	 */
	public static Object getByPath(final Map<String, Object> map, String path){
		return getByPath0(map, path);
	}
	
	
	/**
	 * 目前只能解析下面几种情况的js属性：
	 * 1. '.' [-a-zA-Z0-9_]+
	 * 2. '[' [0-9]+ ']'
	 * @param objFromJsObject
	 * @param path
	 * @return
	 */
	public static Integer getIntegerByPath(final List<Object> map, String path){
		final Object r = getByPath(map, path);
		if(r instanceof Number){
			return Integer.valueOf(r.toString());
		}
		throw new IllegalArgumentException("不是number类型");
	}
	
	
	
	/**
	 * 目前只能解析下面几种情况的js属性：
	 * 1. '.' [-a-zA-Z0-9_]+
	 * 2. '[' [0-9]+ ']'
	 * @param objFromJsObject
	 * @param path
	 * @return
	 */
	public static String getStringByPath(final List<Object> map, String path){
		final Object r = getByPath(map, path);
		return r != null? r.toString(): null;
	}
	
	/**
	 * 目前只能解析下面几种情况的js属性：
	 * 1. '.' [-a-zA-Z0-9_]+
	 * 2. '[' [0-9]+ ']'
	 * @param objFromJsObject
	 * @param path
	 * @return
	 */
	public static Object getByPath(final List<Object> map, String path){
		return getByPath0(map, path);
	}
	
	/**
	 * 目前只能解析下面几种情况的js属性：
	 * 1. '.' [-a-zA-Z0-9_]+
	 * 2. '[' [0-9]+ ']'
	 * @param objFromJsObject
	 * @param path
	 * @return
	 */
	static Object getByPath0(final Object map, String path){
		if(map == null || Strings.isNullOrEmpty(path)){
			throw new IllegalArgumentException();
		}
		//js对象字面量的第一个属性引用，一定不是数组，一定是普通的属性
		path = "." + path.trim();
		final List<IAttribute> attList = AttributeChainParser.parse(path);
		Object currentObj = map;
		for(final IAttribute attribute: attList){
			if(currentObj == null){
				return null;
			}
			if(attribute instanceof Attribute){
				final Attribute att = (Attribute)attribute;
				if(currentObj instanceof Map){
					currentObj = ((Map)currentObj).get(att.name);
					continue;
				}
				throw new IllegalArgumentException("xpath应该是Map类型，但按照参数查找到的却不是");
			}else if(attribute instanceof ArrayAttribute){
				final ArrayAttribute att = (ArrayAttribute)attribute;
				if(currentObj instanceof List){
					final List l = (List)currentObj;
					if(l.size() > att.index){
						currentObj = l.get(att.index);
						continue;
					}else{
						throw new IndexOutOfBoundsException();
					}
				}
				throw new IllegalArgumentException("xpath应该是List类型，但按照参数查找到的却不是");
			}else{
				throw new IllegalStateException();//unreachable
			}
		}
		return currentObj;
	}
	
	
}
