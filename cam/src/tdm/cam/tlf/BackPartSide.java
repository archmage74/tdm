package tdm.cam.tlf;

import tdm.cam.tlf.transformer.BacksideBottomTransformer;
import tdm.cam.tlf.transformer.BacksideLeftTransformer;
import tdm.cam.tlf.transformer.BacksideRightTransformer;
import tdm.cam.tlf.transformer.BacksideTopTransformer;
import tdm.cam.tlf.transformer.BacksideUpTransformer;
import tdm.cam.tlf.transformer.IPlaneCoordinatesTransformer;

public class BackPartSide extends CamPartSide {

	IPlaneCoordinatesTransformer upTransformer = new BacksideUpTransformer();
	IPlaneCoordinatesTransformer topTransformer = new BacksideTopTransformer();
	IPlaneCoordinatesTransformer bottomTransformer = new BacksideBottomTransformer();
	IPlaneCoordinatesTransformer leftTransformer = new BacksideLeftTransformer();
	IPlaneCoordinatesTransformer rightTransformer = new BacksideRightTransformer();
	
	public BackPartSide(PartDimensions dimensions) {
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
