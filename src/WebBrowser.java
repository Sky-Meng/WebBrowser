import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.AbstractDocument.Content;

public class WebBrowser extends JFrame implements HyperlinkListener,ActionListener {
	
	//����������������ʾ��ַ��
	JToolBar bar=new JToolBar();
	
	//������ҳ��ʾҳ��
	JTextField jurl=new JTextField(60);  //����һ���µĲ��������ʼ����Ϊ60,���ڵ�ַ������
	JEditorPane jEditorPane1=new JEditorPane();  //Webҳ����ʾ��
	JScrollPane scrollpane=new JScrollPane(jEditorPane1); //������
	
	JFileChooser chooser=new JFileChooser(); //�ļ�ѡ����
	JFileChooser chooser1=new JFileChooser();
	String htmlSource;
	JWindow window=new JWindow(WebBrowser.this);
	
	JButton button2=new JButton("���ڻ�ԭ");
	Toolkit toolkit=Toolkit.getDefaultToolkit(); //java�������߰� �ǳ�����
	
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
	
	//����������
	JToolBar toolBar=new JToolBar();
	//�����������еİ�ť����
	JButton  picSave=new JButton("���Ϊ");
	JButton	 picBack=new JButton("����");
	JButton  picForward=new JButton("ǰ��");
	JButton  picView=new JButton("�鿴Դ����");
	JButton  picExit=new JButton("�˳�");
	
	JLabel label=new JLabel("��ַ");
	JButton button=new JButton("ת��");
	
	Box adress=Box.createHorizontalBox(); //���������֮����һ���̶��Ŀռ���  ���ֹ�������һ������������
	//���� ArrayList ,�������ʷ��ַ
	private ArrayList history=new ArrayList();
	//���α�������ʾ��ʷ��¼�ķ���˳��
	private int historyIndex;
	
	
	
	 public WebBrowser() {
		setTitle("С����ҳ�����");
		setResizable(false);  //���ڴ�С�Ƿ�ɵ� true�ɵ�   false���ɵ�
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
		
		//��ӹ�������ť
		toolBar.add(picSave);
		toolBar.addSeparator();
		toolBar.add(picBack);
		toolBar.add(picForward);
		toolBar.addSeparator();
		toolBar.add(picView);
		toolBar.addSeparator();
		toolBar.add(picExit);
		
		//��ӵ�ַ������ť
		adress.add(label);
		adress.add(jurl);
		adress.add(button);
		bar.add(adress);
		
		Container contentpane=getContentPane();
		scrollpane.setPreferredSize(new Dimension(100, 500));  //����һ���������
		contentpane.add(scrollpane, BorderLayout.SOUTH);      //��ӵ����������÷���Ϊ�·�
		contentpane.add(bar, BorderLayout.CENTER);			// ����ַ���ŵ�����������λ��
		contentpane.add(toolBar, BorderLayout.NORTH);		//���������ŵ��������Ϸ�
		
		//Ϊ��������¼�����
		button.addActionListener(this);
		jurl.addActionListener(this);
		saveAsItem.addActionListener(this);
		picSave.addActionListener(this);
		exitItem.addActionListener(this);
		picExit.addActionListener(this);
		backItem.addActionListener(this);
		picBack.addActionListener(this);
		forwardItem.addActionListener(this);
		picForward.addActionListener(this);
		fullscreenItem.addActionListener(this);
	}
	
