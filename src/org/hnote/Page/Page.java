package org.hnote.Page;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 页面抽象类
 * @author Zehao Jin
 *
 */
public abstract class Page{
	
	//获得bid，category，bname的pattern
	protected String itemPattern;
	
	//此网站的url规则，由具体的子类实现下面的nextURL方法返回下个一url
	protected String urls;
	
	//如17k.com，用来写入数据库的host字段
	protected String host;
	
	//page content
	protected String content;
	
	//书，为<bid,category +'#' + bname>
	private static Map<String,String> books = new HashMap<String,String>();
	

	public Page() {
	}
	
	public abstract String nextURL();
	
	public String getHost(){
		return host;
	}
	
	public static String decode(String str){  
        String[] tmp = str.split("&#|;&#|;");
        StringBuffer sb = new StringBuffer("");  
        for (int i=0; i<tmp.length; i++ ){  
            if (tmp[i].matches("\\d{5}")){  
                sb.append((char)Integer.parseInt(tmp[i]));  
            } else {  
                sb.append(tmp[i]);  
            }  
        }  
        return sb.toString();  
    }
	
	/**
	 * 设置页面content
	 * @param is 输入流
	 */
	public void setContent(InputStream is){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int n = -1;
		try {
			while((n = is.read()) != -1){
				baos.write(n);
			}
			content = baos.toString("utf8");
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * 解析页面，获得bid等。
	 */
	public void parsePage(){
		Pattern p = Pattern.compile(itemPattern);
		Matcher m = p.matcher(content);
		Map<String,String> b = new HashMap<String,String>();
		while(m.find()){
			String category = m.group(1);
			if(host.equals("17k.com"))
				category = decode(category);
			String id = m.group(2);
			String name = m.group(3);
			b.put(id, category + '#' + name);
		}
		addBook(b);
	}
	
	protected  void addBook(Map<String,String> book){
		/*
		 * 必须采用锁保证books安全
		 */
		synchronized(books){
			books.putAll(book);
		}
		
	}
	
	public Map<String,String> getAndClearBooks(){
		HashMap<String,String> map;
		synchronized(books){
			map = new HashMap<String,String>(books);
			System.out.println(Thread.currentThread().getName() + ": ---------------Get book size :" + map.size() + "------------------");
			books.clear();
		}
		return  map;
	}
	

}
