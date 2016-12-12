package ClientUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class UI5 extends JFrame {

	private JPanel contentPane;
	private JTextField userid;
	public static String usid;
	public DataOutputStream toServer;
	public DataInputStream fromServer;
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
		
		JLabel lblNewLabel = new JLabel("input the userid which you want to add:");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(21, 10, 275, 63);
		contentPane.add(lblNewLabel);
		
		userid = new JTextField();
		userid.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userid.setBounds(63, 83, 219, 69);
		contentPane.add(userid);
		userid.setColumns(10);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				usid=userid.getText();
				try {
					fromServer=new DataInputStream(UI.socket.getInputStream());
					toServer=new DataOutputStream(UI.socket.getOutputStream());
					toServer.writeInt(8);
					toServer.writeUTF(usid);
					String s=fromServer.readUTF();
					System.out.println(s);
					if(s.equals("no such user! ")){
						JOptionPane.showMessageDialog(null,"fail", "add friends reminder", JOptionPane.ERROR_MESSAGE);
					}
					
					
					/*TODO*/
					else{
						//JOptionPane.showMessageDialog(null,"success", "add friends reminder", JOptionPane.INFORMATION_MESSAGE);
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.setBounds(230, 209, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CENCAL");
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton_1.setBounds(333, 210, 93, 23);
		contentPane.add(btnNewButton_1);
	}
}
