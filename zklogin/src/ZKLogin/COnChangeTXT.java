package ZKLogin;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
//import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Textbox;


public class COnChangeTXT extends SelectorComposer<Component> {

	private static final long serialVersionUID = -3603507523436734732L;

	@Wire
	private Textbox copy;

	@Listen("onChanging = #t1")
    public void onChanging(InputEvent event) {

        copy.setValue(event.getValue());

    }

}