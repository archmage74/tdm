package tdm.cam.ui.client.sketch;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.math.PlaneHelper;
import tdm.cam.ui.client.IDisplayPart;
import tdm.cam.ui.client.sketch.draw.Circle;
import tdm.cam.ui.client.sketch.draw.DrawList;
import tdm.cam.ui.client.sketch.draw.Rectangle;
import tdm.cam.ui.client.sketch.transform.ICoordinateTransformerFactory;

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
	
	protected ICoordinateTransformerFactory transformerFactory;
	
	public SketchView(Canvas canvas) {
		this.canvas = canvas;

		canvas.setCoordinateSpaceWidth(MAX_X);
		canvas.setCoordinateSpaceHeight(MAX_Y);
		this.context = canvas.getContext2d();
	}

	int i = 0;
	@Override
	public void displayPart(ImosPart part) {
		context.clearRect(0, 0, MAX_X, MAX_Y);
		DrawList drawList = new DrawList();
		drawList.addTransformers(transformerFactory.createTransformers(part.getDimensions()));
		drawPartOutline(drawList, part);
		drawPartDrillings(drawList, part);
		drawList.draw(context);
	}
	
	public void addDrillingFilter(IDrillingFilter filter) {
		drillingFilters.add(filter);
	}

	private void drawPartOutline(DrawList drawList, ImosPart part) {
		double l = part.getDimensions().getLength();
		double h = part.getDimensions().getWidth();
		drawList.addElement(new Rectangle(0, 0, l, h));
	}

	private void drawPartDrillings(DrawList drawList, ImosPart part) {
		for (ImosDrilling drilling : part.getDrillings()) {
			if (isDisplayed(drilling)) {
				drawDrilling(drawList, drilling, part);
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
	
	private void drawDrilling(DrawList drawList, ImosDrilling drilling, ImosPart part) {
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
			drawList.addElement(new Circle(x, y, radius, color));
		}
	}

	public ICoordinateTransformerFactory getTransformerFactory() {
		return transformerFactory;
	}

	public void setTransformerFactory(ICoordinateTransformerFactory transformerFactory) {
		this.transformerFactory = transformerFactory;
	}

}
