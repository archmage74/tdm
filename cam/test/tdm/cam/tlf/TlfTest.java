package tdm.cam.tlf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import tdm.cam.util.TextFileWriter;

import com.floreysoft.jmte.Engine;


public class TlfTest {

	CamPartTestDataFactory camPartFactory;
	
	@Before
	public void setup() {
		camPartFactory = new CamPartTestDataFactory();
	}
	
	@Test
	public void singleDrillingTest() {
		
		CamPart camPart = camPartFactory.createDrilling10CamPart();
		
		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();
		for (TlfDocument doc : docs) {
			textFileWriter.printEscapedTlf(doc.getTlf(), System.out);
			textFileWriter.writeEscapedTlf(doc.getTlf(), "./tlf/test/" + doc.getName());
		}
	}

	@Test 
	public void Drilling10and35Test() {
		CamPart camPart = camPartFactory.createDrilling10And35CamPart();
		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();
		for (TlfDocument doc : docs) {
			textFileWriter.printEscapedTlf(doc.getTlf(), System.out);
			textFileWriter.writeEscapedTlf(doc.getTlf(), "./tlf/test/" + doc.getName());
		}
	}
	
	@Test
	public void jmteEngineTest() {
		 String input = "${name}";
		 Map<String, Object> model = new HashMap<String, Object>();
		 model.put("name", "Minimal Template Engine");
		 Engine engine = new Engine();
		 String transformed = engine.transform(input, model);
		 Assert.assertEquals(transformed, "Minimal Template Engine");
	}
	
}
