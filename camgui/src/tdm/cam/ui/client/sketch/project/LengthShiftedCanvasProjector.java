package tdm.cam.ui.client.sketch.project;

import tdm.cam.model.math.Dimensions;
import tdm.cam.ui.client.sketch.SketchView;

public class LengthShiftedCanvasProjector implements IProjector {

	protected Dimensions dimensions;
	
	public LengthShiftedCanvasProjector(Dimensions dimensions) {
		this.dimensions = dimensions;
	}
	
	@Override
	public double px(double x) {
		return SketchView.OFFSET_X + dimensions.getLength() + plx(x);
	}

	@Override
	public double py(double y) {
		return SketchView.MAX_Y - SketchView.OFFSET_Y + ply(y);
	}

	@Override
	public double plx(double x) {
		return (int) x;
	}

	@Override
	public double ply(double y) {
		return - (int) y;
	}

}
