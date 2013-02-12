package tdm.cam.tlf.imos2tlf;

import tdm.cam.model.imos.ImosProfile;
import tdm.cam.tlf.TlfProfile;

public class TlfProfileFactory {

	public TlfProfile createProfile(ImosProfile imosProfile) {
		TlfProfile profile = new TlfProfile();
		profile.setThick(imosProfile.getThick());
		profile.setProfileType(imosProfile.getProfileType());
		return profile;
	}

}
