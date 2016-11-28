import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class UI2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;

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
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(155, 65, 153, 31);
		contentPane.add(textField_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(155, 10, 153, 31);
		contentPane.add(textField);
		
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
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(155, 117, 153, 31);
		contentPane.add(textField_2);
		
		JLabel label_3 = new JLabel("EMAIL:");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(10, 173, 74, 23);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(155, 169, 153, 31);
		contentPane.add(textField_3);
		
		JButton button = new JButton("SUBMIT");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.setBounds(318, 130, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CANCEL");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_1.setBounds(318, 173, 93, 23);
		contentPane.add(button_1);
	}
}
