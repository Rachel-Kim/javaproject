package cs;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import net.regex_iciba;
import net.regex_bing;
import net.regex_youdao;
import DataBase.DataBase;
import DataBase.DictionaryManager;
import DataBase.UserManager;
import ClientUI.UI3;
public class Server extends JFrame{
	private JTextArea jta=new JTextArea();
	private static Connection conn = null;
	public static int numzanjinshan,numzanyoudao,numzanbing;
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
			//服务器监听
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
					int requesttype=inputFromClient.readInt();
					if(requesttype==1){//服务器接收来自客户端的在网络查词的申请
						jta.append("receive  SEARCH WORD request  "+'\n');
					String inputword=inputFromClient.readUTF();
					conn = DataBase.connect();
					Statement statement = conn.createStatement();
					ResultSet resultSet=statement.executeQuery("select * from dictionary where Word='"+inputword+"'");
					while(resultSet.next()){
						numzanjinshan=resultSet.getInt(2);
						numzanyoudao=resultSet.getInt(3);
						numzanbing=resultSet.getInt(4);
					}
					outputToClient.writeInt(numzanjinshan);
					outputToClient.writeInt(numzanyoudao);
					outputToClient.writeInt(numzanbing);
					int TYPE=inputFromClient.readInt();
					
					String result=" ";
					String result2=" ";
					String result3=" ";
					if(TYPE==1){
						result=regex_iciba.icibasearch(inputword);
						outputToClient.writeUTF(result);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("jinshansearch result:" + result + '\n');
					}
					else if(TYPE==3){
						result=regex_bing.bingsearch(inputword);
						outputToClient.writeUTF(result);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("bingsearch result:" + result + '\n');
					}
					else if(TYPE==2){
						result=regex_youdao.youdaosearch(inputword);
						outputToClient.writeUTF(result);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("youdaosearch result:" + result + '\n');
					}
					else if(TYPE==4){
						result=regex_iciba.icibasearch(inputword);
						result2=regex_youdao.youdaosearch(inputword);
						result3=regex_bing.bingsearch(inputword);
						outputToClient.writeUTF(result);
						outputToClient.writeUTF(result2);
						outputToClient.writeUTF(result3);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("jinshansearch result:" + result + '\n');
						jta.append("youdaosearch result:" + result2 + '\n');
						jta.append("bingsearch result:" + result3 + '\n');
					}
					else if(TYPE==5){
						result=regex_iciba.icibasearch(inputword);
						result2=regex_youdao.youdaosearch(inputword);
						outputToClient.writeUTF(result);
						outputToClient.writeUTF(result2);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("jinshansearch result:" + result + '\n');
						jta.append("bingsearch result:" + result2 + '\n');
					}
					else if(TYPE==6){
						result=regex_iciba.icibasearch(inputword);
						result3=regex_bing.bingsearch(inputword);
						outputToClient.writeUTF(result);
						outputToClient.writeUTF(result3);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("jinshansearch result:" + result + '\n');
						jta.append("youdaosearch result:" + result3 + '\n');
					}
					else if(TYPE==7){
						result2=regex_youdao.youdaosearch(inputword);
						result3=regex_bing.bingsearch(inputword);
						outputToClient.writeUTF(result2);
						outputToClient.writeUTF(result3);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("youdaosearch result:" + result2 + '\n');
						jta.append("bingsearch result:" + result3 + '\n');
					}
					else if(TYPE==8){
						result=regex_iciba.icibasearch(inputword);
						result2=regex_youdao.youdaosearch(inputword);
						result3=regex_bing.bingsearch(inputword);
						outputToClient.writeUTF(result);
						outputToClient.writeUTF(result2);
						outputToClient.writeUTF(result3);
						jta.append("word received from client:"+inputword+'\n');
						jta.append("jinshansearch result:" + result + '\n');
						jta.append("youdaosearch result:" + result2 + '\n');
						jta.append("bingsearch result:" + result3 + '\n');
					}
					
				}//if
					if(requesttype==2){  //服务器接收来自客户端的新的单词加入数据库的申请
						jta.append("receive ADD WORD TO DATABASE request "+'\n');
						String inputword=inputFromClient.readUTF();
						Statement statement = DataBase.connect().createStatement();
						ResultSet resultSet=statement.executeQuery("select 1 from dictionary where Word='"+inputword+"'");
						//对是否已在数据库进行判断
						if(!resultSet.next()){
						DictionaryManager dic=new DictionaryManager();
						dic.AddWord(inputword);
						jta.append("add word to database successfully!"+'\n');
						}
						else{
							jta.append("word exists! add word to database failed! "+'\n');
						}
					}
					if(requesttype==3){  //服务器接收来自客户端的点赞请求
						String praisekind=inputFromClient.readUTF();
						String inputword=inputFromClient.readUTF();
						String Uid=inputFromClient.readUTF();
						if(praisekind.equals("jinshan")){
							jta.append("receive ADD JINSHAN PRAISE request  "+'\n');
							DictionaryManager dic=new DictionaryManager();
							if(dic.AddPraise(Uid, inputword, 1)==true){
								jta.append("add jinshan praise successfully!"+'\n');
								outputToClient.writeUTF("add jinshan praise successfully!");
							}
							else{
								jta.append("add jinshan praise failed!"+'\n');
								outputToClient.writeUTF("add jinshan praise failed!");
							}
						}
						if(praisekind.equals("youdao")){
							jta.append("receive ADD YOUDAO PRAISE request  "+'\n');
							DictionaryManager dic=new DictionaryManager();
							if(dic.AddPraise(Uid, inputword, 2)==true){
								jta.append("add youdao praise successfully!"+'\n');
								outputToClient.writeUTF("add youdao praise successfully!");
							}
							else{
								jta.append("add youdao praise failed!"+'\n');
								outputToClient.writeUTF("add youdao praise failed!");
							}
						}
						if(praisekind.equals("bing")){
							jta.append("receive ADD BING PRAISE request  "+'\n');
							DictionaryManager dic=new DictionaryManager();
							if(dic.AddPraise(Uid, inputword, 3)==true){
								jta.append("add bing praise successfully!"+'\n');
								outputToClient.writeUTF("add bing praise successfully!");
							}
							else{
								jta.append("add bing praise failed!"+'\n');
								outputToClient.writeUTF("add bing praise failed!");
							}
						}
					}
					if(requesttype==4){//服务器接收来自客户端的取消点赞请求
						String praisekind=inputFromClient.readUTF();
						String inputword=inputFromClient.readUTF();
						String Uid=inputFromClient.readUTF();
						if(praisekind.equals("jinshan")){
							jta.append("receive JINSHAN DELPRAISE request  "+'\n');
							DictionaryManager dic=new DictionaryManager();
							if(dic.DelPraise(Uid, inputword, 1)==true){
								jta.append("jinshan delpraise successfully!"+'\n');
								outputToClient.writeUTF("jinshan delpraise successfully!");
							}
							else{
								jta.append("jinshan delpraise failed!"+'\n');
								outputToClient.writeUTF("jinshan delpraise failed!");
							}
						}
						if(praisekind.equals("youdao")){
							jta.append("receive YOUDAO DELPRAISE request  "+'\n');
							DictionaryManager dic=new DictionaryManager();
							if(dic.DelPraise(Uid, inputword, 2)==true){
								jta.append("youdao delpraise successfully!"+'\n');
								outputToClient.writeUTF("youdao delpraise successfully!");
							}
							else{
								jta.append("youdao delpraise failed!"+'\n');
								outputToClient.writeUTF("youdao delpraise failed!");
							}
			
						}
						if(praisekind.equals("bing")){
							jta.append("receive BING DELPRAISE request  "+'\n');
							DictionaryManager dic=new DictionaryManager();
							if(dic.DelPraise(Uid, inputword, 3)==true){
								jta.append("bing delpraise successfully!"+'\n');
								outputToClient.writeUTF("bing delpraise successfully!");
							}
							else{
								jta.append("bing delpraise failed!"+'\n');
								outputToClient.writeUTF("bing delpraise failed!");
							}
						}
					}
					if(requesttype==5){ //服务器接收来自客户端的注册申请
						jta.append("receive SIGN IN request  "+'\n');
						String uid=inputFromClient.readUTF();
						String pw=inputFromClient.readUTF();
						UserManager.createUser(uid,pw);
						jta.append("create user successfully! "+'\n');
						outputToClient.writeUTF("create user successfully! ");
					}
					if(requesttype==6){ //服务器接收来自客户端的登录申请
						jta.append("receive LOG IN request  "+'\n');
						String uid=inputFromClient.readUTF();
						String pw=inputFromClient.readUTF();
						if(UserManager.identityVerify(uid, pw)==true){
							jta.append("log in successfully!  "+'\n');
							outputToClient.writeUTF("log in successfully!");
							UserManager.login(uid);
							}
						else{
							jta.append("log in failed! "+'\n');
							outputToClient.writeUTF("log in failed!");
						}
					}
					if(requesttype==7){ //服务器接收来自客户端的修改密码申请
						jta.append("receive MODIFY PASSWORD reqeust "+'\n');
						String uid=inputFromClient.readUTF();
						String oldpw=inputFromClient.readUTF();
						String newpw=inputFromClient.readUTF();
						if(UserManager.changePassword(uid, oldpw, newpw)==true){
							jta.append("modify password successfully!  "+'\n');
							outputToClient.writeUTF("modify password successfully!");
						}
						else{
							jta.append("modify password failed!  "+'\n');
							outputToClient.writeUTF("modify password failed!");
						}
					}
					if(requesttype==8){//服务器接收来自客户端的添加好友申请
						jta.append("receive ADD FRIENDS request   "+'\n');
						String usid=inputFromClient.readUTF();
						conn= DataBase.connect();
						Statement statement =conn.createStatement();
						ResultSet resultSet=statement.executeQuery("select 1 from usertable where username='"+usid+"'");
						//对是否已在数据库进行判断
						if(!resultSet.next()){
							jta.append("no such user! "+'\n');
							outputToClient.writeUTF("no such user! ");
						}
						else{
							jta.append("search the user successfully! "+'\n');
							//outputToClient.writeUTF("search the user successfully! ");
							resultSet=statement.executeQuery("select 1 from Login where username='"+usid+"'");
							if(!resultSet.next()){
								jta.append("the user is offline,I'll send the message when he is online!"+'\n');
							}
							else{
								jta.append("I'm sending the message to the user");
							}
						}
						DataBase.close(conn);
					}
					if(requesttype==9){ //服务器接收来自客户端的注销申请
						jta.append("receive LOG OUT request  "+'\n');
						String uid=inputFromClient.readUTF();
						UserManager.logout(uid);
						jta.append("log out successfully!"+'\n');
					}
					if(requesttype==10){ //服务器接收来自客户端的刷新在线好友列表申请
						jta.append("receive DISPLAY ONLINE USER request  "+'\n');
						conn = DataBase.connect();
						Statement statement = conn.createStatement();
						Statement statement2=conn.createStatement();
						ResultSet resultSet=statement.executeQuery("select * from Login");
						ResultSet resultSet2=statement2.executeQuery("select count(*) from Login");
						if(resultSet2.next()){
							int count=resultSet2.getInt(1);
							outputToClient.writeInt(count);
						}
						while(resultSet.next()){
							outputToClient.writeUTF(resultSet.getString(1));
						}
						DataBase.close(conn);
					}
					if(requesttype==11){ //服务器接收来自客户端的刷新离线好友列表申请
						jta.append("receive DISPLAY OFFLINE USER request  "+'\n');
						conn = DataBase.connect();
						Statement statement3 = conn.createStatement();
						Statement statement4=conn.createStatement();
						Statement statement5=conn.createStatement();
						ResultSet resultSet3=statement3.executeQuery("select count(*) from Login");
						ResultSet resultSet4=statement4.executeQuery("select count(*) from USERTABLE");
						ResultSet resultSet5=statement5.executeQuery("select distinct username from USERTABLE where username not in (select username from Login)");
						int count,count1 = 0,count2 = 0;
						if(resultSet3.next()){
							count1=resultSet3.getInt(1);
						}
						if(resultSet4.next()){
							count2=resultSet4.getInt(1);
						}
						count=count2-count1;
						outputToClient.writeInt(count);
						while(resultSet5.next()){
							outputToClient.writeUTF(resultSet5.getString(1));
						}
						DataBase.close(conn);
					}
					/**if(requesttype==12){ //服务器接收来自客户端的查看用户点赞情况-金山
						jta.append("receive DISPLAY USER'S JINSHAN PRAISE request  "+'\n');
						String userid=inputFromClient.readUTF();
						
						conn = DataBase.connect();
						Statement statement1 = conn.createStatement();
						Statement statement2 = conn.createStatement();
						
						ResultSet resultSet1=statement1.executeQuery("select count(*) as value from jinshanpraise where username='"+userid+"'");
						ResultSet resultSet2=statement2.executeQuery("select * from jinshanpraise where username='" +userid+"'");
						if(resultSet1.next()){
							int count=resultSet1.getInt(1);
							outputToClient.writeInt(count);
						}
						
						
						while(resultSet2.next()){
							String result=resultSet2.getString(2);
							outputToClient.writeUTF(result);
						}
						if(!resultSet2.next()){
							outputToClient.writeUTF("No praise record!");
						}
						DataBase.close(conn);
					}
					if(requesttype==13){//服务器接收来自客户端的查看用户点赞情况-有道
						jta.append("receive DISPLAY USER'S YOUDAO PRAISE request  "+'\n');
						String userid=inputFromClient.readUTF();
						
						conn = DataBase.connect();
						Statement statement3 = conn.createStatement();
						Statement statement4 = conn.createStatement();
						
						ResultSet resultSet3=statement3.executeQuery("select count(*) as value from youdaopraise where username='"+userid+"'");
						ResultSet resultSet4=statement4.executeQuery("select * from youdaopraise where username='" +userid+"'");
						if(resultSet3.next()){
							int count=resultSet3.getInt(1);
							outputToClient.writeInt(count);
						}
						
						
						while(resultSet4.next()){
							String result=resultSet4.getString(2);
							outputToClient.writeUTF(result);
						}
						if(!resultSet4.next()){
							outputToClient.writeUTF("No praise record!");
						}
						DataBase.close(conn);
					}*/
					if(requesttype==12){ //服务器接收来自客户端的发送单词卡请求-金山
						jta.append("receive SEND WORDCARD request  "+'\n');
						String type=inputFromClient.readUTF();
						String sender=inputFromClient.readUTF();
						String receiver=inputFromClient.readUTF();
						String word=inputFromClient.readUTF();
						DictionaryManager dic=new DictionaryManager();
						dic.Addwordcard(sender, receiver, word,type);
						jta.append("The word has put to database  "+'\n');
						
					}
					if(requesttype==13){ //服务器接收来自客户端的询问是否有单词卡传送
						jta.append("I'm searching in the database !"+'\n');
						String asker=inputFromClient.readUTF();
						conn = DataBase.connect();
						Statement statement = conn.createStatement();
						ResultSet resultSet=statement.executeQuery("select * from Wordcard where receiver='"+asker+"'");
						if(resultSet.next()){
							jta.append("search success!");
							String word=resultSet.getString(3);
							outputToClient.writeUTF(word);
							String sender=resultSet.getString(1);
							outputToClient.writeUTF(sender);
							String type=resultSet.getString(4);
							if(type.equals("jinshan")){
								String s=regex_iciba.icibasearch(word);
								outputToClient.writeUTF(s);
							}
							else if(type.equals("youdao")){
								String s=regex_youdao.youdaosearch(word);
								outputToClient.writeUTF(s);
							}
							else if(type.equals("bing")){
								String s=regex_bing.bingsearch(word);
								outputToClient.writeUTF(s);
							}
							DictionaryManager dic=new DictionaryManager();
							dic.Delwordcard(sender, asker, word,type);
							
							
						}
						else{
							jta.append("search failed!");
							outputToClient.writeUTF("no one sended wordcard to you!");
						}
						DataBase.close(conn);
					}
					
			}//while
				
			}//try
			catch(IOException e){
				System.err.println(e);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//run
	}//class
}






