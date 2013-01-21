package tdm.cam.model.imos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlType(name="profile")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImosProfile implements IsSerializable {

	private int prfNo;
	private String prfId;
	private double prfLen;

	private double thick;

	private int prfp; // kante v/n
	private int prfb; // ecksituation der kante zur folgekante
	
	public int getPrfNo() {
		return prfNo;
	}

	public void setPrfNo(int prfNo) {
		this.prfNo = prfNo;
	}

	public String getPrfId() {
		return prfId;
	}

	public void setPrfId(String prfId) {
		this.prfId = prfId;
	}

	public double getPrfLen() {
		return prfLen;
	}

	public void setPrfLen(double prfLen) {
		this.prfLen = prfLen;
	}

	public double getThick() {
		return thick;
	}

	public void setThick(double thick) {
		this.thick = thick;
	}

	public int getPrfp() {
		return prfp;
	}

	public void setPrfp(int prfp) {
		this.prfp = prfp;
	}

	public int getPrfb() {
		return prfb;
	}

	public void setPrfb(int prfb) {
		this.prfb = prfb;
	}

}
