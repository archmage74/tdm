package tdm.cam.ui.client;

import tdm.cam.ui.client.prj.Part;
import tdm.cam.ui.client.prj.Project;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class RotatePartCmd implements KeyDownHandler {

	private Project project;

	private DrawPartCmd drawPartCmd;

	public RotatePartCmd(Project project, DrawPartCmd drawPartCmd) {
		this.project = project;
		this.drawPartCmd = drawPartCmd;
	}

	@Override
	public void onKeyDown(KeyDownEvent event) {
		int selectedPartIndex = drawPartCmd.getPartListBox().getSelectedIndex();
		Part selectedPart;
		if (selectedPartIndex == -1) {
			return;
		}

		selectedPart = project.getPartByIndex(selectedPartIndex);
		switch (event.getNativeKeyCode()) {
		case KeyCodes.KEY_LEFT:
			selectedPart.rotateLeft();
			break;
		case KeyCodes.KEY_RIGHT:
			selectedPart.rotateRight();
			break;
		}
		drawPartCmd.refresh();
	}

}
