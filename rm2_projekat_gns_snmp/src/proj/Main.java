package proj;
import com.ireasoning.protocol.*;
import com.ireasoning.protocol.snmp.*;

import java.awt.Component;
import java.awt.Container;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.*;







public class Main  {
	private static JTable jt  ;
    private static JTable jt2  ;
    private static JTable jt3 ;
	public static void showTable(TableModel model,TableModel model2,TableModel model3,TableModel model11,TableModel model22,TableModel model33)
    {
        JFrame f = new JFrame();
        
        JTable jt = new JTable(model);
        JTable jt2 = new JTable(model2);
        JTable jt3 = new JTable(model3);
        
        JTable jtu = new JTable(model11);
        JTable jtu2 = new JTable(model22);
        JTable jtu3 = new JTable(model33);
        
        jt.setAutoCreateColumnsFromModel(true);
        jt2.setAutoCreateColumnsFromModel(true);
        jt3.setAutoCreateColumnsFromModel(true);
        
        jtu.setAutoCreateColumnsFromModel(true);
        jtu2.setAutoCreateColumnsFromModel(true);
        jtu3.setAutoCreateColumnsFromModel(true);
        
        //JScrollPane pane = new JScrollPane(jt);
        //JScrollPane pane2 = new JScrollPane(jt2);
        //JScrollPane pane3 = new JScrollPane(jt3);
        //f.getContentPane().add(pane);
        // f.getContentPane().add(pane2);
        // f.getContentPane().add(pane3);
        
       Container c=f.getContentPane();
       c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
       
       c.add(jt.getTableHeader());c.add(jt);
       c.add(jt2.getTableHeader());c.add(jt2);
       c.add(jt3.getTableHeader());c.add(jt3);
       
       c.add(jtu.getTableHeader());c.add(jtu);
       c.add(jtu2.getTableHeader());c.add(jtu2);
       c.add(jtu3.getTableHeader());c.add(jtu3);
       
       f.pack();
       
       //  f.setSize(1000, 800);
        f.setVisible(true);
        
        f.addWindowListener(new java.awt.event.WindowAdapter(){
          public void windowClosing(java.awt.event.WindowEvent e)
          {
            System.exit(0);
          }
        } );
        
    }
	
	public static void main(String[] args) throws IOException {
		
		SnmpTarget target1 = new SnmpTarget("192.168.10.1", 161,"si2019", "si2019" );
		SnmpSession session = new SnmpSession(target1);
		session.loadMib2();
		
		SnmpTarget target2 = new SnmpTarget("192.168.20.1", 161,"si2019", "si2019" );
		SnmpSession session2 = new SnmpSession(target2);
		session2.loadMib2();
		
		SnmpTarget target3 = new SnmpTarget("192.168.30.1", 161,"si2019", "si2019" );
		SnmpSession session3 = new SnmpSession(target3);
		session3.loadMib2();
		
		SnmpTableModel table = session.snmpGetTable(".1.3.6.1.2.1.6.13");
		SnmpTableModel table2 = session2.snmpGetTable(".1.3.6.1.2.1.6.13");
		SnmpTableModel table3 = session3.snmpGetTable(".1.3.6.1.2.1.6.13");
		
		SnmpTableModel udp = session.snmpGetTable(".1.3.6.1.2.1.7.5");
		SnmpTableModel udp2 = session2.snmpGetTable(".1.3.6.1.2.1.7.5");
		SnmpTableModel udp3 = session3.snmpGetTable(".1.3.6.1.2.1.7.5");
		
        try
        {
            
            if(table == null)
            {
                System.err.println( "Table not found in loaded MIBs");
                return;
            }
            if(table2 == null)
            {
                System.err.println( "Table2 not found in loaded MIBs");
                return;
            }
            if(table3 == null)
            {
                System.err.println( "Table3 not found in loaded MIBs");
                return;
            }
            if(udp == null)
            {
                System.err.println( "udp not found in loaded MIBs");
                return;
            }
            if(udp2 == null)
            {
                System.err.println( "udep2 not found in loaded MIBs");
                return;
            }
            if(udp3 == null)
            {
                System.err.println( "udp3 not found in loaded MIBs");
                return;
            }
           
            table.setTranslateValue(true);// to use values defined in MIB
            table.startPolling(5);//poll table every 30 seconds
       
            udp.setTranslateValue(true);
            udp.startPolling(5);
            

            table2.setTranslateValue(true);// to use values defined in MIB
            table2.startPolling(5);//poll table every 30 seconds
             
            udp2.setTranslateValue(true);
            udp2.startPolling(5);

            table3.setTranslateValue(true);// to use values defined in MIB
            table3.startPolling(5);//poll table every 30 seconds
             
            udp3.setTranslateValue(true);
            udp3.startPolling(5);
            showTable(table,table2,table2,udp,udp2,udp3);

            // Thread.sleep(45 * 1000);
            // table.refreshNow();
            // Thread.sleep(45 * 1000);
            // System.out.println( "To stop polling");
            // table.stopPolling();
            
            // session.close();// !!! Do NOT close session, since it's used by polling thread
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        
        /*  while (true) {
        	 
        	try {
        		jt.repaint();jt2.repaint();jt3.repaint();
				Thread.sleep(45 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 
        }*/ 
        
        
        
        
        
        
        
        
        
	}

	
}
