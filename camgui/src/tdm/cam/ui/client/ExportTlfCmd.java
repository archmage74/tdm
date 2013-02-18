package tdm.cam.ui.client;

import tdm.cam.model.cmd.Rotation;
import tdm.cam.model.cmd.RotationList;
import tdm.cam.ui.client.prj.Part;
import tdm.cam.ui.client.prj.Project;
import tdm.cam.ui.shared.ExportResult;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

public class ExportTlfCmd implements ClickHandler, AsyncCallback<ExportResult>, IDisplayProject {

	private static final String EXPORT_BUTTON_TEXT = "Export CNC (*.tlf): ";

	protected final ImosServiceAsync imosService = GWT.create(ImosService.class);

	private Project project;
	
	private Button exportTlfButton;
	
	public ExportTlfCmd(Button exportTlfButton) {
		this.exportTlfButton = exportTlfButton;
		exportTlfButton.addClickHandler(this);
		updateButton();
	}
	
	public void displayProject(Project project) {
		this.project = project;
		updateButton();
	}
	
	@Override
	public void refresh() {
		updateButton();
	}
	
	@Override
	public void onClick(ClickEvent event) {
		if (project == null || project.getImosProject() == null || project.getImosProject().getOrderId() == null) {
			// FIXME show error
		} else {
			RotationList rotationList = new RotationList();
			for (Part part : project.getParts()) {
				if (part.getRotationInDegrees() != 0) {
					Rotation rotation = new Rotation();
					rotation.setAngle((int) part.getRotationInDegrees());
					rotation.setBarcode(part.getTransformedImosPart().getBarcode());
					rotationList.addRotation(rotation);
				}
			}
			imosService.exportTlf(project.getImosProject().getOrderId(), rotationList, this);
		}
	}

	@Override
	public void onFailure(Throwable caught) {
		new MessageDialog("Error: " + caught.getMessage()).show();
	}
	
	@Override
	public void onSuccess(ExportResult exportResult) {
		switch (exportResult.getResult()) {
		case OK:
			new MessageDialog("Export abgeschlossen").show();
			break;
		case WARNING:
			new MessageDialog("Export mit Warnung(en) abgeschlossen:", exportResult.getDetails()).show();
			break;
		case ERROR:
			new MessageDialog("Export fehlerhaft:", exportResult.getDetails()).show();
			break;
		default:
			new MessageDialog("Unknown export result: " + exportResult.getResult()).show();
		}
	}
	
	private void updateButton() {
		String orderId; 
		if (project == null || project.getImosProject() == null || project.getImosProject().getOrderId() == null) {
			orderId = "<leer>";
			exportTlfButton.setEnabled(false);
		} else {
			orderId = project.getImosProject().getOrderId();
			exportTlfButton.setEnabled(true);
		}
		exportTlfButton.setText(EXPORT_BUTTON_TEXT + orderId);
	}
	
}
