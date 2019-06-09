package Util;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DownPic {
    private static final String saveImgPath="D:\\Workplace\\lucenedemo\\src\\main\\resources\\menuPic";
    public static  String downPic(Elements elements) throws IOException{
        String src=elements.attr("src");//获取img中的src路径
        //获取后缀名
        String imageName = src.substring(src.lastIndexOf("/") + 1,src.length());
        //连接url
        URL url = new URL(src);
        URLConnection uri=url.openConnection();
        //获取数据流
        InputStream is=uri.getInputStream();
        //写入数据流
        OutputStream os = new FileOutputStream(new File(saveImgPath, imageName));
        byte[] buf = new byte[1024];
        int l=0;
        while((l=is.read(buf))!=-1){
            os.write(buf, 0, l);
        }
        return saveImgPath+"\\"+imageName;

    }

    public static void showPic(String picpath){

            Image image = null;
            try {
                // Read from a file
                if(picpath==null){
                    picpath="D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\null.jpg";
                }
                File sourceimage = new File(picpath);  //source.gif图片要与HelloJava.java同在一目录下
                image = ImageIO.read(sourceimage);
/*
            // Read from an input stream
            InputStream is = new BufferedInputStream(
                    new FileInputStream("mid.jpg"));  //mid.jpg图片要与HelloJava.java同在一目录下
            image = ImageIO.read(is);
*/

            } catch (IOException e) {
                e.printStackTrace();
            }

            // Use a label to display the image
            JFrame frame = new JFrame();
            Toolkit tk=Toolkit.getDefaultToolkit() ;
             Image im=tk.createImage("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\collect.png");  //图标
             frame.setIconImage(im);
            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            //关闭窗口--退出调试
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
