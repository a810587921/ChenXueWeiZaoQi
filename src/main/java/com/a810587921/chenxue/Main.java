package com.a810587921.chenxue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by 22 on 2018/5/21.
 */
public class Main
{
	public static Main main;
	public static void main(String[] args) throws IOException, AWTException {

		main=new Main();
	}

	ImagePanel panel;
	JTextField field;
	JFrame frame;
	Robot robot;
	public Main() throws IOException, AWTException {
		frame=new JFrame();
		robot = new Robot();
		panel=new ImagePanel();
		frame.setLayout(null);
		//frame.add(panel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(800,400);
		frame.setContentPane(panel);
		frame.setBackground(new Color(255,255,255));
		frame.setVisible(true);
		field=new JTextField();
		field.setBounds(5,200,400,40);
		JButton button=new JButton("造谣");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setMsg(field.getText());
				panel.repaint();

				new Thread(new STask(panel)).start();
			}
		});
		button.setBounds(410,200,80,40);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.add(field);
		panel.add(button);
		//panel.getGraphics().drawImage(image,5,5,image.getWidth(),image.getHeight(),panel);
	}

}
class ImagePanel extends JPanel{
	private int x=0;
	private int y=0;
	public void s(){
		int sX=x+20;
		if(sX<175)
			sX=175;
		BufferedImage s=Main.main.robot.createScreenCapture(new Rectangle(Main.main.frame.getX()+10,Main.main.frame.getY()+50,sX,50+50));
		//ImageIO.write(s,"png",new File("F:/chenxue.png"));
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new Images(s), null);
	}
	private String msg="github:https://github.com/a810587921/ChenXueWeiZaoQi";
	public ImagePanel(){
	}
	public void setMsg(String msg)
	{
		this.msg=msg;
	}

	@Override public void paintComponent(Graphics g){
		super.paintComponent(g);

		BufferedImage image= null;
		try {
			image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("tx.png"));
			int baseX=5;
			int baseY=50;
			g.drawImage(image, baseX, baseY, this);
			x=image.getWidth();
			y=image.getHeight();
			image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("name.png"));
			g.drawImage(image, baseX+x+5, baseY, this);


			image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("left.png"));
			x=image.getWidth();
			y=image.getHeight();
			g.drawImage(image, baseX+x+20, baseY+20, this);


			drawText(msg,g);

			//System.out.println(Main.main.frame.getX()+","+Main.main.frame.getY());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void drawText(String msg,Graphics g) throws IOException, FontFormatException {
		int index=0;
		//Font font=new Font("方正黑体简体",Font.PLAIN,15);
		Font font=new Font("微软雅黑",Font.PLAIN,13);
		int baseX=25;
		int baseY=50;
		Color fontColor=new Color(204,178,122);
		Color def=g.getColor();
		g.setFont(font);
		int b=0;
		int font1X;
		int font2X;
		int fontY;
		BufferedImage image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("font1.png"));
		font1X=image.getWidth();
		fontY=image.getHeight();
		image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("font2.png"));
		font2X=image.getWidth();
		int imgX=0;
		int font2Count=0;
		while(index<msg.length())
		{
			String a=msg.substring(index,index+1);
			index+=1;
			try
			{
				String name=(a.matches("[a-zA-Z0-9]") ||
				a.equalsIgnoreCase(",") ||
				a.equalsIgnoreCase(".") ||
				a.equalsIgnoreCase(":") ||
				a.equalsIgnoreCase("?") ||
				a.equalsIgnoreCase("/") ||
				a.equalsIgnoreCase("\\")) ? "font2.png":"font1.png";
				if(name.equalsIgnoreCase("font1.png"))
					imgX+=13;//imgX+=font1X;
				else
				{
					if(font2Count==4)
					{
						font2Count=0;
						imgX+=8;
					}
					else
					{
						imgX+=7;
						font2Count+=1;
					}
				}
				/*image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream(name));
				b=baseX+x+image.getWidth();
				a(g,b+image.getWidth(),baseY+20);
				g.drawImage(image, b+image.getWidth(), baseY+20, this);
				a(g,b+image.getWidth(),baseY+20);
				b+=image.getWidth();

				x+=image.getWidth();*/
			}catch (Exception e){e.printStackTrace();}
		}
		image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("font1.png"));
		int fX=baseX+x+17;
		g.drawImage(image,fX , baseY+20, imgX,fontY,this);

		image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("right.png"));
		g.drawImage(image, fX+imgX, baseY+20, this);
		a(g,b+image.getWidth(),baseY+20);
		x=fX+imgX;

		g.setColor(fontColor);
		//MyDrawString(msg,baseX+40,baseY+45,1.2,g);
		g.drawString(msg,baseX+35,baseY+45);
		g.setColor(def);
	}
	public static void a(Graphics g,int x,int y)
	{
		Color def=g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x,y,1,1);
		g.setColor(def);
	}
}
class STask implements Runnable {
	ImagePanel panel;
	public STask(ImagePanel panel)
	{
		this.panel=panel;
	}
	public void run()
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		panel.s();
	}
}