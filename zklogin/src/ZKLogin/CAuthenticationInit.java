package ZKLogin;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import Services.CAuthenticationService;
import Services.CUserCredential;
import Services.IAuthenticationService;

public class CAuthenticationInit implements Initiator {

	//services
	IAuthenticationService authService = new CAuthenticationService();
	
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		
		CUserCredential UserCredential = authService.getUserCredential();
		
		if ( UserCredential == null || UserCredential.isAnonymous() ) {
		
			Executions.sendRedirect("login.zul");
			return;
			
		}

	}
}