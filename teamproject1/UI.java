import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
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

import net.regex_baidu;
import net.regex_bing;
import net.regex_youdao;
import DataBase.DataBase;
import DataBase.DictionaryManager;
import DataBase.DataBaseConnectionPool;
public class UI {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	int type=0;
	int count1_1=0,count1_2=0,count2_1=0,count2_2=0,count3_1=0,count3_2=0;
	public static void main(String[] args) {
		System.out.println("Hello,This is the sever!!");
		DataBase.connect();
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
	 */
	public UI() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 423);
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
		
		JCheckBox checkBox = new JCheckBox("百度");
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
		
		
		
		JButton btnLogIn = new JButton("log in");
		btnLogIn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				UI3 frame3=new UI3();
				frame3.setVisible(true);
			}
		});
		btnLogIn.setBounds(437, 9, 93, 23);
		panel.add(btnLogIn);
		
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
		btnGood.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				count1_1++;
				if(count1_1==2){
					JOptionPane.showMessageDialog( null , "您已点赞，不能重复点击" ,"错误" , JOptionPane.ERROR_MESSAGE) ;
					count1_1=1;
				}
			}
		});
		
		btnGood.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnGood.setBounds(373, 78, 45, 23);
		panel.add(btnGood);
		
		JLabel label = new JLabel("百度");
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
		btnBad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				count1_2++;
				if(count1_2==2){
					JOptionPane.showMessageDialog( null , "您已取消点赞，不能重复点击" ,"错误" , JOptionPane.ERROR_MESSAGE) ;
					count1_2=1;
				}
			}
		});
		btnBad.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnBad.setBounds(373, 104, 45, 23);
		panel.add(btnBad);
		
		JButton button_4 = new JButton("▲");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				count2_1++;
				if(count2_1==2){
					JOptionPane.showMessageDialog( null , "您已点赞，不能重复点击" ,"错误" , JOptionPane.ERROR_MESSAGE) ;
					count2_1=1;
				}
			}
		});
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_4.setBounds(373, 152, 45, 23);
		panel.add(button_4);
		
		JButton button_1 = new JButton("▼");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				count2_2++;
				if(count2_2==2){
					JOptionPane.showMessageDialog( null , "您已取消点赞，不能重复点击" ,"错误" , JOptionPane.ERROR_MESSAGE) ;
					count2_2=1;
				}
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_1.setBounds(373, 174, 45, 23);
		panel.add(button_1);
		
		JButton button_3 = new JButton("▲");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				count3_1++;
				if(count3_1==2){
					JOptionPane.showMessageDialog( null , "您已点赞，不能重复点击" ,"错误" , JOptionPane.ERROR_MESSAGE) ;
					count3_1=1;
				}
			}
		});
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_3.setBounds(373, 229, 45, 23);
		panel.add(button_3);
		
		JButton button_5 = new JButton("▼");
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				count3_2++;
				if(count3_2==2){
					JOptionPane.showMessageDialog( null , "您已取消点赞，不能重复点击" ,"错误" , JOptionPane.ERROR_MESSAGE) ;
					count3_2=1;
				}
			}
		});
		button_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_5.setBounds(373, 254, 45, 23);
		panel.add(button_5);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(513, 42, 144, 293);
		panel.add(scrollPane_3);
		
		JList list = new JList();
		scrollPane_3.setViewportView(list);
		
		JButton btnNewButton = new JButton("add friends");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(541, 345, 116, 23);
		panel.add(btnNewButton);
		
		
	//	ImageIcon image=new ImageIcon("Baidu.jpg");
	//	JLabel lblNewLabel = new JLabel(image);
	//	lblNewLabel.setBounds(507, 70, 111, 58);
	//	panel.add(lblNewLabel);
		
		String inputWord ="";
		 btnSearch.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){
	            	type=judge_type(checkBox,checkBox_1,checkBox_2);
	         
	            	try {
	            		
	            		searchWords(inputWord,type,label,textArea_1,btnSend,btnGood,btnBad
								,label_1,textArea_2,button,button_1,button_4
								,label_2,textArea_3,button_2,button_3,button_5);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        });
	
		

	}
	public void searchWords(String inputWord,int TYPE
			,JLabel label,JTextArea textArea_1,JButton btnSend,JButton btnGood,JButton btnBad
			,JLabel label_1,JTextArea textArea_2,JButton button,JButton button_1,JButton button_4
			,JLabel label_2,JTextArea textArea_3,JButton button_2,JButton button_3,JButton button_5) throws MalformedURLException, FileNotFoundException{
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
		if(type==1 && flag==true){
			label.setVisible(true);
			textArea_1.setVisible(true);
			btnSend.setVisible(true);
			btnGood.setVisible(true);
			btnBad.setVisible(true);
			
			label_1.setVisible(false);
			textArea_2.setVisible(false);
			button.setVisible(false);
			button_1.setVisible(false);
			button_4.setVisible(false);
			
			label_2.setVisible(false);
			textArea_3.setVisible(false);
			button_2.setVisible(false);
			button_3.setVisible(false);
			button_5.setVisible(false);
			
			textArea_1.setText("");
			String result="";
			result=regex_baidu.baidusearch(inputWord);
			textArea_1.setText(result);
		}
		if(type==2 && flag==true){
			label.setVisible(false);
			textArea_1.setVisible(false);
			btnSend.setVisible(false);
			btnGood.setVisible(false);
			btnBad.setVisible(false);
			
			label_1.setVisible(true);
			textArea_2.setVisible(true);
			button.setVisible(true);
			button_1.setVisible(true);
			button_4.setVisible(true);
			
			label_2.setVisible(false);
			textArea_3.setVisible(false);
			button_2.setVisible(false);
			button_3.setVisible(false);
			button_5.setVisible(false);
			
			label_1.setBounds(10, 64, 54, 15);
			
			textArea_2.setText("");
			String result="";
			result=regex_bing.bingsearch(inputWord);
			textArea_2.setText(result);
		}
		if(type==3 && flag==true){

			label.setVisible(false);
			textArea_1.setVisible(false);
			btnSend.setVisible(false);
			btnGood.setVisible(false);
			btnBad.setVisible(false);
			
			label_1.setVisible(false);
			textArea_2.setVisible(false);
			button.setVisible(false);
			button_1.setVisible(false);
			button_4.setVisible(false);
			
            label_2.setVisible(true);
            textArea_3.setVisible(true);
            button_2.setVisible(true);
            button_3.setVisible(true);
            button_5.setVisible(true);
			
			textArea_3.setText("");
			String result="";
			result=regex_youdao.youdaosearch(inputWord);
			textArea_3.setText(result);

		}
		if(type==4 && flag==true){
            label.setVisible(true);
            textArea_1.setVisible(true);
            btnSend.setVisible(true);
            btnGood.setVisible(true);
            btnBad.setVisible(true);
            
            label_1.setVisible(true);
            textArea_2.setVisible(true);
            button.setVisible(true);
            button_1.setVisible(true);
            button_4.setVisible(true);


            label_2.setVisible(true);
            textArea_3.setVisible(true);
            button_2.setVisible(true);
            button_3.setVisible(true);
            button_5.setVisible(true);
			
			textArea_1.setText("");
			textArea_2.setText("");
			textArea_3.setText("");
			String result="";
			result=regex_baidu.baidusearch(inputWord);
			textArea_1.setText(result);
			result=regex_bing.bingsearch(inputWord);
			textArea_2.setText(result);
			result=regex_youdao.youdaosearch(inputWord);
			textArea_3.setText(result);
		}
		if(type==5 && flag==true){
            label.setVisible(true);
            textArea_1.setVisible(true);
            btnSend.setVisible(true);
            btnGood.setVisible(true);
            btnBad.setVisible(true);
            
            label_1.setVisible(true);
            textArea_2.setVisible(true);
            button.setVisible(true);
            button_1.setVisible(true);
            button_4.setVisible(true);
			
            label_2.setVisible(false);
            textArea_3.setVisible(false);
            button_2.setVisible(false);
            button_3.setVisible(false);
            button_5.setVisible(false);
			
			textArea_1.setText("");
			textArea_2.setText("");
			String result="";
			result=regex_baidu.baidusearch(inputWord);
			textArea_1.setText(result);
			result=regex_bing.bingsearch(inputWord);
			textArea_2.setText(result);
		}
		if(type==6 && flag==true){
            label.setVisible(true);
            textArea_1.setVisible(true);
            btnSend.setVisible(true);
            btnGood.setVisible(true);
            btnBad.setVisible(true);
			
            label_1.setVisible(false);
            textArea_2.setVisible(false);
            button.setVisible(false);
            button_1.setVisible(false);
            button_4.setVisible(false);
			
            label_2.setVisible(true);
            textArea_3.setVisible(true);
            button_2.setVisible(true);
            button_3.setVisible(true);
            button_5.setVisible(true);
			
			textArea_1.setText("");
			textArea_3.setText("");
			String result="";
			result=regex_baidu.baidusearch(inputWord);
			textArea_1.setText(result);
			result=regex_youdao.youdaosearch(inputWord);
			textArea_3.setText(result);
		}
		if(type==7 && flag==true){
            label.setVisible(false);
            textArea_1.setVisible(false);
            btnSend.setVisible(false);
            btnGood.setVisible(false);
            btnBad.setVisible(false);
			
            label_1.setVisible(true);
            textArea_2.setVisible(true);
            button.setVisible(true);
            button_1.setVisible(true);
            button_4.setVisible(true);


            label_2.setVisible(true);
            textArea_3.setVisible(true);
            button_2.setVisible(true);
            button_3.setVisible(true);
            button_5.setVisible(true);
			
			textArea_2.setText("");
			textArea_3.setText("");
			String result="";
			result=regex_bing.bingsearch(inputWord);
			textArea_2.setText(result);
			result=regex_youdao.youdaosearch(inputWord);
			textArea_3.setText(result);
		}
		if(type==8 && flag==true){
            label.setVisible(false);
            textArea_1.setVisible(false);
            btnSend.setVisible(false);
            btnGood.setVisible(false);
            btnBad.setVisible(false);
            
            label_1.setVisible(false);
            textArea_2.setVisible(false);
            button.setVisible(false);
            button_1.setVisible(false);
            button_4.setVisible(false);

            label_2.setVisible(false);
            textArea_3.setVisible(false);
            button_2.setVisible(false);
            button_3.setVisible(false);
            button_5.setVisible(false);
			
			textArea_1.setText("");
			textArea_2.setText("");
			textArea_3.setText("");
			String result="";
			result=regex_baidu.baidusearch(inputWord);
			textArea_1.setText(result);
			result=regex_bing.bingsearch(inputWord);
			textArea_2.setText(result);
			result=regex_youdao.youdaosearch(inputWord);
			textArea_3.setText(result);
		}
		if(flag==true){
			DictionaryManager dic=new DictionaryManager();
			dic.AddWord(inputWord);
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
