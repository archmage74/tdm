package tdm.cam.ui.client.sketch.draw;

import java.util.Collection;

public class DrawExpanse {

	double minx;
	double maxx;
	double miny;
	double maxy;

	public DrawExpanse() {
		minx = Double.MAX_VALUE;
		maxx = Double.MIN_VALUE;
		miny = Double.MAX_VALUE;
		maxy = Double.MIN_VALUE;
	}

	public void expand(Point... points) {
		for (Point p : points) {
			expand(p.getX(), p.getY());
		}
	}
	
	public void expand(Collection<Point> points) {
		for (Point p : points) {
			expand(p.getX(), p.getY());
		}
	}
	
	public void expand(double x, double y) {
		if (minx > x) {
			minx = x;
		}
		if (maxx < x) {
			maxx = x;
		}
		if (miny > y) {
			miny = y;
		}
		if (maxy < y) {
			maxy = y;
		}
	}

	public double getMinx() {
		return minx;
	}

	public double getMaxx() {
		return maxx;
	}

	public double getMiny() {
		return miny;
	}

	public double getMaxy() {
		return maxy;
	}

}
