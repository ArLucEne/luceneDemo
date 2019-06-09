package Swing;

import Util.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 用户信息展示
 */

class test extends JFrame{
    private static JFrame jFrame;
    private static JLabel jLabel;
    private static JLabel jLabel2;
    private static JLabel jLabel3;
    private static JLabel jLabel4;
    private static  JLabel jLabel5;
    private static JLabel jLabel6;

    private static JLabel jTextField;
    private static JLabel jTextField2;
    private static JLabel jTextField3;
    private static JLabel jTextField4;
    private static JLabel jTextField5;
    private static JLabel jTextField6;

    private static JButton jButton;

    public static String username;

    public  void go(User user){

        jFrame=new JFrame("添加信息");
        jFrame.setSize(500,800);
        jFrame.setVisible(true);



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

        jTextField=new JLabel(user.getName());
        jTextField2=new JLabel(user.getPhone());
        jTextField3=new JLabel(user.getNashoucai());
        jTextField4=new JLabel(user.getKouwei());
        jTextField5=new JLabel(user.getFavofood());
        jTextField6=new JLabel(user.getTime());
        jTextField.setFont(new Font("宋体",Font.BOLD,20));
        jTextField2.setFont(new Font("宋体",Font.BOLD,20));
        jTextField3.setFont(new Font("宋体",Font.BOLD,20));
        jTextField4.setFont(new Font("宋体",Font.BOLD,20));
        jTextField5.setFont(new Font("宋体",Font.BOLD,20));
        jTextField6.setFont(new Font("宋体",Font.BOLD,20));

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

            private void jButtonActionPerformed(ActionEvent evt) {

                jFrame.dispose();
            }
        });

    }

}