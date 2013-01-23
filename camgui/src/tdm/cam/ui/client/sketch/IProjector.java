package tdm.cam.ui.client.sketch;

import tdm.cam.model.math.Dimensions;

public interface IProjector {

	public int px(double x, Dimensions d);
	
	public int py(double y, Dimensions d);

	public int plx(double x);

	public int ply(double y);
	
}
