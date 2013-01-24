package tdm.cam.ui.client.sketch.draw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

import tdm.cam.ui.client.sketch.project.IProjector;
import tdm.cam.ui.client.sketch.transform.ICoordinateTransformer;

public abstract class Rectangle implements IDrawElement {

	protected List<Point> points;

	protected CssColor color;
	
	public Rectangle(double x, double y, double l, double h) {
		init(x, y, l, h, CssColor.make(0, 0, 0));
	}

	public Rectangle(double x, double y, double l, double h, CssColor color) {
		init(x, y, l, h, color);
	}
	
	protected abstract void beforeDraw(Context2d context);

	protected abstract void afterDraw(Context2d context);

	private void init(double x, double y, double l, double h, CssColor color) {
		this.color = color;
		this.points = new ArrayList<Point>();
		this.points.add(new Point(x, y));
		this.points.add(new Point(x + l, y));
		this.points.add(new Point(x + l, y + h));
		this.points.add(new Point(x, y + h));
	}

	@Override
	public void draw(Context2d context) {
		beforeDraw(context);
		context.setStrokeStyle(color);
		context.beginPath();
		Point p0 = points.get(0);
		context.moveTo(p0.getX(), p0.getY());
		Point p = points.get(1);
		context.lineTo(p.getX(), p.getY());
		p = points.get(2);
		context.lineTo(p.getX(), p.getY());
		p = points.get(3);
		context.lineTo(p.getX(), p.getY());
		context.lineTo(p0.getX(), p0.getY());
		afterDraw(context);
	}

	@Override
	public Collection<Point> getExpansions() {
		return points;
	}

	@Override
	public void transform(ICoordinateTransformer transformer) {
		for (Point p : points) {
			p.transform(transformer);
		}
	}

	@Override
	public void project(IProjector projector) {
		for (Point p : points) {
			p.project(projector);
		}
	}

}