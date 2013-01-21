package tdm.cam.ui.client;

import tdm.cam.model.imos.ImosProject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;

public class LoadProjectCmd implements AsyncCallback<ImosProject>, ClickHandler {

	protected final ImosServiceAsync imosService = GWT.create(ImosService.class);

	protected CamUI camUI;
	protected TextBox orderIdTextBox;
	
	public LoadProjectCmd(CamUI camUI, TextBox orderIdTextBox) {
		this.camUI = camUI;
		this.orderIdTextBox = orderIdTextBox;
	}

	@Override
	public void onFailure(Throwable caught) {
		// FIXME show error
	}

	@Override
	public void onSuccess(ImosProject project) {
		camUI.displayProject(project);
	}

	@Override
	public void onClick(ClickEvent event) {
		String orderId = orderIdTextBox.getText();
		if (orderId == null) {
			// FIXME show error
		} else {
			imosService.readProject(orderId, this);
		}
	};
			
}
