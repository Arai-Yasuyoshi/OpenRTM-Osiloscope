
public class StartRTC {
	
	//���C���X���b�h���~�߂�
	public synchronized void readyRTC(){
		try{
			wait();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//���C���X���b�h���ĊJ����
	public synchronized void startRTC(){
		notify();
	}
}
