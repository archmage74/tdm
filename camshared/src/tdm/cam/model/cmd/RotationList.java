package tdm.cam.model.cmd;

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
public class RotationList implements IsSerializable {

	@XmlElementWrapper(name="rotations")
	@XmlElement(name="rotation")
	private List<Rotation> rotations = new ArrayList<Rotation>();

	public List<Rotation> getRotations() {
		return rotations;
	}
	
	public void addRotation(Rotation rotation) {
		rotations.add(rotation);
	}

	public void setRotations(List<Rotation> rotations) {
		this.rotations = rotations;
	}

}
