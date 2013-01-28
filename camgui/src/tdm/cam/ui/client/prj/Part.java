package tdm.cam.ui.client.prj;

import tdm.cam.model.imos.ImosPart;

public class Part {

	protected ImosPart imosPart;
	
	protected double rotation = 0;

	public Part(ImosPart imosPart) {
		this.imosPart = imosPart;
		rotation = 0;
	}
	
	public void rotateLeft() {
		rotate(- Math.PI / 2);
	}
	
	public void rotateRight() {
		rotate(Math.PI / 2);
	}
	
	public void rotate(double angle) {
		rotation += angle;
	}

	public ImosPart getImosPart() {
		return imosPart;
	}

	public void setImosPart(ImosPart imosPart) {
		this.imosPart = imosPart;
	}

	public double getRotation() {
		return rotation;
	}
	
	public double getRotationInDegrees() {
		return rotation / Math.PI * 180;
	}
	
}
