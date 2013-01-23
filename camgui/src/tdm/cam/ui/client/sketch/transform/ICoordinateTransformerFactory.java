package tdm.cam.ui.client.sketch.transform;

import java.util.Collection;

import tdm.cam.model.math.Dimensions;

public interface ICoordinateTransformerFactory {

	public Collection<ICoordinateTransformer> createTransformers(Dimensions dimensions);
	
}
