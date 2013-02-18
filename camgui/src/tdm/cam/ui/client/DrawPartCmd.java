package tdm.cam.ui.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tdm.cam.ui.client.prj.Part;
import tdm.cam.ui.client.prj.Project;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;

public class DrawPartCmd implements ChangeHandler, IDisplayProject {

	protected List<Part> parts = new ArrayList<Part>();

	private ListBox partListBox;

	protected Collection<IDisplayPart> partDisplays;

	public DrawPartCmd(ListBox partListBox, Collection<IDisplayPart> partDisplays) {
		this.setPartListBox(partListBox);
		this.partDisplays = partDisplays;
	}

	@Override
	public void onChange(ChangeEvent event) {
		drawPart();
	}

	@Override
	public void refresh() {
		drawPart();
	}

	@Override
	public void displayProject(Project project) {
		// FIXME updating the listbox should be moved to a partlistbox specialized class
		getPartListBox().clear();
		for (Part part : project.getParts()) {
			getPartListBox().addItem(part.getTransformedImosPart().getBarcode());
		}

		setParts(project.getParts());
	}

	public void setParts(Collection<Part> parts) {
		this.parts.clear();
		this.parts.addAll(parts);
	}

	protected void drawPart() {
		int partIndex = getPartListBox().getSelectedIndex();
		Part selectedPart = parts.get(partIndex);
		for (IDisplayPart display : partDisplays) {
			display.displayPart(selectedPart);
		}
	}

	public ListBox getPartListBox() {
		return partListBox;
	}

	public void setPartListBox(ListBox partListBox) {
		this.partListBox = partListBox;
	}

}
