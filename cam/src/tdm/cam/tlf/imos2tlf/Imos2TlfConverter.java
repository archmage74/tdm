package tdm.cam.tlf.imos2tlf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tdm.cam.export.transform.TransformationService;
import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProfile;
import tdm.cam.model.imos.ImosProject;
import tdm.cam.model.imos.PartRotator;
import tdm.cam.model.math.Epsilon;
import tdm.cam.model.math.Matrix3x3;
import tdm.cam.model.math.Plane;
import tdm.cam.model.math.PlaneHelper;
import tdm.cam.model.math.RotationMatrixFactory;
import tdm.cam.tlf.TlfDocument;
import tdm.cam.tlf.TlfDrilling;
import tdm.cam.tlf.TlfFrontSide;
import tdm.cam.tlf.TlfPart;
import tdm.cam.tlf.TlfPartSide;
import tdm.cam.tlf.TlfProfile;
import tdm.cam.tlf.imos2tlf.filter.DrillingFilter;
import tdm.cam.tlf.imos2tlf.filter.HorizontalFailFilter;
import tdm.cam.tlf.imos2tlf.filter.NonThroughUpFailFilter;
import tdm.cam.tlf.imos2tlf.filter.ThroughFailFilter;

public class Imos2TlfConverter {

	private TlfPartFactory partFactory;
	private TlfDrillingFactory drillingFactory;
	private TlfProfileFactory profileFactory;
	private TransformationService transformationService;
	
	private RotationMatrixFactory rotationMatrixFactory;
	private PartRotator partRotator;
	
	public Imos2TlfConverter() {
		this.partFactory = new TlfPartFactory();
		this.drillingFactory = new TlfDrillingFactory();
		this.drillingFactory.init();
		this.profileFactory = new TlfProfileFactory();
		this.transformationService = new TransformationService();
		this.rotationMatrixFactory = new RotationMatrixFactory();
		this.partRotator = new PartRotator();
	}

	public TlfPart convert(ImosPart imosPart) {
		TlfPart tlfPart = partFactory.createTlfPart(imosPart);

		// frontside
//		Matrix3x3 turnOver = rotationMatrixFactory.createXRotationInDegrees(180);
//		partRotator.rotatePart(imosPart, turnOver);
		
		int numDrillingsUp = countNonThroughUpDrillings(imosPart.getDrillings(), imosPart.getDimensions().getThick());
		int numDrillingsDown = countNonThroughDownDrillings(imosPart.getDrillings(), imosPart.getDimensions().getThick());
		
		// down > 0 -> vollgas front
		// beide 0 -> vollgas front
		// nur down 0 -> vollgas back
		List<DrillingFilter> frontSideFilters = new ArrayList<DrillingFilter>();
		List<DrillingFilter> backSideFilters = new ArrayList<DrillingFilter>();

		if (numDrillingsDown == 0 && numDrillingsUp != 0) {
			frontSideFilters.add(new NonThroughUpFailFilter(imosPart.getDimensions().getThick()));
			frontSideFilters.add(new ThroughFailFilter(imosPart.getDimensions().getThick()));
			frontSideFilters.add(new HorizontalFailFilter());

			backSideFilters.add(new NonThroughUpFailFilter(imosPart.getDimensions().getThick()));
		} else {
			frontSideFilters.add(new NonThroughUpFailFilter(imosPart.getDimensions().getThick()));
			
			backSideFilters.add(new NonThroughUpFailFilter(imosPart.getDimensions().getThick()));
			backSideFilters.add(new ThroughFailFilter(imosPart.getDimensions().getThick()));
			backSideFilters.add(new HorizontalFailFilter());
		}
			
		TlfPartSide frontside = createSide(TlfDocument.FRONT_SIDE_SUFFIX, imosPart, frontSideFilters);
		tlfPart.addSide(frontside);

		// backside
		Matrix3x3 toBackSide = rotationMatrixFactory.createYRotationInDegrees(180);
		partRotator.rotatePart(imosPart, toBackSide);
		
		TlfPartSide backside = createSide(TlfDocument.BACK_SIDE_SUFFIX, imosPart, backSideFilters);
		tlfPart.addSide(backside);
		
		return tlfPart;
	}

