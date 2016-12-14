/**package ClientUI;
import java.util.Timer;
import java.util.TimerTask;
public class jishi{
	Timer timer;
	public jishi(int seconds){
		timer=new Timer();
		timer.schedule(new jishitask(), seconds*1000);
	}
	class jishitask extends TimerTask{
		public void run(){
			
			timer.cancel();
		}
	}
}*/