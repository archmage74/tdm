package tdm.cam.ui.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tdm.cam.model.imos.ImosProject;

public class DrawProjectCmd {

	protected List<ImosProject> parts = new ArrayList<ImosProject>();
	
	protected Collection<IDisplayProject> projectDisplays;
	
	public DrawProjectCmd(Collection<IDisplayProject> projectDisplays) {
		this.projectDisplays = projectDisplays;
	}

	public void drawProject(ImosProject project) {
		for (IDisplayProject display: projectDisplays) {
			display.displayProject(project);
		}
	}

}
