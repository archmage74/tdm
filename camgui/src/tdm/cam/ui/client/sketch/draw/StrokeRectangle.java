package tdm.cam.ui.client.sketch.draw;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class StrokeRectangle extends Rectangle {

	public StrokeRectangle(double x, double y, double l, double h) {
		super(x, y, l, h);
	}

	public StrokeRectangle(double x, double y, double l, double h, CssColor color) {
		super(x, y, l, h, color);
	}

	@Override
	protected void beforeDraw(Context2d context) {
		context.setLineWidth(4);
	}

	@Override
	protected void afterDraw(Context2d context) {
		context.stroke();
	}

}
