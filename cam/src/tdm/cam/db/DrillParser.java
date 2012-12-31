package tdm.cam.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import tdm.cam.tlf.DrillingTemplate;

public class DrillParser {

	public static final String DRILL_CONFIG_PATH = "./config/drills.properties";

	private static final String PROP_DRILLS = "drills";
	private static final String PROP_PREFIX_DRILL = "drill";
	private static final String PROP_VELENTRATA = "VELENTRATA";
	private static final String PROP_TIPOUNTA = "TIPOUNTA";
	private static final String PROP_RALLFORO = "RALLFORO";

	public Map<String, DrillingTemplate> readDrillConfiguration() {
		Properties drillProperties = new Properties();
		try {
			drillProperties.load(new FileInputStream(DRILL_CONFIG_PATH));
		} catch (IOException e) {
			throw new RuntimeException("could not load " + DRILL_CONFIG_PATH, e);
		}
		return parseProperties(drillProperties);
	}

	private Map<String, DrillingTemplate> parseProperties(Properties drillProperties) {
		Map<String, DrillingTemplate> templates = new HashMap<String, DrillingTemplate>();

		String drillProp = drillProperties.getProperty(PROP_DRILLS);
		StringTokenizer tokenizer = new StringTokenizer(drillProp, ",");

		while (tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken();
			DrillingTemplate template = createTemplate(token, drillProperties);
			templates.put(token, template);
		}

		return templates;
	}

	private DrillingTemplate createTemplate(String diameterKey, Properties drillProperties) {
		Double diameter = parseDiameter(diameterKey);

		int rallforo = parseParameter(diameterKey, PROP_RALLFORO, drillProperties);
		int tipounta = parseParameter(diameterKey, PROP_TIPOUNTA, drillProperties);
		int velentrata = parseParameter(diameterKey, PROP_VELENTRATA, drillProperties);
		DrillingTemplate template = new DrillingTemplate();
		template.setDiameter(diameter);
		template.setParamRallforo(rallforo);
		template.setParamTipounta(tipounta);
		template.setParamVelentrata(velentrata);
		return template;
	}

	private int parseParameter(String diameterKey, String param, Properties drillProperties) {
		String key = PROP_PREFIX_DRILL + diameterKey + "." + param;
		String value = drillProperties.getProperty(key);
		int i = 0;
		try {
			i = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new RuntimeException("error while parsing drill-config: " + param + " for diameter='" + diameterKey + "' not found");
		}
		return i;
	}

	private double parseDiameter(String diameterKey) {
		Double diameter = null;
		String diameterString = new String(diameterKey);
		if (diameterKey.charAt(0) == DrillingTemplate.PREFIX_THROUGH) {
			diameterString = diameterString.substring(1);
		}
		try {
			diameter = new Double(diameterString);
		} catch (NumberFormatException e) {
			throw new RuntimeException("error while parsing drill-config: The value '" + diameterKey
					+ "' is not a valid diameter for a drill.");
		}
		return diameter;
	}

}
