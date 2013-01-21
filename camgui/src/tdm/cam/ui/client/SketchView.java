package tdm.cam.ui.client;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.math.PlaneHelper;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class SketchView implements IDisplayPart {

	private static final int MAX_X = 3600;
	private static final int MAX_Y = 1400;
	private static final int OFFSET_X = 80;
	private static final int OFFSET_Y = 5;
	public static final CssColor COL_PART = CssColor.make(0, 0, 0);
	public static final CssColor COL_DRILLING_SMALL = CssColor.make(255, 0, 0);
	public static final CssColor COL_DRILLING_BIG = CssColor.make(0, 0, 255);
	public static final CssColor COL_DRILLING_HORIZONTAL = CssColor.make(255, 0, 0);

	protected PlaneHelper planeHelper = PlaneHelper.getInstance();

	protected Canvas canvas;

	protected Context2d context;

	public SketchView(Canvas canvas) {
		this.canvas = canvas;

		canvas.setCoordinateSpaceWidth(MAX_X);
		canvas.setCoordinateSpaceHeight(MAX_Y);
		this.context = canvas.getContext2d();
	}

	@Override
	public void displayPart(ImosPart part) {
		context.clearRect(0, 0, MAX_X, MAX_Y);

		drawPartOutline(part);
		drawPartDrillings(part);
	}

	private void drawPartOutline(ImosPart part) {
		double sx = part.getDimensions().getLength();
		double sy = part.getDimensions().getWidth();
		CssColor color = CssColor.make(0, 0, 0);
		context.setLineWidth(4);
		context.setStrokeStyle(color);
		context.strokeRect(OFFSET_X, MAX_Y - OFFSET_Y - sy, sx, sy);
	}

	private void drawPartDrillings(ImosPart part) {
		for (ImosDrilling drilling : part.getDrillings()) {
			if (!planeHelper.isHorizontal(drilling)) {
				drawDrilling(drilling);
			}
		}
	}

	private void drawDrilling(ImosDrilling drilling) {
		CssColor color;
		double radius;
		if (drilling.getDiameter() >= 20.0) {
			color = COL_DRILLING_BIG;
			radius = drilling.getDiameter() / 2;
		} else {
			color = COL_DRILLING_SMALL;
			radius = drilling.getDiameter();
		}
		double stepX = (drilling.getEndX() - drilling.getX()) / drilling.getNumDrillings();
		double stepY = (drilling.getEndY() - drilling.getY()) / drilling.getNumDrillings();
		for (int drillIndex = 0; drillIndex < drilling.getNumDrillings(); drillIndex++) {
			double x = drilling.getX() + drillIndex * stepX;
			double y = drilling.getY() + drillIndex * stepY;
			context.setFillStyle(color);
			context.beginPath();
			context.arc(OFFSET_X + x, MAX_Y - OFFSET_Y - y, radius, 0, Math.PI * 2);
			context.closePath();
			context.fill();
		}
	}

}
