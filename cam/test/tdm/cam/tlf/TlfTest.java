package tdm.cam.tlf;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import tdm.cam.TlfAssert;
import tdm.cam.imos.ImosPart;
import tdm.cam.tlf.imos2tlf.Imos2TlfConverter;
import tdm.cam.util.TextFileWriter;

import com.floreysoft.jmte.Engine;

public class TlfTest {

	private CamPartTestDataFactory camPartFactory;

	private Imos2TlfConverter imos2Tlf;

	private TextFileWriter textFileWriter;

	private static final String ACTUAL_PREFIX = "./tlf/test/";
	private static final String EXPECTED_PREFIX = "./test/tdm/cam/tlf/results/";

	private static final String EXPECTED_DRILLING_10 = EXPECTED_PREFIX + "drilling10.tlf";
	private static final String EXPECTED_DRILLING_10_AND_35 = EXPECTED_PREFIX + "drilling10And35.tlf";
	private static final String EXPECTED_PLANE_1_DRILLING_6 = EXPECTED_PREFIX + "plane1Drilling6.tlf";
	private static final String EXPECTED_PLANE_2_DRILLING_6 = EXPECTED_PREFIX + "plane2Drilling6.tlf";
	private static final String EXPECTED_PLANE_3_DRILLING_5 = EXPECTED_PREFIX + "plane3Drilling5.tlf";
	private static final String EXPECTED_PLANE_4_DRILLING_5 = EXPECTED_PREFIX + "plane4Drilling5.tlf";
	private static final String EXPECTED_DRILLING_10_BACKSIDE = EXPECTED_PREFIX + "drilling10_backside.tlf";
	private static final String EXPECTED_DRILLING_8_THROUGH = EXPECTED_PREFIX + "drilling8_through.tlf";
	private static final String EXPECTED_PLANE_1_AND_BACKSIDE = EXPECTED_PREFIX + "plane1FromBackside.tlf";
	private static final String EXPECTED_PLANE_2_AND_BACKSIDE = EXPECTED_PREFIX + "plane2FromBackside.tlf";
	private static final String EXPECTED_PLANE_3_AND_BACKSIDE = EXPECTED_PREFIX + "plane3FromBackside.tlf";
	private static final String EXPECTED_PLANE_4_AND_BACKSIDE = EXPECTED_PREFIX + "plane4FromBackside.tlf";

	private static final String EXPECTED_ROW_DRILLING_FRONTSIDE = EXPECTED_PREFIX + "rowDrilling_frontside.tlf";
	private static final String EXPECTED_ROW_DRILLING_BACKSIDE = EXPECTED_PREFIX + "rowDrilling_backside.tlf";

	private static final String EXPECTED_PROFILE_FRONTSIDE_BOTTOM = EXPECTED_PREFIX + "profileBottom_frontside.tlf";
	private static final String EXPECTED_PROFILE_BACKSIDE_BOTTOM = EXPECTED_PREFIX + "profileBottom_backside.tlf";;
	private static final String EXPECTED_PROFILE_FRONTSIDE_TOP = EXPECTED_PREFIX + "profileTop_frontside.tlf";
	private static final String EXPECTED_PROFILE_FRONTSIDE_LEFT = EXPECTED_PREFIX + "profileLeft_frontside.tlf";
	private static final String EXPECTED_PROFILE_FRONTSIDE_RIGHT = EXPECTED_PREFIX + "profileRight_frontside.tlf";


	@Before
	public void setup() {
		File actualDir = new File(ACTUAL_PREFIX);
		if (!actualDir.exists()) {
			actualDir.mkdir();
		}
		camPartFactory = new CamPartTestDataFactory();
		imos2Tlf = new Imos2TlfConverter();
		textFileWriter = new TextFileWriter();
	}

	private List<String> writeOutputFiles(Collection<TlfDocument> docs) {
		List<String> fileNames = new ArrayList<String>();
		for (TlfDocument doc : docs) {
			String fileName = ACTUAL_PREFIX + doc.getName();
			textFileWriter.writeEscapedTlf(doc.getTlf(), fileName);
			fileNames.add(fileName);
		}
		return fileNames;
	}