	/**
	 * ʵ�ּ������ӿڵ�actionPerformed ����
	 * ��Ҫʵ�ְ�ť�Ͳ˵�������
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO  �Զ����ɷ������
		String url="";
		//����ת��ť getSource()��ȡ����
		if (e.getSource()==button) {
			//��õ�ַ������
			url=jurl.getText();
			//url ��Ϊ""��������http://��ͷ
			if (url.length()>0&&url.startsWith("http://")) {
				try {
					//jEditorPane1������ʾurl��������
					jEditorPane1.setPage(url);
					//��url������ӵ���ʷ��¼
					history.add(url);
					//historyIndex ����ֵ��Ϊhistory���󳤶�-1
					historyIndex=history.size()-1;
					//��ҳ�����ó�Ϊ�༭״̬����ᵼ��������ɾԴ�뵼����ҳ��ʾ������
					jEditorPane1.setEditable(false);
					//���²���
					jEditorPane1.revalidate();
				} catch (Exception e1) {
					//�������ʧ�ܣ������Ի����޷��򿪸���ҳ��
					JOptionPane.showMessageDialog(WebBrowser.this, "�޷��򿪸���ҳ", "С�����������", JOptionPane.ERROR_MESSAGE);
					
				}
			}
			////url ��Ϊ""�����Ҳ���http://��ͷ
			else if (url.length()>0&&!url.startsWith("http://")) {
				//��urlǰ�����"http://"
				url="http://"+url;
				try {
					//jEditorPane1������ʾurl��������
					jEditorPane1.setPage(url);
					//��url������ӵ���ʷ��¼
					history.add(url);
					//historyIndex ����ֵ��Ϊhistory���󳤶�-1
					historyIndex=history.size()-1;
					//��ҳ�����ó�Ϊ�༭״̬����ᵼ��������ɾԴ�뵼����ҳ��ʾ������
					jEditorPane1.setEditable(false);
					//���²���
					jEditorPane1.revalidate();
				} catch (Exception e1) {
					//�������ʧ�ܣ������Ի����޷��򿪸���ҳ��
					JOptionPane.showMessageDialog(WebBrowser.this, "�޷��򿪸���ҳ", "С�����������", JOptionPane.ERROR_MESSAGE);
					
				}
			}
			
			else if (url.length()==0) {
				JOptionPane.showMessageDialog(WebBrowser.this, "�����������ӵ�ַ", "С�����������", JOptionPane.ERROR_MESSAGE);

			}
			
		}else if (e.getSource()==jurl) {

			//��õ�ַ������
			url=jurl.getText();
			//url ��Ϊ""��������http://��ͷ
			if (url.length()>0&&url.startsWith("http://")) {
				try {
					//jEditorPane1������ʾurl��������
					jEditorPane1.setPage(url);
					//��url������ӵ���ʷ��¼
					history.add(url);
					//historyIndex ����ֵ��Ϊhistory���󳤶�-1
					historyIndex=history.size()-1;
					//��ҳ�����ó�Ϊ�༭״̬����ᵼ��������ɾԴ�뵼����ҳ��ʾ������
					jEditorPane1.setEditable(false);
					//���²���
					jEditorPane1.revalidate();
				} catch (Exception e1) {
					//�������ʧ�ܣ������Ի����޷��򿪸���ҳ��
					JOptionPane.showMessageDialog(WebBrowser.this, "�޷��򿪸���ҳ", "С�����������", JOptionPane.ERROR_MESSAGE);
					
				}
			}
			////url ��Ϊ""�����Ҳ���http://��ͷ
			else if (url.length()>0&&!url.startsWith("http://")) {
				//��urlǰ�����"http://"
				url="http://"+url;
				try {
					//jEditorPane1������ʾurl��������
					jEditorPane1.setPage(url);
					//��url������ӵ���ʷ��¼
					history.add(url);
					//historyIndex ����ֵ��Ϊhistory���󳤶�-1
					historyIndex=history.size()-1;
					//��ҳ�����ó�Ϊ�༭״̬����ᵼ��������ɾԴ�뵼����ҳ��ʾ������
					jEditorPane1.setEditable(false);
					//���²���
					jEditorPane1.revalidate();
				} catch (Exception e1) {
					//�������ʧ�ܣ������Ի����޷��򿪸���ҳ��
					JOptionPane.showMessageDialog(WebBrowser.this, "�޷��򿪸���ҳ", "С�����������", JOptionPane.ERROR_MESSAGE);
					
				}
			}
			
			else if (url.length()==0) {
				JOptionPane.showMessageDialog(WebBrowser.this, "�����������ӵ�ַ", "С�����������", JOptionPane.ERROR_MESSAGE);

			}
		}
		//���Ϊ��
		else if (e.getSource()==picSave||e.getSource()==saveAsItem) {
			url=jurl.getText().toString().trim();//trim() ȥ�հ�
			if (url.length()>0&&!url.startsWith("http://")) {
				url="http://"+url;
			}if (!url.equals("")) {
				saveFile();
			} else {
				JOptionPane.showMessageDialog(WebBrowser.this, "�����������ӵ�ַ", "С�����������", JOptionPane.ERROR_MESSAGE);
			}
		}
		//�˳�
		else if (e.getSource()==exitItem||e.getSource()==picExit) {
			System.exit(0);
		}
		//����
		else if (e.getSource()==backItem||e.getSource()==picBack) {
			historyIndex --;
			if(historyIndex<0)
				historyIndex=0;
			url=jurl.getText();
			try {
				//���history�����б���ַ֮ǰ�ķ��ʵ�ַ
				url=(String) history.get(historyIndex);
				jEditorPane1.setPage(url);
				jurl.setText(url.toString());
				//��ҳ�����ó�Ϊ�༭״̬����ᵼ��������ɾԴ�뵼����ҳ��ʾ������
				jEditorPane1.setEditable(false);
				//���²���
				jEditorPane1.revalidate();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//ǰ��
		else if (rootPaneCheckingEnabled) {

			historyIndex ++;
			if(historyIndex>=history.size())
				historyIndex=history.size()-1;
			url=jurl.getText();
			try {
				//���history�����б���ַ֮��ķ��ʵ�ַ
				url=(String) history.get(historyIndex);
				jEditorPane1.setPage(url);
				jurl.setText(url.toString());
				//��ҳ�����ó�Ϊ�༭״̬����ᵼ��������ɾԴ�뵼����ҳ��ʾ������
				jEditorPane1.setEditable(false);
				//���²���
				jEditorPane1.revalidate();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		//ȫ��
		else if (e.getSource()==fullscreenItem) {
			boolean add_button2=true;
			//�����Ļ��С
			Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
			Container content=window.getContentPane();
			content.add(bar, "North");
			content.add(scrollpane, "Center");
			
			//button2 Ϊ������ȫ������Ļ�ԭ��ť
			if (add_button2==true) {
				bar.add(button2);
			}
			//Ϊbutton2����¼�
			button2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					WebBrowser.this.setEnabled(true);
					window.remove(bar);
					window.remove(toolBar);
					window.remove(scrollpane);
					window.setVisible(false);
					
					scrollpane.setPreferredSize(new Dimension(100, 500));  
					getContentPane().add(scrollpane, BorderLayout.SOUTH);      
					getContentPane().add(bar, BorderLayout.CENTER);			
					getContentPane().add(toolBar, BorderLayout.NORTH);	
					bar.remove(button2);
					pack();
				}
			});
			window.setSize(size);
			window.setVisible(true);
		}
		
	}
	
	
	
	/**
	 * 
	 */
	private void saveFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO  �Զ����ɷ������
			try {
				if (e.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
				jEditorPane1.setPage(e.getURL());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  WebBrowser webBrowser=new WebBrowser();
		  webBrowser.pack();
		  webBrowser.setVisible(true);

	}

}
