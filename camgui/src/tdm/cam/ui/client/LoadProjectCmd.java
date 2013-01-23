package tdm.cam.ui.client;

import java.util.Collection;

import tdm.cam.model.imos.ImosProject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;

public class LoadProjectCmd implements AsyncCallback<ImosProject>, ClickHandler {

	protected final ImosServiceAsync imosService = GWT.create(ImosService.class);

	protected Collection<IDisplayProject> projectDisplays;
	protected TextBox orderIdTextBox;
	
	public LoadProjectCmd(TextBox orderIdTextBox, Collection<IDisplayProject> projectDisplays) {
		this.orderIdTextBox = orderIdTextBox;
		this.projectDisplays = projectDisplays;
	}

	@Override
	public void onFailure(Throwable caught) {
		// FIXME show error
	}

	@Override
	public void onSuccess(ImosProject project) {
		for (IDisplayProject display : projectDisplays) {
			display.displayProject(project);
		}
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
