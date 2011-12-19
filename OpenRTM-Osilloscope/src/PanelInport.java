

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JTextField;

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
public class PanelInport extends javax.swing.JPanel {
	public JComboBox cmbDataType;
	public 	JTextField txtDataName;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public PanelInport() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			FlowLayout thisLayout = new FlowLayout();
			this.setPreferredSize(new java.awt.Dimension(289, 37));
			this.setLayout(thisLayout);
			{
				ComboBoxModel cmbDatatypeModel = 
					new DefaultComboBoxModel(
							//new String[] {"RTC::TimedLong", "RTC::TimedShort", "RTC::TimedDouble", "RTC::TimedFloat" });
							Data.dataType);
				cmbDataType = new JComboBox();
				this.add(cmbDataType);
				cmbDataType.setModel(cmbDatatypeModel);
				cmbDataType.setPreferredSize(new java.awt.Dimension(140, 26));
			}
			{
				txtDataName = new JTextField();
				this.add(txtDataName);
				txtDataName.setPreferredSize(new java.awt.Dimension(107, 26));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
