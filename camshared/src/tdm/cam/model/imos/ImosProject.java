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
	
	@XmlElementWrapper(name="warnings")
	@XmlElement(name="warning")
	private List<String> warnings = new ArrayList<String>();
	
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

	public List<String> getWarnings() {
		return warnings;
	}

	public void addWarning(String warning) {
		warnings.add(warning);
	}
	
	public void setWarnings(List<String> warnings) {
		this.warnings.clear();
		this.warnings.addAll(warnings);
	}

}
