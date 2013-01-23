package tdm.cam.ui.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProject;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;

public class DrawPartCmd implements ChangeHandler, IDisplayProject {

	protected List<ImosPart> parts = new ArrayList<ImosPart>();

	protected ListBox partListBox;

	protected Collection<IDisplayPart> partDisplays;

	public DrawPartCmd(ListBox partListBox, Collection<IDisplayPart> partDisplays) {
		this.partListBox = partListBox;
		this.partDisplays = partDisplays;
	}

	@Override
	public void onChange(ChangeEvent event) {
		int partIndex = partListBox.getSelectedIndex();
		ImosPart selectedPart = parts.get(partIndex);
		for (IDisplayPart display : partDisplays) {
			display.displayPart(selectedPart);
		}
	}

	@Override
	public void displayProject(ImosProject project) {
		// FIXME updating the listbox should be moved to a partlistbox specialized class
		partListBox.clear();
		for (ImosPart part : project.getParts()) {
			partListBox.addItem(part.getBarcode());
		}

		setParts(project.getParts());
	}

	public void setParts(List<ImosPart> parts) {
		this.parts.clear();
		this.parts.addAll(parts);
	}

}
