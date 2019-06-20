//为防止乱码 请将工程默认GBK编码更改为 UTF-8编码
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//游戏运行入口
public class FiveChess{public static void main(String[] args){new FiveChessMenu();}}
//界面类，这是游戏主体框架
class FiveChessWindows extends JFrame {//五子棋窗口
	private Chessboard chessboard = new Chessboard();//五子棋盘【关键】
	private Chess chess = new Chess();	//五子棋业务逻辑【关键】	
	private Panel toolbar1,toolbar2,toolbar3;	
	private boolean flag;//
	private JButton startButton;
	private JTextField text1, text2,text3,text4;
	private JButton prompt1,prompt2;
	private JButton backButton1,backButton2;
	private JButton exitButton;
	private int owner_flag=1;//用于左右互搏
	private boolean prompt_flag=false;
	private String utf_hz = null,utf_bz=null; //
	//完成五子棋游戏界面
	public void init(Boolean flag,int res_flag){
		this.flag=flag;Chess.FIRST=res_flag;
		setTitle("java版五子棋-five");//创建游戏界面窗口
		toolbar1 = new Panel();
		toolbar2 = new Panel();
		toolbar3 = new Panel();
		toolbar1.setLayout(new GridLayout(3,1,20,20));
		toolbar3.setLayout(new GridLayout(3,1,20,20));
		text1 = new JTextField();
		text1.setPreferredSize(new Dimension(60, 60)); // 设置大小
		text1.setFont(new Font(null, Font.BOLD,50));
		text1.setEditable(false);//设置不可编辑
		text2 = new JTextField();
		text2.setPreferredSize(new Dimension(60, 60)); // 设置大小
		text2.setFont(new Font(null, Font.BOLD,50));
		text2.setEditable(false);//设置不可编辑
		text3 = new JTextField();
		text3.setPreferredSize(new Dimension(60, 60)); // 设置大小
		text3.setFont(new Font(null, Font.BOLD,50));
		text3.setEditable(false);//设置不可编辑
		text4 = new JTextField();
		text4.setPreferredSize(new Dimension(60, 60)); // 设置大小
		text4.setFont(new Font(null, Font.BOLD,50));
		text4.setEditable(false);//设置不可编辑
		//把“选项”菜单加入到菜单栏
		//MyButton startButton= new MyButton("       重   新    开     始           ");
		startButton = new MyButton("       重     新    开     始           ");
		backButton1 = new MyButton("       悔      棋         ");
		backButton2 = new MyButton("       悔      棋         ");
		prompt1 = new MyButton("   提        ？      示       ");
		prompt2 = new MyButton("   提        ？      示       ");
		exitButton = new MyButton("        返     回    菜    单             ");
		toolbar1.setBackground(Color.cyan);
		toolbar2.setBackground(Color.green);
		toolbar3.setBackground(Color.cyan);
		if(!flag)toolbar1.add(new JLabel(new ImageIcon("./src/玩家.png")));
		else toolbar1.add(new JLabel(new ImageIcon("./src/电脑1.png")));
		toolbar3.add(new JLabel(new ImageIcon("./src/玩家.png")));
		JPanel j3 =new JPanel(new GridLayout(2,1,5,5));
		j3.add(text1);j3.add(text3);
		toolbar1.add(j3);
		if(!flag)text1.setText("—玩 家—");
		else text1.setText("—电 脑—");
		JPanel j4 =new JPanel(new GridLayout(2,1,5,5));
		j4.add(text2);j4.add(text4);
		toolbar3.add(j4);
		text2.setText("—玩 家—");
		JPanel j1=new JPanel();
		 j1.setLayout(new GridLayout(3,1,20,20));
		 j1.setBackground(Color.cyan);
		 j1.add(prompt1);
		 j1.add(backButton1);
		 toolbar1.add(j1);
		 JPanel j2=new JPanel();
		 j2.setLayout(new GridLayout(3,1,20,20));//网格布局
		 j2.setBackground(Color.cyan);
		 j2.add(prompt2);
		 j2.add(backButton2);
		 toolbar3.add(j2);
		 exitButton.setSize(180, 60);
		toolbar2.add(startButton);
		toolbar2.add(exitButton);
		Gbk_Utf1();
		textInit();
		ButtonInit();//按钮事件绑定初始化
			add(toolbar1, BorderLayout.WEST);
			add(toolbar2, BorderLayout.SOUTH);
			add(toolbar3, BorderLayout.EAST);
			add(chessboard,BorderLayout.CENTER);//把五子棋盘加入到frame
			//绑定鼠标事件，要下棋了，为了避免写无用的抽象方法的实现，
			chessboard.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					play(e);//鼠标点击引发下棋事件，处理下棋事件比较繁琐，为此开一个方法
				}
			});	
		//设置frame窗口左上角图标
		 setIconImage(this.getToolkit().getImage("./src/favicon.png"));
		 setLocation(225, 100);
		 setSize(1198, 800);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setResizable(false);
		 setVisible(true);
		 AIFirstOptionInit();//
	}
	public void ButtonInit() {
		backButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xy=chessboard.goback();
				if(xy>0)chess.goback(xy);
			}
		});
		backButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    // goback();
				int xy=chessboard.goback();
				if(xy>0)chess.goback(xy);
			}
		});
		prompt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prompt_flag=!prompt_flag;
				chessboard.setPrompt(prompt_flag);
				if(prompt_flag) chessboard.setScores(chess.getScores());
				chessboard.repaint();
				
			}
		});	
		prompt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prompt_flag=!prompt_flag;
				chessboard.setPrompt(prompt_flag);
				if(prompt_flag) {chessboard.setScores(chess.getScores());}
				chessboard.repaint();
			}
		});	
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FiveChessMenu();
                dispose();
			}
		});	
			
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FiveChessWindows five = new FiveChessWindows();
				  five.init(flag,1);
				  new RockPaperScissors(flag,five);
				  dispose();
			}
		});	
	}
	//“先后手处理事件
	public void AIFirstOptionInit(){
				//棋盘还没有落子的时候可以选择“机器先手”，一旦有落子，选择“机器先手”失效
				if(chessboard.isEmpty()){
					if(Chess.FIRST ==-1) {
					//机器先手，则先在中间位置下一个棋子
					chessboard.addChessman(7, 7, -1);
					chess.addChessman(7, 7, -1);
				}
	}
}
	public void Gbk_Utf1() {
		try {
				utf_hz= new String("⚫".getBytes("UTF-8"), "UTF-8");
				utf_bz = new String("⚪".getBytes("UTF-8"), "UTF-8");
			} catch (UnsupportedEncodingException e1) {e1.printStackTrace();}
	}
	public void textInit() {
		if(Chess.FIRST==-1) {text3.setText(" 先手"+utf_hz);text4.setText(" 后手 "+utf_bz);}
		else {text3.setText(" 后手 "+utf_bz);text4.setText(" 先手 "+utf_hz);}
	}
	//核心业务逻辑  处理鼠标落子事件
	public void play(MouseEvent e){
		int cellSize = chessboard.getCellSize();//每个格子的边长
		int x = (e.getX() - 5) / cellSize;//像素值转换成棋盘坐标
		int y = (e.getY() - 5) / cellSize;//像素值转换成棋盘坐标
		//判断落子是否合法
		boolean isLegal = chess.isLegal(x, y);
		//如果落子合法
		if(isLegal){
			chessboard.addChessman(x, y, owner_flag);//界面方面加一个棋子
			chess.addChessman(x, y, owner_flag);//逻辑业务方面加一个棋子
			//判断人类是否胜利
			if(chess.isWin(x, y, owner_flag)){
				 chessboard.setIsWin(chess.getisWinChess());
				 JOptionPane.showMessageDialog(this, "                   己方获胜", "您赢了！", JOptionPane.PLAIN_MESSAGE);
				chessboard.init();//初始化
				chess.init();
				textInit();
				return;
			}
			Location loc = chess.searchLocation();//根据五元贡献的方法确定
			if(flag) {
				//机器落子
				//Location loc = chess.searchLocation();//根据五元贡献的方法确定
				chessboard.addChessman(loc);
				chess.addChessman(loc.getX(), loc.getY(), loc.getOwner());
				//判断机器是否胜利
				if(chess.isWin(loc.getX(), loc.getY(), -1)){
					chessboard.setIsWin(chess.getisWinChess());
					JOptionPane.showMessageDialog(this, "             对方获胜", "您输了！", JOptionPane.PLAIN_MESSAGE);
					chessboard.init();
					chess.init();
					textInit();
					return;
				}
			}else owner_flag=-owner_flag;
			chessboard.setPrompt(prompt_flag);//更行提示分数和棋子
			 chess.searchLocation();chessboard.setScores(chess.getScores());
		}
	} 
}
 class Chessboard extends JPanel{//棋盘类 继承了 JPanel
	private static final long serialVersionUID = 1L;
	public static final int CHESSBOARD_SIZE = 15;//棋盘大小15X15
	private ArrayList<Location> locationList = new ArrayList<>();//棋盘上所有可以落子的位置坐标等信息
	private Color backgroundColor = new Color(255, 245, 186);//棋盘背景色
	private Color lineColor = new Color(66, 66, 66);//棋盘线条颜色
	private int margin = 30;//棋盘边缘长度
	private int[][] scores;//获取胜利棋子;
	private int[] iswinxy;
	private boolean prompt_flag=false;
	public void init(){locationList.clear();repaint();}	//初始化棋盘
	public void setPrompt(boolean prompt_flag2) {this.prompt_flag=prompt_flag2;}//获取是否提示状态
	public void setScores(int[][] scores) {this.scores=scores;}//获取分数数据
	public void setIsWin(int[] iswinxy) {this.iswinxy=iswinxy;}
	public void paint(Graphics g){//覆盖paint方法
		super.paint(g);
		drawChessboard(g);//画棋盘
		drawScores(g);//画分数
		drawiswin(g);//画胜利棋子
		drawChessman(g);//画棋子
	}
	public void drawChessboard(Graphics g){//画棋盘
		
		g.setColor(backgroundColor);//先画背景
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(lineColor);//画线
		int cellSize = (this.getWidth() - 2*margin)/(CHESSBOARD_SIZE - 1);//每个格子的边长
		for(int i = 0; i < CHESSBOARD_SIZE; i++){
			g.drawLine(margin, margin + i*cellSize,this.getWidth() - margin, margin + i*cellSize);//画横线
			g.drawLine(margin + i*cellSize, margin, margin + i*cellSize, this.getHeight() -margin);//画纵线
		}
	}
	public void drawChessman(Graphics g){//画棋子
		for(int i = 0; i < locationList.size(); i++){
			Location loc = locationList.get(i);
			int cellSize = (this.getWidth() - 2*margin)/(CHESSBOARD_SIZE - 1);//每个格子的边长
		  if(i==locationList.size()-1) {
				g.setColor(new Color(207,13,62));// 标记最后一个棋子四周为红色
				g.fillRoundRect(margin + cellSize*loc.getX() - cellSize/2-5, margin + cellSize*loc.getY() - cellSize/2-5, cellSize+8, cellSize+8,100,100);
				g.setColor(backgroundColor);
				g.fillRoundRect(margin + cellSize*loc.getX() - cellSize/2-2, margin + cellSize*loc.getY() - cellSize/2-2, cellSize+2, cellSize+2,100,100);
			}	 
		    //根据先后手设置棋子为黑色和白色
			if(loc.getOwner() == Chess.FIRST)g.setColor(Color.BLACK);
		    else g.setColor(Color.WHITE);	//画棋子
			g.fillOval(margin + cellSize*loc.getX() - cellSize/2+3, margin + cellSize*loc.getY() - cellSize/2+3, cellSize-6, cellSize-6);

		}
	}
  //划分数
	public void drawScores(Graphics g) {
		if(prompt_flag) {
		   int Maxscore=0,Maxx=-1,Maxy=-1;
		  g.setColor(Color.darkGray);
		  int cellSize = (this.getWidth() - 2*margin)/(CHESSBOARD_SIZE - 1);//每个格子的边长
		  for(int i = 0; i < CHESSBOARD_SIZE; i++)
			for(int j = 0; j < CHESSBOARD_SIZE; j++) {
				if(Maxscore<=scores[i][j]){Maxscore=scores[i][j];Maxx=i;Maxy=j;}
	//解开注释 可在棋盘上绘制分数//g.drawString(""+scores[i][j],margin + i*cellSize, margin + j*cellSize);
		  }
		  g.setColor(Color.green);//画提示棋子。
		  g.fillOval(margin + cellSize*Maxx - cellSize/2+5, margin + cellSize*Maxy - cellSize/2+5, cellSize-8, cellSize-8);
		}
	}		
		public void drawiswin(Graphics g) { //画胜利棋子
			 if(iswinxy!=null && iswinxy.length>3) {
			  g.setColor(Color.darkGray);System.out.println(iswinxy.length);
			  int cellSize = (this.getWidth() - 2*margin)/(CHESSBOARD_SIZE - 1);//每个格子的边长
			  int x1,y1;
			  for(int i=0;i<5;i++) {
			     x1=iswinxy[i]/100;y1=iswinxy[i]%100;
			    g.setColor(new Color(207,13,62));//
				g.fillRoundRect(margin + cellSize*x1 - cellSize/2-5, margin + cellSize*y1 - cellSize/2-5, cellSize+8, cellSize+8,100,100);
				g.setColor(backgroundColor);
				g.fillRoundRect(margin + cellSize*x1 - cellSize/2-2, margin + cellSize*y1 - cellSize/2-2, cellSize+2, cellSize+2,100,100);
			  }	
			  iswinxy=null;
		  }  
		}	
	public int goback() {
		if (locationList.size() == 0)return -1;
		int x=locationList.get(locationList.size()-1).getX();
		int y=locationList.get(locationList.size()-1).getY();
		locationList.remove(locationList.size()-1);repaint();
		return x*100+y;
	}
	public void addChessman(int x, int y, int owner){locationList.add(new Location(x, y, owner));repaint();}//落子
	public void addChessman(Location loc){locationList.add(loc);repaint();}
	public int getCellSize(){return (this.getWidth() - 2*margin)/(CHESSBOARD_SIZE - 1);}//计算棋盘每个小格子的大小
	public boolean isEmpty() {return locationList.size() == 0 ? true : false;}//判断棋盘是否还没有棋子
}
class Location{
	private int x;//某个棋盘位置横坐标，0-14
	private int y;//某个棋盘位置纵坐标，0-14
	private int owner;//占据该位置的棋手方，1是人类，-1是机器，0是空
	private int score;//对该位置的打的分数
	public Location(){}//构造方法
	public Location(int x, int y, int owner){this.x = x;this.y = y;this.owner = owner;}
	public Location(int x, int y, int owner, int score){this(x, y, owner);this.score = score;}
	public int getX(){return this.x;}//x 坐标
	public void setX(int x){this.x = x;}
	public int getY(){return this.y;} //y坐标
	public void setY(int y){this.y = y;}
	public int getOwner(){return this.owner;}// 棋盘点 状态坐标
	public void setOwner(int owner){this.owner = owner;}
	public int getScore(){return this.score;}// 该点分数
	public void setScore(int score){this.score = score;}
}
//下棋业务核心类，与界面棋盘对应，业务放在这里，可以和界面代码分离
 class Chess{
	public static final int CHESSBOARD_SIZE = 15;
	public static int FIRST = 1;//先手，-1表示机器，1表示人类，与Location类中的对应
	private int[][] chessboard = new int[CHESSBOARD_SIZE][CHESSBOARD_SIZE];//与界面棋盘对应，0代表空，-1代表机器，1代表人类
	private int[][] score = new int[CHESSBOARD_SIZE][CHESSBOARD_SIZE];//每个位置得分
	private int[] iswinxy=new int[6];//胜利棋子坐标。
	public Chess(){}	
	public void goback(int xy) {chessboard[xy/100][xy%100]=0;}
	public void init(){
		FIRST = 1;//默认人类先手
		for(int i = 0; i  < CHESSBOARD_SIZE; i++)
			for(int j = 0; j < CHESSBOARD_SIZE; j++){chessboard[i][j] = 0; score[i][j] = 0;}//初始化棋盘 和分数表
	}
	public void addChessman(int x, int y, int owner){chessboard[x][y] = owner;}	//落子
	public boolean isLegal(int x, int y){//棋盘边界判断 以及是否落子	//判断落子位置是否合法
		if(x >=0 && x < CHESSBOARD_SIZE && y >= 0 && y < CHESSBOARD_SIZE && chessboard[x][y] == 0)return true;
		return false;
	}
	public boolean isWin(int x, int y, int owner){//判断哪方赢了（必定有刚落的子引发，因此只需判断刚落子的周围），owner为-1代表机器，owner为1代表人类
	 int sum = 0,k=1; for(int i=0;i<6;i++)iswinxy[i]=0;
	 iswinxy[0]=x*100+y;
	 //判断纵向上下方向
	 for(int i = x - 1; i >= 0; i--)if(chessboard[i][y] == owner){ sum++;iswinxy[k++]=i*100+y;}else break;	
	 for(int i = x + 1; i < CHESSBOARD_SIZE; i++)if(chessboard[i][y] == owner){sum++;iswinxy[k++]=i*100+y;}else break;
     if(sum >= 4) {return true;}
	 //判断横向向左右边
     sum = 0;k=1;
	 for(int i = y - 1; i >= 0; i--)if(chessboard[x][i] == owner){sum++;iswinxy[k++]=x*100+i;}else break;
	 for(int i = y + 1; i < CHESSBOARD_SIZE; i++)if(chessboard[x][i] == owner){sum++;iswinxy[k++]=x*100+i;}else break;
	 if(sum >= 4) {return true;}
	 //判断左上角到右下角方向上侧和左上角到右下角方向下侧（正斜线）
	 sum = 0;k=1;
	 for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j-- )if(chessboard[i][j] == owner){sum++;iswinxy[k++]=i*100+j;}else break;
	 for(int i = x + 1, j = y + 1; i < CHESSBOARD_SIZE && j < CHESSBOARD_SIZE; i++, j++ )if(chessboard[i][j] == owner){sum++;iswinxy[k++]=i*100+j;}else break;
	 if(sum >= 4) {return true;}
	 //判断右上角到左下角方向上侧和右上角到左下角方向下侧（反斜线）
	  sum = 0;k=1;
	  for(int i = x + 1, j = y - 1; i < CHESSBOARD_SIZE && j >= 0; i++, j--)if(chessboard[i][j] == owner){sum++;iswinxy[k++]=i*100+j;}else break;
	  for(int i = x - 1, j = y + 1; i >= 0 && j < CHESSBOARD_SIZE; i--, j++ )if(chessboard[i][j] == owner){sum++;iswinxy[k++]=i*100+j;}else break;
	  if(sum >= 4) {return true;}
       return false;
	}
	public int[] getisWinChess() { return iswinxy;}//获取胜利棋子
	public int[][] getchessborad() { return chessboard;}//获取胜利棋子
	//确定机器落子位置
	//使用五元组评分算法
	//算法思路：对15X15的572个五元组分别评分，一个五元组的得分就是该五元组为其中每个位置贡献的分数，  一个位置的分数就是其所在所有五元组分数之和。所有空位置中分数最高的那个位置就是落子位置。
	public Location searchLocation(){
		int humanChessmanNum = 0;//五元组中的黑棋数量
		int machineChessmanNum = 0;//五元组中的白棋数量
		int tupleScoreTmp = 0;//五元组得分临时变量
		int goalX = -1;//目标位置x坐标
		int goalY = -1;//目标位置y坐标	
		int maxScore = -1;//最大分数
		for(int i = 0; i  < CHESSBOARD_SIZE; i++)//每次都初始化下score评分数组（全部置零）////每次机器找寻落子位置，评分都重新算一遍
		 for(int j = 0; j < CHESSBOARD_SIZE; j++) score[i][j] = 0;
		//1.扫描横向和横向的15个行
		for(int i = 0; i < CHESSBOARD_SIZE; i++){
			for(int j = 0; j < CHESSBOARD_SIZE-4; j++){
				for(int k = j; k < j + 5; k++)//每次加五个状态
					if(chessboard[i][k] == -1) machineChessmanNum++;else if(chessboard[i][k] == 1)humanChessmanNum++;
			  tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);//为该五元组的每个位置添加分数
				for(int k = j; k < j + 5; k++)score[i][k] += tupleScoreTmp;
				humanChessmanNum =tupleScoreTmp=machineChessmanNum=0;//清零
				for(int k = j; k < j + 5; k++){//每次加五个状态
					if(chessboard[k][i] == -1) machineChessmanNum++;
					else if(chessboard[k][i] == 1)humanChessmanNum++;
				 }
			  tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
				for(int k = j; k < j + 5; k++)score[k][i] += tupleScoreTmp;
			   humanChessmanNum =tupleScoreTmp=machineChessmanNum=0;//清零
			}
		}
		//3.扫描右上角到左下角上侧部分
		for(int i = CHESSBOARD_SIZE-1; i >= 4; i--){
			for(int k = i, j = 0; j < CHESSBOARD_SIZE && k >= 0; j++, k--){
				int m = k, n = j;
				while(m > k - 5 && k - 5 >= -1){
					if(chessboard[m][n] == -1) machineChessmanNum++;
					else if(chessboard[m][n] == 1)humanChessmanNum++;
					m--;n++;
				}
				if(m == k-5){//注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					for(m = k, n = j; m > k - 5 ; m--, n++)score[m][n] += tupleScoreTmp;//为该五元组的每个位置添加分数
				}
				  humanChessmanNum =tupleScoreTmp=machineChessmanNum=0;//清零
			}
		}
		//4.扫描右上角到左下角下侧部分
		for(int i = 1; i < CHESSBOARD_SIZE; i++){
			for(int k = i, j = CHESSBOARD_SIZE-1; j >= 0 && k < CHESSBOARD_SIZE; j--, k++){
				int m = k,n=j;
				while(m < k + 5 && k + 5 <= CHESSBOARD_SIZE){
					if(chessboard[n][m] == -1) machineChessmanNum++;
					else if(chessboard[n][m] == 1)humanChessmanNum++;
					m++;n--;
				}
				if(m == k+5){//注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					for(m = k, n = j; m < k + 5; m++, n--)score[n][m] += tupleScoreTmp;//为该五元组的每个位置添加分数
				}
				humanChessmanNum =tupleScoreTmp=machineChessmanNum=0;//清零
			}
		}
		//5.扫描左上角到右下角部分
		for(int i = 0; i < CHESSBOARD_SIZE-4; i++){
			for(int k = i, j = 0; j < CHESSBOARD_SIZE && k < CHESSBOARD_SIZE; j++, k++){
				int m,n;
				for(m=k,n=j;m < k + 5 && k + 5 <= CHESSBOARD_SIZE;m++,n++){
					if(chessboard[m][n] == -1) machineChessmanNum++;
					else if(chessboard[m][n] == 1)humanChessmanNum++;
				}
				if(m == k + 5){//注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					for(m = k, n = j; m < k + 5; m++, n++)score[m][n] += tupleScoreTmp;	//为该五元组的每个位置添加分数
				}
				  humanChessmanNum =tupleScoreTmp=machineChessmanNum=0;//清零
			   for(m=k,n=j;m < k + 5 && k + 5 <= CHESSBOARD_SIZE;m++,n++){
						if(chessboard[n][m] == -1) machineChessmanNum++;
						else if(chessboard[n][m] == 1)humanChessmanNum++;
				}
				if(m == k + 5){//注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
				   //为该五元组的每个位置添加分数
					for(m = k, n = j; m < k + 5; m++, n++)score[n][m] += tupleScoreTmp;
				 }
				humanChessmanNum =tupleScoreTmp=machineChessmanNum=0;//清零 
			}
		}		
		for(int i = 0; i < CHESSBOARD_SIZE; i++)//从空位置中找到得分最大的位置(确定落子位置)
			for(int j = 0; j < CHESSBOARD_SIZE; j++)
				if(chessboard[i][j] == 0 && score[i][j] > maxScore){goalX = i; goalY = j; maxScore = score[i][j];}
				else if(chessboard[i][j] != 0)score[i][j]=0;
		if(goalX != -1 && goalY != -1)return new Location(goalX, goalY, -1);
		return new Location(-1, -1, -1);//没找到坐标说明平局了
	}
	public int[][] getScores() {return score;}
	//各种五元组情况评分表
	public int tupleScore(int humanChessmanNum, int machineChessmanNum){
		int[] hb= {0,15,400,1800,100000};//人类落子 1，2，3，4 分别对应 15 400 1800，100000
		int[] mb= {0,35,800,15000,800000};//机器落子 1，2，3，4分别对应 35 800 15000 800000
		//1.既有人类落子，又有机器落子，判分为0
		if(humanChessmanNum > 0 && machineChessmanNum > 0)return 0;
		if(humanChessmanNum == 0 && machineChessmanNum == 0)return 7;
		if(machineChessmanNum >0)return mb[machineChessmanNum];
		if(humanChessmanNum>0)return hb[humanChessmanNum];
		return -1;//若是其他结果肯定出错了。这行代码根本不可能执行
	}
}
//自定义按钮
 class MyButton extends JButton  {
	 private Color quit = new Color(205, 255, 205);// 离开时颜色
	 public static final Color BUTTON_COLOR1 = new Color(205, 255, 205);
	 public static final Color BUTTON_COLOR2 = new Color(51, 154, 47);
	 public MyButton(String s) {
	  super(s);	
		setFont(new java.awt.Font(null,  1,  22)); //
		setBorder(BorderFactory.createRaisedBevelBorder());//凸出
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	  setContentAreaFilled(false);// 是否显示外围矩形区域 选否
	 }
	 public void paintComponent(Graphics g) {
	  g.setColor(quit);
	  g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,20, 20);
	  super.paintComponent(g);
	 }
	 public void paintBorder(Graphics g) { g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1,20, 20);}
 }
 
 
 
 
 
 
 
 
 
 

