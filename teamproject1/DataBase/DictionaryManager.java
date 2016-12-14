package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

//Using Type to discriminate meanings from different sources
//Type 1:Jinshan
//Type 2:Bing
//Type 3:Youdao

public class DictionaryManager {
	//public static boolean AddWord(String word,String[] meaning, int type) 
	public static boolean AddWord(String word){
		boolean change = false;
		try {
			Statement statement = DataBase.connect().createStatement();
			//String sql = "insert into Dictionary(Word) values('"
			//		+ word+"');";
			String sql = "insert into Dictionary(Word,NumZanJinshan,NumZanYoudao,NumZanBing) values('"
					+ word +"',0,0,0);";
			statement.execute(sql);
			change = true;
		} catch (SQLException e) {
			System.out.println("Word Exists!");
			e.printStackTrace();
		}
		//finally{
		//	DataBase.close(conn);
	//	}
		return change;
	}
	public static boolean AddPraise(String username, String word,int addpraisetype){
		boolean change = false;
		Connection conn = null;
		try {
			conn = DataBase.connect();
			Statement statement = conn.createStatement();
			String sql = null;
			if(addpraisetype == 1)
				sql = "insert into JinshanPraise(username,Word) values('"+ username + "','"+ word + "');";
			if(addpraisetype==2)
				sql = "insert into YouDaoPraise(username,Word) values('"+ username + "','"+ word + "');";
			if(addpraisetype==3)
				sql = "insert into BingPraise(username,Word) values('"+ username + "','"+ word + "');";
			change = statement.execute(sql);
			DataBase.close(conn);
			return true;
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null,
			//	       "你已经点过赞了，不要重复点击!", "系统信息", JOptionPane.ERROR_MESSAGE);
			DataBase.close(conn);
			return false;
		}
		/**finally{
			DataBase.close(conn);
			return true;
		}*/
		
	}
	public static boolean DelPraise(String username, String word,int delpraisetype){
		boolean change=false;
		Connection conn=null;
		try {
			conn=DataBase.connect();
			Statement statement=conn.createStatement();
			String sql=null;
			if(delpraisetype==1)
				sql="delete from JinshanPraise where username = '"+ username + "' and word = '"+ word + "';";
			if(delpraisetype==2)
				sql="delete from YouDaoPraise where username = '"+ username + "' and word = '"+ word + "';";
			if(delpraisetype==3)
				sql="delete from BingPraise where username = '"+ username + "' and word = '"+ word + "';";
			
			change=statement.execute(sql);
			DataBase.close(conn);
			return true;
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null,
			//	       "你还没点过赞", "系统信息", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		/**finally{
			DataBase.close(conn);
		}
		return true;*/
	}
}
