package tdm.cam.ui.client.sketch;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.math.PlaneHelper;
import tdm.cam.ui.client.IDisplayPart;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class SketchView implements IDisplayPart {

	public static final int MAX_X = 3600;
	public static final int MAX_Y = 1400;
	public static final int OFFSET_X = 80;
	public static final int OFFSET_Y = 5;

	public static final CssColor COL_PART = CssColor.make(0, 0, 0);
	public static final CssColor COL_DRILLING_SMALL = CssColor.make(255, 0, 0);
	public static final CssColor COL_DRILLING_BIG = CssColor.make(0, 0, 255);
	public static final CssColor COL_DRILLING_HORIZONTAL = CssColor.make(255, 0, 0);

	protected PlaneHelper planeHelper = PlaneHelper.getInstance();

	protected Canvas canvas;

	protected Context2d context;

	protected List<IDrillingFilter> drillingFilters = new ArrayList<IDrillingFilter>();
	
	protected List<ICoordinateTransformer> transformers = new ArrayList<ICoordinateTransformer>();
	
	protected IProjector projector;
	
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
	
	public void addDrillingFilter(IDrillingFilter filter) {
		drillingFilters.add(filter);
	}

	public void addCoordinateTransformers(ICoordinateTransformer transformer) {
		transformers.add(transformer);
	}

	public void setProjector(IProjector projector) {
		this.projector = projector;
	}

	private void drawPartOutline(ImosPart part) {
		double sx = part.getDimensions().getLength();
		double sy = part.getDimensions().getWidth();
		CssColor color = CssColor.make(0, 0, 0);
		context.setLineWidth(4);
		context.setStrokeStyle(color);
		int x = projector.px(tx(0, part), part.getDimensions());
		int y = projector.py(ty(0, part), part.getDimensions());
		int lx = projector.plx(tx(sx, part));
		int ly = projector.ply(ty(sy, part));
		context.strokeRect(x, y, lx, ly);
	}

	private void drawPartDrillings(ImosPart part) {
		for (ImosDrilling drilling : part.getDrillings()) {
			if (isDisplayed(drilling)) {
				drawDrilling(drilling, part);
			}
		}
	}
	
	private boolean isDisplayed(ImosDrilling drilling) {
		for (IDrillingFilter filter : drillingFilters) {
			if (!filter.isDisplayed(drilling)) {
				return false;
			}
		}
		return true;
	}
	
	private int tx(double x, ImosPart part) {
		for (ICoordinateTransformer transformer : transformers) {
			x = transformer.tx(x, part.getDimensions());
		}
		return (int) x;
	}

	private int ty(double y, ImosPart part) {
		for (ICoordinateTransformer transformer : transformers) {
			y = transformer.ty(y, part.getDimensions());
		}
		return (int) y;
	}

	private void drawDrilling(ImosDrilling drilling, ImosPart part) {
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
			int cx = projector.px(tx(x, part), part.getDimensions());
			int cy = projector.py(ty(y, part), part.getDimensions());
			context.arc(cx, cy, radius, 0, Math.PI * 2);
			context.closePath();
			context.fill();
		}
	}

}
