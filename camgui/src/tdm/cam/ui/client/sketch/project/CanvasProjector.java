package tdm.cam.ui.client.sketch.project;

import tdm.cam.ui.client.sketch.SketchView;

public class CanvasProjector implements IProjector {

	@Override
	public double px(double x) {
		return SketchView.OFFSET_X + plx(x);
	}

	@Override
	public double py(double y) {
		return SketchView.MAX_Y - SketchView.OFFSET_Y + ply(y);
	}

	@Override
	public double plx(double x) {
		return x;
	}

	@Override
	public double ply(double y) {
		return - y;
	}

}
