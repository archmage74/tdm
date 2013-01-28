package tdm.cam.ui.client.prj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProject;

public class Project {
	
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
			Part part = new Part(imosPart);
			parts.add(part);
			partsByBarcode.put(imosPart.getBarcode(), part);
		}
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
