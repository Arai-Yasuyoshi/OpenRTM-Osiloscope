import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SetupFrame extends javax.swing.JFrame {
	
	
	
	private JButton btnStart;
	private JPanel jPanel4;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JPanel jPanel3;
	private JScrollPane jScrollPane1;
	private JSpinner spnNumber;
	private JLabel jLabel1;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private PanelInport[] panelInport = new PanelInport[Data.MAXIMUM_DATAPORTS];

	public static int dataNumber;
	public static int[] dataType = new int[Data.MAXIMUM_DATAPORTS];
	public static String[] dataName = new String[Data.MAXIMUM_DATAPORTS];
	
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SetupFrame inst = new SetupFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public SetupFrame() {
		super();
		Setup.readSetup();
		initGUI();
		setTitle("入力データポートの設定");
	}
	
	private void initGUI() {
		try {
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setResizable(false);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.NORTH);
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("\u30dd\u30fc\u30c8\u6570");
				}
				{
					SpinnerNumberModel model = new SpinnerNumberModel(dataNumber,1,10,1);
					spnNumber = new JSpinner();
					jPanel1.add(spnNumber);
					spnNumber.setModel(model);
					spnNumber.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							spnNumberStateChanged(evt);
						}
					});
				}
			}
			
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				{
					jPanel2 = new JPanel();
					BoxLayout jPanel2Layout = new BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS);
					jPanel2.setLayout(jPanel2Layout);
					jScrollPane1.setViewportView(jPanel2);
					{
						jPanel3 = new JPanel();
						jPanel2.add(jPanel3);
						jPanel3.setPreferredSize(new java.awt.Dimension(303, 22));
						{
							jLabel2 = new JLabel();
							jPanel3.add(jLabel2);
							jLabel2.setText("\u30c7\u30fc\u30bf\u578b");
						}
						{
							jLabel4 = new JLabel();
							jPanel3.add(jLabel4);
							jLabel4.setText("            ");
						}
						{
							jLabel3 = new JLabel();
							jPanel3.add(jLabel3);
							jLabel3.setText("\u30c7\u30fc\u30bf\u540d");
						}
					}
					for(int i = 0; i < 10; i++){
						panelInport[i] = new PanelInport();
						if(dataNumber <= i){
							panelInport[i].cmbDataType.setEnabled(false);
							panelInport[i].txtDataName.setEnabled(false);
						}else{
							panelInport[i].cmbDataType.setSelectedIndex(dataType[i]);
							panelInport[i].txtDataName.setText(dataName[i]);
						}
						jPanel2.add(panelInport[i]);
					}
					
					
				}
			}
			{
				jPanel4 = new JPanel();
				getContentPane().add(jPanel4, BorderLayout.SOUTH);
				{
					btnStart = new JButton();
					jPanel4.add(btnStart);
					btnStart.setText("RTC\u3092\u8d77\u52d5");
					btnStart.setPreferredSize(new java.awt.Dimension(143, 39));
					btnStart.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStartActionPerformed(evt);
						}
					});
				}
			}

			pack();
			this.setSize(349, 395);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnStartActionPerformed(ActionEvent evt) {
		
		OsiloscopeComp.dataNumber = (Integer)spnNumber.getValue();
		OsiloscopeComp.dataType = new int[OsiloscopeComp.dataNumber];
		OsiloscopeComp.dataName = new String[OsiloscopeComp.dataNumber];
		for(int i = 0; i < OsiloscopeComp.dataNumber; i++){
			OsiloscopeComp.dataType[i] = panelInport[i].cmbDataType.getSelectedIndex();
			OsiloscopeComp.dataName[i] = panelInport[i].txtDataName.getText();
		}
		
		for(int i = 0; i <OsiloscopeComp.dataNumber; i++){
			if(OsiloscopeComp.dataName[i].isEmpty()){
				JOptionPane.showMessageDialog(this, "データ名を入力してください");
				return;
			}
		}
		
		//設定ファイルを更新
		Setup.writeSetup(OsiloscopeComp.dataNumber, OsiloscopeComp.dataType, OsiloscopeComp.dataName);
		
		//メインのスレッドを再開，このフレームを破棄
		OsiloscopeComp.rtc.startRTC();
		this.dispose();
	}
	
	private void spnNumberStateChanged(ChangeEvent evt) {
		for(int i = 0; i < Data.MAXIMUM_DATAPORTS; i++){
			if(i < (Integer)spnNumber.getValue()){
				panelInport[i].cmbDataType.setEnabled(true);
				panelInport[i].txtDataName.setEnabled(true);
			}else{
				panelInport[i].cmbDataType.setEnabled(false);
				panelInport[i].txtDataName.setEnabled(false);
			}
		}
		
	}

}
