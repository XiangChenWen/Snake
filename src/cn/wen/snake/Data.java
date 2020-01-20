package cn.wen.snake;

import java.net.URL;
import javax.swing.*;


//存放外部数据
public class Data {
	
	//头部的图片 URL:定位图片的地址		ImageIcon:图片
	public static URL headerURL = Data.class.getResource("/statics/header.png");
	public static ImageIcon	header = new ImageIcon(headerURL); 
	
	//头部：
	public static URL upURL = Data.class.getResource("/statics/坤上.jpg");
	public static ImageIcon up = new ImageIcon(upURL);
	public static URL downURL = Data.class.getResource("/statics/坤下.jpg");
	public static ImageIcon down = new ImageIcon(downURL);
	public static URL leftURL = Data.class.getResource("/statics/坤左.jpg");
	public static ImageIcon left = new ImageIcon(leftURL);
	public static URL rightURL = Data.class.getResource("/statics/坤右.jpg");
	public static ImageIcon right = new ImageIcon(rightURL);
	//身体
	public static URL bodyURL = Data.class.getResource("/statics/body.png");
	public static ImageIcon body = new ImageIcon(bodyURL);
	//食物
	public static URL foodURL = Data.class.getResource("/statics/篮球.jpg");
	public static ImageIcon food = new ImageIcon(foodURL);
}
