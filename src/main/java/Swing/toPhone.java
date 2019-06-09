package Swing;

import javax.swing.*;

import java.awt.*;

/**
 * 将食谱名和材料发送至手机
 */
public class toPhone extends JFrame {
    private JPanel jpanel;
    private JTextPane jTextPane;
    private JScrollPane jScrollPane1;
    private JButton jButton;

    public void go(String s){

        super.setTitle("发送至手机");
        super.setSize(380,430);
        Toolkit tk=Toolkit.getDefaultToolkit() ;
        Image image=tk.createImage("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\phone.png");  //图标
        this.setIconImage(image);

        jScrollPane1=new JScrollPane();
        jScrollPane1.setViewportView(jTextPane);
        jScrollPane1.setBounds(1,1,350,350);

        jpanel=new JPanel();
        jTextPane= new JTextPane ();
        this.getContentPane().add(jpanel);
        jpanel.setLayout(null);
        jpanel.setPreferredSize(new Dimension(800,800));

        jpanel.add(jTextPane);
        jpanel.add(jScrollPane1);
        jTextPane.setBounds(1,1,360,360);
        jTextPane.setFont(new Font("微软雅黑",Font.BOLD, 18));
        jTextPane.setText(s);

        this.setVisible(true);
    }


}