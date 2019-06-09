package crawler;

import Util.DownPic;
import Util.Menu;
import Util.menuDao;
import Util.sqlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.processor.PageProcessor;


import java.io.IOException;
import java.sql.Connection;

public class magic4meishichina implements PageProcessor {
    // 抓取网站的相关配置，包括编码、抓取间隔
    private Site site = Site.me().setRetryTimes(3).setSleepTime(10);

    //保存文件时存储的待检索文件路径
    //static String path="D:\\Workplace\\lucenedemo\\index";

    //记录爬取页面数量
    private static int num=0;

    //数据库连接
    private Connection conn= sqlUtil.connect();

    //创建全局爬虫对象
    private static Spider spider=new Spider(new magic4meishichina());

    /**
     * 重写process
     * 自定义爬取策略
     * @param page
     */
    @Override
    public void process(Page page) {

        //将符合正则表达式的链接加入待爬取连接池
        page.addTargetRequests(page.getHtml().links().regex("http://www.meishij.net/zuofa/[^\\s#]\\S{3,}").all());
        page.addTargetRequests(page.getHtml().links().regex("(http://www.meishij.net/chufang/diy/[^\\s&]\\w{3,})").all());

        //使用Jsoup筛选所需内容
        Document doc = Jsoup.parse(page.getHtml().toString());
        String url = page.getUrl().toString();
        String name = doc.select("h1.title").text();
        String materils = doc.select("div.materials_box").select("div").text();
        String steps = doc.select("div.edit").select("p").text();
        Elements elements = doc.select("div.cp_headerimg_w").select("img[src~=(?i)\\.(png|jpe?g)]");
        String kouwei=doc.select("li.w127.bb0").text();

        String PicPath = null;
        try {
            PicPath = DownPic.downPic(elements);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //《---！！！！！！！！！！！！！！----》
        //此方法一直不好用
        //对于没有爬取到所需内容的页面要跳过
        if (materils == null || name == null || materils == "") {
            System.out.println("skip this page:" + page.getUrl());
            page.setSkip(true);
            page.setSkip(false);

        }
        //将提取的内容set到Menu对象中存储
        if(materils!=null&&materils!="") {
            Menu menu = new Menu();
            menu.setUrl(url);
            menu.setName(name);
            menu.setMaterial(materils);
            menu.setSteps(steps);
            menu.setPicPath(PicPath);
            menu.setKouwei(kouwei);
            // menu.setDate(new Date(new java.util.Date().getTime()));

            //存入到数据库
            new menuDao().add(menu, conn);

            //System.out.println(menu.toString());


            num++;
        }
        //只用于测试，只爬取50各页面
        //正式使用需删除
        if (num > 50) {
            System.out.println("stopping crawlder");
            spider.stop();
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void crawler2M(){
        //spider.test("http://www.meishij.net/zuofa/xiangsufanshamantoutiao.html");
      long startTime, endTime;

        //爬取“美食杰”网站
        String urltemplete="http://www.meishij.net/zuofa/hongshaopaiguhuluobo.html";
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        spider.addUrl(urltemplete).thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒,共爬取"+num+"页面");
    }
   /* public static void main(String[] args){
        crawler2M();
    }*/


}
