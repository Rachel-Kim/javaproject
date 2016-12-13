package ClientUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import DataBase.UserManager;
import DataBase.User;
public class UI3 extends JFrame {

	private JPanel contentPane;
	private JTextField idtext;
	private JPasswordField passwordField;
	public DataOutputStream toServer;
	public DataInputStream fromServer;
	public static String uid;
	/**
	 * Launch the application.
	 */
	static boolean login;
	/**public void setlogin(boolean log_in){
		this.login=log_in;
	}
	public boolean getlogin(){
		return this.login;
	}*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI3 frame = new UI3();
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
	public UI3() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		lblNewLabel.setBounds(62, 51, 39, 32);
		contentPane.add(lblNewLabel);
		
		idtext = new JTextField();
		idtext.setBounds(205, 58, 127, 26);
		contentPane.add(idtext);
		idtext.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		lblPassword.setBounds(62, 113, 133, 32);
		contentPane.add(lblPassword);
		
		JButton loginbutton = new JButton("LOG IN");
		loginbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				uid=idtext.getText();
				String pw=new String(passwordField.getPassword());
				//if(uid !=null && pw!=null && UserManager.identityVerify(uid, pw)==true){
				
				if(uid!=null && pw!=null){
					try {
						fromServer=new DataInputStream(UI.socket.getInputStream());
						toServer=new DataOutputStream(UI.socket.getOutputStream());
						toServer.writeInt(6);
						toServer.writeUTF(uid);
						toServer.writeUTF(pw);
						String s=fromServer.readUTF();
						System.out.println(s);
						if(s.equals("log in successfully!")){
							JOptionPane.showMessageDialog(null,"success!", "Log reminder!", JOptionPane.INFORMATION_MESSAGE);
							login=true;
							//UserManager.login(uid);
						}
						else{
							JOptionPane.showMessageDialog(null,"fail!", "Log reminder!", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		});
		loginbutton.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		loginbutton.setBounds(94, 194, 101, 23);
		contentPane.add(loginbutton);
		
		JButton cancelbutton = new JButton("CANCEL");
		cancelbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				dispose();
			}
		});
		cancelbutton.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		cancelbutton.setBounds(218, 194, 116, 23);
		contentPane.add(cancelbutton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 119, 127, 26);
		contentPane.add(passwordField);
	}

}
