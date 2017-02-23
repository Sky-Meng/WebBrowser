import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class WebBrowser extends JFrame implements HyperlinkListener,ActionListener {
	
	//建立工具栏用来显示地址栏
	JToolBar bar=new JToolBar();
	
	//建立网页显示页面
	JTextField jurl=new JTextField(60);  //建立一个新的并设置其初始长度为60,用于地址输入栏
	JEditorPane jEditorPane1=new JEditorPane();  //Web页的显示器
	JScrollPane scrollpane=new JScrollPane(jEditorPane1); //滚动条
	
	JFileChooser chooser=new JFileChooser(); //文件选择器
	JFileChooser chooser1=new JFileChooser();
	String htmlSource;
	JWindow window=new JWindow(WebBrowser.this);
	
	JButton button2=new JButton("窗口还原");
	Toolkit toolkit=Toolkit.getDefaultToolkit(); //java开发工具包 是抽象类
	
	//建立菜单栏
	JMenuBar jMenuBar1= new JMenuBar();
	//建立文件菜单组
	JMenu fileMenu=new JMenu("文件（F）");
	//建立文件菜单项
	JMenuItem saveAsItem=new JMenuItem("另存为（S）…");
	JMenuItem exitItem=new JMenuItem("退出（X）");
	
	//建立编辑菜单栏
	JMenu editMenu=new JMenu("编辑（E）");
	JMenuItem backItem=new JMenuItem("后退");
	JMenuItem forwardItem=new JMenuItem("前进");
	
	//建立视图菜单栏
	JMenu viewMenu=new JMenu("视图（V）");
	JMenuItem fullscreenItem=new JMenuItem("全屏（U）");
	JMenuItem sourceItem= new JMenuItem("查看源码（C）");
	JMenuItem reloadItem=new JMenuItem("刷新（R）");
	
	//建立帮助菜单栏
	JMenu helpMenu=new JMenu("帮助（H）");
	JMenuItem aboutItem=new JMenuItem("关于（A）");
	
	//建立工具栏
	JToolBar toolBar=new JToolBar();
	//建立工具栏中的按钮主件
	JButton  picSave=new JButton("另存为");
	JButton	 picBack=new JButton("后退");
	JButton  picForward=new JButton("前进");
	JButton  picView=new JButton("查看源代码");
	JButton  picExit=new JButton("退出");
	
	JLabel label=new JLabel("地址");
	JButton button=new JButton("转向");
	
	Box adress=Box.createHorizontalBox(); //让两个组件之间有一个固定的空间量  布局管理器的一个轻量级容器
	//利用 ArrayList ,来存放历史地址
	private ArrayList history=new ArrayList();
	//整形变量，表示历史记录的访问顺序
	private int historyIndex;
	
	
	
	 public WebBrowser() {
		setTitle("小宇网页浏览器");
		setResizable(false);  //窗口大小是否可调 true可调   false不可调
		//setLocation(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //三种关闭方式
		
		//为 jEditorPane1 添加事件监听
		jEditorPane1.addHyperlinkListener(this);
		
		//为文件菜单设置热键（Alt+F）
		fileMenu.setMnemonic('F');
		//为另存为设置两组快捷键 上面为Alt+s 后面为Ctrl+S
		saveAsItem.setMnemonic('S');
		saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		exitItem.setMnemonic('Q');
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		//将保存菜单项加入菜单组中
		fileMenu.add(saveAsItem);
		//在菜单中添加隔离
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		//设置编辑菜单项
		editMenu.setMnemonic('E');
		backItem.setMnemonic('B');
		backItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		forwardItem.setMnemonic('D');
		forwardItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		editMenu.add(backItem);
		editMenu.add(forwardItem);
		
		//设置视图菜单选项
		viewMenu.setMnemonic('V');
		fullscreenItem.setMnemonic('U');
		fullscreenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		sourceItem.setMnemonic('C');
		sourceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		reloadItem.setMnemonic('R');
		reloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		viewMenu.add(fullscreenItem);
		viewMenu.add(sourceItem);
		viewMenu.addSeparator();
		viewMenu.add(reloadItem);
		
		//设置帮助菜单选项
		helpMenu.setMnemonic('H');
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		helpMenu.add(aboutItem);
		
		//将所有的菜单选项添加到菜单栏
		jMenuBar1.add(fileMenu);
		jMenuBar1.add(editMenu);
		jMenuBar1.add(viewMenu);
		jMenuBar1.add(helpMenu);
		setJMenuBar(jMenuBar1);
		
		//添加工具栏按钮
		toolBar.add(picSave);
		toolBar.addSeparator();
		toolBar.add(picBack);
		toolBar.add(picForward);
		toolBar.addSeparator();
		toolBar.add(picView);
		toolBar.addSeparator();
		toolBar.add(picExit);
		
		//添加地址栏及按钮
		adress.add(label);
		adress.add(jurl);
		adress.add(button);
		bar.add(adress);
		
		Container contentpane=getContentPane();
		scrollpane.setPreferredSize(new Dimension(100, 500));  //设置一个滚动面板
		contentpane.add(scrollpane, BorderLayout.SOUTH);      //添加到容器中设置方向为下方
		contentpane.add(bar, BorderLayout.CENTER);			// 将地址栏放到容器的中心位置
		contentpane.add(toolBar, BorderLayout.NORTH);		//将工具栏放到容器的上方
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO  自动生成方法存根
		
		
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO  自动生成方法存根
		
	}
	
	public static void main(String[] args) {
		  WebBrowser webBrowser=new WebBrowser();
		  webBrowser.pack();
		  webBrowser.setVisible(true);

	}

}
