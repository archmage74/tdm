package tdm.cam.model.imos;

import tdm.cam.model.imos.ImosProfile;


public class TestProfileParameters {

//	private PartDimensions dimensions = new PartDimensions(714, 404, 19);
	private ProfileType profileType = ProfileType.POS_V;
	private double prfLen = 714;
	private double thick = 2;
	
	public TestProfileParameters() {
	}
	
	public TestProfileParameters profileType(ProfileType profileType) {
		this.profileType= profileType;
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
	
	public ImosProfile create() {
		ImosProfile p = new ImosProfile();
		p.setProfileType(profileType);
		p.setPrfLen(prfLen);
		p.setThick(thick);
		return p;
	}
	
}
