package ZKLogin;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
//import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Timer;

public class CRemoteFrameController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 4341176174682517367L;

	
	
	@Wire
	Button btnRemoteButton;
	
	@Wire
	Button btnClearCount;

	@Wire 
	Timer timer1;

	@Wire
	Listbox ListBox1;
	
	int intCount = 0;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);
	
		ListModelList<String> SimpleList = new ListModelList<String>();
		
		ListBox1.setModel( SimpleList );
		
		timer1.stop();
		
	}	
	
	@Listen( "onClick=#btnRemoteButton" )
	public void onClick_btnRemoteButton( Event event ) {
	
		Clients.showNotification( "Event from here!","info", btnRemoteButton, "end_center", 1000 );
		
		if ( timer1.isRunning() == false )  {
		
			timer1.start();
		   
		    btnRemoteButton.setLabel( "Stop timer..." );
		   
		}
		else {
		   
			timer1.stop();

			btnRemoteButton.setLabel( "Start timer..." );
			
		}
			
		
	}

	@Listen( "onClick=#btnClearCount" )
	public void onClick_btnClearCount( Event event ) {
		
		Clients.showNotification( "Event from here!","info", btnClearCount, "end_center", 1000 );

		timer1.stop();

		btnRemoteButton.setLabel( "Start timer..." );

		intCount = 0;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ListModelList<String> SimpleList = (ListModelList) ListBox1.getModel();
		
		SimpleList.clear();
		
	}
	
	@Listen( "onTimer=#timer1" )
	public void onTimer_timer( Event event ) {

		intCount += 1;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ListModelList<String> SimpleList = (ListModelList) ListBox1.getModel();
		
		SimpleList.add( Integer.toString( intCount ) );
		
		
	}
	
}
