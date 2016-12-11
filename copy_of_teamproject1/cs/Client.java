package cs;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Client{
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	public static void main(String[] args){
		new Client();
	}
	public Client(){
		try{
			Socket socket=new Socket("localhost",8000);
			fromServer=new DataInputStream(socket.getInputStream());
			toServer=new DataOutputStream(socket.getOutputStream());
			Scanner input=new Scanner(System.in);
			while(true){
				double radius=input.nextDouble();
				toServer.writeDouble(radius);
				toServer.flush();
				double area=fromServer.readDouble();
				System.out.println("Radius is"+radius + "\n");
				System.out.println("Area received from the server is"+area+'\n');
			}
		}
		catch(IOException ex){
			System.out.println(ex.toString()+'\n');
		}
	}
	
}

