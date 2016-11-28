package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Using Type to discriminate meanings from different sources
//Type 1:Baidu
//Type 2:Bing
//Type 3:Youdao

public class DictionaryManager {
	public static boolean SetMeaning(String word,String[] meaning,int type)
	{
		boolean change = false;
		try {
			Statement statement = DataBase.connect().createStatement();
			// Statement 是 Java 执行数据库操作的一个重要方法，
			//用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。用于执行不带参数的简单SQL语句。
			String sql = "select Word from Dictionary;";
			ResultSet result = statement.executeQuery(sql); //方法 executeQuery 用于产生单个结果集的语句，例如 SELECT 语句。
			boolean existence = false;
			while(result.next()){
				if(word.equals(result.getString("Word")))
						existence = true;
			}
			if(existence == false)
				return false;
			if(type == 1)//方法 execute 用于执行返回多个结果集、多个更新计数或二者组合的语句
				statement.execute("update Dictionary set Baidu = '"+ meaning.toString() +"' where Word = '"+word+"';");
			else if(type == 2)
				statement.execute("update Dictionary set Bing = '"+ meaning.toString() +"' where Word = '"+word+"';");
			else
				statement.execute("update Dictionary set Youdao = '"+ meaning.toString() +"' where Word = '"+word+"';");
			change = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return change;
	}
	//public static boolean AddWord(String word,String[] meaning, int type) 
	public static boolean AddWord(String word) 
	{
		boolean change = false;
		try {
			Statement statement = DataBase.connect().createStatement();
			//String sql = "insert into Dictionary(Word) values('"
			//		+ word+"');";
			String sql = "insert into Dictionary(Word,NumZanBaidu,NumZanYoudao,NumZanBing) values('"
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
	public static boolean AddPraise(String word)
	{
		boolean change = false;
		try {
			Statement statement = DataBase.connect().createStatement();
			String sql = "insert into Dictionary(Word,Baidu,Bing,Youdao) values('"
					+ word +"null,null,null";
			statement.execute(sql);
			change = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return change;
	}
}
