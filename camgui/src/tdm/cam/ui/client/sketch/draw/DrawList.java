package tdm.cam.ui.client.sketch.draw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;

import tdm.cam.ui.client.sketch.project.IProjector;
import tdm.cam.ui.client.sketch.project.LeftBottomAlignedCanvasProjector;
import tdm.cam.ui.client.sketch.transform.ICoordinateTransformer;

public class DrawList {
	
	protected boolean finished = false;
	
	protected List<IDrawElement> elements = new ArrayList<IDrawElement>();
	
	protected DrawExpanse drawExpanse = new DrawExpanse();
	
	protected List<ICoordinateTransformer> transformers = new ArrayList<ICoordinateTransformer>();
	
	public DrawList() {

	}
	
	public void addElement(IDrawElement element) {
		for (ICoordinateTransformer transformer : transformers) {
			element.transform(transformer);
		}
		drawExpanse.expand(element.getExpansions());
		elements.add(element);
	}

	public void addTransformer(ICoordinateTransformer transformer) {
		this.transformers.add(transformer);
	}
	
	public void addTransformers(Collection<ICoordinateTransformer> transformers) {
		this.transformers.addAll(transformers);
	}
	
	public void draw(Context2d context) {
		IProjector projector = new LeftBottomAlignedCanvasProjector(drawExpanse);
		for (IDrawElement element : elements) {
			element.project(projector);
			element.draw(context);
		}
	}
	
}
