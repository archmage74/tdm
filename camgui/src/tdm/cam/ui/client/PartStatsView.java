package tdm.cam.ui.client;

import java.util.HashMap;
import java.util.Map;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.math.Dimensions;
import tdm.cam.model.math.Plane;
import tdm.cam.model.math.PlaneHelper;
import tdm.cam.ui.client.prj.Part;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PartStatsView implements IDisplayPart {

	private static final String TITLE_NAME = "Name:";
	private static final String TITLE_DRILL_FRONT = "Bohrungen front:";
	private static final String TITLE_DRILL_BACK = "Bohrungen back:";
	private static final String TITLE_DRILL_TOP = "Bohrungen oben:";
	private static final String TITLE_DRILL_BOTTOM = "Bohrungen unten:";
	private static final String TITLE_DRILL_LEFT = "Bohrungen links:";
	private static final String TITLE_DRILL_RIGHT = "Bohrungen rechts:";
	private static final String TITLE_LENGTH = "Länge:";
	private static final String TITLE_WIDTH = "Breite:";
	private static final String TITLE_HEIGHT = "Höhe:";

	private static final String EMPTY = "<leer>";
	private static final String TITLE_ROTATION = "Rot:";
	
	private Label barcodeValueLabel;
	private Label nameValueLabel;
	private Label lengthValueLabel;
	private Label widthValueLabel;
	private Label heightValueLabel;
	private Map<Plane, Label> drillValueLabels = new HashMap<Plane, Label>();

	private Label rotationValueLabel;

	private PlaneHelper planeHelper = PlaneHelper.getInstance();
	
	public PartStatsView(Panel parentPanel) {
		VerticalPanel panel = new VerticalPanel();

		panel.add(createBarcodeLevel());
		
		panel.add(createInfoGrid());
		parentPanel.add(panel);
	}

	private Label createBarcodeLevel() {
		barcodeValueLabel = new Label(EMPTY);
		barcodeValueLabel.setStyleName(CssStyles.PART_INFO_VALUE);
		return barcodeValueLabel;
	}

	private Grid createInfoGrid() {
		Grid infoGrid = new Grid(12, 2);
		
		int row = 0;
		nameValueLabel = addInfo(infoGrid, row++, TITLE_NAME);
		lengthValueLabel = addInfo(infoGrid, row++, TITLE_LENGTH);
		widthValueLabel = addInfo(infoGrid, row++, TITLE_WIDTH);
		heightValueLabel = addInfo(infoGrid, row++, TITLE_HEIGHT);
		drillValueLabels.put(Plane.FRONT, addInfo(infoGrid, row++, TITLE_DRILL_FRONT));
		drillValueLabels.put(Plane.BACK, addInfo(infoGrid, row++, TITLE_DRILL_BACK));
		drillValueLabels.put(Plane.TOP, addInfo(infoGrid, row++, TITLE_DRILL_TOP));
		drillValueLabels.put(Plane.BOTTOM, addInfo(infoGrid, row++, TITLE_DRILL_BOTTOM));
		drillValueLabels.put(Plane.LEFT, addInfo(infoGrid, row++, TITLE_DRILL_LEFT));
		drillValueLabels.put(Plane.RIGHT, addInfo(infoGrid, row++, TITLE_DRILL_RIGHT));
		rotationValueLabel = addInfo(infoGrid, row++, TITLE_ROTATION);

		return infoGrid;
	}
	
	@Override
	public void displayPart(Part part) {
		ImosPart imosPart = part.getTransformedImosPart();
		String barcode = imosPart.getBarcode() == null ? EMPTY : imosPart.getBarcode(); 
		barcodeValueLabel.setText(barcode);

		String name = imosPart.getName() == null ? EMPTY : imosPart.getName();
		nameValueLabel.setText(name);
		
		Dimensions d = imosPart.getDimensions();
		lengthValueLabel.setText("" + d.getLength());
		widthValueLabel.setText("" + d.getWidth());
		heightValueLabel.setText("" + d.getThick());
		
		Map<Plane, Integer> drillNumbers = calculateDrillNumbers(imosPart);
		for (Plane plane : drillValueLabels.keySet()) {
			drillValueLabels.get(plane).setText(drillNumbers.get(plane) + " Stk.");
		}
		
		rotationValueLabel.setText("" + Math.round(part.getRotationInDegrees()));
	}

	private Map<Plane, Integer> calculateDrillNumbers(ImosPart part) {
		Map<Plane, Integer> drillNumbers = new HashMap<Plane, Integer>();
		for (Plane p : Plane.values()) {
			drillNumbers.put(p, 0);
		}
		
		for (ImosDrilling drilling : part.getDrillings()) {
			Plane plane = planeHelper.getPlaneForDirection(drilling.getDirection());
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
