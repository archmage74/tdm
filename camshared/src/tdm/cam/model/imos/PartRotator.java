package tdm.cam.model.imos;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.model.math.Dimensions;
import tdm.cam.model.math.Matrix3x3;
import tdm.cam.model.math.Vector3;

public class PartRotator {

	public ImosPart createRotatedPart(ImosPart partToRotate, Matrix3x3 rot) {
		ImosPart rotatedPart = new ImosPart(partToRotate);

		Dimensions rotatedDimensions = rotateDimensions(partToRotate.getDimensions(), rot);
		List<ImosDrilling> rotatedDrillings = rotateDrillings(partToRotate.getDrillings(), rot);
		List<ImosProfile> rotatedProfiles = rotateProfiles(partToRotate.getProfiles(), rot);

		leftBottomAlign(rotatedDimensions, rotatedDrillings);

		rotatedPart.setDimensions(rotatedDimensions);
		rotatedPart.setDrillings(rotatedDrillings);
		rotatedPart.setProfiles(rotatedProfiles);
		
		return rotatedPart;
	}
	
	public void rotatePart(ImosPart partToRotate, Matrix3x3 rot) {
		ImosPart rotatedPart = createRotatedPart(partToRotate, rot);
		
		partToRotate.setDimensions(rotatedPart.getDimensions());
		partToRotate.setDrillings(rotatedPart.getDrillings());
		partToRotate.setProfiles(rotatedPart.getProfiles());
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
		
		double dz = 0;
		if (dimensions.getThick() < 0) {
			dz = -dimensions.getThick();
		}
		
		for (ImosDrilling drilling : drillings) {
			drilling.setX(drilling.getX() + dx);
			drilling.setY(drilling.getY() + dy);
			drilling.setZ(drilling.getZ() + dz);
			drilling.setEndX(drilling.getEndX() + dx);
			drilling.setEndY(drilling.getEndY() + dy);
			drilling.setEndZ(drilling.getEndZ() + dz);
		}
		dimensions.setLength(Math.abs(dimensions.getLength()));
		dimensions.setWidth(Math.abs(dimensions.getWidth()));
		dimensions.setThick(Math.abs(dimensions.getThick()));
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
