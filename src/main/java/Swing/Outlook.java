package Swing;


import Controller.Controller;
import Util.*;
import Util.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class Outlook extends JFrame {
    private Connection conn= sqlUtil.connect();

    private JButton jButton1; //更新菜谱
    private JButton jButton2; //搜索
    private JButton jButton3; //我的收藏
    private JButton jButton4;  //每日推荐
    private JButton jButton5;  //收藏 圆形
    private JButton jButton6;  //计时
    private JButton jButton7;  // 发送手机

    private JComboBox jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;

    private JScrollPane jScrollPane1;  //右侧的菜谱详情是1 左侧list 是2

    private JTextField jTextField1;
    private JTextArea jTextPane2;


    private JTextPane jTextPane1;  //详情




    private JPanel jPane1; //左
    private JPanel jPane2; //右
    public static JDialog downlode;
    private JDialog downover;
    private JList list;
    private DefaultListModel listModel;
    private JRadioButton namesearch;
    private JRadioButton easysearch;
    private JRadioButton plansearch;
    private ButtonGroup group;


    private ArrayList<Menu> menus=new ArrayList();

    //界面
    public  Outlook() {
        //设置标题
        super.setTitle("中华小当家");
        super.setSize(1250,860);
        // 标签
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3= new JButton();
        jButton4= new JButton();
        jButton5= new JButton();
        jButton6= new JButton();
        jButton7= new JButton();

        //菜谱详情
        // jLabel2 = new JLabel();
        jScrollPane1 = new JScrollPane();

        //panel
        jTextPane1 = new JTextPane();
        // jTextPane2 = new JTextPane();
        // jTextPane3 = new JTextPane();
        //食材名称
        jLabel3 = new JLabel();
        //食材名称输入框
        jTextField1 = new JTextField();
        jTextPane2 = new JTextArea();

        //搜索按钮

        jPane1 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\0011.jpg");
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        jPane2 = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\2.jpg");

                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };

        class RButton extends JButton {
            public RButton(String label) {
                super(label);
                // 这些声明把按钮扩展为一个圆而不是一个椭圆。
                Dimension size = getPreferredSize();
                size.width = size.height = Math.max(size.width, size.height);
                setPreferredSize(size);
                //这个调用使JButton不画背景，而允许我们画一个圆的背景。
                setContentAreaFilled(false);
                this.setBackground(Color.WHITE);
            }

            // 画圆的背景和标签
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    // 你可以选一个高亮的颜色作为圆形按钮类的属性
                    g.setColor(Color.LIGHT_GRAY);
                }
                else {
                    g.setColor(getBackground());
                }
                g.fillOval(0, 0, getSize().width - 1,getSize().height - 1);
                //这个调用会画一个标签和焦点矩形。
                super.paintComponent(g);
            }

            // 用简单的弧画按钮的边界。
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawOval(0, 0, getSize().width - 1,getSize().height - 1);
            }
            // 侦测点击事件
            Shape shape;
            public boolean contains(int x, int y) {
                // 如果按钮改变大小，产生一个新的形状对象。
                if (shape == null ||!shape.getBounds().equals(getBounds()))
                {shape = new Ellipse2D.Float(0, 0,getWidth(), getHeight());
                }
                return shape.contains(x, y);
            }}

        jButton5 = new RButton("");
        jButton5.setBounds(450, 150,57, 57);
        ImageIcon icon = new ImageIcon("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\collect.png");
        jButton5.setIcon(icon);;
        jButton5.setFont(new Font("微软雅黑",Font.BOLD, 20));
        jButton5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }

            private void jButton5ActionPerformed(ActionEvent evt) {
                Menu m= menus.get(list.getSelectedIndex());
                System.out.println(m.toString());
                new CollectionDao().add(m, conn);

                Dialog dialog=new Dialog();
                dialog.go("收藏成功");
            }
        });

        Toolkit tk=Toolkit.getDefaultToolkit() ;
        Image image=tk.createImage("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\3.png");  //图标
        this.setIconImage(image);

        // 搜索到的List结果放在JList列表中

        listModel=new DefaultListModel();

        list=new JList(listModel);
        list.setFont(new Font("楷体",Font.BOLD, 22));


        list.setBounds(80, 230, 465,500);
        list.setVisible(true);
        JScrollPane jScrollPane2=new JScrollPane(list);
        jScrollPane2.setBounds(75,230, 465, 500);
        jPane1.add(jScrollPane2);



        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if(!list.getValueIsAdjusting()&&!list.isSelectionEmpty()){   //设置只有释放鼠标时才触发
                    System.out.println(list.getSelectedValue());

                    Menu m= menus.get(list.getSelectedIndex());

                    String s="";
                    s="食材\n\n"+m.getMaterial()+"\n\n"+m.getKouwei()+"\n\n\n步骤\n       "
                            + core.exchangeSteps(m.getSteps())+"\n\n\n\n查看更多"+m.getUrl().replace("\n","");
                    jTextPane1.setText(s);
                    jTextPane2.setText("        "+m.getName());
                    DownPic.showPic(m.getPicPath());
                }
            }
        });


        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        jPane1.setLayout(null);
        jPane1.setPreferredSize(new Dimension(800,800));

        jPane2.setLayout(null);
        jPane2.setPreferredSize(new Dimension(800,800));
        this.getContentPane().add(jPane1);
        this.getContentPane().add(jPane2);
        jPane1.setBounds(1,1,1200,1400);
        jPane2.setBounds(1,10,1200,1400);


        jPane2.add(jButton5); ///////////////////////


        jButton1.setText("设置");
        jButton1.setFont(new Font("宋体",Font.BOLD, 20));


        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                testSet ts=new testSet();
                ts.go();
            }
        });
        jPane1.add(jButton1);

        jButton1.setBounds(390, 50, 120,28);
        jButton1.setBackground(Color.WHITE);

        jButton3.setText("我的收藏");
        jButton3.setFont(new Font("宋体",Font.BOLD, 20));
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }

            private void jButton3ActionPerformed(ActionEvent evt) {
                collection c=new collection();
                c.go();
            }
        });
        jPane1.add(jButton3);
        jButton3.setBounds(105, 50, 120,28);
        jButton3.setBackground(Color.WHITE);

        jButton4.setText("每日推荐");
        jButton4.setFont(new Font("宋体",Font.BOLD, 20));
        jButton4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }

            private void jButton4ActionPerformed(ActionEvent evt) {
                Recommand re=new Recommand();
                re.go();
            }
        });
        jPane1.add(jButton4);
        jButton4.setBounds(250, 50, 120,28);
        jButton4.setBackground(Color.WHITE);
