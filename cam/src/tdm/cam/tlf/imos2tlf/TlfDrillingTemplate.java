package tdm.cam.tlf.imos2tlf;

import tdm.cam.math.Dimensions;
import tdm.cam.tlf.Drilling;
import tdm.cam.tlf.ITlfEngineHolder;
import tdm.cam.tlf.RowDrilling;


public class TlfDrillingTemplate implements ITlfEngineHolder {

	public static final char PREFIX_THROUGH = 'V';
	
	protected double diameter;
	protected int paramVelentrata;
	protected int paramRallforo;
	protected int paramTipounta;
	
	public TlfDrillingTemplate() {
		super();
	}

	public Drilling createDrilling(Dimensions dimensions) {
		Drilling drilling = new Drilling(this, dimensions);
		return drilling;
	}
	
	public RowDrilling createRowDrilling(Dimensions dimensions) {
		RowDrilling drilling = new RowDrilling(this, dimensions);
		return drilling;
	}
	
	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
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

}