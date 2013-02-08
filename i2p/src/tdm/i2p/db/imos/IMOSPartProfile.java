package tdm.i2p.db.imos;

public class IMOSPartProfile {
	
	public static String NO_PROFILE = "PRF_00";
	
	public static Integer POS_V = 1;
	public static Integer POS_R = 2;
	public static Integer POS_H = 3;
	public static Integer POS_L = 4;

	private int prfNo;
	private String prfId;
	private double prfLen;

	private int prfp;   // ecksituation der kante zur folgekante
	private int prfb;   // kante v/n  
	
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
	public int getPrfNo() {
		return prfNo;
	}
	public void setPrfNo(int prfNo) {
		this.prfNo = prfNo;
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

	public String toString() {
		String s = "Profile { " +
			"prfId = " + prfId +
			", prfLen = " + prfLen +
			", prfNo = " + prfNo +
			", prfp = " + prfp +
			", prfb = " + prfb +
			" }";
		return s;
	}
	
}

/*
// IDBPRF columns
private String ID;
private String ORDERID;
private int PRFNO;
private String PRFID;
private String PRFP;
private String PRFB;
private double PRFLEN;
private double PRFHGT;
private double PRFTHK;
private String PRFWS;
private String PRFNAME;
private double PRFCPU;
private String PRFCOL1;
private String PRFCOL2;
private String HEIGHT;
private String CONTLEN;
private String PRFHGTFIN;
private String PRFTHKFIN;
private int DIFFTYPE;
private String RENDERP;
*/