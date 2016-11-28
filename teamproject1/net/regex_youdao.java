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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class regex_youdao {
	private static String url_prefix = "http://dict.youdao.com/search?q=";
	static String word = "";
	
	public static String youdaosearch(String word) throws MalformedURLException, FileNotFoundException{
		//System.out.println("请输入要搜索的单词");
		//Scanner input = new Scanner(System.in);
		//word = input.nextLine();
		StringBuilder text = new StringBuilder();
		InputStream is = null;
		try {
			URL url = new URL(url_prefix + word.replaceAll(" ", "%20") + "&keyfrom=dict.index");
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
		


		
		
		String str="";
		str = text.toString();
		
		String sss="";
		
		Pattern wordPattern = Pattern.compile("(?<=<div id=\"phrsListTab\" class=\"trans-wrapper clearfix\">)[\u0000-\uFFFF]*(?=</div>\\s*</div>\\s*<div id=\"webTrans\" class=\"trans-wrapper trans-tab\">)");
		Matcher m = wordPattern.matcher(str);
		String wordString = null;
		if(m.find()){
			wordString = m.group();
			//System.out.println(wordString);
			Pattern p1 = Pattern.compile("(?<=<span class=\"keyword\">)[^<]*(?=</span>)"); 
			Pattern explains = Pattern.compile("(?<=<ul>)[\u0000-\uFFFF]*(?=</ul>)");
			m = p1.matcher(wordString);
			//if(m.find())
			//	System.out.println(m.group());

			m = explains.matcher(wordString);
			if(m.find()){
				//System.out.println(m.group());
				String ex = m.group();
				Pattern pex = Pattern.compile("(?<=<li>)[^<]*(?=</li>)");
				Matcher mathcer = pex.matcher(ex);
				ArrayList<String> explain = new ArrayList<String>();
				while(mathcer.find()){
					sss += mathcer.group();
					System.out.println(mathcer.group());
					explain.add(mathcer.group());
				}
				
			}
		}else{
					
			sss = "No Match";
		}
		
		
		System.out.println(sss);
		
				//System.out.println("~~~~~~搜索结束~~~~~~~");
				//System.out.println(sss);
				return sss;
	}

	
	
	
}
