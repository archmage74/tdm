package tdm.cam.tlf;

import java.util.HashMap;
import java.util.Map;

import tdm.cam.tlf.transformer.IPlaneCoordinatesTransformer;

public class TlfProfile implements ITlfNode, ITlfEngineHolder {

	public static String entity = "Line.entity.jmte";

	public static String NO_PROFILE = "PRF_00";

	// index within the campart plane for tlf reference between entity and work
	private int index;

	private int prfNo;
	private String prfId;
	private double prfLen;

	private double thick;

	private int prfp; // kante v/n
	private int prfb; // ecksituation der kante zur folgekante

	private double planeX1;
	private double planeY1;

	private double planeX2;
	private double planeY2;

	protected IPlaneCoordinatesTransformer planeCoordinatesTransformer;

	@Override
	public String exportEntity() {
		StringBuffer tlf = new StringBuffer();

		Map<String, Object> profileModel = new HashMap<String, Object>();
		profileModel.put("profile", this);
		tlf.append(ENGINE.transform(entity, profileModel));

		return tlf.toString();
	}

	@Override
	public String exportWork() {
		return "";
	}

	@Override
	public boolean isSideIndependent() {
		return true;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	public String getPrfId() {
		return prfId;
	}

	public void setPrfId(String prfId) {
		this.prfId = prfId;
	}

	public double getPrfLen() {
		return prfLen;
	}

	public void setPrfLen(double prfLen) {
		this.prfLen = prfLen;
	}

	public int getPrfNo() {
		return prfNo;
	}

	public void setPrfNo(int prfNo) {
		this.prfNo = prfNo;
	}

	public double getThick() {
		return thick;
	}

	public void setThick(double thick) {
		this.thick = thick;
	}

	public int getPrfp() {
		return prfp;
	}

	public void setPrfp(int prfp) {
		this.prfp = prfp;
	}

	public int getPrfb() {
		return prfb;
	}

	public void setPrfb(int prfb) {
		this.prfb = prfb;
	}

	public double getPlaneX1() {
		return planeX1;
	}

	public double getPlaneY1() {
		return planeY1;
	}

	public double getPlaneX2() {
		return planeX2;
	}

	public double getPlaneY2() {
		return planeY2;
	}

	public void setPlaneCoordinatesTransformer(IPlaneCoordinatesTransformer planeCoordinatesTransformer) {
		this.planeCoordinatesTransformer = planeCoordinatesTransformer;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Profile { ");
		sb.append("index = ").append(index);
		sb.append(", prfId = ").append(prfId);
		sb.append(", prfLen = ").append(prfLen);
		sb.append(", prfNo = ").append(prfNo);
		sb.append(", thick = ").append(thick);
		sb.append(", prfp = ").append(prfp);
		sb.append(", prfb = ").append(prfb);
		sb.append(" }");
		return sb.toString();
	}

	@Override
	public void calculatePlaneCoordinates(PartDimensions dimensions) {
		if (prfNo == TlfProfileType.POS_V.getValue()) {
			planeX1 = planeCoordinatesTransformer.getPlaneX(dimensions, 0, thick, 0);
			planeY1 = planeCoordinatesTransformer.getPlaneY(dimensions, 0, thick, 0);
			planeX2 = planeCoordinatesTransformer.getPlaneX(dimensions, dimensions.getLength(), thick, 0);
			planeY2 = planeCoordinatesTransformer.getPlaneY(dimensions, dimensions.getLength(), thick, 0);
		} else if (prfNo == TlfProfileType.POS_H.getValue()) {
			planeX1 = planeCoordinatesTransformer.getPlaneX(dimensions, 0, dimensions.getWidth() - thick, 0);
			planeY1 = planeCoordinatesTransformer.getPlaneY(dimensions, 0, dimensions.getWidth() - thick, 0);
			planeX2 = planeCoordinatesTransformer.getPlaneX(dimensions, dimensions.getLength(), dimensions.getWidth() - thick, 0);
			planeY2 = planeCoordinatesTransformer.getPlaneY(dimensions, dimensions.getLength(), dimensions.getWidth() - thick, 0);
		} else if (prfNo == TlfProfileType.POS_L.getValue()) {
			planeX1 = planeCoordinatesTransformer.getPlaneX(dimensions, thick, 0, 0);
			planeY1 = planeCoordinatesTransformer.getPlaneY(dimensions, thick, 0, 0);
			planeX2 = planeCoordinatesTransformer.getPlaneX(dimensions, thick, dimensions.getWidth(), 0);
			planeY2 = planeCoordinatesTransformer.getPlaneY(dimensions, thick, dimensions.getWidth(), 0);
		} else if (prfNo == TlfProfileType.POS_R.getValue()) {
			planeX1 = planeCoordinatesTransformer.getPlaneX(dimensions, dimensions.getLength() - thick, 0, 0);
			planeY1 = planeCoordinatesTransformer.getPlaneY(dimensions, dimensions.getLength() - thick, 0, 0);
			planeX2 = planeCoordinatesTransformer.getPlaneX(dimensions, dimensions.getLength() - thick, dimensions.getWidth(), 0);
			planeY2 = planeCoordinatesTransformer.getPlaneY(dimensions, dimensions.getLength() - thick, dimensions.getWidth(), 0);
		} else {
			throw new RuntimeException("unknown profile encountered");
		}
	}

}

// IDBPRF columns
// private String ID;
// private String ORDERID;
// private int PRFNO;
// private String PRFID;
// private String PRFP;
// private String PRFB;
// private double PRFLEN;
// private double PRFHGT;
// private double PRFTHK;
// private String PRFWS;
// private String PRFNAME;
// private double PRFCPU;
// private String PRFCOL1;
// private String PRFCOL2;
// private String HEIGHT;
// private String CONTLEN;
// private String PRFHGTFIN;
// private String PRFTHKFIN;
// private int DIFFTYPE;
// private String RENDERP;
