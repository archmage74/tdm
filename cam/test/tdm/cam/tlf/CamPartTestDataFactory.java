package tdm.cam.tlf;

import java.util.Map;

import tdm.cam.imos.DrillParser;
import tdm.cam.imos.ImosDrilling;
import tdm.cam.imos.ImosPart;
import tdm.cam.imos.ImosProfile;
import tdm.cam.math.Dimensions;
import tdm.cam.tlf.imos2tlf.TlfDrillingTemplate;

public class CamPartTestDataFactory {

	Map<String, TlfDrillingTemplate> drillTemplates;
	
	public CamPartTestDataFactory() {
		DrillParser parser = new DrillParser();
		drillTemplates = parser.readDrillConfiguration();
	}

	public ImosPart createDrilling10CamPart() {
		ImosPart camPart = createCamPart();

		ImosDrilling drilling = new TestDrillingParameters().create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public ImosPart createDrilling10BacksideCamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling = new TestDrillingParameters().z(19).angleX(180).angleZ(180).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public ImosPart createDrilling8ThroughBacksideCamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling = new TestDrillingParameters().z(19).angleX(180).angleZ(180).diameter(8).deep(19).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public ImosPart createDrilling10And35CamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling35A = new TestDrillingParameters().diameter(35).x(74).y(330).angleZ(180).deep(14).create();
		camPart.addDrilling(drilling35A);
		ImosDrilling drilling10A = new TestDrillingParameters().diameter(10).create();
		camPart.addDrilling(drilling10A);
		ImosDrilling drilling10B = new TestDrillingParameters().diameter(10).x(124).create();
		camPart.addDrilling(drilling10B);

		return camPart;
	}

	public ImosPart createPlane1Drilling6CamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling = new TestDrillingParameters().diameter(6).x(100).y(404).z(8).angleX(-90).deep(25).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public ImosPart createPlane2Drilling6CamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling = new TestDrillingParameters().diameter(6).x(100).y(0).z(8).angleX(-90).angleZ(180).deep(11).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	

	public ImosPart createPlane3Drilling5CamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling = new TestDrillingParameters().diameter(5).x(0).y(50).z(8).angleX(-90).angleZ(-90).deep(11).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public ImosPart createPlane4Drilling5CamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling = new TestDrillingParameters().diameter(5).x(0).y(50).z(8).angleX(-90).angleZ(90).deep(11).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public ImosPart createDrilling10BacksideAndPlane1Drilling5CamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling5 = new TestDrillingParameters().diameter(5).x(100).y(404).z(8).angleX(-90).deep(15).create();
		camPart.addDrilling(drilling5);
		ImosDrilling drilling10 = new TestDrillingParameters().diameter(10).z(19).angleX(180).angleZ(180).deep(11).create();
		camPart.addDrilling(drilling10);

		return camPart;
	}
	
	public ImosPart createDrilling10BacksideAndPlane2Drilling5CamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling5 = new TestDrillingParameters().diameter(5).x(100).y(0).z(8).angleX(-90).angleZ(180).deep(15).create();
		camPart.addDrilling(drilling5);
		ImosDrilling drilling10 = new TestDrillingParameters().diameter(10).z(19).angleX(180).angleZ(180).deep(11).create();
		camPart.addDrilling(drilling10);

		return camPart;
	}
	
	public ImosPart createDrilling10BacksideAndPlane3Drilling5CamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling5 = new TestDrillingParameters().diameter(5).x(714).y(50).z(8).angleX(-90).angleZ(-90).deep(15).create();
		camPart.addDrilling(drilling5);
		ImosDrilling drilling10 = new TestDrillingParameters().diameter(10).z(19).angleX(180).angleZ(180).deep(11).create();
		camPart.addDrilling(drilling10);

		return camPart;
	}
	
	public ImosPart createDrilling10BacksideAndPlane4Drilling5CamPart() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling5 = new TestDrillingParameters().diameter(5).x(0).y(50).z(8).angleX(-90).angleZ(90).deep(15).create();
		camPart.addDrilling(drilling5);
		ImosDrilling drilling10 = new TestDrillingParameters().diameter(10).z(19).angleX(180).angleZ(180).deep(11).create();
		camPart.addDrilling(drilling10);

		return camPart;
	}
	
	public ImosPart createRowDrilling6Frontside() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling = new TestDrillingParameters().x(80).y(60).endX(380).endY(200).diameter(6).numDrillings(7).create();
		camPart.addDrilling(drilling);

		return camPart;
	}

	public ImosPart createRowDrilling6Backside() {
		ImosPart camPart = createCamPart();
		
		ImosDrilling drilling = new TestDrillingParameters().angleX(180).x(80).y(60).endX(380).endY(200).diameter(6).numDrillings(7).create();
		camPart.addDrilling(drilling);
		
		return camPart;
	}
	
	public ImosPart createProfileBottom() {
		ImosPart camPart = createCamPart();

		ImosDrilling drilling = new TestDrillingParameters().create();
		camPart.addDrilling(drilling);
		ImosProfile profile = new TestProfileParameters().prfNo(TlfProfileType.POS_V.getValue()).create();
		camPart.addProfile(profile);
		
		return camPart;
	}

	public ImosPart createProfileBottomBackSide() {
		ImosPart camPart = createCamPart();

		ImosDrilling drilling = new TestDrillingParameters().angleX(180).create();
		camPart.addDrilling(drilling);
		ImosProfile profile = new TestProfileParameters().prfNo(TlfProfileType.POS_V.getValue()).create();
		camPart.addProfile(profile);
		
		return camPart;
	}

	public ImosPart createProfileTop() {
		ImosPart camPart = createCamPart();

		ImosDrilling drilling = new TestDrillingParameters().create();
		camPart.addDrilling(drilling);
		ImosProfile profile = new TestProfileParameters().prfNo(TlfProfileType.POS_H.getValue()).create();
		camPart.addProfile(profile);
		
		return camPart;
	}

	public ImosPart createProfileLeft() {
		ImosPart camPart = createCamPart();

		ImosDrilling drilling = new TestDrillingParameters().create();
		camPart.addDrilling(drilling);
		ImosProfile profile = new TestProfileParameters().prfNo(TlfProfileType.POS_L.getValue()).prfLen(404).create();
		camPart.addProfile(profile);
		
		return camPart;
	}

	public ImosPart createProfileRight() {
		ImosPart camPart = createCamPart();

		ImosDrilling drilling = new TestDrillingParameters().create();
		camPart.addDrilling(drilling);
		ImosProfile profile = new TestProfileParameters().prfNo(TlfProfileType.POS_R.getValue()).prfLen(404).create();
		camPart.addProfile(profile);
		
		return camPart;
	}

	public ImosPart createProfileRightBackSide() {
		ImosPart camPart = createCamPart();

		ImosDrilling drilling = new TestDrillingParameters().angleX(180).create();
		camPart.addDrilling(drilling);
		ImosProfile profile = new TestProfileParameters().prfNo(TlfProfileType.POS_R.getValue()).prfLen(404).create();
		camPart.addProfile(profile);
		
		return camPart;
	}

	private ImosPart createCamPart() {
		ImosPart camPart = new ImosPart();
		camPart.setDimensions(new Dimensions(714.0, 404.0, 19.0));
		camPart.setBarcode(Thread.currentThread().getStackTrace()[2].getMethodName());
		return camPart;
	}
	
}
