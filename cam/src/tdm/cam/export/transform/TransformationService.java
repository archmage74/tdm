package tdm.cam.export.transform;

import java.util.Collection;
import java.util.Map;

import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProject;
import tdm.cam.model.imos.PartRotator;
import tdm.cam.model.math.Matrix3x3;
import tdm.cam.model.math.RotationMatrixFactory;

public class TransformationService {
	
	protected PartRotator partRotator = new PartRotator();
	
	protected RotationMatrixFactory rotationMatrixFactory = new RotationMatrixFactory();

	public void rotate(ImosProject project, Map<String, Integer> rotationMap) {
		rotate(project.getParts(), rotationMap);
	}
	
	public void rotate(Collection<ImosPart> parts, Map<String, Integer> rotationMap) {
		for (ImosPart part : parts) {
			Integer angle = rotationMap.get(part.getBarcode());
			if (angle != null) {
				Matrix3x3 rot = rotationMatrixFactory.createZRotationInDegrees(angle);
				partRotator.rotatePart(part, rot);
			}
		}
	}
	
}
