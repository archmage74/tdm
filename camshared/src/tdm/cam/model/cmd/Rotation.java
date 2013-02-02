package tdm.cam.model.cmd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlType()
@XmlAccessorType(XmlAccessType.FIELD)
public class Rotation implements IsSerializable {

	private String barcode;

	private int angle;

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
