package tdm.cam.ui.client.sketch.transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tdm.cam.model.math.Dimensions;

public class BackCoordinateTransformerFactory implements ICoordinateTransformerFactory {

	@Override
	public Collection<ICoordinateTransformer> createTransformers(Dimensions dimensions) {
		List<ICoordinateTransformer> transformers = new ArrayList<ICoordinateTransformer>();
		transformers.add(new XBackTransformer(dimensions));
		return transformers;
	}
	
}
