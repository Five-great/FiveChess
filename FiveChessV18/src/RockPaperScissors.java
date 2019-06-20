
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
public class RockPaperScissors extends JFrame {
	private JLabel lb1, lb2, lb3, lb4; // 提示标签
	private JTextField ta1, ta2;// 两个文本框
	private JButton b1, b2, b3,b4; // 三个按钮
	private JPanel p1, p2,p3; // 三个JPanel面板
	private int res_flag=1; // 两个JPanel面板
	private boolean flag;
	private String utf_jd = null; //剪刀
	private String utf_st = null;// 石头
	private String utf_b = null;//布
	private FiveChessWindows five;
			public RockPaperScissors(boolean flag,FiveChessWindows five) {
				this.flag=flag; this.five=five;
			// 初始化所有组件
			lb1 = new JLabel("猜拳");
			lb2 = new JLabel("  您出拳： ");
			lb3 = new JLabel("电脑出拳：");
			lb4 = new JLabel(" ");
			ta1 = new JTextField();
			ta1.setPreferredSize(new Dimension(60, 60)); // 设置大小
			ta1.setFont(new Font(null, Font.BOLD,50));
			ta1.setEditable(false);//设置不可编辑
			ta2 = new JTextField();
			ta2.setPreferredSize(new Dimension(60, 60));
			ta2.setFont(new Font(null, Font.BOLD,50));
			ta2.setEditable(false);//设置不可编辑
			Gbk_Utf();
			b1 = new JButton("剪刀"+utf_jd);
			b2 = new JButton("石头"+utf_st);
			b3 = new JButton("布"+utf_b);
			b4 = new JButton("开始下棋⚪");
			p1 = new JPanel();
			p2 = new JPanel();
			p3 = new JPanel();
			// 设置第一个面板内容
			Box box = Box.createVerticalBox();
			Box box1 = Box.createHorizontalBox();
			box1.add(lb2);
			box1.add(ta1);
			box1.add(new JLabel(" VS "));
			box1.add(ta2);
			box1.add(lb3);
			box.add(lb1);
			box.add(Box.createVerticalStrut(30));
			box.add(box1);
			box.add(Box.createVerticalStrut(30));
			box.add(lb4);
			p1.add(box);
			p3.add(b4);
			// 设置第二个面板
			p2.setLayout(new GridBagLayout()); // 使用GridBagLayout布局管理器
			p2.setPreferredSize(new Dimension(0, 60));
			GridBagConstraints g2 = new GridBagConstraints();
			g2.fill = GridBagConstraints.BOTH;
			g2.weightx = 1.0;
			g2.weighty = 1.0;
			g2.gridx = 0;
			g2.gridy = 0;
			p2.add(b1, g2);
			g2.gridx = 1;
			p2.add(b2, g2);
			g2.gridx = 2;
			p2.add(b3, g2);
			//为4个按钮添加事件
			b1.addActionListener(new buttonAction());
			b2.addActionListener(new buttonAction());
			b3.addActionListener(new buttonAction());
			b4.addActionListener(new buttonAction());
			getContentPane().add(p1,BorderLayout.NORTH);
			getContentPane().add(p3);
			getContentPane().add(p2, BorderLayout.SOUTH);
			setTitle("猜拳决定落子顺序");
			setLocation(560, 360);
			setSize(540, 330);
			setIconImage(this.getToolkit().getImage("./src/favicon.png"));
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
	public void Gbk_Utf() {
		try {
				utf_jd = new String("✌".getBytes("UTF-8"), "UTF-8");
				utf_st = new String("✊".getBytes("UTF-8"), "UTF-8");
				utf_b = new String("👋".getBytes("UTF-8"), "UTF-8");
			} catch (UnsupportedEncodingException e1) {e1.printStackTrace();}
	}
			
//事件类
class buttonAction extends AbstractAction{
		public void actionPerformed(ActionEvent e) {
		 String res="";
		if(e.getSource()==b1){
	    ta1.setText(utf_jd);
		res=init(ta1.getText());
		}else if(e.getSource()==b2){
		ta1.setText(utf_st);
		res=init(ta1.getText());
		}else if(e.getSource()==b3){
		ta1.setText(utf_b);
		res=init(ta1.getText());
		}else {
			 new FiveChessWindows().init(flag,res_flag);
			 five.dispose();
				dispose();	
		}
		if(res.equals("电脑赢了 您后手。")) res_flag=-1; 
		if(res.equals("您赢了 您先手。")) res_flag=1; 
     }
// 模拟电脑出拳，产生三个随机数。0代表剪刀，1代表石头，2代表布
public String getQuan(){
 String str="";
	int num=new Random().nextInt(3) ;//产生随机数
	if(num==0)str=utf_jd;
	else if(num==1)str=utf_st;
	else str=utf_b;
	return str;
}
public String init(String wo){
	String sy=""; // 保存输赢结果
	String dncq=getQuan(); //电脑出拳
	if(wo.equals(dncq))sy="平局,再来一次";
	else if(wo.equals(utf_jd)&&dncq.equals(utf_b) || wo.equals(utf_st)&&dncq.equals(utf_jd)||wo.equals(utf_b)&&dncq.equals(utf_st))sy="您赢了 您先手。";
    else sy="电脑赢了 您后手。";
    ta2.setText(dncq);// 电脑出拳
    lb4.setText(sy);
    return sy;
  }
}
 
   
}