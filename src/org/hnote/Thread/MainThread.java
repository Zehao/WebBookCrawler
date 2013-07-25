package org.hnote.Thread;

import org.hnote.Page.Page;
import org.hnote.Page.Page17k;


/**
 * ���ץȡ�̣߳�һ��д�̡߳�
 * ץȡ�߳�ͨ�������page�������ѭ����url��ȡ�����book
 * д�̶߳�ʱ�Ľ�page�����е�books����ȫ��д�����ݿⲢ���books
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
