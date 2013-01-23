package tdm.cam.ui.client.sketch.project;

import tdm.cam.ui.client.sketch.SketchView;
import tdm.cam.ui.client.sketch.draw.DrawExpanse;

public class LeftBottomAlignedCanvasProjector implements IProjector {

	protected DrawExpanse expanse;
	
	public LeftBottomAlignedCanvasProjector(DrawExpanse expanse) {
		this.expanse = expanse;
	}
	
	@Override
	public double px(double x) {
		return SketchView.OFFSET_X + plx(x) - expanse.getMinx();
	}

	@Override
	public double py(double y) {
		return SketchView.MAX_Y - SketchView.OFFSET_Y + ply(y) + expanse.getMiny();
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
