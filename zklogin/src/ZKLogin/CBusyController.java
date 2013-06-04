package ZKLogin;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

public class CBusyController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 5371813838204693420L;
	
	@Wire
	Window w;
	
	@Listen( "onLater=#w" )
	public void onLater() {

		try {
		  
			Thread.sleep( 5000 );
		  
		}
		catch ( Exception Ex ) {
			
			
		}
		
		Clients.clearBusy(); //remove the busy message
		
	}
	
	@Listen( "onClick=#longoperation" )
	public void onClick() {
		
		  // Clients.
		  Clients.showBusy("Ejecutando..."); //show a busy message to user
		  Events.echoEvent("onLater", w, null); //echo an event back
			
	}
	
	
}
