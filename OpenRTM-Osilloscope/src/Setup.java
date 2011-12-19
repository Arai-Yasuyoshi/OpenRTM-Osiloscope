import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

//設定ファイル"setup.ini"に対して操作を行うクラス
public class Setup {
	
	private static final String filename = "setup.ini";
	
	//setup.iniを更新する
	public static void writeSetup(int dataNumber, int[] datatype, String[] dataname){
		try{
			File file = new File(filename);
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			
			bw.write("[データ数]\n");
			bw.write(dataNumber + "\n");
			
			for(int i = 0; i < dataNumber; i++){
				bw.write("[データ"+ i + "]\n");
				bw.write(datatype[i] + "\n");
				bw.write(dataname[i] + "\n");
			}
			
			bw.close();
			
		}catch(Exception e){
			System.out.println("ファイルに保存できませんでした");
		}
	}
	
	//setup.iniを読み込む
	public static void readSetup(){
		try{
			File file = new File(filename);
			FileReader filereader = new FileReader(file);
			BufferedReader br = new BufferedReader(filereader);
			
			String str;
			int dataNumber;
			
			str = br.readLine();
			if(!str.equals("[データ数]")){
				System.out.println("データが壊れています");
				initSetup();
				return;
			}
			
			dataNumber = Integer.parseInt(br.readLine());
			if(dataNumber <= 0 || Data.MAXIMUM_DATAPORTS < dataNumber){
				SetupFrame.dataNumber = 1;
				System.out.println("データが壊れています");
				initSetup();
				return;
			}
			SetupFrame.dataNumber = dataNumber;		
			
			for(int i = 0; i < dataNumber; i++){
				
				str = br.readLine();
				if(!str.equals("[データ" + i + "]")){
					System.out.println("データが壊れています");
					initSetup();
					return;
				}
				
				str = br.readLine();
				if( !(0 <= Integer.parseInt(str) && Integer.parseInt(str) < Data.dataType.length) ){
					System.out.println("データが壊れています");
					initSetup();
					return;
				}
				SetupFrame.dataType[i] = Integer.parseInt(str);
								
				str = br.readLine();
				SetupFrame.dataName[i] = str;
			}
			
			if(br.readLine() != null){
				System.out.println("データが壊れています");
				initSetup();
			}
			
			br.close();
			
			
			for(int i = 0; i < dataNumber; i++){
				System.out.println("SetupFrame.initialDatatype[" + i + "] = " + SetupFrame.dataType[i]);
				System.out.println("SetupFrame.initialDataname[" + i + "] = " + SetupFrame.dataName[i]);
			}
			
		}catch(Exception e){
			System.out.println("ファイルを読み込めませんでした");
			initSetup();
		}
	}
	
	//setup.iniが存在しないとき，またはファイルが壊れているときこのメソッドを呼ぶ
	public static void initSetup(){
		try{
			File file = new File(filename);
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			
			bw.write("[データ数]\n");
			bw.write(1 + "\n");
			
			bw.write("[データ"+ 0 + "]\n");
			bw.write(0 + "\n");
			bw.write("out0\n");
			
			bw.close();
			
			
		}catch(Exception e){
			System.out.println("ファイルに保存できませんでした");
		}
		
		readSetup();
	}
}	
