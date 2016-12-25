package ClientUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;

import DataBase.UserManager;
import DataBase.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
public class UI2 extends JFrame {

	private JPanel contentPane;
	private JTextField idtext;
	private JTextField emailtext;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	public DataOutputStream toServer;
	public DataInputStream fromServer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI2 frame = new UI2();
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
	public UI2() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idtext = new JTextField();
		idtext.setColumns(10);
		idtext.setBounds(155, 10, 153, 31);
		contentPane.add(idtext);
		
		JLabel label = new JLabel("CONFIRM PASSWORD:");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(10, 112, 142, 41);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("ID:");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(10, 10, 54, 31);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("PASSWORD:");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(10, 69, 74, 23);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("EMAIL:");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(10, 173, 74, 23);
		contentPane.add(label_3);
		
		emailtext = new JTextField();
		emailtext.setColumns(10);
		emailtext.setBounds(155, 169, 153, 31);
		contentPane.add(emailtext);
		
		JButton submitbutton = new JButton("SUBMIT");
		submitbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String Pw0=new String(passwordField.getPassword());
				String Pw1=new String(passwordField_1 .getPassword());
				
				if(Pw0!=null && Pw1!=null && Pw0.equals(Pw1)){
					String uidStr=idtext.getText();
					String emailStr=emailtext.getText();
					if(uidStr!=null && emailStr!=null){
						try {
							fromServer=new DataInputStream(UI.socket.getInputStream());
							toServer=new DataOutputStream(UI.socket.getOutputStream());
							toServer.writeInt(5);
							toServer.writeUTF(uidStr);
							toServer.writeUTF(Pw0);
							String s=fromServer.readUTF();
							if(s.equals("create user failed! ")){
								JOptionPane.showMessageDialog(null,"the user exists!", "sign in reminder!", JOptionPane.ERROR_MESSAGE);
							}
							else{
								JOptionPane.showMessageDialog(null,"Success!", "sign in reminder!", JOptionPane.INFORMATION_MESSAGE);
							}
							System.out.println(s);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//UserManager.createUser(uidStr,Pw0);
						
						//dispose();
					}
					else
						JOptionPane.showMessageDialog(null,"fail!", "sign reminder!", JOptionPane.ERROR_MESSAGE);
				}
				
					
				
			}
		});
		submitbutton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		submitbutton.setBounds(318, 130, 93, 23);
		contentPane.add(submitbutton);
		
		JButton cancelbutton = new JButton("CANCEL");
		cancelbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
		cancelbutton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		cancelbutton.setBounds(318, 173, 93, 23);
		contentPane.add(cancelbutton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 61, 153, 31);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(155, 112, 153, 31);
		contentPane.add(passwordField_1);
		
		
		
		
		
	}
}