	private TlfPartSide createSide(String sideName, ImosPart imosPart, List<DrillingFilter> frontSideFilters) {
		TlfFrontSide side = new TlfFrontSide(sideName, imosPart.getDimensions());
		addDrillingsFiltered(side, imosPart, frontSideFilters);
		for (ImosProfile imosProfile : imosPart.getProfiles()) {
			TlfProfile tlfSideProfile = profileFactory.createProfile(imosProfile);
			side.addNode(tlfSideProfile);
		}
		return side;
	}
	
	private void addDrillingsFiltered(TlfFrontSide side, ImosPart imosPart, List<DrillingFilter> filters) {
		for (ImosDrilling imosDrilling : imosPart.getDrillings()) {
			ImosDrilling filteredDrilling = applyFilters(imosDrilling, filters);
			if (filteredDrilling != null) {
				TlfDrilling tlfDrilling = drillingFactory.createDrilling(side.getDimensions(), filteredDrilling);
				addDrilling(side, tlfDrilling);
			}
		}
	}

	private ImosDrilling applyFilters(ImosDrilling drilling, List<DrillingFilter> filters) {
		for (DrillingFilter filter : filters) {
			drilling = filter.filter(drilling);
			if (drilling == null) {
				return null;
			}
		}
		return drilling;
	}
	
	private void addDrilling(TlfFrontSide frontSide, TlfDrilling drilling) {
		Plane plane = PlaneHelper.getInstance().getPlaneForDirection(drilling.getDirection());
		if (plane == Plane.FRONT) {
			frontSide.addNode(drilling);
		} else if (plane == Plane.BACK) {
			frontSide.addNode(drilling);
		} else if (plane == Plane.TOP) {
			frontSide.addPlane1Drilling(drilling);
		} else if (plane == Plane.BOTTOM) {
			frontSide.addPlane2Drilling(drilling);
		} else if (plane == Plane.LEFT) {
			frontSide.addPlane3Drilling(drilling);
		} else if (plane == Plane.RIGHT) {
			frontSide.addPlane4Drilling(drilling);
		} else {
			// throw new DrillingAngleException(drilling.getAngleX(), drilling.getAngleY(), drilling.getAngleZ());
			System.out.println("Schraegbohrung gefunden, wird ignoriert");
		}
	}

	private int countNonThroughUpDrillings(List<ImosDrilling> drillings, double thick) {
		return countNonThroughDrillings(drillings, thick, 1);
	}
	
	private int countNonThroughDownDrillings(List<ImosDrilling> drillings, double thick) {
		return countNonThroughDrillings(drillings, thick, -1);
	}

	private int countNonThroughDrillings(List<ImosDrilling> drillings, double thick, double zDir) {
		int count = 0;
		for (ImosDrilling drilling : drillings) {
			if (Epsilon.equals(drilling.getDirection().getZ(), zDir) && !Epsilon.greaterOrEqual(drilling.getDeep(), thick)) {
				count ++;
			}
		}
		return count;
	}

	public Collection<TlfPart> convert(Collection<ImosPart> imosParts) {
		return convert(imosParts, new HashMap<String, Integer>());
	}
		
	public Collection<TlfPart> convert(Collection<ImosPart> imosParts, Map<String, Integer> rotationMap) {
		transformationService.rotate(imosParts, rotationMap);

		List<TlfPart> tlfParts = new ArrayList<TlfPart>();
		
		for (ImosPart imosPart : imosParts) {
			tlfParts.add(convert(imosPart));
		}
		
		return tlfParts;
	}

	public Collection<TlfPart> convert(ImosProject imosProject, Map<String, Integer> rotationMap) {
		return convert(imosProject.getParts(), rotationMap);
	}
}
