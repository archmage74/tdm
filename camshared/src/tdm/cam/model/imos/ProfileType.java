package tdm.cam.model.imos;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum ProfileType implements IsSerializable {

	POS_V (1),
	POS_R (2),
	POS_H (3),
	POS_L (4);

	int value;
	
	public static ProfileType create(int prfNo) {
		for (ProfileType type : ProfileType.values()) {
			if (prfNo == type.getValue()) {
				return type;
			}
		}
		return null;
	}
	
	ProfileType(Integer number) {
		this.value = number;
	}

	public int getValue() {
		return value;
	}

}
