package image;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.applet.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class SendWord extends JFrame {
	
	
	String getImg(String word,String[] str){
        int width = 600;      
        int height = 240;
//        int width = 1200;      
//        int height = 1600;
        int fontSize = (int)(width*0.03) ;   
        int borderWidth;
        int XEdge;
        int YEdge;
        borderWidth = 9;
        XEdge = YEdge = 9;
        
        //String s = "find vt. 查找，找到；发现；认为；感到；获得"; 
        
        String filePath = "F:/Img/" + new Date().getTime()+".png";
        File file = new File(filePath);     
        Font font = new Font("微软雅黑",Font.PLAIN,fontSize);

        
        
        
        //创建一个画布  
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);      
       //获取画布的画笔  
        Graphics2D g2 = (Graphics2D)bi.getGraphics(); 
        
        //为整体设置字体
        g2.setFont(font);
        //设置背景色
        g2.setBackground(Color.WHITE);      
        g2.clearRect(0, 0, width, height);   

        
        g2.setPaint(new Color(186,205,236)); //设置画笔颜色   
        g2.fillRect(XEdge, YEdge, width-2*XEdge, borderWidth);  
        g2.fillRect(XEdge, YEdge, borderWidth, height-2*YEdge);
        g2.fillRect(width-XEdge-borderWidth, YEdge, borderWidth, height-2*YEdge);
        g2.fillRect(XEdge, height-YEdge-borderWidth, width-2*XEdge, borderWidth);  
        
        g2.setPaint(new Color(177,137,190));      
        g2.fillRect((int)width/3, XEdge+borderWidth,(int)width/3 , (int)(fontSize*1.3));
        g2.setPaint(Color.white);
        g2.drawString("WordCard", (int)(width/3*1.25) ,(int)((XEdge+borderWidth)*2) ); 
        
        

        FontRenderContext context = g2.getFontRenderContext();      
        //Rectangle2D bounds = font.getStringBounds(s, context);      
//        double x = (width - bounds.getWidth()) / 2;      
//        double y = (height - bounds.getHeight()) / 2;      
//        double ascent = -bounds.getY();      
//        double baseY = y + ascent;      
        int x = 2*XEdge + borderWidth;  
        double y = height/3;
        //double y = (height - bounds.getHeight()) / 3;      
        //double ascent = -bounds.getY();      
        //double baseY = y + ascent;  
        

       //绘制字符串 
        
        
        //字体颜色
        g2.setPaint(new Color(177,137,230)); 
//        g2.drawString(word, x , (int)y - (fontSize));
        g2.drawString(word, x , (int)y - (fontSize));
        g2.setPaint(Color.gray);
       for(int i = 0; i < str.length; i++){
        //g2.drawString(str[i], x , (int)baseY + i*(fontSize+5));   
    	   g2.drawString(str[i], x , (int)y + i*(fontSize+5));   
        }
        
        
      //-----------生成图片开始-------------
      try {  
           //将生成的图片保存为jpg格式的文件。ImageIO支持jpg、png、gif等格式  
           ImageIO.write(bi, "jpg", file);  
           System.out.println("生成图片成功"); 
           return filePath;
       } catch (IOException e) {  
           System.out.println("生成图片出错");  
           e.printStackTrace();  
       } 
      //-------------生成图片结束------------
      
      //return filePath;
      return null;

	}

	 public void Show(String filePath) { 

		// int width = 500; 
		// int height = 700; 
		 //String urlString="E://Img//1.png";  
		 //String url="E://Img//1.png";  
		 String url=filePath; 
		 ImageIcon image = new ImageIcon(url); 
		 
		 //ImageIcon image = new ImageIcon("no.gif"); 
		 //image.setImage(image.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)); 
		 JLabel label = new JLabel(image); 
		  
		 getContentPane().add(label); 
		  
		 setBounds(100, 100, 650, 350); 
		 setDefaultCloseOperation(HIDE_ON_CLOSE); 
		 setVisible(true); 
		 } 



	
		// public void create(String str){
		 public void create(String word,String str){	 
			 String[] s = split(str);
			 
			 SendWord sw =  new SendWord();
				//s.getImg(str);
				sw.Show(sw.getImg(word,s)); 
			 
		 }
		 
		 String[] split( String exp ){
			 String regEx="prep\\.|n\\.|vi\\.|vt\\.|adj\\.|adv\\.|v\\.|pron\\.|conj\\.|a\\.|art\\.|pl\\.|int\\.|网络"; 
			 Pattern p =Pattern.compile(regEx);     
			 Matcher m = p.matcher(exp); 
			 
			 String[] words = p.split(exp);     
			 
				/*将句子结束符连接到相应的句子后*/    
				if(words.length > 0)     
				{     
				    int count = 0;     
				    while(count < words.length)     
				    {     
				        if(m.find())     
				        {     
				            //words[count] += m.group();   
//				        	words[count] = m.group() + words[count];   
				        	words[count+1] = m.group() + words[count+1];   
				        }     
				        count++;     
				    }     
				}     
				return words;
		 }
		 
		 
	/*	 public static void main(String[] args) throws MalformedURLException, FileNotFoundException { 
		        
		        String s = "";
		        String word = "fine";
		        regex_iciba ic = new regex_iciba();
		        //s = ic.icibasearch(word);
		        regex_youdao yd = new regex_youdao();
//		        s = yd.youdaosearch(word);
		        regex_bing b = new regex_bing();
		        s = b.bingsearch(word);
		        SendWord sw =  new SendWord();
		        sw.create(word,s);
		        //sw.create(s);
		        
		 } 
	*/
	

}
