package tdm.cam.tlf;

public class FrontPartSide extends CamPartSide {

	public FrontPartSide(PartDimensions dimensions) {
		super(dimensions);
	}

	@Override
	public void addDrilling(Drilling drilling) {
//		drilling.mirrorX();
		drilling.mirrorY();
		super.addDrilling(drilling);
	}
	
	@Override
	public boolean removeDrilling(Drilling drilling) {
		boolean removed = super.removeDrilling(drilling);
//		drilling.mirrorX();
		drilling.mirrorY();
		return removed;
	}
	
	@Override
	public void addPlane1Drilling(Drilling drilling) {
		double x = drilling.getX();
		double y = drilling.getY();
		double z = drilling.getZ();
		
		drilling.setZ(0.0); // FIXME -> offset in tlf
		drilling.setX(x);
		drilling.setY(z);
		
		super.addPlane1Drilling(drilling);
	}

	@Override
	public void addPlane2Drilling(Drilling drilling) {
		double x = drilling.getX();
		double y = drilling.getY();
		double z = drilling.getZ();
		
		drilling.setZ(0.0); // FIXME -> offset in tlf
		drilling.setX(x);
		drilling.setY(dimensions.getThick() - z);
		
		super.addPlane2Drilling(drilling);
	}

	@Override
	public void addPlane3Drilling(Drilling drilling) {
		double x = drilling.getX();
		double y = drilling.getY();
		double z = drilling.getZ();
		
		drilling.setZ(0.0); // FIXME -> offset in tlf
		drilling.setX(dimensions.getWidth() - y);
		drilling.setY(z);
		
		super.addPlane3Drilling(drilling);
	}

	@Override
	public void addPlane4Drilling(Drilling drilling) {
		double x = drilling.getX();
		double y = drilling.getY();
		double z = drilling.getZ();
		
		drilling.setZ(0.0); // FIXME -> offset in tlf
		drilling.setX(y);
		drilling.setY(z);
		
		super.addPlane4Drilling(drilling);
	}

}
