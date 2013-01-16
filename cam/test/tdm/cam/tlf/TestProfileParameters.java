package tdm.cam.tlf;

public class TestProfileParameters {

//	private PartDimensions dimensions = new PartDimensions(714, 404, 19);
	private int prfNo = 1;
	private double prfLen = 714;
	private double thick = 2;
	
	public TestProfileParameters() {
	}
	
	public TestProfileParameters prfNo(int prfNo) {
		this.prfNo = prfNo;
		return this;
	}
	
	public TestProfileParameters prfLen(double prfLen) {
		this.prfLen = prfLen;
		return this;
	}
	
	public TestProfileParameters thick(double thick) {
		this.thick = thick;
		return this;
	}
	
	public TlfProfile create() {
		TlfProfile p = new TlfProfile();
		p.setPrfNo(prfNo);
		p.setPrfLen(prfLen);
		p.setThick(thick);
		return p;
	}
	
}
