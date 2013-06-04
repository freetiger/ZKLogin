package ZKLogin;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
//import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class CHelpManagerController extends SelectorComposer<Component> {

	private static final long serialVersionUID = -5806617050756985624L;

	@Wire
	Window frmHelpManager;
	
	@Wire
	Iframe HelpContent;
	
	@Wire
	Textbox edSearch;

	@Wire
	Button btnSearch;
	
	@Listen("onChanging = #edSearch")
	public void searchChange( InputEvent event ) {
		
		btnSearch.setDisabled( event.getValue().trim().isEmpty() );
		
	}
	
	@Listen("onClick = #btnSearch")
	public void searchContent( Event event ) {
		
		HelpContent.setSrc( "http://localhost:8080/zklogin/html/help/main/document.html?highlight=" + edSearch.getValue() );
		
	}

	@Listen("onClick = #btnClose")
	public void showModal( Event event ) {
		
		frmHelpManager.detach();
		
		//HelpContent.
		
	}
	
}
