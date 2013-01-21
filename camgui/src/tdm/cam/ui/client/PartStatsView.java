package tdm.cam.ui.client;

import java.util.HashMap;
import java.util.Map;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.math.Dimensions;
import tdm.cam.model.math.Plane;
import tdm.cam.model.math.PlaneHelper;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PartStatsView implements IDisplayPart {

	private static final String TITLE_NAME = "Name:";
	private static final String TITLE_BARCODE = "Barcode:";
	private static final String TITLE_DRILL_FRONT = "Bohrungen front:";
	private static final String TITLE_DRILL_BACK = "Bohrungen back:";
	private static final String TITLE_DRILL_TOP = "Bohrungen oben:";
	private static final String TITLE_DRILL_BOTTOM = "Bohrungen unten:";
	private static final String TITLE_DRILL_LEFT = "Bohrungen links:";
	private static final String TITLE_DRILL_RIGHT = "Bohrungen rechts:";
	private static final String TITLE_DIMENSIONS = "L/B/H:";

	private static final String EMPTY = "<leer>";
	
	private Label barcodeValueLabel;
	private Label nameValueLabel;
	private Label dimensionsValueLabel;
	private Map<Plane, Label> drillValueLabels = new HashMap<Plane, Label>();

	private PlaneHelper planeHelper = PlaneHelper.getInstance();
	
	public PartStatsView(Panel parentPanel) {
		VerticalPanel panel = new VerticalPanel();

		panel.add(createInfoGrid());
		parentPanel.add(panel);
	}

	private Grid createInfoGrid() {
		Grid infoGrid = new Grid(9, 2);
		
		int row = 0;
		barcodeValueLabel = addInfo(infoGrid, row++, TITLE_BARCODE);
		nameValueLabel = addInfo(infoGrid, row++, TITLE_NAME);
		dimensionsValueLabel = addInfo(infoGrid, row++, TITLE_DIMENSIONS);
		drillValueLabels.put(Plane.FRONT, addInfo(infoGrid, row++, TITLE_DRILL_FRONT));
		drillValueLabels.put(Plane.BACK, addInfo(infoGrid, row++, TITLE_DRILL_BACK));
		drillValueLabels.put(Plane.TOP, addInfo(infoGrid, row++, TITLE_DRILL_TOP));
		drillValueLabels.put(Plane.BOTTOM, addInfo(infoGrid, row++, TITLE_DRILL_BOTTOM));
		drillValueLabels.put(Plane.LEFT, addInfo(infoGrid, row++, TITLE_DRILL_LEFT));
		drillValueLabels.put(Plane.RIGHT, addInfo(infoGrid, row++, TITLE_DRILL_RIGHT));

		return infoGrid;
	}
	
	@Override
	public void displayPart(ImosPart part) {
		String barcode = part.getBarcode() == null ? EMPTY : part.getBarcode(); 
		barcodeValueLabel.setText(barcode);

		String name = part.getName() == null ? EMPTY : part.getName();
		nameValueLabel.setText(name);
		
		Dimensions d = part.getDimensions();
		String dimensions = d.getLength() + " / " + d.getWidth() + " / " + d.getThick();
		dimensionsValueLabel.setText(dimensions);
		
		Map<Plane, Integer> drillNumbers = calculateDrillNumbers(part);
		for (Plane plane : drillValueLabels.keySet()) {
			drillValueLabels.get(plane).setText(drillNumbers.get(plane) + " Stk.");
		}
	}

	private Map<Plane, Integer> calculateDrillNumbers(ImosPart part) {
		Map<Plane, Integer> drillNumbers = new HashMap<Plane, Integer>();
		for (Plane p : Plane.values()) {
			drillNumbers.put(p, 0);
		}
		
		for (ImosDrilling drilling : part.getDrillings()) {
			Plane plane = planeHelper.getPlaneForDirection(drilling);
			drillNumbers.put(plane, drillNumbers.get(plane) + drilling.getNumDrillings());
		}

		return drillNumbers;
	}

	private Label addInfo(Grid infoGrid, int row, String title) {
		Label titleLabel = new Label(title);
		
		Label valueLabel = new Label();
		valueLabel.setStyleName(CssStyles.PART_INFO_VALUE);
		
		infoGrid.setWidget(row, 0, titleLabel);
		infoGrid.setWidget(row, 1, valueLabel);
		
		return valueLabel;
	}

}
