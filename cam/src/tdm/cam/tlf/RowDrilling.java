package tdm.cam.tlf;

import java.util.HashMap;
import java.util.Map;

public class RowDrilling extends Drilling {

	public static final double OFFSET = 32.0;
	
	public static String entity = "RowDrilling.entity.jmte";
	public static String work = "RowDrilling.work.jmte";

	protected double endX;
	protected double endY;
	protected int numDrillings; 

	protected double planeEndX;
	protected double planeEndY;

	public RowDrilling(PartDimensions dimensions) {
		super(dimensions);
		this.dimensions = dimensions;
	}
	
	public RowDrilling(TlfDrillingTemplate template, PartDimensions dimensions) {
		super(template, dimensions);		
	}

	@Override
	public void calculatePlaneCoordinates(PartDimensions dimensions) {
		super.calculatePlaneCoordinates(dimensions);
		planeEndX = planeCoordinatesTransformer.getPlaneX(dimensions, endX, endY, 0);
		planeEndY = planeCoordinatesTransformer.getPlaneY(dimensions, endX, endY, 0);
	};

	@Override
	public String exportEntity() {
		StringBuffer tlf = new StringBuffer();
	
		Map<String, Object> drillingModel = new HashMap<String, Object>();
		drillingModel.put("dimensions", dimensions);
		drillingModel.put("drilling", this);
		tlf.append(ENGINE.transform(entity, drillingModel));
	
		return tlf.toString();
	}

	@Override
	public String exportWork() {
		StringBuffer tlf = new StringBuffer();
	
		Map<String, Object> drillingModel = new HashMap<String, Object>();
		drillingModel.put("dimensions", dimensions);
		drillingModel.put("drilling", this);
		tlf.append(ENGINE.transform(work, drillingModel));
	
		return tlf.toString();
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}

	public int getNumDrillings() {
		return numDrillings;
	}

	public void setNumDrillings(int numDrillings) {
		this.numDrillings = numDrillings;
	}

	public double getPlaneEndX() {
		return planeEndX;
	}

	public void setPlaneEndX(double planeEndX) {
		this.planeEndX = planeEndX;
	}

	public double getPlaneEndY() {
		return planeEndY;
	}

	public void setPlaneEndY(double planeEndY) {
		this.planeEndY = planeEndY;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Drilling { ");
		sb.append("index=").append(index).append(", ");
		sb.append("x=").append(x).append(", ");
		sb.append("y=").append(y).append(", ");
		sb.append("z=").append(z).append(", ");
		sb.append("angleX=").append(angleX).append(", ");
		sb.append("angleY=").append(angleY).append(", ");
		sb.append("angleZ=").append(angleZ).append(", ");
		sb.append("deep=").append(deep).append(", ");
		sb.append("diameter=").append(diameter).append(", ");
		sb.append("numDrillings=").append(numDrillings).append(", ");
		sb.append("endX=").append(endX).append(", ");
		sb.append("endY=").append(endY).append(", ");
		sb.append("paramVelentrata=").append(paramVelentrata).append(", ");
		sb.append("paramRallforo=").append(paramRallforo).append(", ");
		sb.append("paramTipounta=").append(paramTipounta);
		sb.append(" }");
		return sb.toString();
	}

}