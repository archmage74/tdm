package tdm.cam.tlf;

import java.util.HashMap;
import java.util.Map;

public class Drilling implements ITlfNode {

	public static final double THROUGH_ADD_ON = 4.0;
	
	public static String entity = "Drilling.entity.jmte";
	public static String work = "Drilling.work.jmte";
	
	protected int index;
	protected double x;
	protected double y;
	protected double z;
	protected double angleX;
	protected double angleY;
	protected double angleZ;
	protected double deep;

	// FIXME add plane-X/Y/Z coords, so drillings can easily be removed and added to another side/plane
	
	protected PartDimensions dimensions;
	
	protected double diameter;
	protected int paramVelentrata;
	protected int paramRallforo;
	protected int paramTipounta;
	
	// FIXME check if it makes sense to add dimensions in camPart.addDrilling()
	public Drilling(PartDimensions dimensions) {
		super();
		this.dimensions = dimensions;
	}
	
	public Drilling(DrillingTemplate template, PartDimensions dimensions) {
		this(dimensions);		
		this.setDiameter(template.getDiameter());
		this.setParamRallforo(template.getParamRallforo());
		this.setParamTipounta(template.getParamTipounta());
		this.setParamVelentrata(template.getParamVelentrata());
	}

	public Object exportEntity(PartDimensions dimensions) {
		StringBuffer tlf = new StringBuffer();
	
		Map<String, Object> drillingModel = new HashMap<String, Object>();
		drillingModel.put("dimensions", dimensions);
		drillingModel.put("drilling", this);
		tlf.append(ENGINE.transform(entity, drillingModel));
	
		return tlf.toString();
	}

	public Object exportWork(PartDimensions dimensions) {
		StringBuffer tlf = new StringBuffer();
	
		Map<String, Object> drillingModel = new HashMap<String, Object>();
		drillingModel.put("dimensions", dimensions);
		drillingModel.put("drilling", this);
		tlf.append(ENGINE.transform(work, drillingModel));
	
		return tlf.toString();
	}

	public void mirrorX() {
		x = (dimensions.getLength() - x);
	}

	public void mirrorY() {
		y = (dimensions.getWidth() - y);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getAngleX() {
		return angleX;
	}

	public void setAngleX(double angleX) {
		this.angleX = angleX;
	}

	public double getAngleY() {
		return angleY;
	}

	public void setAngleY(double angleY) {
		this.angleY = angleY;
	}

	public double getAngleZ() {
		return angleZ;
	}

	public void setAngleZ(double angleZ) {
		this.angleZ = angleZ;
	}

	public double getDeep() {
		return deep;
	}

	public void setDeep(double deep) {
		this.deep = deep;
		if (this.deep < 0) {
			this.deep *= -1;
		}
	}
	
	public double getMachineDeep() {
		if (isThrough()) {
			return this.deep + Drilling.THROUGH_ADD_ON; 
		} else {
			return this.deep;
		}
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getRadius() {
		return this.diameter / 2.0;
	}
	
	public int getParamVelentrata() {
		return paramVelentrata;
	}

	public void setParamVelentrata(int paramVelentrata) {
		this.paramVelentrata = paramVelentrata;
	}

	public int getParamRallforo() {
		return paramRallforo;
	}

	public void setParamRallforo(int paramRallforo) {
		this.paramRallforo = paramRallforo;
	}

	public int getParamTipounta() {
		return paramTipounta;
	}

	public void setParamTipounta(int paramTipounta) {
		this.paramTipounta = paramTipounta;
	}

	public PartDimensions getDimensions() {
		return dimensions;
	}

	public boolean isThrough() {
		// FIXME: check orientation first for horizontal drillings

		if (angleX == 90 || angleX == -90) {
			return false;
		}
//		if (angleY == 90 || angleY == -90) {
//			return false;
//		}
		
		if (deep >= (dimensions.getThick() - 0.01)) { // 0.01mm epsilon to compare doubles  
			return true;
		} else {
			return false;
		}
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
		sb.append("paramVelentrata=").append(paramVelentrata).append(", ");
		sb.append("paramRallforo=").append(paramRallforo).append(", ");
		sb.append("paramTipounta=").append(paramTipounta);
		sb.append(" }");
		return sb.toString();
	}

}