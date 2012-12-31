package tdm.i2p.db.imos;

import java.util.HashMap;

import tdm.i2p.db.protime.SPosEntry;

public class IMOSPart {
	
	public static String NO_SURFACE = "NO_SURF";
	
	private String orderId;
	private String id;
	private double cnt;

	private String name1;
	private String barcode;
	private String matId;
	private double fLeng;
	private double fWidth;
	private String surfTId;
	private String surfBId;
	
	private SPosEntry sPos;

	// profiles
	private HashMap<Integer, IMOSPartProfile> profileMap = new HashMap<Integer, IMOSPartProfile>(4);

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getCnt() {
		return cnt;
	}
	public void setCnt(double cnt) {
		this.cnt = cnt;
	}

	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getMatId() {
		return matId;
	}
	public void setMatId(String matId) {
		this.matId = matId;
	}
	public double getFLeng() {
		return fLeng;
	}
	public void setFLeng(double fLeng) {
		this.fLeng = fLeng;
	}
	public double getFWidth() {
		return fWidth;
	}
	public void setFWidth(double fWidth) {
		this.fWidth = fWidth;
	}
	public String getSurfTId() {
		return surfTId;
	}
	public void setSurfTId(String surfTId) {
		this.surfTId = surfTId;
	}
	public String getSurfBId() {
		return surfBId;
	}
	public void setSurfBId(String surfBId) {
		this.surfBId = surfBId;
	}
	
	public IMOSPartProfile getProfile(Integer profileType) {
		return profileMap.get(profileType);
	}

	public void putProfile(IMOSPartProfile profile) {
		profileMap.put(profile.getPrfNo(), profile);
	}
	
	public String toString() {
		String s = "IMOSPart {" + 
			" orderId=" + orderId +
			", id=" + id +
			", cnt=" + cnt +
			", name1=" + name1 +
			", barcode=" + barcode +
			", matId=" + matId +
			", fLeng=" + fLeng +
			", fWidth=" + fWidth +
			", surfTId=" + surfTId +
			", surfBId=" + surfBId +
			", profiles=" + profileMap +
			", sPos=" + sPos +
			" }";
		return s;
	}
	public SPosEntry getSPosEntry() {
		return sPos;
	}
	public void setSPos(SPosEntry sPos) {
		this.sPos = sPos;
	}
	public HashMap<Integer, IMOSPartProfile> getProfileMap() {
		return profileMap;
	}
	public void setProfileMap(HashMap<Integer, IMOSPartProfile> profileMap) {
		this.profileMap = profileMap;
	}
}

/*
// IDBGPL columns
private int TYP;
private String CPID;
private String MATNAME;
private String MATGRID;
private double MATGROR;
private String SURFTNAM;
private String SURFTGRID;
private double SURFTGROR;
private double SURFTLEN;
private double SURFTWIDTH;
private double SURFTTHK;
private String SURFBNAM;
private String SURFBGRID;
private double SURFBGROR;
private double SURFBLEN;
private double SURFBWIDTH;
private double SURFBTHK;
private double FTHK;
private double CLENG;
private double CWIDTH;
private double CTHK;
private double RLENG;
private double RWIDTH;
private double RTHK;
private String ISPEC;
private double AREA;
private double PRICE;
private String NCNO;
private String TEXT1;
private String TEXT2;
private String ID_SERIE;
private String ID_TEXT;
private String ID_NCNO;
private int NC_FLAG;
private int BOM_FLAG;
private int CUT_FLAG;
private String KMS;
private int MPE_TYPE;
private String PARENT_ID;
private String VMOTHER_ID;
private String VENEER_ID;
private String FLITCH_ID;
private double X_VMOTHER;
private double Y_VMOTHER;
private double VM_ORIENT;
private int MIRROR_FL;
private String INFO1;
private String INFO2;
private String INFO3;
private String INFO4;
private String INFO5;
private String CHECKSUM1;
private String CHECKSUM2;
private String MATCAT;
private double MATCPU;
private double SURFTCPU;
private double SURFBCPU;
private String MATCOL1;
private String MATCOL2;
private String SURFTCOL1;
private String SURFTCOL2;
private String SURFBCOL1;
private String SURFBCOL2;
private String PARTPOSSTR;
private String PRODCODE;
//private int DIFFTYPE;
private String RENDERPMAT;
private String RENDERPSUT;
private String RENDERPSUB;
*/

