package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog extends JDialog {
    public JFrame jFrame;
    private JPanel jPanel;
    private JButton jButton;
    private JLabel jLabel;

    public void go(String text){
        jFrame=new JFrame("提示");
        jFrame.setVisible(true);
        jFrame.setLocation(500,300);
        jFrame.setSize(300,200);

        jPanel=new JPanel();
        jFrame.setContentPane(jPanel);
        jPanel.setLayout(null);
        jPanel.setBackground(Color.WHITE);

        Toolkit tk=Toolkit.getDefaultToolkit() ;
        Image image=tk.createImage("D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\collect.png");  //图标
        jFrame.setIconImage(image);

       // jPanel.setPreferredSize(new Dimension(300,200));
        //jPanel.setSize(300,300);

        jButton=new JButton("OK");
        jButton.setBackground(Color.WHITE);
        jButton.setBounds(100,100,70,20);
        jLabel=new JLabel();
        jLabel.setBounds(95,15,150,80);
        jLabel.setText(text);
        //jLabel.setFont(new Font("宋体",Font.BOLD, 20));
        jLabel.setFont(new Font("宋体",Font.BOLD,20));

        jPanel.add(jButton);
        jPanel.add(jLabel);

        jFrame.setVisible(true);
       // jFrame.setLocation(300,300);
      //  jFrame.setSize(300,200);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //System.out.println("_________");
                jFrame.setVisible(false);
            }
        });

    }




}
