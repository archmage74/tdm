package tdm.cam.ui.client;

import tdm.cam.model.imos.ImosProject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

public class ExportTlfCmd implements ClickHandler, AsyncCallback<Void>, IDisplayProject {

	private static final String EXPORT_BUTTON_TEXT = "Export CNC (*.tlf): ";

	protected final ImosServiceAsync imosService = GWT.create(ImosService.class);

	private ImosProject project;
	
	private Button exportTlfButton;
	
	public ExportTlfCmd(Button exportTlfButton) {
		this.exportTlfButton = exportTlfButton;
		exportTlfButton.addClickHandler(this);
		updateButton();
	}
	
	public void displayProject(ImosProject part) {
		this.project = part;
		updateButton();
	}
	
	@Override
	public void onClick(ClickEvent event) {
		if (project == null || project.getOrderId() == null) {
			// FIXME show error
		} else {
			imosService.exportTlf(project.getOrderId(), this);
		}
	}

	@Override
	public void onFailure(Throwable caught) {
		// FIXME show error
	}
	
	@Override
	public void onSuccess(Void result) {
		// FIXME show export overview
	}
	
	private void updateButton() {
		String orderId; 
		if (project == null || project.getOrderId() == null) {
			orderId = "<leer>";
			exportTlfButton.setEnabled(false);
		} else {
			orderId = project.getOrderId();
			exportTlfButton.setEnabled(true);
		}
		exportTlfButton.setText(EXPORT_BUTTON_TEXT + orderId);
	}
	
}
