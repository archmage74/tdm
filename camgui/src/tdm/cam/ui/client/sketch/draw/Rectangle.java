package tdm.cam.ui.client.sketch.draw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tdm.cam.ui.client.sketch.project.IProjector;
import tdm.cam.ui.client.sketch.transform.ICoordinateTransformer;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class Rectangle implements IDrawElement {

	protected List<Point> points;
	
	public Rectangle(double x, double y, double l, double h) {
		points = new ArrayList<Point>();
		points.add(new Point(x, y));
		points.add(new Point(x + l, y));
		points.add(new Point(x + l, y + h));
		points.add(new Point(x, y + h));
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

	@Override
	public void draw(Context2d context) {
		CssColor color = CssColor.make(0, 0, 0);
		context.setLineWidth(4);
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
		context.stroke();
	}

}
