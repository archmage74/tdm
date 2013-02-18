package tdm.cam.model.imos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;

import tdm.cam.model.math.Dimensions;

@XmlType()
@XmlAccessorType(XmlAccessType.FIELD)
public class ImosPart implements IsSerializable {

	private String id;

	private String orderId;

	private String name;

	private String matId;

	private String barcode;

	private Dimensions dimensions = new Dimensions();

	@XmlElementWrapper(name="drillings")
	@XmlElement(name="drilling")
	private List<ImosDrilling> drillings = new ArrayList<ImosDrilling>();
	
	@XmlElementWrapper(name="profiles")
	@XmlElement(name="profile")
	private List<ImosProfile> profiles = new ArrayList<ImosProfile>();

	public ImosPart() {
		
	}
	
	public ImosPart(ImosPart other) {
		this.id = new String(other.getId() == null ? "" : other.getId());
		this.barcode = new String(other.getBarcode() == null ? "" : other.getBarcode());
		this.matId = new String(other.getMatId() == null ? "" : other.getMatId());
		this.name = new String(other.getName() == null ? "" : other.getName());
		this.orderId = new String(other.getOrderId() == null ? "" : other.getOrderId());
		this.setDimensions(other.getDimensions());
		this.setDrillings(other.getDrillings());
		this.setProfiles(other.getProfiles());
	}

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

	public void setDrillings(List<ImosDrilling> drillings) {
		this.drillings = drillings;
	}

	public List<ImosProfile> getProfiles() {
		return profiles;
	}
	
	public void addProfile(ImosProfile profile) {
		profiles.add(profile);
	}

	public void setProfiles(List<ImosProfile> profiles) {
		this.profiles = profiles;
	}
	
}
