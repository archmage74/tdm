package tdm.cam.ui.client.prj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProject;

public class Project {

	public static final double DEFAULT_ROTATION = Math.PI;
	
	protected ImosProject imosProject;
	
	protected List<Part> parts = new ArrayList<Part>();
	
	protected Map<String, Part> partsByBarcode = new HashMap<String, Part>();

	public Project() {
		
	}
	
	public void setImosProject(ImosProject imosProject) {
		this.imosProject = imosProject;
		parts.clear();
		partsByBarcode.clear();
		
		for (ImosPart imosPart : imosProject.getParts()) {
			if (imosPart.getDrillings().size() > 0) {
				Part part = new Part(imosPart);
				part.rotate(DEFAULT_ROTATION);
				parts.add(part);
				partsByBarcode.put(imosPart.getBarcode(), part);
			}
		}
		Comparator<Part> listOrder = new AlphabeticalPartListOrder();
		Collections.sort(parts, listOrder);
	}

	public Collection<Part> getParts() {
		return parts;
	}
	
	public Part getPartByBarcode(String barcode) {
		return partsByBarcode.get(barcode);
	}

	public Part getPartByIndex(int index) {
		return parts.get(index);
	}

	public ImosProject getImosProject() {
		return imosProject;
	}

}
