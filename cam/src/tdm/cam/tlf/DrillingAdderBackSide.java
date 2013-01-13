package tdm.cam.tlf;

// FIXME remove
@Deprecated
public class DrillingAdderBackSide implements INodeAdder {

	@Override
	public void addPlane1Node(CamPartSide side, ITlfNode node) {
		Drilling drilling = convertToDrilling(node);
		drilling.setPlaneX(side.mirrorX(drilling.getX()));
		drilling.setPlaneY(side.mirrorZ(drilling.getZ()));
		side.addPlane1Drilling(drilling);
	}

	@Override
	public void addPlane2Node(CamPartSide side, ITlfNode node) {
		Drilling drilling = convertToDrilling(node);
		drilling.setPlaneX(side.mirrorX(drilling.getX()));
		drilling.setPlaneY(side.mirrorZ(drilling.getZ()));
		side.addPlane2Drilling(drilling);
	}

	@Override
	public void addPlane3Node(CamPartSide side, ITlfNode node) {
		Drilling drilling = convertToDrilling(node);
		drilling.setPlaneX(side.mirrorY(drilling.getY()));
		drilling.setPlaneY(side.mirrorZ(drilling.getZ()));
		side.addPlane3Drilling(drilling);
	}

	@Override
	public void addPlane4Node(CamPartSide side, ITlfNode node) {
		Drilling drilling = convertToDrilling(node);
		drilling.setPlaneX(drilling.getY());
		drilling.setPlaneY(side.mirrorZ(drilling.getZ()));
		side.addPlane4Drilling(drilling);
	}

	private Drilling convertToDrilling(ITlfNode drilling) {
		if (drilling instanceof Drilling) {
			return (Drilling) drilling;
		} else {
			throw new RuntimeException("drilling expected");
		}
	}

}
