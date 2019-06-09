package Swing;

import Util.DownPic;
import Util.Menu;
import Util.core;
import Util.sqlUtil;
import org.apache.lucene.document.Document;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class collection {
    public JFrame jFrame;
    private JPanel jPanel;
    private JButton jButton;
    private JLabel jLabel;
    private JList list;
    private DefaultListModel listModel;

    private static Connection conn= sqlUtil.connect();


    public void go(){
        jFrame=new JFrame("MyCollection");
        jFrame.setVisible(true);
        jFrame.setLocation(500,300);
        jFrame.setSize(460,700);


        Toolkit tk=Toolkit.getDefaultToolkit() ;
        Image image=tk.createImage("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\collect.png");  //图标
        jFrame.setIconImage(image);

        listModel=new DefaultListModel();
        list=new JList(listModel);
        list.setFont(new Font("楷体",Font.BOLD, 20));
        list.setBounds(20, 60, 400,480);
        list.setVisible(true);

        jPanel=new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\9.jpg");
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        jFrame.setContentPane(jPanel);
        jPanel.setLayout(null);
        // jPanel.setPreferredSize(new Dimension(300,200));
        jPanel.setSize(440,600);

        jButton=new JButton("OK");
        jButton.setBounds(280,600,100,30);
        jButton.setFont(new Font("楷体",Font.BOLD, 20));
        jButton.setBackground(Color.WHITE);
        jLabel=new JLabel();
        jLabel.setBounds(180,15,100,30);
        jLabel.setText("我的收藏");
        //jLabel.setFont(new Font("宋体",Font.BOLD, 20));
        jLabel.setFont(new Font("宋体",Font.BOLD,20));

        jPanel.add(jButton);
        jPanel.add(jLabel);
        jPanel.add(list);
        JScrollPane jScrollPane2=new JScrollPane(list);
        jScrollPane2.setBounds(20, 60, 400,480);
        jPanel.add(jScrollPane2);

        final ArrayList<Menu> menus=ReadColl();
        for(Menu m:menus){
            listModel.addElement(m.getName());
        }
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if(!list.getValueIsAdjusting()&&!list.isSelectionEmpty()){   //设置只有释放鼠标时才触发
                    System.out.println(list.getSelectedValue());


                    Menu m= menus.get(list.getSelectedIndex());
                    DownPic.showPic(m.getPicPath());
                }
            }
        });

        jFrame.setVisible(true);
        //jFrame.setLocation(300,300);
        //jFrame.setSize(300,200);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //System.out.println("_________");
                jFrame.setVisible(false);
                jFrame.dispose();
            }
        });

    }

    private ArrayList ReadColl() {

        ArrayList<Menu> menus=new ArrayList<>();

        //rs即从数据库中得到的所有信息
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from menucollection";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String name = rs.getString("name");
                String material = rs.getString("material");
                String steps = rs.getString("steps");
                String url = rs.getString("url");
                String picpath = rs.getString("picpath");
                String kouwei=rs.getString("kouwei");

                Menu m=new Menu();
                m.setPicPath(picpath);
                m.setUrl(url);
                m.setName(name);
                m.setSteps(steps);
                m.setMaterial(material);
                m.setKouwei(kouwei);

                menus.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }
/*
    public static void main(String[] args){
        collection collection=new collection();
        collection.go();

    }*/

}
