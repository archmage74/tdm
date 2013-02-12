package tdm.cam.model.imos;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import tdm.cam.model.math.Vector3;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlType
@XmlEnum(String.class)
public enum ProfileType implements IsSerializable {

	@XmlEnumValue("bottom") POS_V (1, new Vector3(0, -1, 0)),
	@XmlEnumValue("right") POS_R (2, new Vector3(1, 0, 0)),
	@XmlEnumValue("top") POS_H (3, new Vector3(0, 1, 0)),
	@XmlEnumValue("left") POS_L (4, new Vector3(-1, 0, 0));

	protected int value;
	protected Vector3 direction;
	
	public static ProfileType createByProfileNumber(int prfNo) {
		for (ProfileType type : ProfileType.values()) {
			if (prfNo == type.getValue()) {
				return type;
			}
		}
		return null;
	}
	
	public static ProfileType createByDirection(Vector3 direction) {
		if (direction == null) {
			return null;
		}
		for (ProfileType profileType : ProfileType.values()) {
			if (direction.equals(profileType.getDirection())) {
				return profileType;
			}
		}
		return null;
	}
	
	ProfileType(Integer number, Vector3 direction) {
		this.value = number;
		this.direction = direction;
	}

	public int getValue() {
		return value;
	}

	public Vector3 getDirection() {
		return direction;
	}

}
