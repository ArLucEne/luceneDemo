package Search;

import Util.Menu;
import Util.sqlUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.apache.lucene.document.Document;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public  class Searcher {
    static Analyzer analyzer=new IKAnalyzer();
    static  String index_path="D:\\Workplace\\lucenedemo\\DBIndex";



    public static ArrayList search(String key){
        ArrayList<Menu> menus=new ArrayList<Menu>();
        try{
            Directory indexDir=FSDirectory.open(new File(index_path).toPath());
            DirectoryReader reader=DirectoryReader.open(indexDir);
            IndexSearcher searcher=new IndexSearcher(reader);

            String[] fields={"name","material","steps"};
            BooleanClause.Occur[] clauses={BooleanClause.Occur.SHOULD,BooleanClause.Occur.MUST,BooleanClause.Occur.SHOULD};
            Query query= MultiFieldQueryParser.parse(key,fields,clauses,analyzer);



            TopDocs topDocs=searcher.search(query,20,new Sort());

            ScoreDoc[] docs=topDocs.scoreDocs;
            System.out.println("匹配文档数目："+docs.length);

            for(ScoreDoc doc:docs){
                Document document=searcher.doc(doc.doc);
                float socore=doc.score;
                //String result=highlighter.getBestFragment(analyzer, "material", document.get("material"));
                Menu menu=new Menu();
                menu.setName(document.get("name"));
                menu.setMaterial(document.get("material"));
                menu.setSteps(document.get("steps"));
                menu.setUrl(document.get("url"));
                menu.setPicPath(document.get("picpath"));
                menu.setKouwei(document.get("kouwei"));
               // System.out.println(document.get("picpath"));
                menu.setScore(socore);
                menus.add(menu);

            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e2){
            e2.printStackTrace();
        }
        return menus;
    }

    public static ArrayList<Menu> searchName(String name){
        ArrayList<Menu> menus=new ArrayList<Menu>();
        try{
            Directory indexDir=FSDirectory.open(new File(index_path).toPath());
            DirectoryReader reader=DirectoryReader.open(indexDir);
            IndexSearcher searcher=new IndexSearcher(reader);

            String[] fields={"name","material"};
            BooleanClause.Occur[] clauses={BooleanClause.Occur.MUST,BooleanClause.Occur.SHOULD};
            Query query= MultiFieldQueryParser.parse(name,fields,clauses,analyzer);

            TopDocs topDocs=searcher.search(query,20,new Sort());

            ScoreDoc[] docs=topDocs.scoreDocs;
            System.out.println("匹配文档数目："+docs.length);

            for(ScoreDoc doc:docs){
                Document document=searcher.doc(doc.doc);
                float socore=doc.score;
                //String result=highlighter.getBestFragment(analyzer, "material", document.get("material"));
                Menu menu=new Menu();
                menu.setName(document.get("name"));
                menu.setMaterial(document.get("material"));
                menu.setSteps(document.get("steps"));
                menu.setUrl(document.get("url"));
                menu.setPicPath(document.get("picpath"));
                menu.setKouwei(document.get("kouwei"));
                // System.out.println(document.get("picpath"));
                menu.setScore(socore);
                menus.add(menu);

            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e2){
            e2.printStackTrace();
        }
        return menus;

    }
/*
//土豆 白菜 茄子 香菇 排骨
    public static void main(String[] args){
       ArrayList<Menu> menus=search("鸡爪");
       for(Menu m:menus){
           System.out.println(m.toString());
       }
    }
    */

}
