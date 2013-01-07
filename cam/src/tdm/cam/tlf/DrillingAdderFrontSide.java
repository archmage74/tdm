package tdm.cam.tlf;

public class DrillingAdderFrontSide implements IDrillingAdder {

	@Override
	public void addDrilling(CamPartSide side, Drilling drilling) {
		drilling.setPlaneX(drilling.getX());
		drilling.setPlaneY(side.mirrorY(drilling.getY()));
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

}
