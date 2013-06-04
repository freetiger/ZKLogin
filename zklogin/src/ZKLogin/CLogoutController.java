package ZKLogin;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

import Services.CAuthenticationService;
import Services.IAuthenticationService;

public class CLogoutController extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 3627564474561088527L;

	//services
	IAuthenticationService authService = new CAuthenticationService();
	
	@Listen("onClick=#logout")
	public void doLogout(){

		authService.logout();		
		
		Executions.sendRedirect("/index.zul");
		
	}
}
