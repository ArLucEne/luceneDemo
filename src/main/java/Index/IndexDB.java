package Index;

import Util.sqlUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;


import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class IndexDB {
    private static final String index_path="D:\\Workplace\\lucenedemo\\DBIndex";       //生成的索引文件路径
    private static IndexWriter writer=null;
    private static Analyzer analyzer=null;
    private static Connection conn=sqlUtil.connect();            //全局数据库连接


    public static void createIndex(){
        try {
            Directory dir= FSDirectory.open(new File(index_path).toPath());
            analyzer=new IKAnalyzer(true);
            IndexWriterConfig config=new IndexWriterConfig(analyzer);
            writer=new IndexWriter(dir,config);
            writer.deleteAll();

            Statement stmt = conn.createStatement();
            String sql="select * from menu";
            ResultSet rs=stmt.executeQuery(sql);

            //rs即从数据库中得到的所有信息
            while(rs.next()){
                Document doc=new Document();
                String name=rs.getString("name");
                String material=rs.getString("material");
                String steps=rs.getString("steps");
                String url=rs.getString("url");
                String picpath=rs.getString("picpath");
                String kouwei=rs.getString("kouwei");

                //System.out.println(kouwei);

                //<----!!!!!!!!!!!!!!!!---->
                //如何给不同域修改权值！！！
                //存储四个Field
                if(picpath==null)
                    continue;
                TextField nameField=new TextField("name",name,Field.Store.YES);
                Field materiField=new TextField("material",material,Field.Store.YES);
                TextField stepsFiled=new TextField("steps",steps,Field.Store.YES);
                TextField urlField=new TextField("url",url,TextField.Store.YES);
                TextField picField=new TextField("picpath",picpath,TextField.Store.YES);

                TextField kouweiField=new TextField("kouwei",kouwei,TextField.Store.YES);


                doc.add(nameField);
                doc.add(materiField);
                doc.add(stepsFiled);
                doc.add(urlField);
                doc.add(picField);
                doc.add(kouweiField);

                //写进索引
                writer.addDocument(doc);
                System.out.println("index the menu :"+name+"::::"+url);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
/*
    public static void main(String[] args){
        createIndex();
    }*/

}
