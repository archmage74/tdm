package tdm.cam.ui.client.sketch;

import tdm.cam.model.math.Dimensions;

public class LengthShiftedCanvasProjector implements IProjector {

	@Override
	public int px(double x, Dimensions d) {
		return SketchView.OFFSET_X + (int) d.getLength() + plx(x);
	}

	@Override
	public int py(double y, Dimensions d) {
		return SketchView.MAX_Y - SketchView.OFFSET_Y + ply(y);
	}

	@Override
	public int plx(double x) {
		return (int) x;
	}

	@Override
	public int ply(double y) {
		return - (int) y;
	}

}
