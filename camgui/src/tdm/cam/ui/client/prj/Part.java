package tdm.cam.ui.client.prj;

import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.PartRotator;
import tdm.cam.model.math.Matrix3x3;
import tdm.cam.model.math.RotationMatrixFactory;

public class Part {

	protected RotationMatrixFactory rotationMatrixFactory = new RotationMatrixFactory();
	
	protected PartRotator partRotator = new PartRotator();
	
	protected ImosPart originalImosPart;
	
	protected ImosPart transformedImosPart;
	
	protected double rotation = 0;

	public Part(ImosPart imosPart) {
		this.originalImosPart = imosPart;
		this.rotation = 0;
		this.rotate(this.rotation);
	}
	
	public void rotateLeft() {
		rotate(- Math.PI / 2);
	}
	
	public void rotateRight() {
		rotate(Math.PI / 2);
	}
	
	public void rotate(double angleDifference) {
		rotation += angleDifference;
		Matrix3x3 rotationMatrix = rotationMatrixFactory.createZRotation(rotation);
		transformedImosPart = partRotator.createRotatedPart(originalImosPart, rotationMatrix);
	}

	public double getRotation() {
		return rotation;
	}
	
	public double getRotationInDegrees() {
		return rotation / Math.PI * 180;
	}

	public ImosPart getOriginalImosPart() {
		return originalImosPart;
	}

	public void setOriginalImosPart(ImosPart originalImosPart) {
		this.originalImosPart = originalImosPart;
	}

	public ImosPart getTransformedImosPart() {
		return transformedImosPart;
	}

	public void setTransformedImosPart(ImosPart transformedImosPart) {
		this.transformedImosPart = transformedImosPart;
	}
	
}
