package net;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class regex_bing {
	private static String url_prefix = "http://cn.bing.com/dict/search?q=";
	static String word = "";
	
	public static String bingsearch(String word) throws MalformedURLException, FileNotFoundException{
		//System.out.println("请输入要搜索的单词");
		//Scanner input = new Scanner(System.in);
		//word = input.nextLine();
		StringBuilder text = new StringBuilder();
		InputStream is = null;
		try {
			URL url = new URL(url_prefix + word.replaceAll(" ", "%20"));
			//new URL(url_prefix + word);
			is = url.openStream();
			//System.out.println("The url is " + url); //debug 
			BufferedReader infile = new BufferedReader(new InputStreamReader(is));
			String str = null;
			//StringBuilder text = new StringBuilder();
			while((str = infile.readLine()) != null){
				text.append("\n" + str);
			}
	        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(is != null)
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		URL url = new URL(url_prefix + word.replaceAll(" ", "%20"));
		//URL url = new URL(url_prefix + word);
		//System.out.println("baidu Search a word begin");	//debug
		//System.out.println("The url is " + url); //debug //debug right
		//search(word);
		//System.out.println(text);
		
		
		String str  = null;

		
		Pattern p1 = Pattern.compile("(?<=<td>).*(?=</td>)");
		p1 = Pattern.compile("(?<=<h1><strong>).*(?=</strong></h1>)");
		str = text.toString();
		Matcher m = p1.matcher(str);
		//while(m.find())
		//	System.out.println(m.group());
		
		File sourceFile = new File("output_bing_temp.txt");
		PrintWriter output = new PrintWriter(sourceFile);
		Pattern explains = Pattern.compile("(?<=<ul><li><span class=\"pos\">)[\u0000-\uFFFF]*(?=</span></span></li></ul>)");
	    //explains = Pattern.compile(regEx);
		m = explains.matcher(str);
		while(m.find()){
			//System.out.println(m.group());
			output.println(m.group());
	        output.close();
		}
			

		
		Scanner input_text = new Scanner(sourceFile);
		String st = "";
		String sss="";
		if(input_text.hasNext()){
			st = input_text.nextLine();
		}else{
			sss = "No Match";
		}
            
				for (String s: st.split("[<][^<>]*[>]")) {
					  if (!s.equals("")) {
					    //System.out.println(s);
						  sss+=s;
						  sss += " ";
					  }
				}
		
				//System.out.println("~~~~~~搜索结束~~~~~~~");
				//System.out.println(sss);
				return sss;
	}

	
	
	
}
