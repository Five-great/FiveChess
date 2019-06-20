import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FiveChessMenu extends JFrame{
	private JMenuBar menu;//菜单栏
	private JMenu viewmenu,helpmenu;//菜单栏中的“选项”菜单
	private Action replayOption;//说明
	private Action funOption;//规则
	private Action aboutOption;//下拉项中的“关于”选项
	 public FiveChessMenu() {
		 setTitle("java版五子棋-five");//创建游戏界面窗口
		  JLayeredPane	layeredPane=new JLayeredPane();
			JPanel jp = new JPanel();
			jp.setBounds(0,0,1050,815);
			jp.add(new JLabel(new ImageIcon("./src/Menu.png")));
		 JButton b1 =new MyButton("       人  机  大  战         ");
		 b1.setSize(240, 50); 
		 b1.setLocation(400, 290);
		  b1.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  FiveChessWindows five = new FiveChessWindows();
				  five.init(true,1);
				  new RockPaperScissors(true,five);
	                dispose();
				}
			});
		  JButton b2 =new MyButton("        左  右  互  战         ");
		  b2.setSize(240, 50);
		  b2.setLocation(400, 350);
		  b2.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				 new FiveChessWindows().init(false,1);
	                dispose();
				}
			}); 
		  JButton b3 =new MyButton("     退          出   ");
		  b3.setSize(180, 50);
		  b3.setLocation(435, 410);
		  b3.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {System.exit(0);}});//将jp放到最底层。
			layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);	//将jb放到高一层的地方
			layeredPane.add(b1,JLayeredPane.MODAL_LAYER);
			layeredPane.add(b2,JLayeredPane.MODAL_LAYER);
			layeredPane.add(b3,JLayeredPane.MODAL_LAYER);
			   menu = new JMenuBar();//创建菜单栏
			    viewmenu = new JMenu("            查  看          ");//创建菜单栏中的“选项”菜单
			    helpmenu= new JMenu("      帮  助       ");
				menu.add(viewmenu);
				//把“重玩一盘”、“机器先手”、“人类先手”加入“选项”下拉项中
				replayOptionInit();
				viewmenu.add(replayOption);
				viewmenu.addSeparator();//分割线
				funOptionInit();
				viewmenu.add(funOption);
				aboutOptionInit();
				helpmenu.add(aboutOption);
				menu.add(helpmenu);//把menu设置为frame的菜单栏
				setJMenuBar(menu);//把menu设置为frame的菜单栏
				
				layeredPane.add(menu,JLayeredPane.MODAL_LAYER);
				setLayeredPane(layeredPane);
			//设置frame窗口左上角图标
			 setIconImage(this.getToolkit().getImage("./src/favicon.png"));
			 setLocation(300, 100);
			 setSize(1050, 814);
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setVisible(true);
		 
	 }
	 
		//选项绑定相应的处理事件
		public void replayOptionInit(){
			replayOption = new AbstractAction("游 戏 说 明 ", new ImageIcon("./src/按钮.png")){
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null, "<本游游戏采用 五元贡献法 实现 人机对战> ","游  戏   说   明     ", JOptionPane.PLAIN_MESSAGE);
				}
			};
		}
		public void funOptionInit(){
			funOption = new AbstractAction("玩 法 说 明 ", new ImageIcon("./src/按钮.png")){
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null, "<本游游戏玩法同一般五子棋玩法 哈哈哈 人机对战> "," 玩   法 说   明     ", JOptionPane.PLAIN_MESSAGE);
				}
			};
		}
		public void aboutOptionInit(){
			aboutOption = new AbstractAction("关   于 ", new ImageIcon("./src/按钮.png")){
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null, "<本游戏由 电子信息工程学院2016级通信工程2班 >  \n     <            李海龙           李洁             杜科             >\n                    <                  共同制作                 >","   关    于    ", JOptionPane.PLAIN_MESSAGE);
				} 
			};
		}

	 
}