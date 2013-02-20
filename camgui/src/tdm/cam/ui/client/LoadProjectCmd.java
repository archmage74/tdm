package tdm.cam.ui.client;

import java.util.Collection;

import tdm.cam.model.imos.ImosProject;
import tdm.cam.ui.client.prj.Project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;

public class LoadProjectCmd implements AsyncCallback<ImosProject>, ClickHandler {

	protected final ImosServiceAsync imosService = GWT.create(ImosService.class);

	protected Project project;
	protected Collection<IDisplayProject> projectDisplays;
	protected TextBox orderIdTextBox;
	
	public LoadProjectCmd(Project project, TextBox orderIdTextBox, Collection<IDisplayProject> projectDisplays) {
		this.project = project;
		this.orderIdTextBox = orderIdTextBox;
		this.projectDisplays = projectDisplays;
	}

	@Override
	public void onFailure(Throwable caught) {
		new MessageDialog("Error: " + caught.getMessage()).show();
	}

	@Override
	public void onSuccess(ImosProject imosProject) {
		project.setImosProject(imosProject); 
		for (IDisplayProject display : projectDisplays) {
			display.displayProject(project);
		}
		if (!imosProject.getWarnings().isEmpty()) {
			showLoadWarnings(project);
		}
	}

	private void showLoadWarnings(Project p) {
		StringBuffer sb = new StringBuffer();
		for (String s : p.getImosProject().getWarnings()) {
			sb.append(s).append("\n");
		}
		new MessageDialog("Projekt erfolgreich geladen", sb.toString()).show();
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
