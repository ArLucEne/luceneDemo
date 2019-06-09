package Swing;


import Util.Menu;
import Util.sqlUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * 每日推荐窗口
 */
public class Recommand extends JFrame {


    private static JButton button;
    private static JLabel jLabel1;
    private static JLabel jLabel2;
    private static JLabel jLabel3;

    private static JLabel jLabea;
    private static JLabel jLabelb;
    private static JLabel jLabelc;
    private static JFrame jFrame;

    private static Connection conn= sqlUtil.connect();
    static final ArrayList<Menu> menus=recomand();
    static int i=2;
    public static void go(){

        jFrame=new JFrame();
        jFrame.setTitle("今日推荐");
        jFrame.setSize(930,550);


        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();


       Menu a=menus.get(i);
       Menu b=menus.get(i-1);
       Menu c=menus.get(i-2);

        button = new JButton();
        button.setBackground(Color.WHITE);
        button.setBounds(435,430,52,52);

        ImageIcon icon = new ImageIcon("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\remenus.png");  //更新的小图标
        button.setIcon(icon);;
        button.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            /**
             * 读取menus中的菜单，每三个一组
             * 更新到Label中
             * @param evt
             */
            private void jButton1ActionPerformed(ActionEvent evt) {
                i=(i+3)%menus.size();
                Menu a=menus.get(i);
                Menu b=menus.get(i-1);
                Menu c=menus.get(i-2);
                ImageIcon icon1=new ImageIcon(a.getPicPath());
                ImageIcon icon2=new ImageIcon(b.getPicPath());
                ImageIcon icon3=new ImageIcon(c.getPicPath());
                jLabea.setIcon(icon1);
                jLabelb.setIcon(icon2);
                jLabelc.setIcon(icon3);

                jLabel1.setText(a.getName());
                jLabel2.setText(b.getName());
                jLabel3.setText(c.getName());

            }

        }
        );

        jLabea=new JLabel();
        jLabelb=new JLabel();
        jLabelc=new JLabel();

        ImageIcon icon1=new ImageIcon(a.getPicPath());
        ImageIcon icon2=new ImageIcon(b.getPicPath());
        ImageIcon icon3=new ImageIcon(c.getPicPath());

        jLabea.setIcon(icon1);
        jLabelb.setIcon(icon2);
        jLabelc.setIcon(icon3);


        jFrame.getContentPane().setLayout(null);
        jFrame.getContentPane().add(button);
        jFrame.getContentPane().add(jLabel1);
        jFrame.getContentPane().add(jLabel2);
        jFrame.getContentPane().add(jLabel3);
        jFrame.getContentPane().add(jLabea);
        jFrame.getContentPane().add(jLabelb);
        jFrame.getContentPane().add(jLabelc);

        jFrame.getContentPane().setBackground(Color.WHITE);

        Toolkit tk=Toolkit.getDefaultToolkit() ;
        Image image=tk.createImage("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\recommend.png");  //推荐图标
        jFrame.setIconImage(image);

        jLabea.setBounds(1, 1, 300, 350);
        jLabelb.setBounds(305, 1, 300, 350);
        jLabelc.setBounds(610, 1, 300, 350);



        jLabel1.setText(a.getName());
        jLabel1.setFont(new Font("宋体",Font.BOLD, 18));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel2.setText(b.getName());
        jLabel2.setFont(new Font("宋体",Font.BOLD, 18));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel3.setText(c.getName());
        jLabel3.setFont(new Font("宋体",Font.BOLD, 18));
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel1.setBounds(1,400,300,23);
        jLabel2.setBounds(300,400,300,23);
        jLabel3.setBounds(600,400,300,23);
        jFrame.setVisible(true);
    }

    /**
     * 从数据库中读取15个随机菜单
     * @return
     */
    private static ArrayList<Menu> recomand(){
        ArrayList<Menu> menus=new ArrayList<>();
        //rs即从数据库中得到的所有信息
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from menu";
            ResultSet rs = stmt.executeQuery(sql);


            Random random = new Random();
            ArrayList num =new ArrayList();

            //生成200内15个随机数
            int i;
            for(i=0;i<15;i++){
                int n=random.nextInt(500);
                num.add(n);
            }


            i=0;


            //将指定菜单加到menus队列中
            while (rs.next()) {

                String name = rs.getString("name");
                String material = rs.getString("material");
                String steps = rs.getString("steps");
                String url = rs.getString("url");
                String picpath = rs.getString("picpath");

                if(picpath!=null&&name!=null&&num.contains(i)) {
                    Menu m = new Menu();
                    m.setPicPath(picpath);
                    m.setUrl(url);
                    m.setName(name);
                    m.setSteps(steps);
                    m.setMaterial(material);

                    menus.add(m);

                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;

    }
    /*
    public static void main(String args[]) {

        Recommand r = new Recommand();
        r.go();
    }*/

}
