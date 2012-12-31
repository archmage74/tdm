package tdm.cam.tlf;

public class BackPartSide extends CamPartSide {

	public BackPartSide(PartDimensions dimensions) {
		super(dimensions);
	}

	@Override
	public void addDrilling(Drilling drilling) {
		drilling.mirrorX();
		drilling.mirrorY();
		super.addDrilling(drilling);
	}
	
	@Override
	public boolean removeDrilling(Drilling drilling) {
		boolean removed = super.removeDrilling(drilling);
		drilling.mirrorX();
		drilling.mirrorY();
		return removed;
	}
	
	@Override
	public void addPlane1Drilling(Drilling drilling) {
		// FIXME 
		throw new UnsupportedOperationException("backside does not support horizontal drillings");
	}

	@Override
	public void addPlane2Drilling(Drilling drilling) {
		// FIXME 
		throw new UnsupportedOperationException("backside does not support horizontal drillings");
	}

	@Override
	public void addPlane3Drilling(Drilling drilling) {
		// FIXME 
		throw new UnsupportedOperationException("backside does not support horizontal drillings");
	}

	@Override
	public void addPlane4Drilling(Drilling drilling) {
		// FIXME 
		throw new UnsupportedOperationException("backside does not support horizontal drillings");
	}

}
