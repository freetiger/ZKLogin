package ZKLogin;

import java.io.File;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Iframe;

public class CRemoteFrameController1 extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 209520507651234243L;

	@Wire 
	Iframe htmlframe;
	
	@Wire
	Groupbox mainGropuBox;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);
	
		String strJarFolder = Executions.getCurrent().getDesktop().getWebApp().getRealPath( "/" );
		
		AMedia Media = new AMedia( new File( strJarFolder + "html/help/main/document_embedded.html" ), "html", null ); 
		
		htmlframe.setContent( Media );
		
		/*mainGropuBox.addEventListener( Events.ON_OPEN, new EventListener<Event>() {
		    
			public void onEvent(Event event) throws Exception {
			
				 event.stopPropagation();
				
			}
			
		});*/
		
	}
	
}
