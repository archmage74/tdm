package tdm.cam.tlf;

import java.util.Map;

import tdm.cam.db.DrillParser;

public class CamPartTestDataFactory {

	Map<String, DrillingTemplate> drillTemplates;
	
	public CamPartTestDataFactory() {
		DrillParser parser = new DrillParser();
		drillTemplates = parser.readDrillConfiguration();
	}
	
	public CamPart createDrilling10CamPart() {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode("12341001");
		
		Drilling drilling = drillTemplates.get(10).createDrilling(camPart.getDimensions());
		drilling.setX(74);
		drilling.setY(378);
		drilling.setZ(0);
		drilling.setAngleX(0);
		drilling.setAngleY(0);
		drilling.setAngleZ(180);
		drilling.setDeep(11);
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createDrilling10And35CamPart() {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode("12341001");
		
		int entityIndex = 0;
		
		Drilling drilling35A = drillTemplates.get(35).createDrilling(camPart.getDimensions());
		drilling35A.setX(74);
		drilling35A.setY(330);
		drilling35A.setZ(0);
		drilling35A.setAngleX(0);
		drilling35A.setAngleY(0);
		drilling35A.setAngleZ(180);
		drilling35A.setDeep(14);
		drilling35A.setIndex(entityIndex++);
		camPart.addDrilling(drilling35A);
		
		Drilling drilling10A = drillTemplates.get(10).createDrilling(camPart.getDimensions());
		drilling10A.setX(74);
		drilling10A.setY(378);
		drilling10A.setZ(0);
		drilling10A.setAngleX(0);
		drilling10A.setAngleY(0);
		drilling10A.setAngleZ(180);
		drilling10A.setDeep(11);
		drilling10A.setIndex(entityIndex++);
		camPart.addDrilling(drilling10A);
		
		Drilling drilling10B = drillTemplates.get(10).createDrilling(camPart.getDimensions());
		drilling10B.setX(124);
		drilling10B.setY(378);
		drilling10B.setZ(0);
		drilling10B.setAngleX(0);
		drilling10B.setAngleY(0);
		drilling10B.setAngleZ(180);
		drilling10B.setDeep(11);
		drilling10B.setIndex(entityIndex++);
		camPart.addDrilling(drilling10B);

		return camPart;
	}
}
