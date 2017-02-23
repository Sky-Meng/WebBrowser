import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
	JTextField jurl=new JTextField(60);  //建立一个新的并设置其初始长度为60
	JEditorPane jEditorPane1=new JEditorPane();
	JScrollPane scrollpane=new JScrollPane(jEditorPane1);
	
	JFileChooser chooser=new JFileChooser();
	JFileChooser chooser1=new JFileChooser();
	String htmlSource;
	JWindow window=new JWindow(WebBrowser.this);
	
	JButton button2=new JButton("窗口还原");
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	
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
	
	
	 public WebBrowser() {
		setTitle("网页浏览器");
		setResizable(true);  //窗口大小是否可调 true可调   false不可调
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
		
		
		Container contentpane=getContentPane();
		scrollpane.setPreferredSize(new Dimension(100, 500));  //设置一个滚动面板
		contentpane.add(scrollpane, BorderLayout.SOUTH);      //添加到容器中设置方向为下方
		
		
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