	@Test
	public void drilling10Test() {
		ImosPart camPart = camPartFactory.createDrilling10CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_DRILLING_10, fileName);
	}

	@Test
	public void drilling10BacksideTest() {
		ImosPart camPart = camPartFactory.createDrilling10BacksideCamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);
		TlfAssert.assertTrue(fileName.contains("_backside"));

		TlfAssert.assertFileEquals(EXPECTED_DRILLING_10_BACKSIDE, fileName);
	}

	@Test
	public void drilling8ThroughBacksideTest() {
		ImosPart camPart = camPartFactory.createDrilling8ThroughBacksideCamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);
		TlfAssert.assertTrue(fileName.contains("_frontside")); // only through drilling from backside -> same as frontside

		TlfAssert.assertFileEquals(EXPECTED_DRILLING_8_THROUGH, fileName);
	}

	@Test
	public void drilling10and35Test() {
		ImosPart camPart = camPartFactory.createDrilling10And35CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_DRILLING_10_AND_35, fileName);
	}

	@Test
	public void plane1Drilling6Test() {
		ImosPart camPart = camPartFactory.createPlane1Drilling6CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_1_DRILLING_6, fileName);
	}

	@Test
	public void plane2Drilling6Test() {
		ImosPart camPart = camPartFactory.createPlane2Drilling6CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_2_DRILLING_6, fileName);
	}

	@Test
	public void plane3Drilling5Test() {
		ImosPart camPart = camPartFactory.createPlane3Drilling5CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_3_DRILLING_5, fileName);
	}

	@Test
	public void plane4Drilling5Test() {
		ImosPart camPart = camPartFactory.createPlane4Drilling5CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_4_DRILLING_5, fileName);
	}

	@Test
	public void drilling10BacksideAndPlane1Drilling5Test() {
		ImosPart camPart = camPartFactory.createDrilling10BacksideAndPlane1Drilling5CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_1_AND_BACKSIDE, fileName);
	}

	@Test
	public void drilling10BacksideAndPlane2Drilling5Test() {
		ImosPart camPart = camPartFactory.createDrilling10BacksideAndPlane2Drilling5CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_2_AND_BACKSIDE, fileName);
	}

	@Test
	public void drilling10BacksideAndPlane3Drilling5Test() {
		ImosPart camPart = camPartFactory.createDrilling10BacksideAndPlane3Drilling5CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_3_AND_BACKSIDE, fileName);
	}

	@Test
	public void drilling10BacksideAndPlane4Drilling5Test() {
		ImosPart camPart = camPartFactory.createDrilling10BacksideAndPlane4Drilling5CamPart();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PLANE_4_AND_BACKSIDE, fileName);
	}

	@Test
	public void rowDrillingFrontsideTest() {
		ImosPart camPart = camPartFactory.createRowDrilling6Frontside();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_ROW_DRILLING_FRONTSIDE, fileName);
	}

	@Test
	public void rowDrillingBacksideTest() {
		ImosPart camPart = camPartFactory.createRowDrilling6Backside();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_ROW_DRILLING_BACKSIDE, fileName);
	}

	@Test
	public void profileBottomTest() {
		ImosPart camPart = camPartFactory.createProfileBottom();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PROFILE_FRONTSIDE_BOTTOM, fileName);
	}

	@Test
	public void profileBottomBackSideTest() {
		ImosPart camPart = camPartFactory.createProfileBottomBackSide();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);
		TlfAssert.assertTrue(fileName.contains("_backside"));
		
		TlfAssert.assertFileEquals(EXPECTED_PROFILE_BACKSIDE_BOTTOM, fileName);
	}

	@Test
	public void profileTopTest() {
		ImosPart camPart = camPartFactory.createProfileTop();

		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PROFILE_FRONTSIDE_TOP, fileName);
	}

	@Test
	public void profileLeftTest() {
		ImosPart camPart = camPartFactory.createProfileLeft();
		
		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PROFILE_FRONTSIDE_LEFT, fileName);
	}

	@Test
	public void profileRightTest() {
		ImosPart camPart = camPartFactory.createProfileRight();
		TlfPart tlfPart = imos2Tlf.convert(camPart);
		List<TlfDocument> docs = tlfPart.createTlfDocuments();
		TlfAssert.assertEquals(1, docs.size());
		String fileName = writeOutputFiles(docs).get(0);

		TlfAssert.assertFileEquals(EXPECTED_PROFILE_FRONTSIDE_RIGHT, fileName);
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
