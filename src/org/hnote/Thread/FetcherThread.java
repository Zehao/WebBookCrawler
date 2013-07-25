package org.hnote.Thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.hnote.Page.Page;

/**
 * 抓取线程
 * 
 * @author Zehao Jin
 *
 */
public class FetcherThread implements Runnable{

	private Page page;

	public FetcherThread(Page p ){
		page = p;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Started.");
		String u;
		URL url;
		InputStream is = null ;
		HttpURLConnection conn = null;
		while(true){
			try{
				/**
				 * 连接，获取流
				 */
				u = page.nextURL();
				if(u == null)
					return;
				System.out.println(Thread.currentThread().getName() + ":" + u);////////////////////////////////
				url = new URL(u);
				conn = (HttpURLConnection)url.openConnection();
				//set property
				conn.setRequestProperty("accept", "*/*");  
	            conn.setRequestProperty("connection", "Keep-Alive");  
				conn.connect();
				Thread.sleep(50);
				is = conn.getInputStream();
				
				page.setContent(is);
				
				//开始解析
				page.parsePage();
				
			} catch(IOException e){ 
				System.out.println(Thread.currentThread().getName() + e.getMessage());
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getName()  + e.getMessage());
				e.printStackTrace();
			}finally{
				try{ is.close();conn.disconnect();}catch(Exception ex){}
			}

			
		}

	}

}
