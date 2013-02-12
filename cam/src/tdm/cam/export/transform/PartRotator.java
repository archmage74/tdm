package tdm.cam.export.transform;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProfile;
import tdm.cam.model.imos.ProfileType;
import tdm.cam.model.math.Dimensions;
import tdm.cam.model.math.Matrix3x3;
import tdm.cam.model.math.Vector3;

public class PartRotator {

	public void rotatePart(ImosPart part, Matrix3x3 rot) {
		Dimensions rotatedDimensions = rotateDimensions(part.getDimensions(), rot);
		List<ImosDrilling> rotatedDrillings = rotateDrillings(part.getDrillings(), rot);
		List<ImosProfile> rotatedProfiles = rotateProfiles(part.getProfiles(), rot);

		leftBottomAlign(rotatedDimensions, rotatedDrillings);

		part.setDimensions(rotatedDimensions);
		part.setDrillings(rotatedDrillings);
		part.setProfiles(rotatedProfiles);
	}

	private void leftBottomAlign(Dimensions dimensions, List<ImosDrilling> drillings) {
		double dx = 0;
		if (dimensions.getLength() < 0) {
			dx = -dimensions.getLength();
		}

		double dy = 0;
		if (dimensions.getWidth() < 0) {
			dy = -dimensions.getWidth();
		}
		
		for (ImosDrilling drilling : drillings) {
			drilling.setX(drilling.getX() + dx);
			drilling.setY(drilling.getY() + dy);
			drilling.setEndX(drilling.getEndX() + dx);
			drilling.setEndY(drilling.getEndY() + dy);
		}
		dimensions.setLength(Math.abs(dimensions.getLength()));
		dimensions.setWidth(Math.abs(dimensions.getWidth()));
	}

	private Dimensions rotateDimensions(Dimensions d, Matrix3x3 rot) {
		Vector3 v = new Vector3(d.getLength(), d.getWidth(), d.getThick());
		Vector3 rv = rot.multiply(v);
		Dimensions rotatedDimensions = new Dimensions(rv.getX(), rv.getY(), rv.getZ());
		return rotatedDimensions;
	}

	private List<ImosDrilling> rotateDrillings(List<ImosDrilling> drillings, Matrix3x3 rot) {
		List<ImosDrilling> rotatedDrillings = new ArrayList<ImosDrilling>();
		for (ImosDrilling drilling : drillings) {
			ImosDrilling rotatedDrilling = rotateDrilling(drilling, rot);
			rotatedDrillings.add(rotatedDrilling);
		}
		return rotatedDrillings;
	}

	private ImosDrilling rotateDrilling(ImosDrilling drilling, Matrix3x3 rot) {
		Vector3 rsv = rot.multiply(drilling.getPosition());
		Vector3 rev = rot.multiply(drilling.getEndPosition());
		Vector3 rd = rot.multiply(drilling.getDirection());

		ImosDrilling rotatedDrilling = drilling.clone();
		rotatedDrilling.setPosition(rsv);
		rotatedDrilling.setEndPosition(rev);
		rotatedDrilling.setDirection(rd);

		return rotatedDrilling;
	}

	private List<ImosProfile> rotateProfiles(List<ImosProfile> profiles, Matrix3x3 rot) {
		List<ImosProfile> rotatedProfiles = new ArrayList<ImosProfile>();
		for (ImosProfile profile : profiles) {
			ImosProfile rotatedProfile = rotateProfile(profile, rot);
			rotatedProfiles.add(rotatedProfile);
		}
		return rotatedProfiles;
	}

	private ImosProfile rotateProfile(ImosProfile profile, Matrix3x3 rot) {
		ImosProfile rotatedProfile = profile.clone();
		Vector3 direction = rot.multiply(profile.getProfileType().getDirection());
		
		rotatedProfile.setProfileType(ProfileType.createByDirection(direction));
		return rotatedProfile;
	}

}
