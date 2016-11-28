import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class UI3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
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
		
		textField = new JTextField();
		textField.setBounds(205, 58, 127, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(205, 120, 127, 26);
		contentPane.add(textField_1);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		lblPassword.setBounds(62, 113, 133, 32);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("LOG IN");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		btnNewButton.setBounds(94, 194, 101, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		btnCancel.setBounds(218, 194, 116, 23);
		contentPane.add(btnCancel);
	}

}
