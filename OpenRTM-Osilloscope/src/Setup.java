import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

//�ݒ�t�@�C��"setup.ini"�ɑ΂��đ�����s���N���X
public class Setup {
	
	private static final String filename = "setup.ini";
	
	//setup.ini���X�V����
	public static void writeSetup(int dataNumber, int[] datatype, String[] dataname){
		try{
			File file = new File(filename);
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			
			bw.write("[�f�[�^��]\n");
			bw.write(dataNumber + "\n");
			
			for(int i = 0; i < dataNumber; i++){
				bw.write("[�f�[�^"+ i + "]\n");
				bw.write(datatype[i] + "\n");
				bw.write(dataname[i] + "\n");
			}
			
			bw.close();
			
		}catch(Exception e){
			System.out.println("�t�@�C���ɕۑ��ł��܂���ł���");
		}
	}
	
	//setup.ini��ǂݍ���
	public static void readSetup(){
		try{
			File file = new File(filename);
			FileReader filereader = new FileReader(file);
			BufferedReader br = new BufferedReader(filereader);
			
			String str;
			int dataNumber;
			
			str = br.readLine();
			if(!str.equals("[�f�[�^��]")){
				System.out.println("�f�[�^�����Ă��܂�");
				initSetup();
				return;
			}
			
			dataNumber = Integer.parseInt(br.readLine());
			if(dataNumber <= 0 || Data.MAXIMUM_DATAPORTS < dataNumber){
				SetupFrame.dataNumber = 1;
				System.out.println("�f�[�^�����Ă��܂�");
				initSetup();
				return;
			}
			SetupFrame.dataNumber = dataNumber;		
			
			for(int i = 0; i < dataNumber; i++){
				
				str = br.readLine();
				if(!str.equals("[�f�[�^" + i + "]")){
					System.out.println("�f�[�^�����Ă��܂�");
					initSetup();
					return;
				}
				
				str = br.readLine();
				if( !(0 <= Integer.parseInt(str) && Integer.parseInt(str) < Data.dataType.length) ){
					System.out.println("�f�[�^�����Ă��܂�");
					initSetup();
					return;
				}
				SetupFrame.dataType[i] = Integer.parseInt(str);
								
				str = br.readLine();
				SetupFrame.dataName[i] = str;
			}
			
			if(br.readLine() != null){
				System.out.println("�f�[�^�����Ă��܂�");
				initSetup();
			}
			
			br.close();
			
			
			for(int i = 0; i < dataNumber; i++){
				System.out.println("SetupFrame.initialDatatype[" + i + "] = " + SetupFrame.dataType[i]);
				System.out.println("SetupFrame.initialDataname[" + i + "] = " + SetupFrame.dataName[i]);
			}
			
		}catch(Exception e){
			System.out.println("�t�@�C����ǂݍ��߂܂���ł���");
			initSetup();
		}
	}
	
	//setup.ini�����݂��Ȃ��Ƃ��C�܂��̓t�@�C�������Ă���Ƃ����̃��\�b�h���Ă�
	public static void initSetup(){
		try{
			File file = new File(filename);
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			
			bw.write("[�f�[�^��]\n");
			bw.write(1 + "\n");
			
			bw.write("[�f�[�^"+ 0 + "]\n");
			bw.write(0 + "\n");
			bw.write("out0\n");
			
			bw.close();
			
			
		}catch(Exception e){
			System.out.println("�t�@�C���ɕۑ��ł��܂���ł���");
		}
		
		readSetup();
	}
}	
