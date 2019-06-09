package Swing;


import javax.swing.*;

/**
 * 用于前台界面的多线程
 * 提示下载信息，否则单线程必须先将Diolog关闭才能开始下载
 * 注意：在主线程中应该使用DiologThread.start()启动该线程
 */
public class DiologThread extends Thread {
    @Override
    public void run(){
        testSet.downlode.getContentPane().add(new JLabel("正在下载菜谱……"));
        testSet.downlode.setSize(300,240);
        testSet.downlode.setVisible(true);

    }
}
