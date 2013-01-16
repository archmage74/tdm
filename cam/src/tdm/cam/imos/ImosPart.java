package tdm.cam.imos;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.math.Dimensions;

public class ImosPart {

	private String id;

	private String orderId;

	private String name;

	private String matId;

	private String barcode;

	private Dimensions dimensions = new Dimensions();

	private List<ImosDrilling> drillings = new ArrayList<ImosDrilling>();
	
	private List<ImosProfile> profiles = new ArrayList<ImosProfile>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMatId() {
		return matId;
	}

	public void setMatId(String matId) {
		this.matId = matId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public List<ImosDrilling> getDrillings() {
		return drillings;
	}

	public void addDrilling(ImosDrilling drilling) {
		drillings.add(drilling);
	}

	public List<ImosProfile> getProfiles() {
		return profiles;
	}
	
	public void addProfile(ImosProfile profile) {
		profiles.add(profile);
	}
	
}
