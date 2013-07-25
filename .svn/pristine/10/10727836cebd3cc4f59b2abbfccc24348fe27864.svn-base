package org.hnote.Thread;

import org.hnote.Page.Page;
import org.hnote.Page.Page17k;


/**
 * 多个抓取线程，一个写线程。
 * 抓取线程通过传入的page对象进行循环的url爬取并获得book
 * 写线程定时的将page对象中的books属性全部写入数据库并清空books
 * @author Zehao Jin
 *
 */
public class MainThread{

	public static void main(String[] args){
		Page page = new Page17k();
		for(int i = 1 ; i <= 5; i++){
			Thread fetcher = new Thread(new FetcherThread(page),"Fetcher" + i);
			fetcher.start();
		}
		
		Thread writer = new Thread(new WriterThread(page),"Writer");
		writer.start();
		
	}
	
}
