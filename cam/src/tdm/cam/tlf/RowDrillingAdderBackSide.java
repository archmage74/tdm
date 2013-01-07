package tdm.cam.tlf;

public class RowDrillingAdderBackSide extends DrillingAdderBackSide {

	@Override
	public void addDrilling(CamPartSide side, Drilling drilling) {
		RowDrilling rd = convertToRowDrilling(drilling);
		rd.setPlaneX(side.mirrorX(drilling.getX()));
		rd.setPlaneEndX(side.mirrorX(rd.getEndX()));
		rd.setPlaneY(side.mirrorY(drilling.getY()));
		rd.setPlaneEndY(side.mirrorY(rd.getEndY()));
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
