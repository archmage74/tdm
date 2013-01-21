package tdm.cam.tlf;

import tdm.cam.model.math.Dimensions;
import tdm.cam.tlf.transformer.FrontsideBottomTransformer;
import tdm.cam.tlf.transformer.FrontsideLeftTransformer;
import tdm.cam.tlf.transformer.FrontsideRightTransformer;
import tdm.cam.tlf.transformer.FrontsideTopTransformer;
import tdm.cam.tlf.transformer.FrontsideUpTransformer;
import tdm.cam.tlf.transformer.IPlaneCoordinatesTransformer;

public class TlfFrontSide extends TlfPartSide {

	IPlaneCoordinatesTransformer upTransformer = new FrontsideUpTransformer();
	IPlaneCoordinatesTransformer topTransformer = new FrontsideTopTransformer();
	IPlaneCoordinatesTransformer bottomTransformer = new FrontsideBottomTransformer();
	IPlaneCoordinatesTransformer leftTransformer = new FrontsideLeftTransformer();
	IPlaneCoordinatesTransformer rightTransformer = new FrontsideRightTransformer();
	
	public TlfFrontSide(Dimensions dimensions) {
		super(dimensions);
	}
	
	@Override
	protected IPlaneCoordinatesTransformer getUpTransformer() {
		return upTransformer;
	}

	@Override
	public IPlaneCoordinatesTransformer getTopTransformer() {
		return topTransformer;
	}

	@Override
	public IPlaneCoordinatesTransformer getBottomTransformer() {
		return bottomTransformer;
	}

	@Override
	public IPlaneCoordinatesTransformer getLeftTransformer() {
		return leftTransformer;
	}

	@Override
	public IPlaneCoordinatesTransformer getRightTransformer() {
		return rightTransformer;
	}

}
