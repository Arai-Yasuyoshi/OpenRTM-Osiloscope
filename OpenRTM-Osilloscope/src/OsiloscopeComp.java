// -*- Java -*-
/*!
 * @file OsiloscopeComp.java
 * @brief Standalone component
 * @date $Date$
 *
 * $Id$
 */


import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.ModuleInitProc;
import jp.go.aist.rtm.RTC.RTObject_impl;
import jp.go.aist.rtm.RTC.executionContext.PeriodicExecutionContext;
import jp.go.aist.rtm.RTC.util.Properties;
import RTC.ExecutionContextListHolder;

/*!
 * @class OsiloscopeComp
 * @brief Standalone component Class
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
public class OsiloscopeComp implements ModuleInitProc {

	 static StartRTC rtc;
	
	static int dataNumber;
	static int[] dataType;
	static String[] dataName;

    public void myModuleInit(Manager mgr) {
      Properties prop = new Properties(Osiloscope.component_conf);
      mgr.registerFactory(prop, new Osiloscope(), new Osiloscope());

      // Create a component
      RTObject_impl comp = mgr.createComponent("Osiloscope");
      if( comp==null ) {
    	  System.err.println("Component create failed.");
    	  System.exit(0);
      }
      
      //ComponentProfile prof = comp.get_component_profile();
      
      
      // Example
      // The following procedure is examples how handle RT-Components.
      // These should not be in this function.

//      // Get the component's object reference
//      Manager manager = Manager.instance();
//      RTObject rtobj = null;
//      try {
//          rtobj = RTObjectHelper.narrow(manager.getPOA().servant_to_reference(comp));
//      } catch (ServantNotActive e) {
//          e.printStackTrace();
//      } catch (WrongPolicy e) {
//          e.printStackTrace();
//      }
//
//      // Get the port list of the component
//      PortListHolder portlist = new PortListHolder();
//      portlist.value = rtobj.get_ports();
//
//      // getting port profiles
//      System.out.println( "Number of Ports: " );
//      System.out.println( portlist.value.length );
//      for( int intIdx=0;intIdx<portlist.value.length;++intIdx ) {
//          Port port = portlist.value[intIdx];
//          System.out.println( "Port" + intIdx + " (name): ");
//          System.out.println( port.get_port_profile().name );
//        
//          PortInterfaceProfileListHolder iflist = new PortInterfaceProfileListHolder();
//          iflist.value = port.get_port_profile().interfaces;
//          System.out.println( "---interfaces---" );
//          for( int intIdx2=0;intIdx2<iflist.value.length;++intIdx2 ) {
//              System.out.println( "I/F name: " );
//              System.out.println( iflist.value[intIdx2].instance_name  );
//              System.out.println( "I/F type: " );
//              System.out.println( iflist.value[intIdx2].type_name );
//              if( iflist.value[intIdx2].polarity==PortInterfacePolarity.PROVIDED ) {
//                  System.out.println( "Polarity: PROVIDED" );
//              } else {
//                  System.out.println( "Polarity: REQUIRED" );
//              }
//          }
//          System.out.println( "---properties---" );
//          NVUtil.dump( new NVListHolder(port.get_port_profile().properties) );
//          System.out.println( "----------------" );
//      }
    }

    public static void main(String[] args) {
    		
    	//データポート設定のウィンドウを生成
    	SetupFrame frame = new SetupFrame();
    	frame.setVisible(true);
    	
    	//ここで一旦メインのスレッドを停止
    	rtc = new StartRTC();  
    	rtc.readyRTC();
    	
    	// Initialize manager
    	final Manager manager =  Manager.init(args);

    	// Set module initialization proceduer
        // This procedure will be invoked in activateManager() function.
        OsiloscopeComp init = new OsiloscopeComp();
        manager.setModuleInitProc(init);

        // Activate manager and register to naming service
        manager.activateManager();
        
        // run the manager in blocking mode
        // runManager(false) is the default.
        
        manager.runManager();
        

        // If you want to run the manager in non-blocking mode, do like this
        // manager.runManager(true);
    }

}

