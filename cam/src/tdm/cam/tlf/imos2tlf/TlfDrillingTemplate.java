package tdm.cam.tlf.imos2tlf;

import tdm.cam.math.Dimensions;
import tdm.cam.tlf.TlfDrilling;
import tdm.cam.tlf.ITlfEngineHolder;
import tdm.cam.tlf.TlfRowDrilling;


public class TlfDrillingTemplate implements ITlfEngineHolder {

	public static final char PREFIX_THROUGH = 'V';
	
	protected double diameter;
	protected int paramVelentrata;
	protected int paramRallforo;
	protected int paramTipounta;
	
	public TlfDrillingTemplate() {
		super();
	}

	public TlfDrilling createDrilling(Dimensions dimensions) {
		TlfDrilling drilling = new TlfDrilling(this, dimensions);
		return drilling;
	}
	
	public TlfRowDrilling createRowDrilling(Dimensions dimensions) {
		TlfRowDrilling drilling = new TlfRowDrilling(this, dimensions);
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