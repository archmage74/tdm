package tdm.cam.model.imos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ImosProject implements IsSerializable {

	private String orderId;
	
	private String name;
	
	@XmlElementWrapper(name="parts")
	@XmlElement(name="part")
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
