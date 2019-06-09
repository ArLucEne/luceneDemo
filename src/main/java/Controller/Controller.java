package Controller;

import Util.core;

import Index.IndexDB;
import Search.Searcher;
import crawler.magic4meishichina;

import java.util.ArrayList;

/**
 * 整理所需要的主要函数
 */
public class Controller {
    /**
     * 爬取心食谱网站
     */
    public static void downmenus(){
        System.out.println("正在爬取菜谱………………");
       // webmagic.crawler();
        magic4meishichina.crawler2M();
       // magic4xiangha.crawler();
        System.out.println("爬取完成，正在创建索引……………………");
        IndexDB.createIndex();
        System.out.println("索引创建完成");
    }

    //菜名搜索
   public static ArrayList searchName(String name){
        return Searcher.searchName(name);
   }

    //简单搜索
    public static ArrayList search(String materials){
        return  Searcher.search(materials);
    }

    /**
     * 方案搜索
     * 给定多个市场，返回五个方案
     * 每个方案都包含利用所给食材的不同组合
     * @param materials
     * @return
     */
    public static ArrayList getPlans(String materials){
        return core.getPlans(materials);
    }
}
