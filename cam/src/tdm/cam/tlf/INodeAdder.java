package tdm.cam.tlf;

public interface INodeAdder {

	public abstract void addPlane1Node(TlfPartSide side, ITlfNode node);

	public abstract void addPlane2Node(TlfPartSide side, ITlfNode node);

	public abstract void addPlane3Node(TlfPartSide side, ITlfNode node);

	public abstract void addPlane4Node(TlfPartSide side, ITlfNode node);

}