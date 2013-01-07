package tdm.cam.tlf;

public class RowDrillingAdderFrontSide extends DrillingAdderFrontSide {

	@Override
	public void addDrilling(CamPartSide side, Drilling drilling) {
		RowDrilling rd = convertToRowDrilling(drilling);
		rd.setPlaneEndX(rd.getEndX());
		rd.setPlaneX(drilling.getX());
		rd.setPlaneEndY(side.mirrorY(rd.getEndY()));
		rd.setPlaneY(side.mirrorY(drilling.getY()));
		side.addDrilling(drilling);
	}

	private RowDrilling convertToRowDrilling(Drilling drilling) {
		if (drilling instanceof RowDrilling) {
			return (RowDrilling) drilling;
		} else {
			throw new RuntimeException("row-drilling expecrted");
		}
	}

}