/*
        jButton6.setText("开始计时");
        jButton6.setFont(new Font("宋体",Font.BOLD, 20));
        /*jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPane1.add(jButton6);
        jButton6.setBounds(360, 65, 120,28);
        jButton6.setBackground(Color.WHITE);

*/


        namesearch=new JRadioButton("菜名查询");
        namesearch.setFont(new Font("宋体",Font.BOLD,20));
        easysearch=new JRadioButton("简单查询");
        easysearch.setFont(new Font("宋体",Font.BOLD,20));
        plansearch=new JRadioButton("方案查询");
        plansearch.setFont(new Font("宋体",Font.BOLD,20));
        namesearch.setBounds(90,185,120,25);
        easysearch.setBounds(230,185,120,25);
        plansearch.setBounds(370,185,120,25);
        namesearch.setBackground(Color.WHITE);
        easysearch.setBackground(Color.WHITE);
        plansearch.setBackground(Color.WHITE);
        easysearch.setSelected(true);
        group=new ButtonGroup();
        group.add(easysearch);
        group.add(plansearch);
        group.add(namesearch);
        jPane1.add(easysearch);
        jPane1.add(plansearch);
        jPane1.add(namesearch);

        jLabel3.setText("食材名称");
        jLabel3.setFont(new Font("宋体",Font.BOLD, 20));
        jPane1.add(jLabel3);
        jLabel3.setBounds(70, 125, 90,25);


        jPane1.add(jTextField1);    //输入食材的文本框
        jTextField1.setBounds(170,125, 250,25);
        jTextField1.setFont(new Font("宋体",Font.BOLD, 20));

        jButton2.setText("搜索");
        jButton2.setFont(new Font("宋体",Font.BOLD, 20));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBackground(Color.WHITE);
        jPane1.add(jButton2);
        jButton2.setBounds(430, 125, 100,26);

        // jLabel2.setText("菜谱详情");
        //  jLabel2.setFont(new Font("宋体",Font.BOLD, 25));
        // jPane2.add(jLabel2);
        // jLabel2.setBounds(180, 40, 120,26);

        //菜谱详情的文本部分
        jTextPane1.setFont(new Font("宋体",Font.BOLD,19));

        jTextPane2.setFont(new Font("微软雅黑",Font.PLAIN,24));
        jScrollPane1.setViewportView(jTextPane1);

        //jTextPane2.setText("土豆炒土豆的西红柿");  //菜名

        jPane2.add(jTextPane2);

        jTextPane2.setBounds(1,160, 400,50);


        jButton7.setText("SendToPhone");
        jButton7.setFont(new Font("宋体",Font.BOLD, 21));
        jButton7.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }

            private void jButton7ActionPerformed(ActionEvent evt) {
                Menu m= menus.get(list.getSelectedIndex());
                String s="      "+m.getName()+"\n      食材\n"+m.getMaterial();

                toPhone phone=new toPhone();
                phone.go(s);
            }
        });
        jPane2.add(jButton7);
        jButton7.setBounds(145, 105, 230,30);
        jButton7.setBackground(Color.WHITE);




        //菜谱详情的面板
        jPane2.add(jScrollPane1);
        jScrollPane1.setBounds(2, 230, 520,500);

        jPane1.setVisible(true);
        jPane2.setVisible(true);

