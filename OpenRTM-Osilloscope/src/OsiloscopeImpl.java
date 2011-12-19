// -*- Java -*-
/*!
 * @file  OsiloscopeImpl.java
 * @brief ModuleDescription
 * @date  $Date$
 *
 * $Id$
 */

import java.awt.Event;

import RTC.TimedDouble;
import RTC.TimedFloat;
import RTC.TimedLong;
import RTC.TimedShort;
import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.InPort;
import jp.go.aist.rtm.RTC.util.DataRef;
import RTC.ReturnCode_t;

/*!
 * @class OsiloscopeImpl
 * @brief ModuleDescription
 *
 */

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
public class OsiloscopeImpl extends DataFlowComponentBase {
	
	private BasicFrame frame;
	private double contextRate;
	
  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
	public OsiloscopeImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        for(int i = 0; i < OsiloscopeComp.dataNumber; i++){
        	m_longIn_val[i] = new TimedLong();
        	m_shortIn_val[i] = new TimedShort();
        	m_doubleIn_val[i] = new TimedDouble();
        	m_floatIn_val[i] = new TimedFloat();
        	
        	m_longIn[i] = new DataRef<TimedLong>(m_longIn_val[i]);
        	m_shortIn[i] = new DataRef<TimedShort>(m_shortIn_val[i]);
        	m_doubleIn[i] = new DataRef<TimedDouble>(m_doubleIn_val[i]);
        	m_floatIn[i] = new DataRef<TimedFloat>(m_floatIn_val[i]);
        	
            String str = "in" + i;
            m_longInIn[i] = new InPort<TimedLong>(str, m_longIn[i]);
            m_shortInIn[i] = new InPort<TimedShort>(str, m_shortIn[i]);
            m_doubleInIn[i] = new InPort<TimedDouble>(str, m_doubleIn[i]);
            m_floatInIn[i] = new InPort<TimedFloat>(str, m_floatIn[i]);
        }
        
        
        // </rtc-template>

    }

    /**
     *
     * The initialize action (on CREATED->ALIVE transition)
     * formaer rtc_init_entry() 
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onInitialize() {
        // Registration: InPort/OutPort/Service
        // <rtc-template block="registration">
        // Set InPort buffers
    	String str;
    	
    	for(int i = 0; i < OsiloscopeComp.dataNumber; i++){
    		System.out.println(OsiloscopeComp.dataName[i]);
    		
    		switch(OsiloscopeComp.dataType[i]){
    		case 0:
    			str = "m_longIn" + i;
        		addInPort(str, m_longInIn[i]);
        		break;
    		case 1:
    			str = "m_shortIn" + i;
    			addInPort(str, m_shortInIn[i]);
    			break;
    		case 2:
    			str = "m_doubleIn" + i;
    			addInPort(str, m_doubleInIn[i]);
    			break;
    		case 3:
    			str = "m_floatIn" + i;
    			addInPort(str, m_floatInIn[i]);
    			break;
    		default:
    			System.out.println("選択されたデータ型はまだ実装されていません");
    		}		
    	}
    	
        // </rtc-template>
        frame = new BasicFrame();
        frame.setVisible(true);
        return super.onInitialize();
    }

    /***
     *
     * The finalize action (on ALIVE->END transition)
     * formaer rtc_exiting_entry()
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onFinalize() {
        return super.onFinalize();
    }

    /***
     *
     * The startup action when ExecutionContext startup
     * former rtc_starting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStartup(int ec_id) {
//        return super.onStartup(ec_id);
//    }

    /***
     *
     * The shutdown action when ExecutionContext stop
     * former rtc_stopping_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onShutdown(int ec_id) {
//        return super.onShutdown(ec_id);
//    }

    /***
     *
     * The activated action (Active state entry action)
     * former rtc_active_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onActivated(int ec_id) { 
    	
    	contextRate = get_context(ec_id).get_rate();
    	
    	if (!frame.gc.getAnimationFlag()) {
			frame.jSlider1.setValue(0);
			frame.jSlider1.setEnabled(false);
			frame.btnStartAnimation.setEnabled(false);
			frame.btnStopAnimation.setEnabled(true);
			frame.gc.InitAnimation();
			frame.gc.StartAnimation();
		} else {
			System.out.println("すでにアニメーションは開始されています");
		}
        return super.onActivated(ec_id);
    }

    /***
     *
     * The deactivated action (Active state exit action)
     * former rtc_active_exit()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onDeactivated(int ec_id) {
    	if(frame.gc.getAnimationFlag()){
    		/*
    		if(frame.gc.getBufSize(OsiloscopeComp.datatype[0]) < frame.gc.ELEMENT_X){
				frame.jSlider1.setMaximum(0);
			}else{
				frame.jSlider1.setMaximum(frame.gc.getBufSize(OsiloscopeComp.datatype[0]) - frame.gc.ELEMENT_X);
			}
			*/
    		frame.jSlider1.setMaximum(Buffer.getSize());
    		
			frame.jSlider1.setEnabled(true);
			frame.btnStopAnimation.setEnabled(false);
			frame.btnStartAnimation.setEnabled(true);
			frame.gc.StopAnimation();
		}else{
			System.out.println("アニメーションは停止しています");
		}
        return super.onDeactivated(ec_id);
    }

    /***
     *
     * The execution action that is invoked periodically
     * former rtc_active_do()
     *
     * @param ec_id target ExecutBionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onExecute(int ec_id) {
    	for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
			switch (OsiloscopeComp.dataType[i]) {
			case 0:
				if(m_longInIn[i].isNew()){
					m_longInIn[i].read();
					Buffer.setLongTmp(i, (long)m_longIn[i].v.data);
				}
				break;
			case 1:
				if(m_shortInIn[i].isNew()){
					m_shortInIn[i].read();
					Buffer.setShortTmp(i, (short)m_shortIn[i].v.data);
				}
				break;
			case 2:
				if(m_doubleInIn[i].isNew()){
					m_doubleInIn[i].read();
					Buffer.setDoubleTmp(i, (double)m_doubleIn[i].v.data);
				}
				break;
			case 3:
				if(m_floatInIn[i].isNew()){
					m_floatInIn[i].read();
					Buffer.setFloatTmp(i, (float)m_floatIn[i].v.data);
				}
				break;
			default:
			}
		}
    	BasicFrame.jSlider1.setMaximum(Buffer.getSize());
    	if(frame.gc.getAnimationFlag()){
    		// バッファの更新
    		Buffer.updateBuf();
    	}
    	
        return super.onExecute(ec_id);
    }

    /***
     *
     * The aborting action when main logic error occurred.
     * former rtc_aborting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//  @Override
//  public ReturnCode_t onAborting(int ec_id) {
//      return super.onAborting(ec_id);
//  }

    /***
     *
     * The error action in ERROR state
     * former rtc_error_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    public ReturnCode_t onError(int ec_id) {
//        return super.onError(ec_id);
//    }

    /***
     *
     * The reset action that is invoked resetting
     * This is same but different the former rtc_init_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onReset(int ec_id) {
//        return super.onReset(ec_id);
//    }

    /***
     *
     * The state update action that is invoked after onExecute() action
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStateUpdate(int ec_id) {
//        return super.onStateUpdate(ec_id);
//    }

    /***
     *
     * The action that is invoked when execution context's rate is changed
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onRateChanged(int ec_id) {
    	System.out.println("呼ばれました");
        return super.onRateChanged(ec_id);
    }

    // DataInPort declaration
    // <rtc-template block="inport_declare">
    protected TimedLong[] m_longIn_val = new TimedLong[OsiloscopeComp.dataNumber];
    protected TimedShort[] m_shortIn_val = new TimedShort[OsiloscopeComp.dataNumber];
    protected TimedDouble[] m_doubleIn_val = new TimedDouble[OsiloscopeComp.dataNumber];
    protected TimedFloat[] m_floatIn_val = new TimedFloat[OsiloscopeComp.dataNumber];
    
    protected DataRef<TimedLong>[] m_longIn = new DataRef[OsiloscopeComp.dataNumber];
    protected DataRef<TimedShort>[] m_shortIn = new DataRef[OsiloscopeComp.dataNumber];
    protected DataRef<TimedDouble>[] m_doubleIn = new DataRef[OsiloscopeComp.dataNumber];
    protected DataRef<TimedFloat>[] m_floatIn = new DataRef[OsiloscopeComp.dataNumber];
    /*!
     */
    protected InPort<TimedLong>[] m_longInIn = new InPort[OsiloscopeComp.dataNumber];
    protected InPort<TimedShort>[] m_shortInIn = new InPort[OsiloscopeComp.dataNumber];
    protected InPort<TimedDouble>[] m_doubleInIn = new InPort[OsiloscopeComp.dataNumber];
    protected InPort<TimedFloat>[] m_floatInIn = new InPort[OsiloscopeComp.dataNumber];

    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    
    // </rtc-template>

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    
    // </rtc-template>


}
