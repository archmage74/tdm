package tdm.cam.ui.client.sketch.draw;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class FillRectangle extends Rectangle {

	public FillRectangle(double x, double y, double l, double h) {
		super(x, y, l, h);
	}

	public FillRectangle(double x, double y, double l, double h, CssColor color) {
		super(x, y, l, h, color);
	}

	@Override
	protected void beforeDraw(Context2d context) {
		context.setFillStyle(color);
	}

	@Override
	protected void afterDraw(Context2d context) {
		context.fill();
	}

}
