package tdm.cam.tlf;

import tdm.cam.model.math.Dimensions;
import tdm.cam.tlf.transformer.IPlaneCoordinatesTransformer;

public interface ITlfNode {

	public void calculatePlaneCoordinates(Dimensions dimensions);
	
	public void setPlaneCoordinatesTransformer(IPlaneCoordinatesTransformer planeCoordinatesTransformer);
	
	public String exportEntity();

	public String exportWork();

	public int getIndex();
	
	public void setIndex(int index);
	
	public boolean isSideIndependent();

//	public boolean isHorizontal();
	
}
