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
	
	//����������������ʾ��ַ��
	JToolBar bar=new JToolBar();
	
	//������ҳ��ʾҳ��
	JTextField jurl=new JTextField(60);  //����һ���µĲ��������ʼ����Ϊ60
	JEditorPane jEditorPane1=new JEditorPane();
	JScrollPane scrollpane=new JScrollPane(jEditorPane1);
	
	JFileChooser chooser=new JFileChooser();
	JFileChooser chooser1=new JFileChooser();
	String htmlSource;
	JWindow window=new JWindow(WebBrowser.this);
	
	JButton button2=new JButton("���ڻ�ԭ");
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	
	//�����˵���
	JMenuBar jMenuBar1= new JMenuBar();
	//�����ļ��˵���
	JMenu fileMenu=new JMenu("�ļ���F��");
	//�����ļ��˵���
	JMenuItem saveAsItem=new JMenuItem("���Ϊ��S����");
	JMenuItem exitItem=new JMenuItem("�˳���X��");
	
	//�����༭�˵���
	JMenu editMenu=new JMenu("�༭��E��");
	JMenuItem backItem=new JMenuItem("����");
	JMenuItem forwardItem=new JMenuItem("ǰ��");
	
	//������ͼ�˵���
	JMenu viewMenu=new JMenu("��ͼ��V��");
	JMenuItem fullscreenItem=new JMenuItem("ȫ����U��");
	JMenuItem sourceItem= new JMenuItem("�鿴Դ�루C��");
	JMenuItem reloadItem=new JMenuItem("ˢ�£�R��");
	
	//���������˵���
	JMenu helpMenu=new JMenu("������H��");
	JMenuItem aboutItem=new JMenuItem("���ڣ�A��");
	
	
	 public WebBrowser() {
		setTitle("��ҳ�����");
		setResizable(true);  //���ڴ�С�Ƿ�ɵ� true�ɵ�   false���ɵ�
		//setLocation(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���ֹرշ�ʽ
		
		//Ϊ jEditorPane1 ����¼�����
		jEditorPane1.addHyperlinkListener(this);
		
		//Ϊ�ļ��˵������ȼ���Alt+F��
		fileMenu.setMnemonic('F');
		//Ϊ���Ϊ���������ݼ� ����ΪAlt+s ����ΪCtrl+S
		saveAsItem.setMnemonic('S');
		saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		exitItem.setMnemonic('Q');
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		//������˵������˵�����
		fileMenu.add(saveAsItem);
		//�ڲ˵�����Ӹ���
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		//���ñ༭�˵���
		editMenu.setMnemonic('E');
		backItem.setMnemonic('B');
		backItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		forwardItem.setMnemonic('D');
		forwardItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		editMenu.add(backItem);
		editMenu.add(forwardItem);
		
		//������ͼ�˵�ѡ��
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
		
		//���ð����˵�ѡ��
		helpMenu.setMnemonic('H');
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		helpMenu.add(aboutItem);
		
		//�����еĲ˵�ѡ����ӵ��˵���
		jMenuBar1.add(fileMenu);
		jMenuBar1.add(editMenu);
		jMenuBar1.add(viewMenu);
		jMenuBar1.add(helpMenu);
		setJMenuBar(jMenuBar1);
		
		
		Container contentpane=getContentPane();
		scrollpane.setPreferredSize(new Dimension(100, 500));  //����һ���������
		contentpane.add(scrollpane, BorderLayout.SOUTH);      //��ӵ����������÷���Ϊ�·�
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO  �Զ����ɷ������
		
		
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO  �Զ����ɷ������
		
	}
	
	public static void main(String[] args) {
		  WebBrowser webBrowser=new WebBrowser();
		  webBrowser.pack();
		  webBrowser.setVisible(true);

	}

}
