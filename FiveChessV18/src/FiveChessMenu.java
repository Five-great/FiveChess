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
	private JMenuBar menu;//�˵���
	private JMenu viewmenu,helpmenu;//�˵����еġ�ѡ��˵�
	private Action replayOption;//˵��
	private Action funOption;//����
	private Action aboutOption;//�������еġ����ڡ�ѡ��
	 public FiveChessMenu() {
		 setTitle("java��������-five");//������Ϸ���洰��
		  JLayeredPane	layeredPane=new JLayeredPane();
			JPanel jp = new JPanel();
			jp.setBounds(0,0,1050,815);
			jp.add(new JLabel(new ImageIcon("./src/Menu.png")));
		 JButton b1 =new MyButton("       ��  ��  ��  ս         ");
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
		  JButton b2 =new MyButton("        ��  ��  ��  ս         ");
		  b2.setSize(240, 50);
		  b2.setLocation(400, 350);
		  b2.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				 new FiveChessWindows().init(false,1);
	                dispose();
				}
			}); 
		  JButton b3 =new MyButton("     ��          ��   ");
		  b3.setSize(180, 50);
		  b3.setLocation(435, 410);
		  b3.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {System.exit(0);}});//��jp�ŵ���ײ㡣
			layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);	//��jb�ŵ���һ��ĵط�
			layeredPane.add(b1,JLayeredPane.MODAL_LAYER);
			layeredPane.add(b2,JLayeredPane.MODAL_LAYER);
			layeredPane.add(b3,JLayeredPane.MODAL_LAYER);
			   menu = new JMenuBar();//�����˵���
			    viewmenu = new JMenu("            ��  ��          ");//�����˵����еġ�ѡ��˵�
			    helpmenu= new JMenu("      ��  ��       ");
				menu.add(viewmenu);
				//�ѡ�����һ�̡������������֡������������֡����롰ѡ���������
				replayOptionInit();
				viewmenu.add(replayOption);
				viewmenu.addSeparator();//�ָ���
				funOptionInit();
				viewmenu.add(funOption);
				aboutOptionInit();
				helpmenu.add(aboutOption);
				menu.add(helpmenu);//��menu����Ϊframe�Ĳ˵���
				setJMenuBar(menu);//��menu����Ϊframe�Ĳ˵���
				
				layeredPane.add(menu,JLayeredPane.MODAL_LAYER);
				setLayeredPane(layeredPane);
			//����frame�������Ͻ�ͼ��
			 setIconImage(this.getToolkit().getImage("./src/favicon.png"));
			 setLocation(300, 100);
			 setSize(1050, 814);
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setVisible(true);
		 
	 }
	 
		//ѡ�����Ӧ�Ĵ����¼�
		public void replayOptionInit(){
			replayOption = new AbstractAction("�� Ϸ ˵ �� ", new ImageIcon("./src/��ť.png")){
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null, "<������Ϸ���� ��Ԫ���׷� ʵ�� �˻���ս> ","��  Ϸ   ˵   ��     ", JOptionPane.PLAIN_MESSAGE);
				}
			};
		}
		public void funOptionInit(){
			funOption = new AbstractAction("�� �� ˵ �� ", new ImageIcon("./src/��ť.png")){
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null, "<������Ϸ�淨ͬһ���������淨 ������ �˻���ս> "," ��   �� ˵   ��     ", JOptionPane.PLAIN_MESSAGE);
				}
			};
		}
		public void aboutOptionInit(){
			aboutOption = new AbstractAction("��   �� ", new ImageIcon("./src/��ť.png")){
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null, "<����Ϸ�� ������Ϣ����ѧԺ2016��ͨ�Ź���2�� >  \n     <            ���           ���             �ſ�             >\n                    <                  ��ͬ����                 >","   ��    ��    ", JOptionPane.PLAIN_MESSAGE);
				} 
			};
		}

	 
}