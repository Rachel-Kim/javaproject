package cs;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Client extends JFrame{
	private JTextField jtf=new JTextField();
	private JTextArea jta=new JTextArea();
	
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	
	public static void main(String[] args){
		new Client();	
	}
	
	public Client(){
		JPanel p=new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("Enter radius"));
	}
}
