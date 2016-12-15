package ClientUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Scanner;

public class UI5 extends JFrame {

	private JPanel contentPane;
	private JTextField receiveridtextField;
	private JButton sendwordbutton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI5 frame = new UI5();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI5() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel inputreceiverlabel = new JLabel("the username who you want to send wordcard:");
		inputreceiverlabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		inputreceiverlabel.setBounds(48, 20, 345, 27);
		contentPane.add(inputreceiverlabel);
		
		receiveridtextField = new JTextField();
		receiveridtextField.setBounds(142, 57, 161, 27);
		contentPane.add(receiveridtextField);
		receiveridtextField.setColumns(10);
		
		sendwordbutton = new JButton("SUBMIT");
		sendwordbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String username=receiveridtextField.getText();
				if(receiveridtextField!=null){
					if(UI.webtype.equals("jinshan")){
						try {
						Scanner input=new Scanner(System.in);
						UI.toServer.writeInt(12);
						UI.toServer.writeUTF("jinshan");
						UI.toServer.writeUTF(UI3.uid);
						//String receiver=list.getModel().getElementAt(index).toString();
						UI.toServer.writeUTF(username);
						//System.out.print("please input the word which you want to send");
						//String word=input.next();
						//toServer.writeUTF(word);
						UI.toServer.writeUTF(UI.inputWord);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					else if(UI.webtype.equals("youdao")){
						try {
						Scanner input=new Scanner(System.in);
						UI.toServer.writeInt(12);
						UI.toServer.writeUTF("youdao");
						UI.toServer.writeUTF(UI3.uid);
						//String receiver=list.getModel().getElementAt(index).toString();
						UI.toServer.writeUTF(username);
						//System.out.print("please input the word which you want to send");
						//String word=input.next();
						//toServer.writeUTF(word);
						UI.toServer.writeUTF(UI.inputWord);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					else if(UI.webtype.equals("bing")){
						try {
						Scanner input=new Scanner(System.in);
						UI.toServer.writeInt(12);
						UI.toServer.writeUTF("bing");
						UI.toServer.writeUTF(UI3.uid);
						//String receiver=list.getModel().getElementAt(index).toString();
						UI.toServer.writeUTF(username);
						//System.out.print("please input the word which you want to send");
						//String word=input.next();
						//toServer.writeUTF(word);
						UI.toServer.writeUTF(UI.inputWord);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
			}
		});
		sendwordbutton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		sendwordbutton.setBounds(277, 209, 93, 23);
		contentPane.add(sendwordbutton);
	}

}
