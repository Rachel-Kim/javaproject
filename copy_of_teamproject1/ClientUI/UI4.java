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
public class UI4 extends JFrame {

	private JPanel contentPane;
	private JTextField idtext;
	private JPasswordField newpwfield;
	public DataOutputStream toServer;
	public DataInputStream fromServer;
	private JPasswordField oldpwfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI4 frame = new UI4();
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
	public UI4() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		lblNewLabel.setBounds(62, 30, 39, 32);
		contentPane.add(lblNewLabel);
		
		idtext = new JTextField();
		idtext.setBounds(207, 37, 127, 26);
		contentPane.add(idtext);
		idtext.setColumns(10);
		
		JLabel lblPassword = new JLabel("OLD PASSWORD:");
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		lblPassword.setBounds(22, 86, 173, 32);
		contentPane.add(lblPassword);
		
		JButton modifybutton = new JButton("MODIFY");
		modifybutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String uid=idtext.getText();
				String oldPw=new String(oldpwfield.getPassword());
				String newPw=new String(newpwfield.getPassword());
				try {
					fromServer=new DataInputStream(UI.socket.getInputStream());
					toServer=new DataOutputStream(UI.socket.getOutputStream());
					toServer.writeInt(7);
					toServer.writeUTF(uid);
					toServer.writeUTF(oldPw);
					toServer.writeUTF(newPw);
					String s=fromServer.readUTF();
					System.out.println(s);
					if(s.equals("modify password successfully!")){
						JOptionPane.showMessageDialog(null,"success!", "modify reminder!", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"fail!", "modify reminder!", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		modifybutton.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		modifybutton.setBounds(62, 209, 117, 23);
		contentPane.add(modifybutton);
		
		JButton cancelbutton = new JButton("CANCEL");
		cancelbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				dispose();
			}
		});
		cancelbutton.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		cancelbutton.setBounds(218, 209, 116, 23);
		contentPane.add(cancelbutton);
		
		newpwfield = new JPasswordField();
		newpwfield.setBounds(207, 149, 127, 26);
		contentPane.add(newpwfield);
		
		JLabel lblNewpassword = new JLabel("NEW PASSWORD:");
		lblNewpassword.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		lblNewpassword.setBounds(22, 142, 173, 32);
		contentPane.add(lblNewpassword);
		
		oldpwfield = new JPasswordField();
		oldpwfield.setBounds(207, 95, 127, 26);
		contentPane.add(oldpwfield);
	}
}
