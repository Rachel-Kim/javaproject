package ClientUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.HashMap;

//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.NebulaBrickWallSkin;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import net.regex_iciba;
import net.regex_bing;
import net.regex_youdao;
import DataBase.DataBase;
import DataBase.DictionaryManager;
import DataBase.DataBaseConnectionPool;
public class UI {

	private JFrame frame;
	private JTextField textField;
	public DataOutputStream toServer;
	public DataInputStream fromServer;
	public static String inputWord;
	public static Socket socket;
	public static int numzanjinshan,numzanyoudao,numzanbing;
	/**
	 * Launch the application.
	 */
	int type=0;
	int count1_1=0,count1_2=0,count2_1=0,count2_2=0,count3_1=0,count3_2=0;
	//boolean login=false;
	public static void main(String[] args) {
		//System.out.println("Hello,This is the sever!!");
		//DataBase.connect();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
			            UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			            JFrame.setDefaultLookAndFeelDecorated(true);
			            JDialog.setDefaultLookAndFeelDecorated(true);
			            SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
			          

			          
			          SubstanceLookAndFeel.setSkin(new NebulaBrickWallSkin());
			          SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
			          SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
			          SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
			            SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
			            //SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitePainter());
			        } catch (Exception e) {
			            System.err.println("Something went wrong!");
			        }
					
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public UI() throws UnknownHostException, IOException {
		initialize();
		socket=new Socket("localhost",8000);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
			JCheckBox checkBox_2 = new JCheckBox("必应");
			checkBox_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			checkBox_2.setBounds(193, 37, 67, 23);
			panel.add(checkBox_2);
		
		JCheckBox checkBox_1 = new JCheckBox("有道");
		checkBox_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		checkBox_1.setBounds(92, 37, 78, 23);
		panel.add(checkBox_1);
		
		JCheckBox checkBox = new JCheckBox("金山");
		checkBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		checkBox.setBounds(10, 37, 55, 23);
		panel.add(checkBox);
		
		textField = new JTextField();
		
		textField.setBounds(10, 10, 314, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnSearch.setBounds(334, 9, 93, 23);
		panel.add(btnSearch);
	
		
		JButton btnSignIn = new JButton("sign in");
		btnSignIn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				UI2 frame2=new UI2();
				frame2.setVisible(true);
				//UI2 window = new UI2();
				//window.frmSignin.setVisible(true);
			}
		});
		btnSignIn.setBounds(541, 9, 93, 23);
		panel.add(btnSignIn);
		
		
		
		JButton btnSend = new JButton("");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		//btnSend.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnSend.setBounds(421, 78, 61, 49);
		ImageIcon icon = new ImageIcon("image\\send.png");
	     btnSend.setIcon(icon);
		panel.add(btnSend);
		
		JButton btnGood = new JButton("▲");
		
		
		btnGood.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnGood.setBounds(373, 78, 45, 23);
		panel.add(btnGood);
		
		JLabel label = new JLabel("金山");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(10, 64, 54, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("有道");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(10, 137, 54, 15);
		panel.add(label_1);
		
		//JButton button = new JButton("send");
		JButton button = new JButton("");
		//button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.setBounds(421, 152, 61, 49);
		button.setIcon(icon);
		panel.add(button);
		
		JLabel label_2 = new JLabel("必应");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(10, 211, 54, 15);
		panel.add(label_2);
		
		//JButton button_2 = new JButton("send");
		JButton button_2 = new JButton("");
		//button_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_2.setBounds(421, 230, 61, 49);
		button_2.setIcon(icon);
		panel.add(button_2);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(20, 79, 341, 48);
		textArea_1.setVisible(true);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setBounds(20, 152, 341, 48);
		textArea_2.setVisible(true);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setEditable(false);
		textArea_3.setBounds(20, 229, 341, 48);
		textArea_3.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea_1);
		scrollPane.setVisible(true);
		scrollPane.setBounds(20, 79, 341, 48);
		panel.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea_2);
		scrollPane_1.setVisible(true);
		scrollPane_1.setBounds(20, 152, 341, 48);
		panel.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane(textArea_3);
		scrollPane_2.setVisible(true);
		scrollPane_2.setBounds(20, 229, 341, 48);
		panel.add(scrollPane_2);
		
		JButton btnBad = new JButton("▼");
		
		btnBad.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnBad.setBounds(373, 104, 45, 23);
		panel.add(btnBad);
		
		JButton button_4 = new JButton("▲");
		
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_4.setBounds(373, 152, 45, 23);
		panel.add(button_4);
		
		JButton button_1 = new JButton("▼");
		
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_1.setBounds(373, 177, 45, 23);
		panel.add(button_1);
		
		JButton button_3 = new JButton("▲");
		
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_3.setBounds(373, 229, 45, 23);
		panel.add(button_3);
		
		JButton button_5 = new JButton("▼");
		
		button_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_5.setBounds(373, 254, 45, 23);
		panel.add(button_5);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(513, 79, 144, 200);
		panel.add(scrollPane_3);
		
		JList list = new JList();
		scrollPane_3.setViewportView(list);
		
		JButton btnNewButton = new JButton("add friends");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UI5 frame5=new UI5();
				frame5.setVisible(true);
			}
		});
		btnNewButton.setBounds(541, 294, 116, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("log out");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(UI3.login==false){
					JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
				}
				else{
					int n=JOptionPane.showConfirmDialog(null,"确定退出该账号吗?","警告",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
					if(n==0){
						UI3.login=false;
						}
				}
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton_1.setBounds(421, 294, 93, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.setVisible(true);
		
		JButton btnNewButton_2 = new JButton("modify password");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(UI3.login==false){
					JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
				}
				else{
					UI4 frame4=new UI4();
					frame4.setVisible(true);
					}
				}
		});
		btnNewButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton_2.setBounds(254, 294, 143, 23);
		panel.add(btnNewButton_2);
		btnNewButton_2.setVisible(true);
		
		JButton btnNewButton_3 = new JButton("Message");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(UI3.login==false){
					JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
				}
			}
		});
		btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton_3.setBounds(132, 294, 102, 23);
		panel.add(btnNewButton_3);
		btnNewButton_3.setVisible(true);
		
		JButton btnNewButton_4 = new JButton("user's praise record");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(UI3.login==false){
					JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
				}
			}
		});
		btnNewButton_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton_4.setBounds(10, 294, 102, 23);
		panel.add(btnNewButton_4);
		btnNewButton_4.setVisible(true);
		
		JButton btnLogIn = new JButton("log in");
		btnLogIn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(UI3.login==true){
					JOptionPane.showMessageDialog( null , "您已登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
				}
				else{
					UI3 frame3=new UI3();
					frame3.setVisible(true);
					//if(UI3.login==true){
					//	btnNewButton_1.setVisible(true);
					//	btnNewButton_2.setVisible(true);
					//	btnNewButton_3.setVisible(true);
					//	btnNewButton_4.setVisible(true);
					//}
				}
			}
			
		});
		btnLogIn.setBounds(437, 9, 93, 23);
		panel.add(btnLogIn);
	//	ImageIcon image=new ImageIcon("Baidu.jpg");
	//	JLabel lblNewLabel = new JLabel(image);
	//	lblNewLabel.setBounds(507, 70, 111, 58);
	//	panel.add(lblNewLabel);
		
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				inputWord=textField.getText();
			}
		});
		
		//inputWord =textField.getText();
		//String inputWord=textField.getText();
		 btnSearch.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){
	            	type=judge_type(checkBox,checkBox_1,checkBox_2);
	            	
	            	try {
	            		
	            		searchWords(inputWord,type,label,scrollPane,textArea_1,btnSend,btnGood,btnBad
								,label_1,scrollPane_1,textArea_2,button,button_4,button_1
								,label_2,scrollPane_2,textArea_3,button_2,button_3,button_5);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	            }
	        });
	//jinshanaddpraise
		 btnGood.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					if(UI3.login==false){
						JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
					}
					else{
						try {
							toServer.writeInt(3);
							toServer.writeUTF("jinshan");
							toServer.writeUTF(inputWord);
							toServer.writeUTF(UI3.uid);
							String s=fromServer.readUTF();
							System.out.println(s);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			});
		 //jinshandelpraise
		 btnBad.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(UI3.login==false){
						JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
					}
					else{
						try {
							toServer.writeInt(4);
							toServer.writeUTF("jinshan");
							toServer.writeUTF(inputWord);
							toServer.writeUTF(UI3.uid);
							String s=fromServer.readUTF();
							System.out.println(s);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
		 //youdaoaddpraise
		 button_4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(UI3.login==false){
						JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
					}
					else{
						try {
							toServer.writeInt(3);
							toServer.writeUTF("youdao");
							toServer.writeUTF(inputWord);
							toServer.writeUTF(UI3.uid);
							String s=fromServer.readUTF();
							System.out.println(s);
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				}
			});
		 //youdaodelpraise
		 button_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(UI3.login==false){
						JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
					}
					else{
						try {
							toServer.writeInt(4);
							toServer.writeUTF("youdao");
							toServer.writeUTF(inputWord);
							toServer.writeUTF(UI3.uid);
							String s=fromServer.readUTF();
							System.out.println(s);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				}
			});
		 //biyingaddpraise
		 button_3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(UI3.login==false){
						JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
					}
					else{
						try {
							toServer.writeInt(3);
							toServer.writeUTF("bing");
							toServer.writeUTF(inputWord);
							toServer.writeUTF(UI3.uid);
							String s=fromServer.readUTF();
							System.out.println(s);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				}
			});
		 //biyingdelpraise
		 button_5.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(UI3.login==false){
						JOptionPane.showMessageDialog( null , "请先登录！" ,"错误", JOptionPane.ERROR_MESSAGE) ;
					}
					else{
						try {
							toServer.writeInt(4);
							toServer.writeUTF("bing");
							toServer.writeUTF(inputWord);
							toServer.writeUTF(UI3.uid);
							String s=fromServer.readUTF();
							System.out.println(s);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				}
			});
	}
	public void searchWords(String inputWord,int TYPE
			,JLabel label,JScrollPane scrollPane,JTextArea textArea_1,JButton btnSend,JButton btnGood,JButton btnBad
			,JLabel label_1,JScrollPane scrollPane_1,JTextArea textArea_2,JButton button,JButton button_4,JButton button_1
			,JLabel label_2,JScrollPane scrollPane_2,JTextArea textArea_3,JButton button_2,JButton button_3,JButton button_5) throws MalformedURLException, FileNotFoundException, SQLException{
		inputWord=textField.getText();
		textArea_1.setText("");
		textArea_2.setText("");
		textArea_3.setText("");
		boolean flag=true;
		if(!inputWord.matches("[a-zA-Z]*")){
			//textArea_1.setText("输入不合法，请输入英文单词！");
			//textArea_2.setText("输入不合法，请输入英文单词！");
			//textArea_3.setText("输入不合法，请输入英文单词！");
			flag=false;
		}
	
		if(type==1 && flag==false){
			textArea_1.setText("输入不合法，请输入英文单词！");
		}
		if(type==2 && flag==false){
			textArea_2.setText("输入不合法，请输入英文单词！");
		}
		if(type==3 && flag==false){
			textArea_3.setText("输入不合法，请输入英文单词！");
		}
		if(type==4 && flag==false){
			textArea_1.setText("输入不合法，请输入英文单词！");
			textArea_2.setText("输入不合法，请输入英文单词！");
			textArea_3.setText("输入不合法，请输入英文单词！");
		}
		if(type==5 && flag==false){
			textArea_1.setText("输入不合法，请输入英文单词！");
			textArea_2.setText("输入不合法，请输入英文单词！");
		}
		if(type==6 && flag==false){
			textArea_1.setText("输入不合法，请输入英文单词！");
			textArea_3.setText("输入不合法，请输入英文单词！");
		}
		if(type==7 && flag==false){
			textArea_2.setText("输入不合法，请输入英文单词！");
			textArea_3.setText("输入不合法，请输入英文单词！");
		}
		if(type==8 && flag==false){
			textArea_1.setText("输入不合法，请输入英文单词！");
			textArea_2.setText("输入不合法，请输入英文单词！");
			textArea_3.setText("输入不合法，请输入英文单词！");
		}
		try{
			//Socket socket=new Socket("localhost",8000);
			fromServer=new DataInputStream(socket.getInputStream());
			toServer=new DataOutputStream(socket.getOutputStream());
			toServer.writeInt(1);
    		toServer.writeUTF(inputWord);
    		numzanjinshan=fromServer.readInt();
    		numzanyoudao=fromServer.readInt();
    		numzanbing=fromServer.readInt();
    		toServer.writeInt(type);
			toServer.flush();
			
			/*Statement statement2 = DataBase.connect().createStatement();
			ResultSet resultSet2=statement2.executeQuery("select * from dictionary where Word='"+inputWord+"'");
			//int numzanbaidu,numzanyoudao,numzanbing;
			while(resultSet2.next()){
				numzanjinshan=resultSet2.getInt(2);
				numzanyoudao=resultSet2.getInt(3);
				numzanbing=resultSet2.getInt(4);
			}*/
			
		if(type==1 && flag==true){
			label.setVisible(true);
			scrollPane.setVisible(true);
			textArea_1.setVisible(true);
			btnSend.setVisible(true);
			btnGood.setVisible(true);
			btnBad.setVisible(true);
			
			label_1.setVisible(false);
			scrollPane_1.setVisible(false);
			textArea_2.setVisible(false);
			button.setVisible(false);
			button_1.setVisible(false);
			button_4.setVisible(false);
			
			label_2.setVisible(false);
			scrollPane_2.setVisible(false);
			textArea_3.setVisible(false);
			button_2.setVisible(false);
			button_3.setVisible(false);
			button_5.setVisible(false);
			
			label.setBounds(10, 64, 54, 15);
			scrollPane.setBounds(20, 79, 341, 48);
			textArea_1.setBounds(20, 79, 341, 48);
			btnGood.setBounds(373, 78, 45, 23);
			btnBad.setBounds(373, 104, 45, 23);
			btnSend.setBounds(421, 78, 61, 49);
			textArea_1.setText("");
			String result="";
			//result=regex_baidu.baidusearch(inputWord);
			result=fromServer.readUTF();
			textArea_1.setText(result);
		}
		else if(type==2 && flag==true){
			label.setVisible(false);
			scrollPane.setVisible(false);
			textArea_1.setVisible(false);
			btnSend.setVisible(false);
			btnGood.setVisible(false);
			btnBad.setVisible(false);
			
			label_1.setVisible(true);
			scrollPane_1.setVisible(true);
			textArea_2.setVisible(true);
			button.setVisible(true);
			button_1.setVisible(true);
			button_4.setVisible(true);
			
			label_2.setVisible(false);
			scrollPane_2.setVisible(false);
			textArea_3.setVisible(false);
			button_2.setVisible(false);
			button_3.setVisible(false);
			button_5.setVisible(false);
			
			label_1.setBounds(10, 64, 54, 15);
			scrollPane_1.setBounds(20, 79, 341, 48);
			textArea_2.setBounds(20, 79, 341, 48);
			button_4.setBounds(373, 78, 45, 23);
			button_1.setBounds(373, 104, 45, 23);
			button.setBounds(421, 78, 61, 49);
			textArea_2.setText("");
			String result="";
			//result=regex_bing.bingsearch(inputWord);
			result=fromServer.readUTF();
			textArea_2.setText(result);
		}
		else if(type==3 && flag==true){

			label.setVisible(false);
			scrollPane.setVisible(false);
			textArea_1.setVisible(false);
			btnSend.setVisible(false);
			btnGood.setVisible(false);
			btnBad.setVisible(false);
			
			label_1.setVisible(false);
			scrollPane_1.setVisible(false);
			textArea_2.setVisible(false);
			button.setVisible(false);
			button_1.setVisible(false);
			button_4.setVisible(false);
			
            label_2.setVisible(true);
            scrollPane_2.setVisible(true);
            textArea_3.setVisible(true);
            button_2.setVisible(true);
            button_3.setVisible(true);
            button_5.setVisible(true);
			
            label_2.setBounds(10, 64, 54, 15);
			scrollPane_2.setBounds(20, 79, 341, 48);
			textArea_3.setBounds(20, 79, 341, 48);
			button_3.setBounds(373, 78, 45, 23);
			button_5.setBounds(373, 104, 45, 23);
			button_2.setBounds(421, 78, 61, 49);
            textArea_3.setText("");
			String result="";
			//result=regex_youdao.youdaosearch(inputWord);
			result=fromServer.readUTF();
			textArea_3.setText(result);

		}
		else if((type==4 || type==8) && flag==true){
            label.setVisible(true);
            scrollPane.setVisible(true);
            textArea_1.setVisible(true);
            btnSend.setVisible(true);
            btnGood.setVisible(true);
            btnBad.setVisible(true);
            
            label_1.setVisible(true);
            scrollPane_1.setVisible(true);
            textArea_2.setVisible(true);
            button.setVisible(true);
            button_1.setVisible(true);
            button_4.setVisible(true);


            label_2.setVisible(true);
            scrollPane_2.setVisible(true);
            textArea_3.setVisible(true);
            button_2.setVisible(true);
            button_3.setVisible(true);
            button_5.setVisible(true);
			
            if(numzanjinshan>=numzanyoudao && numzanyoudao>=numzanbing){
                label.setBounds(10, 64, 54, 15);
    			scrollPane.setBounds(20, 79, 341, 48);
    			textArea_1.setBounds(20, 79, 341, 48);
    			btnGood.setBounds(373, 78, 45, 23);
    			btnBad.setBounds(373, 104, 45, 23);
    			btnSend.setBounds(421, 78, 61, 49);
    			
    			label_1.setBounds(10, 137, 54, 15);
    			scrollPane_1.setBounds(20, 152, 341, 48);
    			textArea_2.setBounds(20, 152, 341, 48);
    			button_4.setBounds(373, 152, 45, 23);
    			button_1.setBounds(373, 177, 45, 23);
    			button.setBounds(421, 152, 61, 49);
    			
    			label_2.setBounds(10, 211, 54, 15);
    			scrollPane_2.setBounds(20, 229, 341, 48);
    			textArea_3.setBounds(20, 229, 341, 48);
    			button_3.setBounds(373, 229, 45, 23);
    			button_5.setBounds(373, 254, 45, 23);
    			button_2.setBounds(421, 230, 61, 49);
                }

            else if(numzanjinshan>=numzanbing && numzanbing>=numzanyoudao){
                label.setBounds(10, 64, 54, 15);
    			scrollPane.setBounds(20, 79, 341, 48);
    			textArea_1.setBounds(20, 79, 341, 48);
    			btnGood.setBounds(373, 78, 45, 23);
    			btnBad.setBounds(373, 104, 45, 23);
    			btnSend.setBounds(421, 78, 61, 49);
    			
    			label_2.setBounds(10, 137, 54, 15);
    			scrollPane_2.setBounds(20, 152, 341, 48);
    			textArea_3.setBounds(20, 152, 341, 48);
    			button_3.setBounds(373, 152, 45, 23);
    			button_5.setBounds(373, 177, 45, 23);
    			button_2.setBounds(421, 152, 61, 49);
    			
    			label_1.setBounds(10, 211, 54, 15);
    			scrollPane_1.setBounds(20, 229, 341, 48);
    			textArea_2.setBounds(20, 229, 341, 48);
    			button_4.setBounds(373, 229, 45, 23);
    			button_1.setBounds(373, 254, 45, 23);
    			button.setBounds(421, 230, 61, 49);
                }

            else if(numzanyoudao>=numzanjinshan && numzanjinshan>=numzanbing){
                label_1.setBounds(10, 64, 54, 15);
    			scrollPane_1.setBounds(20, 79, 341, 48);
    			textArea_2.setBounds(20, 79, 341, 48);
    			button_4.setBounds(373, 78, 45, 23);
    			button_1.setBounds(373, 104, 45, 23);
    			button.setBounds(421, 78, 61, 49);
    			
    			label.setBounds(10, 137, 54, 15);
    			scrollPane.setBounds(20, 152, 341, 48);
    			textArea_1.setBounds(20, 152, 341, 48);
    			btnGood.setBounds(373, 152, 45, 23);
    			btnBad.setBounds(373, 177, 45, 23);
    			btnSend.setBounds(421, 152, 61, 49);
    			
    			label_2.setBounds(10, 211, 54, 15);
    			scrollPane_2.setBounds(20, 229, 341, 48);
    			textArea_3.setBounds(20, 229, 341, 48);
    			button_3.setBounds(373, 229, 45, 23);
    			button_5.setBounds(373, 254, 45, 23);
    			button_2.setBounds(421, 230, 61, 49);
                }

            else if(numzanyoudao>=numzanbing && numzanbing>=numzanjinshan){
                label_1.setBounds(10, 64, 54, 15);
    			scrollPane_1.setBounds(20, 79, 341, 48);
    			textArea_2.setBounds(20, 79, 341, 48);
    			button_4.setBounds(373, 78, 45, 23);
    			button_1.setBounds(373, 104, 45, 23);
    			button.setBounds(421, 78, 61, 49);
    			
    			label_2.setBounds(10, 137, 54, 15);
    			scrollPane_2.setBounds(20, 152, 341, 48);
    			textArea_3.setBounds(20, 152, 341, 48);
    			button_3.setBounds(373, 152, 45, 23);
    			button_5.setBounds(373, 177, 45, 23);
    			button_2.setBounds(421, 152, 61, 49);
    			
    			label.setBounds(10, 211, 54, 15);
    			scrollPane.setBounds(20, 229, 341, 48);
    			textArea_1.setBounds(20, 229, 341, 48);
    			btnGood.setBounds(373, 229, 45, 23);
    			btnBad.setBounds(373, 254, 45, 23);
    			btnSend.setBounds(421, 230, 61, 49);
                }

            else if(numzanbing>=numzanjinshan && numzanjinshan>=numzanyoudao){
                label_2.setBounds(10, 64, 54, 15);
    			scrollPane_2.setBounds(20, 79, 341, 48);
    			textArea_3.setBounds(20, 79, 341, 48);
    			button_3.setBounds(373, 78, 45, 23);
    			button_5.setBounds(373, 104, 45, 23);
    			button_2.setBounds(421, 78, 61, 49);
    			
    			label.setBounds(10, 137, 54, 15);
    			scrollPane.setBounds(20, 152, 341, 48);
    			textArea_1.setBounds(20, 152, 341, 48);
    			btnGood.setBounds(373, 152, 45, 23);
    			btnBad.setBounds(373, 177, 45, 23);
    			btnSend.setBounds(421, 152, 61, 49);
    			
    			label_1.setBounds(10, 211, 54, 15);
    			scrollPane_1.setBounds(20, 229, 341, 48);
    			textArea_2.setBounds(20, 229, 341, 48);
    			button_4.setBounds(373, 229, 45, 23);
    			button_1.setBounds(373, 254, 45, 23);
    			button.setBounds(421, 230, 61, 49);
                }
            else if(numzanbing>=numzanyoudao && numzanyoudao>=numzanjinshan){
                label_2.setBounds(10, 64, 54, 15);
    			scrollPane_2.setBounds(20, 79, 341, 48);
    			textArea_3.setBounds(20, 79, 341, 48);
    			button_3.setBounds(373, 78, 45, 23);
    			button_5.setBounds(373, 104, 45, 23);
    			button_2.setBounds(421, 78, 61, 49);
    			
    			label_1.setBounds(10, 137, 54, 15);
    			scrollPane_1.setBounds(20, 152, 341, 48);
    			textArea_2.setBounds(20, 152, 341, 48);
    			button_4.setBounds(373, 152, 45, 23);
    			button_1.setBounds(373, 177, 45, 23);
    			button.setBounds(421, 152, 61, 49);
    			
    			label.setBounds(10, 211, 54, 15);
    			scrollPane.setBounds(20, 229, 341, 48);
    			textArea_1.setBounds(20, 229, 341, 48);
    			btnGood.setBounds(373, 229, 45, 23);
    			btnSend.setBounds(373, 254, 45, 23);
    			btnSend.setBounds(421, 230, 61, 49);
                }
			textArea_1.setText("");
			textArea_2.setText("");
			textArea_3.setText("");
			String result="";
			String result2="";
			String result3="";
			//result=regex_baidu.baidusearch(inputWord);
			result=fromServer.readUTF();
			textArea_1.setText(result);
			//result=regex_bing.bingsearch(inputWord);
			result2=fromServer.readUTF();
			textArea_2.setText(result2);
			//result=regex_youdao.youdaosearch(inputWord);
			result3=fromServer.readUTF();
			textArea_3.setText(result3);
		}
		else if(type==5 && flag==true){
            label.setVisible(true);
            scrollPane.setVisible(true);
            textArea_1.setVisible(true);
            btnSend.setVisible(true);
            btnGood.setVisible(true);
            btnBad.setVisible(true);
            
            label_1.setVisible(true);
            scrollPane_1.setVisible(true);
            textArea_2.setVisible(true);
            button.setVisible(true);
            button_1.setVisible(true);
            button_4.setVisible(true);
			
            label_2.setVisible(false);
            scrollPane_2.setVisible(false);
            textArea_3.setVisible(false);
            button_2.setVisible(false);
            button_3.setVisible(false);
            button_5.setVisible(false);
			
            if(numzanjinshan>=numzanyoudao){
                label.setBounds(10, 64, 54, 15);
    			scrollPane.setBounds(20, 79, 341, 48);
    			textArea_1.setBounds(20, 79, 341, 48);
    			btnGood.setBounds(373, 78, 45, 23);
    			btnBad.setBounds(373, 104, 45, 23);
    			btnSend.setBounds(421, 78, 61, 49);
    			
    			label_1.setBounds(10, 137, 54, 15);
    			scrollPane_1.setBounds(20, 152, 341, 48);
    			textArea_2.setBounds(20, 152, 341, 48);
    			button_4.setBounds(373, 152, 45, 23);
    			button_1.setBounds(373, 177, 45, 23);
    			button.setBounds(421, 152, 61, 49);
                }
            else{
            	label_1.setBounds(10, 64, 54, 15);
            	scrollPane_1.setBounds(20, 79, 341, 48);
            	textArea_2.setBounds(20, 79, 341, 48);
            	button_4.setBounds(373, 78, 45, 23);
            	button_1.setBounds(373, 104, 45, 23);
            	button.setBounds(421, 78, 61, 49);
            	
            	label.setBounds(10, 137, 54, 15);
            	scrollPane.setBounds(20, 152, 341, 48);
            	textArea_1.setBounds(20, 152, 341, 48);
            	btnGood.setBounds(373, 152, 45, 23);
            	btnBad.setBounds(373, 177, 45, 23);
            	btnSend.setBounds(421, 152, 61, 49);
                }
            
			textArea_1.setText("");
			textArea_2.setText("");
			String result="";
			String result2="";
			//result=regex_baidu.baidusearch(inputWord);
			result=fromServer.readUTF();
			textArea_1.setText(result);
			//result=regex_bing.bingsearch(inputWord);
			result2=fromServer.readUTF();
			textArea_2.setText(result2);
		}
		else if(type==6 && flag==true){
            label.setVisible(true);
            scrollPane.setVisible(true);
            textArea_1.setVisible(true);
            btnSend.setVisible(true);
            btnGood.setVisible(true);
            btnBad.setVisible(true);
			
            label_1.setVisible(false);
            scrollPane_1.setVisible(false);
            textArea_2.setVisible(false);
            button.setVisible(false);
            button_1.setVisible(false);
            button_4.setVisible(false);
			
            label_2.setVisible(true);
            scrollPane_2.setVisible(true);
            textArea_3.setVisible(true);
            button_2.setVisible(true);
            button_3.setVisible(true);
            button_5.setVisible(true);
			
            if(numzanjinshan>=numzanbing){
                label.setBounds(10, 64, 54, 15);
    			scrollPane.setBounds(20, 79, 341, 48);
    			textArea_1.setBounds(20, 79, 341, 48);
    			btnGood.setBounds(373, 78, 45, 23);
    			btnBad.setBounds(373, 104, 45, 23);
    			btnSend.setBounds(421, 78, 61, 49);
    			
    			label_2.setBounds(10, 137, 54, 15);
    			scrollPane_2.setBounds(20, 152, 341, 48);
    			textArea_3.setBounds(20, 152, 341, 48);
    			button_3.setBounds(373, 152, 45, 23);
    			button_5.setBounds(373, 177, 45, 23);
    			button_2.setBounds(421, 152, 61, 49);
                }
            else{
            	label_2.setBounds(10, 64, 54, 15);
            	scrollPane_2.setBounds(20, 79, 341, 48);
            	textArea_3.setBounds(20, 79, 341, 48);
            	button_3.setBounds(373, 78, 45, 23);
            	button_5.setBounds(373, 104, 45, 23);
            	button_2.setBounds(421, 78, 61, 49);
            	
            	label.setBounds(10, 137, 54, 15);
            	scrollPane.setBounds(20, 152, 341, 48);
            	textArea_1.setBounds(20, 152, 341, 48);
            	btnGood.setBounds(373, 152, 45, 23);
            	btnBad.setBounds(373, 177, 45, 23);
            	btnSend.setBounds(421, 152, 61, 49);
                }
            
			textArea_1.setText("");
			textArea_3.setText("");
			String result="";
			String result3="";
			//result=regex_baidu.baidusearch(inputWord);
			result=fromServer.readUTF();
			textArea_1.setText(result);
			//result=regex_youdao.youdaosearch(inputWord);
			result3=fromServer.readUTF();
			textArea_3.setText(result3);
		}
		else if(type==7 && flag==true){
            label.setVisible(false);
            scrollPane.setVisible(false);
            textArea_1.setVisible(false);
            btnSend.setVisible(false);
            btnGood.setVisible(false);
            btnBad.setVisible(false);
			
            label_1.setVisible(true);
            scrollPane_1.setVisible(true);
            textArea_2.setVisible(true);
            button.setVisible(true);
            button_1.setVisible(true);
            button_4.setVisible(true);


            label_2.setVisible(true);
            scrollPane_2.setVisible(true);
            textArea_3.setVisible(true);
            button_2.setVisible(true);
            button_3.setVisible(true);
            button_5.setVisible(true);
			
            if(numzanyoudao>=numzanbing){
                label_1.setBounds(10, 64, 54, 15);
    			scrollPane_1.setBounds(20, 79, 341, 48);
    			textArea_2.setBounds(20, 79, 341, 48);
    			button_4.setBounds(373, 78, 45, 23);
    			button_1.setBounds(373, 104, 45, 23);
    			button.setBounds(421, 78, 61, 49);
    			
    			label_2.setBounds(10, 137, 54, 15);
    			scrollPane_2.setBounds(20, 152, 341, 48);
    			textArea_3.setBounds(20, 152, 341, 48);
    			button_3.setBounds(373, 152, 45, 23);
    			button_5.setBounds(373, 177, 45, 23);
    			button_2.setBounds(421, 152, 61, 49);
                }
            else{
            	label_2.setBounds(10, 64, 54, 15);
            	scrollPane_2.setBounds(20, 79, 341, 48);
            	textArea_3.setBounds(20, 79, 341, 48);
            	button_3.setBounds(373, 78, 45, 23);
            	button_5.setBounds(373, 104, 45, 23);
            	button_2.setBounds(421, 78, 61, 49);
            	
            	label_1.setBounds(10, 137, 54, 15);
            	scrollPane_1.setBounds(20, 152, 341, 48);
            	textArea_2.setBounds(20, 152, 341, 48);
            	button_4.setBounds(373, 152, 45, 23);
            	button_1.setBounds(373, 177, 45, 23);
            	button.setBounds(421, 152, 61, 49);
                }
            
			textArea_2.setText("");
			textArea_3.setText("");
			String result2="";
			String result3="";
			//result=regex_bing.bingsearch(inputWord);
			result2=fromServer.readUTF();
			textArea_2.setText(result2);
			//result=regex_youdao.youdaosearch(inputWord);
			result3=fromServer.readUTF();
			textArea_3.setText(result3);
		}
		
		if(flag==true){
			toServer.writeInt(2);
			toServer.writeUTF(inputWord);
		}
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}
	public int judge_type(JCheckBox box,JCheckBox box_1,JCheckBox box_2){
		if(box.isSelected()==true && box_1.isSelected()==false && box_2.isSelected()==false){
			type=1;
		}
		if(box.isSelected()==false && box_1.isSelected()==true && box_2.isSelected()==false){
			type=2;
		}
		if(box.isSelected()==false && box_1.isSelected()==false && box_2.isSelected()==true){
			type=3;
		}
		if(box.isSelected()==true && box_1.isSelected()==true && box_2.isSelected()==true){
			type=4;
		}
		if(box.isSelected()==true && box_1.isSelected()==true && box_2.isSelected()==false){
			type=5;
		}
		if(box.isSelected()==true && box_1.isSelected()==false && box_2.isSelected()==true){
			type=6;
		}
		if(box.isSelected()==false && box_1.isSelected()==true && box_2.isSelected()==true){
			type=7;
		}
		if(box.isSelected()==false && box_1.isSelected()==false && box_2.isSelected()==false){
			type=8;
		}
		return type;
	}
}
