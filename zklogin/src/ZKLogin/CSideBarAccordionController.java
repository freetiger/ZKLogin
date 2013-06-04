package ZKLogin;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ServiceLoader;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
//import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
//import org.zkoss.zk.ui.impl.DesktopImpl;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
//import org.zkoss.zk.ui.WebApp;
//import org.zkoss.zk.ui.Desktop;

import AbstractModule.CAbstractModule;
import Services.CClassPathLoader;


public class CSideBarAccordionController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 7059672474805972911L;

	EventQueue<Event> GroupEvents;
	
	@Wire
	Tabbox TabBox;

	@Wire
	Tabs TabBoxTabs;
	
	@Wire
	Tabpanels TabBoxPanels;
	
	@Wire
	Button btnTest1;
	
	@Wire
	Button btnTest2;

	@Wire
	Button btnTest3;
	
	@Wire
	Button btnTest4;

	@Wire
	Button btnTest5;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);
	
		if ( TabBoxPanels != null ) {
			
			Tab newTab = new Tab();
			newTab.setLabel("Tab 4 (Dynamical)");
			newTab.setImage( "/imgs/demo.png" );
			
			Tabpanel newPanel = new Tabpanel();
			Label newLabel = new Label();
			newLabel.setValue("This is Panel 4 (Dynamical)");
			
			newPanel.appendChild(newLabel);
			TabBoxPanels.appendChild(newPanel);
			
			TabBoxTabs.appendChild(newTab);
			
			TabBox.setSelectedIndex(3);
			
		}
		
	    GroupEvents = EventQueues.lookup( "SessionEvents", EventQueues.SESSION, true );
	    
	    GroupEvents.subscribe( new EventListener<Event>() {
	        
	    	public void onEvent( Event event ) {
	            
	    		//Clients.showNotification( event.getName() + " " + (String) event.getData() );
	    		
	        }
	    	
	    } );		
		
	    btnTest1.addEventListener( Events.ON_CLICK, new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                //myLabel.setValue("Hooray! 'myButton' was clicked.");
            }
        });

	    
	}

	@Listen( "onCreate=#account" )
	public void onCreate() {

	}
	
	@Listen("onSelect=#TabBox")
	public void OnSelect_TabBox( Event event ) {
		
		Tab t = (Tab) event.getTarget();
		
		//Clients.showNotification( t.getLabel() );
		Clients.showNotification( t.getLabel(), "warning", null, "middle_left", 5000, true );
		//t.setLabel("Clicked");
		//Clients.log("Hola");
		//Clients.alert( t.getLabel(), t.getLabel(), "/imgs/doc.png" );
		
		//System.out.println( t.getLabel() );
		
	}
	
	@Listen( "onClick=#btnTest1" )
	public void onClick_btnTest1( Event event ) {
		
		//CEventCreateTabData CreateTabData = new CEventCreateTabData();
		
		LinkedHashMap<String,Object> ExtraInfo = new LinkedHashMap<>();
		ExtraInfo.put( "id", btnTest1.getLabel() );
		ExtraInfo.put( "label", btnTest1.getLabel() );
		ExtraInfo.put( "from", btnTest1 );
		
		GroupEvents.publish( new Event( "onCreateTab1", null, ExtraInfo ) );		
		
	}
	
	@Listen( "onClick=#btnTest2" )
	public void onClick_btnTest2( Event event ) {
		
		LinkedHashMap<String,Object> ExtraInfo = new LinkedHashMap<>();
		ExtraInfo.put( "id", btnTest2.getLabel() );
		ExtraInfo.put( "label", btnTest2.getLabel() );
		ExtraInfo.put( "from", btnTest2 );

		GroupEvents.publish( new Event( "onCreateTab2", null, ExtraInfo ) );		
		
	}

	@Listen( "onClick=#btnTest3" )
	public void onClick_btnTest3( Event event ) {
		
		Component[] comps = Executions.getCurrent().createComponents("/WEB-INF/zul/remote_frame.zul", null);
		
		LinkedHashMap<String,Object> ExtraInfo = new LinkedHashMap<>();
		ExtraInfo.put( "id", btnTest3.getLabel() );
		ExtraInfo.put( "label", btnTest3.getLabel() );
		ExtraInfo.put( "from", btnTest3 );
		ExtraInfo.put( "remote_frame", comps );
		
		GroupEvents.publish( new Event( "onCreateTab3", null, ExtraInfo ) );		
		
	}

	@Listen( "onClick=#btnTest4" )
	public void onClick_btnTest4( Event event ) {
		
		try {

			Component comps[] = null; //Executions.getCurrent().createComponents("/WEB-INF/zul/remote_frame.zul", null);
		
			//Component component =  Executions.getCurrent().createComponentsDirectly( "<listbox><listitem label=\"foo\"/></listbox>", "zul", null, null ); //Executions.getCurrent().createComponents( "remote_frame.zul", null );
			
			CClassPathLoader ClassPathLoader = new CClassPathLoader();

			
			//String strJarFolder = ""; 
			String strJarFolder = Executions.getCurrent().getDesktop().getWebApp().getRealPath( "/" );  //servletContext.getRealPath("/"); //getJarFolder();*/
			
			ClassPathLoader.LoadClassFiles( strJarFolder + "/WEB-INF/modules/", ".jar", 2 );

			ServiceLoader<CAbstractModule> sl = ServiceLoader.load( CAbstractModule.class );
			sl.reload();

			Iterator<CAbstractModule> it = sl.iterator();

			while ( it.hasNext() ) {

				try {

					CAbstractModule ModuleInstance = it.next();

					System.out.println( ModuleInstance );
					//System.out.println( ModuleInstance.getInteger() );
					
					comps = ModuleInstance.getComponents( Executions.getCurrent() );
					System.out.println( comps );
					
				} 
				catch ( Exception Ex ) {

				}

			}	

			LinkedHashMap<String,Object> ExtraInfo = new LinkedHashMap<>();
			ExtraInfo.put( "id", btnTest4.getLabel() );
			ExtraInfo.put( "label", btnTest4.getLabel() );
			ExtraInfo.put( "from", btnTest3 );
			ExtraInfo.put( "remote_frame", comps );

			GroupEvents.publish( new Event( "onCreateTab3", null, ExtraInfo ) );
		
		}
		catch ( Exception Ex ) {
			
			System.out.println( Ex.getMessage() );
			
		}
		
	}

	
	@Listen( "onClick=#btnTest5" )
	public void onClick_btnTest5( Event event ) {
		
		Component[] comps = Executions.getCurrent().createComponents("/WEB-INF/zul/remote_frame1.zul", null);
		
		LinkedHashMap<String,Object> ExtraInfo = new LinkedHashMap<>();
		ExtraInfo.put( "id", btnTest3.getLabel() );
		ExtraInfo.put( "label", btnTest3.getLabel() );
		ExtraInfo.put( "from", btnTest3 );
		ExtraInfo.put( "remote_frame", comps );
		
		GroupEvents.publish( new Event( "onCreateTab3", null, ExtraInfo ) );		
		
	}
	
}
