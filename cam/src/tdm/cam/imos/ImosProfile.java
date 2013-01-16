package tdm.cam.imos;


public class ImosProfile {

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
