package ZKLogin;

import java.util.LinkedHashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
//import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
//import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;

public class CTabsCenterController extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5199748840193140041L;
	
	@Wire
	Tabbox TabBox;
	
	@Wire
	Tabs TabBoxTabs;
	
	@Wire
	Tabpanels TabBoxPanels;
	
	EventQueue<Event> SessionEvents;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);

	    SessionEvents = EventQueues.lookup( "SessionEvents", EventQueues.SESSION, true );
	    
	    SessionEvents.subscribe( new EventListener<Event>() {
	        
	    	public void onEvent( Event event ) {
	            
	    		//Clients.showNotification( event.getName() + " " + (String) event.getData() );
	    		//Clients.showNotification( event.getName() + " " + (String) event.getData(), "warning", null, "middle_left", 5000, true );
	    		
	    		if ( event != null && event.getData() != null ) {

	    			@SuppressWarnings("unchecked")
					LinkedHashMap<String,Object> ExtraInfo = (LinkedHashMap<String,Object>) event.getData(); 
	    			
    				Tab newTab = (Tab) TabBox.getFellowIfAny( (String) ExtraInfo.get( "id" ) ); 
					
    				boolean bCreateTab = newTab == null;
    				
	    			if ( event.getName().equals( "onCreateTab1" ) ) {

	    				if ( newTab == null ) {
	    					
	    					newTab	= new Tab();
	    					newTab.setLabel( (String) ExtraInfo.get( "label" ) );
	    					newTab.setImage( "/imgs/demo.png" );
	    					newTab.setClosable( true );
	    					newTab.setId( (String) ExtraInfo.get( "id" ) );

	    					Tabpanel newPanel = new Tabpanel();
	    					Label newLabel = new Label();
	    					newLabel.setValue( (String) ExtraInfo.get( "label" ) );

	    					newPanel.appendChild( newLabel );
	    					TabBoxPanels.appendChild( newPanel );

	    					TabBoxTabs.appendChild( newTab );
	    					
	    					Clients.showNotification( "Event from here!","info", (Component) ExtraInfo.get( "from" ), "end_center", 3000 );
	    				
	    				}
	    				
	    				TabBox.setSelectedTab( newTab );
	    				    			
	    		    }
	    			else if ( event.getName().equals( "onCreateTab2" ) ) {
	    				
	    				if ( newTab == null ) {

	    					newTab	= new Tab();
	    					newTab.setLabel( (String) ExtraInfo.get( "label" ) );
	    					newTab.setImage( "/imgs/demo.png" );
	    					newTab.setClosable( true );
	    					newTab.setId(  (String) ExtraInfo.get( "id" ) );

	    					Tabpanel newPanel = new Tabpanel();
	    					Label newLabel = new Label();
	    					newLabel.setValue( (String) ExtraInfo.get( "label" ) );

	    					Include myInclude = new Include();
	    					
	    					myInclude.setSrc( "/WEB-INF/zul/banner.zul" );
	    					
	    					newPanel.appendChild( newLabel );
	    					newPanel.appendChild( myInclude );
	    					TabBoxPanels.appendChild( newPanel );
	    					
	    					TabBoxTabs.appendChild( newTab );
	    				
	    					Clients.showNotification( "Event from here!","info", (Component) ExtraInfo.get( "from" ), "end_center", 3000 );

	    				}
	    				
	    				TabBox.setSelectedTab( newTab );
	    				
	    			}
	    			else if ( event.getName().equals( "onCreateTab3" ) ) {
	    				
	    				if ( newTab == null ) {

	    					newTab	= new Tab();
	    					newTab.setLabel( (String) ExtraInfo.get( "label" ) );
	    					newTab.setImage( "/imgs/demo.png" );
	    					newTab.setClosable( true );
	    					newTab.setId(  (String) ExtraInfo.get( "id" ) );

	    					Tabpanel newPanel = new Tabpanel();
	    					newPanel.setStyle( "overflow: auto; margin: 10px 10px 10px 10px" );
	    					//Label newLabel = new Label();
	    					//newLabel.setValue( (String) ExtraInfo.get( "label" ) );

	    					//Include myInclude = new Include();
	    					
	    					//myInclude.setSrc( "/WEB-INF/zul/banner.zul" );
	    					
	    					Component[] Remote_Frame = (Component[]) ExtraInfo.get( "remote_frame" );
	    					
	    					//newPanel.appendChild( newLabel );
	    					newPanel.appendChild( Remote_Frame[0] );
	    					TabBoxPanels.appendChild( newPanel );
	    					
	    					TabBoxTabs.appendChild(newTab);
	    				
	    					Clients.showNotification( "Event from here!","info", (Component) ExtraInfo.get( "from" ), "end_center", 3000 );
	    					
	    				}
	    				
	    				TabBox.setSelectedTab( newTab );
	    				
	    			}
	    			
	    			if ( bCreateTab ) {
	    				
	    				newTab.addEventListener( "onClose", new EventListener<Event>() {
	    					   
	    					public void onEvent( Event event ) {
	    					      
	    						if ( Messagebox.show("Are you sure to close the tab?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) != Messagebox.OK ) {
	    						
	    							event.stopPropagation();
	    							
	    						}
	    						
	    					}
	    					
	    				});	    				
	    				
	    			} 

	    		}
	    	
	    	}
	    	
	    } );		
		
		
	}
	
}
