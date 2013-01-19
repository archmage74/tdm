package tdm.cam.tlf.imos2tlf;

import tdm.cam.model.imos.ImosProfile;
import tdm.cam.model.imos.ProfileType;
import tdm.cam.tlf.TlfProfile;

public class TlfProfileFactory {

	public TlfProfile createProfile(ImosProfile imosProfile) {
		TlfProfile profile = new TlfProfile();
		profile.setThick(imosProfile.getThick());

		switch(imosProfile.getPrfNo()) {
		case 1:
			profile.setProfileType(ProfileType.POS_V);
			break;
		case 2:
			profile.setProfileType(ProfileType.POS_R);
			break;
		case 3:
			profile.setProfileType(ProfileType.POS_H);
			break;
		case 4:
			profile.setProfileType(ProfileType.POS_L);
			break;
		default:
			throw new RuntimeException("Not support for more than 4 sides per part");
		}
		
		return profile;
	}

}
