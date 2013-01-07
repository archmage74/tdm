package tdm.cam.tlf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import tdm.cam.TlfAssert;
import tdm.cam.util.TextFileWriter;

import com.floreysoft.jmte.Engine;

public class TlfTest {

	private CamPartTestDataFactory camPartFactory;
	
	private static final String EXPECTED_PREFIX = "./test/tdm/cam/tlf/results/";
	private static final String EXPECTED_DRILLING_10 = EXPECTED_PREFIX + "drilling10.tlf";
	private static final String EXPECTED_DRILLING_10_AND_35 = EXPECTED_PREFIX + "drilling10And35.tlf";
	private static final String EXPECTED_PLANE_1_DRILLING_6 = EXPECTED_PREFIX + "plane1Drilling6.tlf";
	private static final String EXPECTED_PLANE_2_DRILLING_6 = EXPECTED_PREFIX + "plane2Drilling6.tlf";
	private static final String EXPECTED_PLANE_3_DRILLING_5 = EXPECTED_PREFIX + "plane3Drilling5.tlf";
	private static final String EXPECTED_PLANE_4_DRILLING_5 = EXPECTED_PREFIX + "plane4Drilling5.tlf";
	private static final String EXPECTED_DRILLING_10_BACKSIDE = EXPECTED_PREFIX + "drilling10_backside.tlf";
	private static final String EXPECTED_DRILLING_10_THROUGH = EXPECTED_PREFIX + "drilling10_through.tlf";
	private static final String EXPECTED_PLANE_4_AND_BACKSIDE = EXPECTED_PREFIX + "plane3FromBackside.tlf";

	@Before
	public void setup() {
		camPartFactory = new CamPartTestDataFactory();
	}

	@Test
	public void drilling10Test() {
		CamPart camPart = camPartFactory.createDrilling10CamPart();

		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();

		TlfAssert.assertEquals(docs.size(), 1);
		TlfDocument doc = docs.get(0);

		String fileName = "./tlf/test/" + doc.getName();
		textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);

		TlfAssert.assertFileEquals(EXPECTED_DRILLING_10, fileName);
	}

	@Test
	public void drilling10BacksideTest() {
		CamPart camPart = camPartFactory.createDrilling10BacksideCamPart();

		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();

		TlfAssert.assertEquals(docs.size(), 1);
		TlfDocument doc = docs.get(0);

		String fileName = "./tlf/test/" + doc.getName();
		TlfAssert.assertTrue(fileName.contains("_backside"));
		textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);

		TlfAssert.assertFileEquals(EXPECTED_DRILLING_10_BACKSIDE, fileName);
	}

	@Test
	public void drilling10ThroughBacksideTest() {
		CamPart camPart = camPartFactory.createDrilling10ThroughBacksideCamPart();

		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();

		TlfAssert.assertEquals(docs.size(), 1);
		TlfDocument doc = docs.get(0);

		String fileName = "./tlf/test/" + doc.getName();
		TlfAssert.assertTrue(fileName.contains("_frontside")); // only through drilling from backside -> same as frontside
		textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);

		TlfAssert.assertFileEquals(EXPECTED_DRILLING_10_THROUGH, fileName); 
	}

	@Test
	public void drilling10and35Test() {
		CamPart camPart = camPartFactory.createDrilling10And35CamPart();
		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();

		TlfAssert.assertEquals(docs.size(), 1);
		TlfDocument doc = docs.get(0);

		String fileName = "./tlf/test/" + doc.getName();
		textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);

		TlfAssert.assertFileEquals(EXPECTED_DRILLING_10_AND_35, fileName);
	}

	@Test
	public void plane1Drilling6Test() {
		CamPart camPart = camPartFactory.createPlane1Drilling6CamPart();

		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();

		TlfAssert.assertEquals(docs.size(), 1);
		TlfDocument doc = docs.get(0);

		String fileName = "./tlf/test/" + doc.getName();
		textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_1_DRILLING_6, fileName);
	}
	
	@Test
	public void plane2Drilling6Test() {
		CamPart camPart = camPartFactory.createPlane2Drilling6CamPart();

		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();

		TlfAssert.assertEquals(docs.size(), 1);
		TlfDocument doc = docs.get(0);

		String fileName = "./tlf/test/" + doc.getName();
		textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_2_DRILLING_6, fileName);
	}
	
	@Test
	public void plane3Drilling5Test() {
		CamPart camPart = camPartFactory.createPlane3Drilling5CamPart();

		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();

		TlfAssert.assertEquals(docs.size(), 1);
		TlfDocument doc = docs.get(0);

		String fileName = "./tlf/test/" + doc.getName();
		textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_3_DRILLING_5, fileName);
	}
	
	@Test
	public void plane4Drilling5Test() {
		CamPart camPart = camPartFactory.createPlane4Drilling5CamPart();

		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();

		TlfAssert.assertEquals(docs.size(), 1);
		TlfDocument doc = docs.get(0);

		String fileName = "./tlf/test/" + doc.getName();
		textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_4_DRILLING_5, fileName);
	}
	
	@Test
	public void drilling10BacksideAndPlane3Drilling5Test() {
		CamPart camPart = camPartFactory.createDrilling10BacksideAndPlane3Drilling5CamPart();

		TlfEngine engine = new TlfEngine();
		List<TlfDocument> docs = engine.createTlfDocuments(camPart);
		TextFileWriter textFileWriter = new TextFileWriter();

		TlfAssert.assertEquals(docs.size(), 1);
		TlfDocument doc = docs.get(0);

		String fileName = "./tlf/test/" + doc.getName();
		textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_4_AND_BACKSIDE, fileName);
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
