package ClientUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class UI5 extends JFrame {

	private JPanel contentPane;
	private JTextField receiveridtextField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	}

}
