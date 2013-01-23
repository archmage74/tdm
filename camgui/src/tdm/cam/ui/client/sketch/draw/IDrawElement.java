package tdm.cam.ui.client.sketch.draw;

import java.util.Collection;

import com.google.gwt.canvas.dom.client.Context2d;

import tdm.cam.ui.client.sketch.project.IProjector;
import tdm.cam.ui.client.sketch.transform.ICoordinateTransformer;

public interface IDrawElement {

	public Collection<Point> getExpansions();
	
	public void transform(ICoordinateTransformer transformer);
	
	public void draw(Context2d context);

	public void project(IProjector projector);
	
}
