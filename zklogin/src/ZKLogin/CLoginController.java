package ZKLogin;

import java.util.Locale;

import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
//import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import Services.CAuthenticationService;
import Services.CUserCredential;
import Services.IAuthenticationService;

public class CLoginController extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 5371813838204690220L;

	//wire components
	@Wire
	Combobox edAccount;
	@Wire
	Textbox edPassword;
	@Wire
	Label lbMessage;
	
	//services
	IAuthenticationService authService = new CAuthenticationService();
	
	public void setLanguage( String strLang ) throws Exception {

		Session CurrentSession = Sessions.getCurrent();
		
		//System.out.println("Org Language: " + CurrentSession.getAttribute( "preflang" ) );

		// set session wide language to new value
		if ( !strLang.isEmpty() ) {

			CurrentSession.setAttribute("preflang", strLang );
			
		}

		// read the session language attribute
		String sessLang = (String) CurrentSession.getAttribute( "preflang" );
		System.out.println("New Language: " + CurrentSession.getAttribute( "preflang" ) );

		// set the new preferred locale
		// otherwise it will use the default language (no session attribute and/or language parameter
		if ( sessLang != null ) {
		
			Locale preferredLocale = org.zkoss.util.Locales.getLocale( sessLang );
			
			CurrentSession.setAttribute( Attributes.PREFERRED_LOCALE, preferredLocale );
			
			//org.zkoss.util.Locales.setThreadLocal(org.zkoss.util.Locales.getLocale(sessLang));
		}

		// Iterate through variables of the current class
		/*for ( Field f : this.getClass().getDeclaredFields() ) {
			
			// System.out.println(this.getClass().getName() + "." + f.getName() + " of type " + f.getType().getName());
			String compName = this.getClass().getName() + "." + f.getName();
			String compLabel = Labels.getLabel(compName);
			String compType = f.getType().getName();

			// only set lable if value found, otherwise it renders empty
			if (!(compLabel == null)) {
				if (compType.equals("org.zkoss.zul.Button")) ((Button) f.get(this)).setLabel(compLabel);
				else if (compType.equals("org.zkoss.zul.Label")) ((Label) f.get(this)).setValue(compLabel);
				// Other component types need to be implemented if required
			}
		}**/

	}	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		System.out.println( Attributes.PREFERRED_LOCALE );
		
		/*List<String> userList = new ArrayList<String>();
		userList.add("Juan");
		userList.add("Pedro");
		userList.add("Tomás");
		userList.add("Andres");
		userList.add("José");
		
		@SuppressWarnings("unchecked")
		ListModelList userModel = new ListModelList( userList );
		/*ListModelList<String> userModel = new ListModelList<String>( Collections.unmodifiableList( userList ) );
		ListModelList<String> userModel = new ListModelList<String>();
		userModel.add("Juan");
		userModel.add("Pedro");
		userModel.add("Tomás");
		userModel.add("Andres");
		userModel.add("José");*/

		
		/*account.setModel( userModel );
		/*account.appendItem("Juan");
		account.appendItem("José");
		account.appendItem("Rafael");
		account.add
		Comboitem ITEM =  account.getSelectedItem();
		ITEM.setI*/
		//ListModel x = new ListModel();
		
		//account.
		
	}	 
	
	@Listen( "onCreate=#edAccount" )
	public void onCreate() {
		
		/*ArrayList<String> userList = new ArrayList<String>();
		userList.add("Juan");
		userList.add("Pedro");
		userList.add("Tomás");
		userList.add("Andres");
		userList.add("José");*/
		
		/*ListModelList<String> userModel = new ListModelList<String>();
		userModel.add("Juan");
		userModel.add("Pedro");
		userModel.add("Tomás");
		userModel.add("Andres");
		userModel.add("José");*/

		//ListModelList<Comboitem> userModel = new ListModelList<Comboitem>();

		Comboitem item = new Comboitem( "Juan", "/imgs/cross.png" );
		
		item.setAttribute( "value" ,"ZZZZZZ" );
	    item.setDescription( "Una prueba juan " );
		
	    edAccount.appendChild( item );
	    
		item = new Comboitem( "José", "/imgs/plus.png" );
		
		item.setAttribute( "value" ,"1001" );
	    item.setDescription( "Una prueba josé" );
		
	    edAccount.appendChild( item );

	    //userModel.add( item );
		
		//account.setModel( userModel );

	}

	@Listen("onSelect=#edAccount")
	public void onSelect( Event event ){
		
		Comboitem Item = edAccount.getSelectedItem();
		
		//String str = account.getValue();
		
		if ( Item != null ) {
			
			String strAttributeValue = (String) Item.getAttribute( "value" );
			
			lbMessage.setValue( strAttributeValue );
			
		}
		
	}
	
	@Listen("onClick=#btnLogin; onOK=#frmLogin" )
	public void onClickbtnLogin( Event event ){
		
		String nm = edAccount.getValue();
		String pd = edPassword.getValue();
		
		if ( authService.login( nm, pd ) == false ) {
			
			lbMessage.setValue( "account or password are not correct." );
			return;
			
		}
		
		CUserCredential UserCredential= authService.getUserCredential();
		lbMessage.setValue( "Welcome, " + UserCredential.getDescription() );
		lbMessage.setSclass("");
		
		Executions.sendRedirect("/");
		
	}

	@Listen("onClick=#btnExit")
	public void onClickbtnExit( Event event ){
		
		if ( Messagebox.show("Are you sure to exit?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK ) {
		
			Executions.sendRedirect("http://www.google.co.ve");
			
		}
		
	}

	@Listen("onClick=#btnHelp")
	public void onClickbtnHelp( Event event ){
		
       Window window = (Window) Executions.createComponents( "/WEB-INF/zul/help_manager.zul", null, null );

       window.doModal();
		/*if ( Messagebox.show("Are you sure to exit?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK ) {
		
			Executions.sendRedirect("http://www.google.co.ve");
			
		}*/
		
	}
	
}
