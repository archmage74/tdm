package tdm.cam.export.transform;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProfile;
import tdm.cam.model.math.Dimensions;
import tdm.cam.model.math.Vector3;
import tdm.cam.model.math.VectorUtil;

public class PartRotator {

	public static final int ROTATION_ANGLE = 90;

	public void rotatePart(ImosPart part, double angleInDegrees) {
		Dimensions rotatedDimensions = rotateDimensions(part.getDimensions(), angleInDegrees);
		List<ImosDrilling> rotatedDrillings = rotateDrillings(part.getDrillings(), angleInDegrees);
		List<ImosProfile> rotatedProfiles = rotateProfiles(part.getProfiles());

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

	private Dimensions rotateDimensions(Dimensions d, double angleInDegrees) {
		Vector3 v = new Vector3(d.getLength(), d.getWidth(), d.getThick());
		Vector3 rv = VectorUtil.rotateByZInDegrees(v, angleInDegrees);
		Dimensions rotatedDimensions = new Dimensions(rv.getX(), rv.getY(), rv.getZ());
		return rotatedDimensions;
	}

	private List<ImosDrilling> rotateDrillings(List<ImosDrilling> drillings, double angleInDegrees) {
		List<ImosDrilling> rotatedDrillings = new ArrayList<ImosDrilling>();
		for (ImosDrilling drilling : drillings) {
			ImosDrilling rotatedDrilling = rotateDrilling(drilling, angleInDegrees);
			rotatedDrillings.add(rotatedDrilling);
		}
		return rotatedDrillings;
	}

	private ImosDrilling rotateDrilling(ImosDrilling drilling, double angleInDegrees) {
		Vector3 sv = new Vector3(drilling.getX(), drilling.getY(), drilling.getZ());
		Vector3 rsv = VectorUtil.rotateByZInDegrees(sv, angleInDegrees);

		Vector3 ev = new Vector3(drilling.getEndX(), drilling.getEndY(), 0);
		Vector3 rev = VectorUtil.rotateByZInDegrees(ev, angleInDegrees);

		ImosDrilling rotatedDrilling = drilling.clone();
		rotatedDrilling.setX(rsv.getX());
		rotatedDrilling.setY(rsv.getY());
		rotatedDrilling.setZ(rsv.getZ());
		rotatedDrilling.setEndX(rev.getX());
		rotatedDrilling.setEndY(rev.getY());
		rotatedDrilling.setAngleZ(rotatedDrilling.getAngleZ() + angleInDegrees);

		return rotatedDrilling;
	}

	private List<ImosProfile> rotateProfiles(List<ImosProfile> profiles) {
		List<ImosProfile> rotatedProfiles = new ArrayList<ImosProfile>();
		for (ImosProfile profile : profiles) {
			ImosProfile rotatedProfile = rotateProfile(profile);
			rotatedProfiles.add(rotatedProfile);
		}
		return rotatedProfiles;
	}

	private ImosProfile rotateProfile(ImosProfile profile) {
		ImosProfile rotatedProfile = profile.clone();
		int prfNo = profile.getPrfNo();
		prfNo -= getRotateProfileDifference();
		if (prfNo < 1) {
			prfNo += 4;
		}
		rotatedProfile.setPrfNo(prfNo);
		return rotatedProfile;
	}

	protected int getRotateProfileDifference() {
		return 1;
	}

}
