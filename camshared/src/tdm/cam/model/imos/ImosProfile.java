package tdm.cam.model.imos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlType(name="profile")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImosProfile implements IsSerializable {

	protected String prfId;
	protected ProfileType profileType;
	protected double prfLen;

	protected double thick;

	protected int prfp; // kante v/n
	protected int prfb; // ecksituation der kante zur folgekante
	
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

	public ProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}
	
	public ImosProfile clone() {
		ImosProfile clone = new ImosProfile();
		clone.prfb = prfb;
		clone.prfId = prfId;
		clone.prfLen = prfLen;
		clone.prfp = prfp;
		clone.thick = thick;
		clone.profileType = profileType;
		return clone;
	}

}
