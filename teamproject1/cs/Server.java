package cs;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import net.regex_baidu;
import net.regex_bing;
import net.regex_youdao;
import DataBase.DataBase;
import DataBase.DictionaryManager;
public class Server extends JFrame{
	private JTextArea jta=new JTextArea();
	public static void main(String[] args){
		new Server();
		
	}
	public Server(){
		setLayout(new BorderLayout());
		add(new JScrollPane(jta),BorderLayout.CENTER);
		setTitle("Server");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try{
			ServerSocket serverSocket=new ServerSocket(8000);
			jta.append("Server started at" + new Date()+'\n');
			int clientNo=1;
			while(true){
				Socket socket=serverSocket.accept();
				jta.append("Starting thread for client" + clientNo+"at" + new Date()+'\n');
				InetAddress inetAddress=socket.getInetAddress();
				jta.append("client" +clientNo + "'s host name is"+inetAddress.getHostName()+"\n");
				jta.append("client"+clientNo +" 's IP Address is"+ inetAddress.getHostAddress()+"\n");
				
				HandleAClient task=new HandleAClient(socket);
				new Thread(task).start();
				clientNo++;
			}
		}
		catch(IOException ex){
			System.err.println(ex);
		}
	}
	class HandleAClient implements Runnable{
		private Socket socket;
		public HandleAClient(Socket socket){
			this.socket=socket;
		}
		public void run(){
			try{
				DataInputStream inputFromClient=new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient=new DataOutputStream(socket.getOutputStream());
				while(true){
					//double radius=inputFromClient.readDouble();
					//double area=radius*radius*Math.PI;
					String inputword=inputFromClient.readUTF();
					int TYPE=inputFromClient.readInt();
					String result=" ";
					String result2=" ";
					String result3=" ";
					if(TYPE==1){
						result=regex_baidu.baidusearch(inputword);
						outputToClient.writeUTF(result);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("baidusearch result:" + result + '\n');
					}
					else if(TYPE==2){
						result=regex_bing.bingsearch(inputword);
						outputToClient.writeUTF(result);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("bingsearch result:" + result + '\n');
					}
					else if(TYPE==3){
						result=regex_youdao.youdaosearch(inputword);
						outputToClient.writeUTF(result);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("youdaosearch result:" + result + '\n');
					}
					else if(TYPE==4){
						result=regex_baidu.baidusearch(inputword);
						result2=regex_bing.bingsearch(inputword);
						result3=regex_youdao.youdaosearch(inputword);
						outputToClient.writeUTF(result);
						outputToClient.writeUTF(result2);
						outputToClient.writeUTF(result3);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("baidusearch result:" + result + '\n');
						jta.append("bingsearch result:" + result2 + '\n');
						jta.append("youdaosearch result:" + result3 + '\n');
					}
					else if(TYPE==5){
						result=regex_baidu.baidusearch(inputword);
						result2=regex_bing.bingsearch(inputword);
						outputToClient.writeUTF(result);
						outputToClient.writeUTF(result2);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("baidusearch result:" + result + '\n');
						jta.append("bingsearch result:" + result2 + '\n');
					}
					else if(TYPE==6){
						result=regex_baidu.baidusearch(inputword);
						result3=regex_youdao.youdaosearch(inputword);
						outputToClient.writeUTF(result);
						outputToClient.writeUTF(result3);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("baidusearch result:" + result + '\n');
						jta.append("youdaosearch result:" + result3 + '\n');
					}
					else if(TYPE==7){
						result2=regex_bing.bingsearch(inputword);
						result3=regex_youdao.youdaosearch(inputword);
						outputToClient.writeUTF(result2);
						outputToClient.writeUTF(result3);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("bingsearch result:" + result2 + '\n');
						jta.append("youdaosearch result:" + result3 + '\n');
					}
					else if(TYPE==8){
						result=regex_baidu.baidusearch(inputword);
						result2=regex_bing.bingsearch(inputword);
						result3=regex_youdao.youdaosearch(inputword);
						outputToClient.writeUTF(result);
						outputToClient.writeUTF(result2);
						outputToClient.writeUTF(result3);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("baidusearch result:" + result + '\n');
						jta.append("bingsearch result:" + result2 + '\n');
						jta.append("youdaosearch result:" + result3 + '\n');
					}
					
				}
			}
			catch(IOException e){
				System.err.println(e);
			}
		}
	}
}






/*package exercise10;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server{
	public static void main(String[] args){
		new Server();
		
	}
	public Server(){
		try{
			ServerSocket serverSocket=new ServerSocket(8000);
			System.out.println("Server started at" + new Date());
			int clientNo=1;
			while(true){
				Socket socket=serverSocket.accept();
				System.out.println("Starting thread for client" + clientNo+"at" + new Date());
				InetAddress inetAddress=socket.getInetAddress();
				System.out.println("client" +clientNo + "'s host name is"+inetAddress.getHostName());
				System.out.println("client"+clientNo +" 's IP Address is"+ inetAddress.getHostAddress());
				
				HandleAClient task=new HandleAClient(socket);
				new Thread(task).start();
				clientNo++;
			}
		}
		catch(IOException ex){
			System.err.println(ex);
		}
	}
	class HandleAClient implements Runnable{
		private Socket socket;
		public HandleAClient(Socket socket){
			this.socket=socket;
		}
		public void run(){
			try{
				DataInputStream inputFromClient=new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient=new DataOutputStream(socket.getOutputStream());
				while(true){
					double radius=inputFromClient.readDouble();
					double area=radius*radius*Math.PI;
					outputToClient.writeDouble(area);
					System.out.println("radius received from client:"+radius);
					System.out.println("Area found:" + area + '\n');
				}
			}
			catch(IOException e){
				System.err.println(e);
			}
		}
	}
}*/