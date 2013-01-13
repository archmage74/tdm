package tdm.cam.tlf;

public interface INodeAdder {

	public abstract void addPlane1Node(CamPartSide side, ITlfNode node);

	public abstract void addPlane2Node(CamPartSide side, ITlfNode node);

	public abstract void addPlane3Node(CamPartSide side, ITlfNode node);

	public abstract void addPlane4Node(CamPartSide side, ITlfNode node);

}