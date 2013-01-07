package tdm.cam.tlf;

public class RowDrillingAdderFrontSide extends DrillingAdderFrontSide {

	@Override
	public void addDrilling(CamPartSide side, Drilling drilling) {
		RowDrilling rd = convertToRowDrilling(drilling);
		rd.setPlaneEndX(rd.getEndX());
		rd.setPlaneX(drilling.getX());
		rd.setPlaneEndY(side.mirrorY(rd.getPlaneEndY()));
		rd.setPlaneY(side.mirrorY(drilling.getY()));
		side.addDrilling(drilling);
	}

	@Override
	public void addPlane1Drilling(CamPartSide side, Drilling drilling) {
		drilling.setPlaneX(drilling.getX());
		drilling.setPlaneY(drilling.getZ());
		side.addPlane1Drilling(drilling);
	}

	@Override
	public void addPlane2Drilling(CamPartSide side, Drilling drilling) {
		drilling.setPlaneX(drilling.getX());
		drilling.setPlaneY(side.mirrorZ(drilling.getZ()));
		side.addPlane2Drilling(drilling);
	}

	@Override
	public void addPlane3Drilling(CamPartSide side, Drilling drilling) {
		drilling.setPlaneX(side.mirrorY(drilling.getY()));
		drilling.setPlaneY(drilling.getZ());
		side.addPlane3Drilling(drilling);
	}

	@Override
	public void addPlane4Drilling(CamPartSide side, Drilling drilling) {
		drilling.setPlaneX(drilling.getY());
		drilling.setPlaneY(drilling.getZ());
		side.addPlane4Drilling(drilling);
	}

	private RowDrilling convertToRowDrilling(Drilling drilling) {
		if (drilling instanceof RowDrilling) {
			return (RowDrilling) drilling;
		} else {
			throw new RuntimeException("row-drilling expecrted");
		}
	}

}