/*
        downover=new JDialog(this,"Successful",true);
        downover.getContentPane().add(new JLabel("下载并创建索引完成！"));
        downover.setSize(300,240);

        downlode=new JDialog(this,"Downloding...",true);
        downlode.getContentPane().add(new JLabel("正在下载菜谱……"));

        downlode.setSize(300,240);*/


    }


    private void jButton2ActionPerformed(ActionEvent evt) {
        listModel.clear();
        String material = jTextField1.getText();

        if (plansearch.isSelected()) {
            menus = Controller.getPlans(material);
            int count = 1;
            String s = "";
            for (Menu m : menus) {
                if (m.getName() == null) {
                    s = "方案 " + count;
                    listModel.addElement(s);
                    count++;
                } else {
                    listModel.addElement("      " + m.getName());
                }
                // listModel.addElement(m.getName());
            }
        }else if(easysearch.isSelected()){
            menus=Controller.search(material);
            for(Menu m:menus){
                listModel.addElement(m.getName());
            }
        }else if(namesearch.isSelected()){
            menus=Controller.searchName(material);
            for(Menu m:menus){
                listModel.addElement(m.getName());
            }
        }
    }

    /**+
     *下载列表中对应的食谱网站
     * 多线程
     *
     */
 /*   private void jButton1ActionPerformed(ActionEvent evt) {
        DiologThread Dlog=new DiologThread();
        Dlog.start();
        int a=jComboBox1.getSelectedIndex();
        if(a==0) Controller.downmenus2X();

        else if(a==1)Controller.downmenus2M();
        else if(a==2)Controller.downmenus2H();
        downlode.setVisible(false);
        downover.setVisible(true);
    }*/

    public static void main(String args[]) {

        Outlook o = new Outlook();
        o.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        o.show();
    }

}
