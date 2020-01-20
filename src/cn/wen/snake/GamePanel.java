package cn.wen.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	int length;// 蛇的长度
	int[] snakeX = new int[900];// 蛇的坐标X
	int[] snakeY = new int[800];// 蛇的坐标Y
	String fx;

	boolean isStart = false;// 游戏是否开始
	Timer timer = new Timer(144, this);// 定时器3

	// 定义食物的显示坐标
	int foodX, foodY;
	Random r = new Random();

	// 死亡判断
	boolean isFail = false;

	//积分系统
	int score;
	
	// 构造器
	public GamePanel() {
		init();
		// 获取键盘的监听事件
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start(); // 让时间动起来
	}

	public void init() {
		length = 3;
		snakeX[0] = 100;
		snakeY[0] = 100;// 头部坐标
		snakeX[1] = 75;
		snakeY[1] = 100;// 第一个身体坐标
		snakeX[2] = 50;
		snakeY[2] = 100;// 第二个身体坐标
		fx = "R";

		foodX = 25 + 25 * r.nextInt(32);
		foodY = 75 + 25 * r.nextInt(18);
		
		score = 0;
	}

	// 画板：画界面，画蛇
	// Graphics: 画笔
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);// 清屏
		this.setBackground(Color.white);// 设置背景的颜色
		//Data.header.paintIcon(this, g, 25, 5);// 绘制顶部的图片
		g.fillRect(25, 75, 850, 550);// 绘制游戏区域

		// 画一条静态的小蛇
		if (fx.equals("R")) {
			Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
		} else if (fx.equals("L")) {
			Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
		} else if (fx.equals("U")) {
			Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
		} else if (fx.equals("D")) {
			Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
		}

		for (int i = 1; i < length; i++) {
			Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);// 蛇身的长度通过 length 来控制
		}
		
		//画积分
		g.setColor(Color.black);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		g.drawString("Length: " + length, 750, 30);
		g.drawString("Score: " + score, 750, 55);
		// 画食物
		Data.food.paintIcon(this, g, foodX, foodY);

		// 游戏提示：是否开始
		if (isStart == false) {
			// 画一个文字，String
			g.setColor(Color.yellow);// 设置画笔的颜色
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
			g.drawString("Press space to start", 250, 350);
		}

		// 失败提醒
		if (isFail == true) {
			// 画一个文字，String
			g.setColor(Color.red);// 设置画笔的颜色
			g.setFont(new Font("微软雅黑", Font.BOLD, 35));
			g.drawString("Failed, press space to start again", 150, 350);
		}
	}

	// 接受键盘的输入：监听
	@Override
	public void keyPressed(KeyEvent e) {
		// 获取按下的键盘是哪个键
		int KeyCode = e.getKeyCode();

		if (KeyCode == KeyEvent.VK_SPACE) { // 如果按下空格键
			if (isFail) {//失败，重新开始游戏
				isFail = false;
				init();//重新初始化游戏
			}
			else {//暂停游戏
				isStart = !isStart;
			}
			repaint();// 刷新界面
		}

		// 键盘控制走向
		if (KeyCode == KeyEvent.VK_LEFT) {
			fx = "L";
		} else if (KeyCode == KeyEvent.VK_RIGHT) {
			fx = "R";
		} else if (KeyCode == KeyEvent.VK_UP) {
			fx = "U";
		} else if (KeyCode == KeyEvent.VK_DOWN) {
			fx = "D";
		}
	}

	// 定时器，监听时间，帧数：执行定时操作
	@Override
	public void actionPerformed(ActionEvent e) {
		// 如果游戏处于开始状态，并且游戏没有结束
		if (isStart && isFail == false) {
			// 右移
			for (int i = length - 1; i > 0; i--) {// 除了头部，身体都向前移动
				snakeX[i] = snakeX[i - 1];
				snakeY[i] = snakeY[i - 1];
			}
			// 通过控制方向让头部移动
			if (fx.equals("R")) {
				snakeX[0] = snakeX[0] + 25;// 头部移动
				if (snakeX[0] > 850) {snakeX[0] = 25;} // 边界判断
			} 
			else if (fx.equals("L")) {
				snakeX[0] = snakeX[0] - 25;// 头部移动
				if (snakeX[0] < 25) {snakeX[0] = 850;} // 边界判断
			} 
			else if (fx.equals("U")) {
				snakeY[0] = snakeY[0] - 25;// 头部移动
				if (snakeY[0] < 75) {snakeY[0] = 600;} // 边界判断
			} 
			else if (fx.equals("D")) {
				snakeY[0] = snakeY[0] + 25;// 头部移动
				if (snakeY[0] > 600) {snakeY[0] = 75;} // 边界判断
			}

			// 如果小蛇的头部和食物的坐标重合了
			if (snakeX[0] == foodX && snakeY[0] == foodY) {
				// 长度+1
				length = length + 1;
				
				score = score + 10;

				// 重新生成食物
				foodX = 25 + 25 * r.nextInt(32);
				foodY = 75 + 25 * r.nextInt(18);
			}
			
			//结束判断
			for (int i =  1; i < length; i++) {
				if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
					isFail = true;
				}
			}

			repaint();// 刷新界面
		}
		timer.start(); // 让时间动起来
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 释放某个键

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
