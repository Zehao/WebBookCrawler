package org.hnote.Page;


public class Page17k extends Page{

	private int currentPage = 1;
	private static final int lastPage = 12150;
	
	/**
	 * 必须在构造函数里面完成设置 host，itemPattern，urls
	 */
	public Page17k(){
		host = "17k.com";
		itemPattern = "<td\\s+class=\"td2\">\\[.*?>(.*?)<[\\s\\S]*?href=\".*?/book/(\\d+)[.]html.*?>(.*?)<";
		urls = "http://all.17k.com/all/0_0__0__{}.html";
	}
	
	/**
	 * 对于具体页面url的实现，保证返回的是下一个要处理的url
	 */
	@Override
	synchronized public String nextURL() {
		if(currentPage == lastPage)
			return null;
		return urls.replace("{}", Integer.toString(currentPage++));
	}

}
