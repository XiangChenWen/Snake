package cn.wen.snake;

import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class StartWindow {
	public static void main(String[] args) {
		// 1. 绘制一个静态窗口 JFrame
		JFrame frame = new JFrame("Snake Game");
		// 设置界面的大小
		frame.setSize(900, 670);// 设置界面的大小
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((width - 900) / 2, (height - 670) / 2);
		frame.setResizable(false);// 窗口大小则不可改变
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭事件，游戏可以关闭了

		// 2.画板 JPnel 可以加入到 JFrame
		frame.add(new GamePanel());

		frame.setVisible(true);// 让窗口能够展现出来

	}

}
