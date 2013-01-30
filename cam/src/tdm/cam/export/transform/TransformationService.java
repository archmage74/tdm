package tdm.cam.export.transform;

import java.util.Collection;
import java.util.Map;

import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProject;

public class TransformationService {
	
	PartRotator partRotator = new PartRotator();
	
	public void rotate(ImosProject project, Map<String, Integer> rotationMap) {
		rotate(project.getParts(), rotationMap);
	}
	
	public void rotate(Collection<ImosPart> parts, Map<String, Integer> rotationMap) {
		for (ImosPart part : parts) {
			Integer angle = rotationMap.get(part.getBarcode());
			if (angle != null) {
				partRotator.rotatePart(part, angle);
			}
		}
	}
	
}
