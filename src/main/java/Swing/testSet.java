package Swing;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Swing.test;
import Util.Menu;
import Util.User;
import Util.sqlUtil;
import Util.userDao;

/**
 * 设置界面
 * 添加用户
 * 更新菜单
 */
public class testSet extends JFrame{

    private JPanel jPane1;
    private JButton jButton1; //更新菜谱
    private JButton jButton2; //添加成员

    private JScrollPane jScrollPane1;
    private JList list;
    private DefaultListModel listModel;
    public  static JDialog downlode;
    private static JDialog downover;



    private static JFrame jFrame;
    private static JLabel jLabel;
    private static JLabel jLabel2;
    private static JLabel jLabel3;
    private static JLabel jLabel4;
    private static  JLabel jLabel5;
    private static JLabel jLabel6;

    private static JTextField jTextField;
    private static JTextField jTextField2;
    private static JTextField jTextField3;
    private static JTextField jTextField4;
    private static JTextField jTextField5;
    private static JTextField jTextField6;

    private static JButton jButton;
  //  public static String username;

   public ArrayList<User> users=ReadUser();     //全局users存储数据库中的用户列表，方便在列表中选中对应Index
    private static Connection conn= sqlUtil.connect();

    /**
     * 设置界面
     */
    public void  go(){

        super.setTitle("设置");
        super.setSize(600,500);
        jButton1 = new JButton();
        jButton2 = new JButton();

        jPane1 =new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\setBack.jpg");
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        jScrollPane1 = new JScrollPane();
        listModel=new DefaultListModel();


        this.setContentPane(jPane1);
        this.getContentPane().setLayout(null);
        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton2);
        this.getContentPane().add(jScrollPane1);
        this.getContentPane().setBackground(Color.WHITE);
        Toolkit tk=Toolkit.getDefaultToolkit() ;
        Image image=tk.createImage("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\Set.png");  //图标
        this.setIconImage(image);

