package org.hnote.Thread;

import org.hnote.Page.Page;
import org.hnote.mysql.MysqlConn;

/**
 * 写线程，定时将page中的books写入数据库，并清空books
 * @author Zehao Jin
 *
 */
public class WriterThread implements Runnable{
	
	private final long sleepTime = 15*1000; //15s

	MysqlConn conn = new MysqlConn();
	Page page;
	
	public WriterThread(Page p) {
		page = p;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Started.");
		while(true){
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			conn.insertBatch(page.getHost(), page.getAndClearBooks());
		}
		
		
	}
}
