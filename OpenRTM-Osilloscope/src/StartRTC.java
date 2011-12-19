
public class StartRTC {
	
	//メインスレッドを止める
	public synchronized void readyRTC(){
		try{
			wait();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//メインスレッドを再開する
	public synchronized void startRTC(){
		notify();
	}
}
