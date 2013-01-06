package tdm.cam.tlf;

public class FrontPartSide extends CamPartSide {

	public FrontPartSide(PartDimensions dimensions) {
		super(dimensions);
	}

	@Override
	public void addDrilling(Drilling drilling) {
		drilling.setPlaneX(drilling.getX());
		drilling.setPlaneY(mirrorY(drilling.getY()));
		super.addDrilling(drilling);
	}
	
	@Override
	public void addPlane1Drilling(Drilling drilling) {
		drilling.setPlaneX(drilling.getX());
		drilling.setPlaneY(drilling.getZ());
		super.addPlane1Drilling(drilling);
	}

	@Override
	public void addPlane2Drilling(Drilling drilling) {
		drilling.setPlaneX(drilling.getX());
		drilling.setPlaneY(mirrorZ(drilling.getZ()));
		super.addPlane2Drilling(drilling);
	}

	@Override
	public void addPlane3Drilling(Drilling drilling) {
		drilling.setPlaneX(mirrorY(drilling.getY()));
		drilling.setPlaneY(drilling.getZ());
		super.addPlane3Drilling(drilling);
	}

	@Override
	public void addPlane4Drilling(Drilling drilling) {
		drilling.setPlaneX(drilling.getY());
		drilling.setPlaneY(drilling.getZ());
		super.addPlane4Drilling(drilling);
	}

}
