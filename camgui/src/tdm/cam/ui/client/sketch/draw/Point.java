package tdm.cam.ui.client.sketch.draw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;

import tdm.cam.ui.client.sketch.project.IProjector;
import tdm.cam.ui.client.sketch.transform.ICoordinateTransformer;

public class Point implements IDrawElement {

	protected double x;
	protected double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override 
	public void transform(ICoordinateTransformer transformer) {
		double nx = transformer.tx(x);
		double ny = transformer.ty(y);
		this.x = nx;
		this.y = ny;
	}

	@Override 
	public void project(IProjector projector) {
		double nx = projector.px(x);
		double ny = projector.py(y);
		this.x = nx;
		this.y = ny;
	}

	@Override
	public Collection<Point> getExpansions() {
		List<Point> points = new ArrayList<Point>();
		points.add(this);
		return points;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void draw(Context2d context) {
		throw new RuntimeException("not supported");
	}
	
}
