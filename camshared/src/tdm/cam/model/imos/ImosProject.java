package tdm.cam.model.imos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class ImosProject {

	private String orderId;
	
	private String name;
	
	private List<ImosPart> parts = new ArrayList<ImosPart>();

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

	public List<ImosPart> getParts() {
		return parts;
	}

	public void addPart(ImosPart part) {
		parts.add(part);
	}
	
	public void setParts(List<ImosPart> parts) {
		this.parts = parts;
	}

}
