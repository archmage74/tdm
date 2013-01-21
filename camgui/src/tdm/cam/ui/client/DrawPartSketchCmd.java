package tdm.cam.ui.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tdm.cam.model.imos.ImosPart;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;

public class DrawPartSketchCmd implements ChangeHandler {

	protected List<ImosPart> parts = new ArrayList<ImosPart>();
	
	protected ListBox partListBox;

	protected Collection<IDisplayPart> partDisplays;
	
	public DrawPartSketchCmd(ListBox partListBox, Collection<IDisplayPart> partDisplays) {
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
	
	public void setParts(List<ImosPart> parts) {
		this.parts.clear();
		this.parts.addAll(parts);
	}

}
