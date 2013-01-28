package tdm.cam.ui.client;

import java.util.Collection;

import tdm.cam.ui.client.prj.Project;

public class DrawProjectCmd {

	protected Collection<IDisplayProject> projectDisplays;
	
	public DrawProjectCmd(Collection<IDisplayProject> projectDisplays) {
		this.projectDisplays = projectDisplays;
	}

	public void drawProject(Project project) {
		for (IDisplayProject display: projectDisplays) {
			display.displayProject(project);
		}
	}

}
