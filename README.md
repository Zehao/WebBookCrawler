WebBookCrawler
==============

针对国内的大部分小说网站，如起点中文网，3G书城，17k等，爬取其书库页面（如http://all.qidian.com/Default.aspx ）。获得书的id，书名等信息。

程序采用多个抓取线程FetcherThread获取页面信息，通过正则表达式获得每本书的每项数据，一个写线程WriterThread完成入库。

程序中的Page类是一个抽象类，要具体针对某个特定的网站，只需要继承并实现Page的几个方法即可，关键是正则表达式。

其他详见代码内说明。
