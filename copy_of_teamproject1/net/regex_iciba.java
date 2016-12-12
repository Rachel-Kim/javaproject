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


public class regex_iciba {
    private static String url_prefix = "http://www.iciba.com/";
    static String word = "";
    
    public static String icibasearch(String word) throws MalformedURLException, FileNotFoundException{

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
        //System.out.println(text);
        
        
        String str  = null;

        //Pattern p1;
        Pattern p1 = Pattern.compile("(?<=<title>).*(?=是什么意思)");
        str = text.toString();

        
        //单词
        Matcher m = p1.matcher(str);
        while(m.find())
            System.out.println(m.group());
        
        //解析内容
//      File sourceFile = new File("output_baidu_temp.txt");
//      PrintWriter output = new PrintWriter(sourceFile);
        
        String st = "";
        //Pattern explains = Pattern.compile("(?<=<p><strong>)[\u0000-\uFFFF]*(?=</span></p></div>)");
        Pattern explains = Pattern.compile("(?<=<li class=\"clearfix\">)[\u0000-\uFFFF]*(?=<li class=\"change clearfix\">)");
        m = explains.matcher(str);
        while(m.find()){
            //System.out.println(m.group());
            st = m.group();
//          output.println(m.group());
//          output.close();
        }

        if(st == ""){
            System.out.println("No Match");
        }
        //------------------
        //String st = input.nextLine();
        String ans = "";
                for (String s: st.split("[<][^<>]*[>]")) {
                      if (!s.equals("")||!s.equals('\n')) {
                        //System.out.println(s);
                          Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                            Matcher mx = p.matcher(s);
                            String dest = mx.replaceAll("");
                          
                            ans += s;
                        //ans += s.replaceAll("\n", "");
                        
                      }
                }
                
                
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher mx = p.matcher(ans);
                String dest = mx.replaceAll("");
                
                //System.out.println(dest);
                return dest;
    }

    
    
    
}
