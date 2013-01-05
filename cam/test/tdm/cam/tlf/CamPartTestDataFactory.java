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
		camPart.setBarcode("createDrilling10CamPart");
		
		DrillingTemplate dt = drillTemplates.get("10");
		Drilling drilling = dt.createDrilling(camPart.getDimensions());
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
	
	public CamPart createDrilling10BacksideCamPart() {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode("createDrilling10BacksideCamPart");
		
		DrillingTemplate dt = drillTemplates.get("10");
		Drilling drilling = dt.createDrilling(camPart.getDimensions());
		drilling.setX(74);
		drilling.setY(378);
		drilling.setZ(19);
		drilling.setAngleX(180);
		drilling.setAngleY(0);
		drilling.setAngleZ(180);
		drilling.setDeep(11);
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createDrilling10ThroughBacksideCamPart() {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode("createDrilling10ThroughBacksideCamPart");
		
		DrillingTemplate dt = drillTemplates.get("10");
		Drilling drilling = dt.createDrilling(camPart.getDimensions());
		drilling.setX(74);
		drilling.setY(378);
		drilling.setZ(0);
		drilling.setAngleX(180);
		drilling.setAngleY(0);
		drilling.setAngleZ(180);
		drilling.setDeep(19);
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createDrilling10And35CamPart() {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode("createDrilling10And35CamPart");
		
		int entityIndex = 0;
		
		Drilling drilling35A = drillTemplates.get("35").createDrilling(camPart.getDimensions());
		drilling35A.setX(74);
		drilling35A.setY(330);
		drilling35A.setZ(0);
		drilling35A.setAngleX(0);
		drilling35A.setAngleY(0);
		drilling35A.setAngleZ(180);
		drilling35A.setDeep(14);
		drilling35A.setIndex(entityIndex++);
		camPart.addDrilling(drilling35A);
		
		Drilling drilling10A = drillTemplates.get("10").createDrilling(camPart.getDimensions());
		drilling10A.setX(74);
		drilling10A.setY(378);
		drilling10A.setZ(0);
		drilling10A.setAngleX(0);
		drilling10A.setAngleY(0);
		drilling10A.setAngleZ(180);
		drilling10A.setDeep(11);
		drilling10A.setIndex(entityIndex++);
		camPart.addDrilling(drilling10A);
		
		Drilling drilling10B = drillTemplates.get("10").createDrilling(camPart.getDimensions());
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

	public CamPart createPlane1Drilling6CamPart() {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode("createPlane1Drilling6CamPart");
		
		DrillingTemplate dt = drillTemplates.get("6");
		Drilling drilling = dt.createDrilling(camPart.getDimensions());
		drilling.setX(100);
		drilling.setY(404);
		drilling.setZ(8);
		drilling.setAngleX(-90);
		drilling.setAngleY(0);
		drilling.setAngleZ(0);
		drilling.setDeep(11);
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createPlane2Drilling6CamPart() {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode("createPlane2Drilling6CamPart");
		
		DrillingTemplate dt = drillTemplates.get("6");
		Drilling drilling = dt.createDrilling(camPart.getDimensions());
		drilling.setX(100);
		drilling.setY(0);
		drilling.setZ(8);
		drilling.setAngleX(-90);
		drilling.setAngleY(0);
		drilling.setAngleZ(180);
		drilling.setDeep(11);
		camPart.addDrilling(drilling);
		return camPart;
	}
	

	public CamPart createPlane3Drilling5CamPart() {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode("createPlane3Drilling5CamPart");
		
		DrillingTemplate dt = drillTemplates.get("5");
		Drilling drilling = dt.createDrilling(camPart.getDimensions());
		drilling.setX(0);
		drilling.setY(50);
		drilling.setZ(8);
		drilling.setAngleX(-90);
		drilling.setAngleY(0);
		drilling.setAngleZ(-90);
		drilling.setDeep(11);
		camPart.addDrilling(drilling);
		return camPart;
	}
	

	public CamPart createPlane4Drilling5CamPart() {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode("createPlane4Drilling5CamPart");
		
		DrillingTemplate dt = drillTemplates.get("5");
		Drilling drilling = dt.createDrilling(camPart.getDimensions());
		drilling.setX(100);
		drilling.setY(0);
		drilling.setZ(8);
		drilling.setAngleX(-90);
		drilling.setAngleY(0);
		drilling.setAngleZ(90);
		drilling.setDeep(11);
		camPart.addDrilling(drilling);
		return camPart;
	}
	


}
