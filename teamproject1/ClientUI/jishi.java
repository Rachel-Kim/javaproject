package ClientUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
//import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Timer;
import javax.swing.JOptionPane;
public class jishi implements ActionListener{
	public static Socket socket;
	private Timer timer;
	public jishi(){
		timer=new Timer(2000,this);
		timer.start();
		//timer.schedule(new jishitask(), seconds*1000);
		//fromServer=new DataInputStream(socket.getInputStream());
		//toServer=new DataOutputStream(socket.getOutputStream());
	}
	public void actionPerformed(ActionEvent e){
		try {
			
			UI.toServer.writeInt(13);
			UI.toServer.writeUTF(UI3.uid);
			String result=UI.fromServer.readUTF();
			if(result.equals("no one sended wordcard to you!"));
				//JOptionPane.showMessageDialog( null , "没有人给你发送单词卡！" ,"提示", JOptionPane.ERROR_MESSAGE) ;
			
			else{
				String sender=UI.fromServer.readUTF();
				String s=UI.fromServer.readUTF();
				int n = JOptionPane.showConfirmDialog(null, sender+"给你发送了单词卡，想查看吗？", "提示", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					System.out.println(result);
					System.out.println(s);
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void main(String[] args){
		new jishi();
	}
	
}


	
	
	
	
	