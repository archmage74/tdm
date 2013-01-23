package tdm.cam.ui.client.sketch.draw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class Circle extends Point {

	protected double r;
	protected CssColor color;
	
	public Circle(double x, double y, double r, CssColor color) {
		super(x, y);
		this.r = r;
		this.color = color;
	}

	@Override
	public Collection<Point> getExpansions() {
		List<Point> points = new ArrayList<Point>();
		points.add(new Point(x - r, y));
		points.add(new Point(x + r, y));
		points.add(new Point(x, y - r));
		points.add(new Point(x, y + r));
		return points;
	}

	@Override
	public void draw(Context2d context) {
		context.setFillStyle(color);
		context.beginPath();
		context.arc(x, y, r, 0, Math.PI * 2);
		context.closePath();
		context.fill();
	}
	
	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

}
