package tdm.i2p.db.protime;

public class Material {
	String id;
	String thickness;
	String edgeVN;

	public Material(String id, String thickness, String edgeVN) {
		this.id = id;
		this.thickness = thickness;
		this.edgeVN = edgeVN;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThickness() {
		return thickness;
	}
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}
	public String getEdgeVN() {
		return edgeVN;
	}
	public void setEdgeVN(String edgeVN) {
		this.edgeVN = edgeVN;
	}
}
