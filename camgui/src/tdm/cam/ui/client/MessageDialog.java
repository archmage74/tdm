package tdm.cam.ui.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class MessageDialog implements ClickHandler {

	protected String message;
	
	protected DialogBox dialogBox;
	
	protected HandlerRegistration closeButtonRegistration;
	
	public MessageDialog() {
		
	}
	
	public MessageDialog(String message) {
		this.message = message;
	}

	public void show() {
		dialogBox = new DialogBox();
		VerticalPanel panel = new VerticalPanel();
		panel.add(new Label(message));
		Button closeButton = new Button("OK");
		closeButtonRegistration = closeButton.addClickHandler(this);
		panel.add(closeButton);
		dialogBox.add(panel);
		dialogBox.show();
	}
	
	@Override
	public void onClick(ClickEvent event) {
		dialogBox.hide();
		closeButtonRegistration.removeHandler();
		dialogBox = null;
	}

}