        downover=new JDialog(this,"Successful",true);
        downlode=new JDialog(this,"Downloding...",true);
        jButton1.setText("更新菜谱");
        jButton1.setFont(new Font("宋体",Font.BOLD, 20));
        jButton1.addActionListener(new java.awt.event.ActionListener()
         {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonActionPerformed(evt);
             }

             /**
              * 双线程
              * 前台提示，
              * 后台下载并检索数据库菜谱
              * @param evt
              */
             private void jButtonActionPerformed(ActionEvent evt) {
                // downlode.getContentPane().add(new JLabel("正在下载菜谱……"));
                // downlode.setSize(300,240);
                 DiologThread Dlog=new DiologThread();
                 Dlog.start();

                 downover.getContentPane().add(new JLabel("下载并创建索引完成！"));
                 downover.setSize(300,240);
                 Controller.downmenus();
                 downlode.setVisible(false);
                 downover.setVisible(true);
             }

             /**
              * 另一种多线程方法，但下载结束后不显示完成提示框
              */
/*
             private void jButtonActionPerformed(ActionEvent evt) {
                 Thread thread=new Thread(){
                     public void run(){
                         Controller.downmenus();
                         this.interrupt();
                     }
                 };
                 Dialog dialog=new Dialog();
                 dialog.go("正在更新食谱.....");
                 thread.start();
                 if(!thread.isAlive()){

                     dialog.go("更新完成");
                 }

             }*/
         });
        jButton1.setBounds(70, 65, 120,28);
        jButton1.setBackground(Color.WHITE);

        jButton2.setText("添加成员");
        jButton2.setFont(new Font("宋体",Font.BOLD, 20));
        jButton2.addActionListener(new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }

        private void jButton3ActionPerformed(ActionEvent evt) {
            go2();
        }
    });
        jButton2.setBounds(70, 125, 120,28);
        jButton2.setBackground(Color.WHITE);

        list=new JList(listModel);
        list.setFont(new Font("微软雅黑",Font.BOLD, 22));
        list.setBounds(80, 230, 465,500);
        list.setVisible(true);
        JScrollPane jScrollPane1=new JScrollPane(list);
        jScrollPane1.setBounds(270,60, 290, 330);

        list.addListSelectionListener(new ListSelectionListener() {
            /**
             * 选中列表中元素显示用户详细信息
             * @param arg0
             */
            public void valueChanged(ListSelectionEvent arg0) {
                if(!list.getValueIsAdjusting()&&!list.isSelectionEmpty()){   //设置只有释放鼠标时才触发
                    User u=users.get(list.getSelectedIndex());
                    test t=new test();
                    t.go(u);
                }
            }
        });


        for(User u:users){
            listModel.addElement(u.getName());
        }



        this.getContentPane().setLayout(null);
        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton2);
        this.getContentPane().add(jScrollPane1);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);

    }


    /**
     * 添加用户信息窗口
     */
    public  void go2(){

        jFrame=new JFrame("添加信息");
        jFrame.setSize(500,800);
        jFrame.setVisible(true);

        //jFrame.getContentPane().setLayout(null);

        JPanel jPane = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\6.jpg");
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        ;
        jFrame.add(jPane);
        jPane.setLayout(null);
        jPane.setBounds(0,0,500,800);

        jLabel=new JLabel("用户名:");
        jLabel2=new JLabel("Phone:");
        jLabel3=new JLabel("拿手菜：");
        jLabel4=new JLabel("喜爱口味：");
        jLabel5=new JLabel("喜爱菜品：");
        jLabel6=new JLabel("掌厨时间：");

        jLabel.setFont(new Font("宋体",Font.BOLD,20));
        jLabel2.setFont(new Font("宋体",Font.BOLD,20));
        jLabel3.setFont(new Font("宋体",Font.BOLD,20));
        jLabel4.setFont(new Font("宋体",Font.BOLD,20));
        jLabel5.setFont(new Font("宋体",Font.BOLD,20));
        jLabel6.setFont(new Font("宋体",Font.BOLD,20));

        jTextField=new JTextField();
        jTextField2=new JTextField();
        jTextField3=new JTextField();
        jTextField4=new JTextField();
        jTextField5=new JTextField();
        jTextField6=new JTextField();

        jPane.add(jLabel);
        jPane.add(jLabel2);
        jPane.add(jLabel3);
        jPane.add(jLabel4);
        jPane.add(jLabel5);
        jPane.add(jLabel6);
        jPane.add(jTextField);
        jPane.add(jTextField2);
        jPane.add(jTextField3);
        jPane.add(jTextField4);
        jPane.add(jTextField5);
        jPane.add(jTextField6);

        jButton=new JButton("OK");
        jButton.setFont(new Font("宋体",Font.BOLD,20));
        jPane.add(jButton);

        jLabel.setBounds(30,30,120,30);
        jLabel2.setBounds(30,130,120,30);
        jLabel3.setBounds(30,230,120,30);
        jLabel4.setBounds(30,330,120,30);
        jLabel5.setBounds(30,430,120,30);
        jLabel6.setBounds(30,530,120,30);

        jTextField.setBounds(180,30,200,40);
        jTextField2.setBounds(180,130,200,40);
        jTextField3.setBounds(180,230,200,40);
        jTextField4.setBounds(180,330,200,40);
        jTextField5.setBounds(180,430,200,40);
        jTextField6.setBounds(180,530,200,40);

        jButton.setBounds(200,610,120,40);
        jButton.setBackground(Color.WHITE);
        jButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }

            /**
             * 读取TextField的文本存储user对象，并写入数据库中
             * @param evt
             */
            private void jButtonActionPerformed(ActionEvent evt) {
                String name =jTextField.getText();
                String phone=jTextField2.getText();
                String nashoucai=jTextField3.getText();
                String kouwei=jTextField4.getText();
                String favofood=jTextField5.getText();
                String time=jTextField6.getText();

                User user=new User();
                user.setTime(time);
                user.setNashoucai(nashoucai);
                user.setName(name);
                user.setKouwei(kouwei);
                user.setFavofood(favofood);
                user.setPhone(phone);

                userDao.add(user,conn);

                listModel.addElement(name);
                users.add(user);

                jFrame.setVisible(false);

                jFrame.dispose();

            }
        });





    }


    /**
     * 读取数据库信息返回到users队列
     * @return
     */
    private ArrayList ReadUser() {

        ArrayList<User> users=new ArrayList<>();

        //rs即从数据库中得到的所有信息
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from user";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String nashoucai = rs.getString("nashoucai");
                String kouwei = rs.getString("kouwei");
                String favofood = rs.getString("favofood");
                String time=rs.getString("time");

                User user=new User();
                user.setName(name);
                user.setPhone(phone);
                user.setNashoucai(nashoucai);
                user.setKouwei(kouwei);
                user.setFavofood(favofood);
                user.setTime(time);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
/*
    public static void main(String args[]) {

        testSet s  = new testSet();
        //s.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        s.go();
    }
    */

}