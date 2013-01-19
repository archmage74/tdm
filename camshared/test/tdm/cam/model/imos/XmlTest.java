package tdm.cam.model.imos;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import tdm.cam.math.Dimensions;

public class XmlTest {

	@Test
	public void xmlTest() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ImosProject.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.marshal(createProject(), System.out);
	}

	private ImosProject createProject() {
		ImosProject project = new ImosProject();
		project.setName("xmlTest");
		
		ImosPart part = new ImosPart();
		part.setDimensions(new Dimensions(714.0, 404.0, 19.0));
		part.setBarcode(Thread.currentThread().getStackTrace()[2].getMethodName());
		
		ImosDrilling drilling5 = new TestDrillingParameters().diameter(5).x(100).y(404).z(8).angleX(-90).deep(15).create();
		part.addDrilling(drilling5);
		ImosDrilling drilling10 = new TestDrillingParameters().diameter(10).z(19).angleX(180).angleZ(180).deep(11).create();
		part.addDrilling(drilling10);
		ImosProfile profile = new TestProfileParameters().prfNo(ProfileType.POS_R.getValue()).prfLen(404).create();
		part.addProfile(profile);
		
		project.addPart(part);

		return project;
	}

}
