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
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());

		Drilling drilling = new TestDrillingParameters().create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createDrilling10BacksideCamPart() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		Drilling drilling = new TestDrillingParameters().z(19).angleX(180).angleZ(180).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createDrilling10ThroughBacksideCamPart() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		Drilling drilling = new TestDrillingParameters().z(19).angleX(180).angleZ(180).deep(19).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createDrilling10And35CamPart() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		Drilling drilling35A = new TestDrillingParameters().drill("35").x(74).y(330).angleZ(180).deep(14).create();
		camPart.addDrilling(drilling35A);
		Drilling drilling10A = new TestDrillingParameters().drill("10").create();
		camPart.addDrilling(drilling10A);
		Drilling drilling10B = new TestDrillingParameters().drill("10").x(124).create();
		camPart.addDrilling(drilling10B);

		return camPart;
	}

	public CamPart createPlane1Drilling6CamPart() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		Drilling drilling = new TestDrillingParameters().drill("6").x(100).y(404).z(8).angleX(-90).deep(11).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createPlane2Drilling6CamPart() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		Drilling drilling = new TestDrillingParameters().drill("6").x(100).y(0).z(8).angleX(-90).angleZ(180).deep(11).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	

	public CamPart createPlane3Drilling5CamPart() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		Drilling drilling = new TestDrillingParameters().drill("5").x(0).y(50).z(8).angleX(-90).angleZ(-90).deep(11).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createDrilling10BacksideAndPlane3Drilling5CamPart() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		Drilling drilling5 = new TestDrillingParameters().drill("5").x(0).y(50).z(8).angleX(-90).angleZ(-90).deep(15).create();
		camPart.addDrilling(drilling5);
		Drilling drilling10 = new TestDrillingParameters().drill("10").z(19).angleX(180).angleZ(180).deep(11).create();
		camPart.addDrilling(drilling10);

		return camPart;
	}
	
	public CamPart createPlane4Drilling5CamPart() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		Drilling drilling = new TestDrillingParameters().drill("5").x(100).y(0).z(8).angleX(-90).angleZ(90).deep(11).create();
		camPart.addDrilling(drilling);
		return camPart;
	}
	
	public CamPart createRowDrilling6Frontside() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		RowDrilling drilling = new TestRowDrillingParameters().create();
		camPart.addDrilling(drilling);
		return camPart;
	}

	public CamPart createRowDrilling6Backside() {
		CamPart camPart = createCamPart(Thread.currentThread().getStackTrace()[0].getMethodName());
		
		RowDrilling drilling = new TestRowDrillingParameters().angleX(180).create();
		camPart.addDrilling(drilling);
		return camPart;
	}

	private CamPart createCamPart(String barcode) {
		CamPart camPart = new CamPart();
		camPart.setLength(714.0);
		camPart.setWidth(404.0);
		camPart.setThick(19.0);
		camPart.setBarcode(barcode);
		return camPart;
	}
	
}
