package AbstractModule;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;

public interface CAbstractModule {

	public Component getComponent( Execution exec );
	
	public Component[] getComponents( Execution exec );

	
}
