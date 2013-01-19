package tdm.cam.imos;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import tdm.cam.tlf.imos2tlf.TlfDrillingTemplate;

public class DrillParser {

	public static final String DRILL_PARSER_PROPERTIES = "/drills.properties";

	private static final String PROP_DRILLS = "drills";
	private static final String PROP_PREFIX_DRILL = "drill";
	private static final String PROP_VELENTRATA = "VELENTRATA";
	private static final String PROP_TIPOUNTA = "TIPOUNTA";
	private static final String PROP_RALLFORO = "RALLFORO";

	public Map<String, TlfDrillingTemplate> readDrillConfiguration() {
		Properties drillProperties = new Properties();
		try {
			InputStream propertiesStream = this.getClass().getResourceAsStream(DRILL_PARSER_PROPERTIES);
			drillProperties.load(propertiesStream);
		} catch (IOException e) {
			throw new RuntimeException("could not load " + DRILL_PARSER_PROPERTIES, e);
		}
		return parseProperties(drillProperties);
	}

	private Map<String, TlfDrillingTemplate> parseProperties(Properties drillProperties) {
		Map<String, TlfDrillingTemplate> templates = new HashMap<String, TlfDrillingTemplate>();

		String drillProp = drillProperties.getProperty(PROP_DRILLS);
		StringTokenizer tokenizer = new StringTokenizer(drillProp, ",");

		while (tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken();
			TlfDrillingTemplate template = createTemplate(token, drillProperties);
			templates.put(token, template);
		}

		return templates;
	}

	private TlfDrillingTemplate createTemplate(String diameterKey, Properties drillProperties) {
		Double diameter = parseDiameter(diameterKey);

		int rallforo = parseParameter(diameterKey, PROP_RALLFORO, drillProperties);
		int tipounta = parseParameter(diameterKey, PROP_TIPOUNTA, drillProperties);
		int velentrata = parseParameter(diameterKey, PROP_VELENTRATA, drillProperties);
		TlfDrillingTemplate template = new TlfDrillingTemplate();
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
		if (diameterKey.charAt(0) == TlfDrillingTemplate.PREFIX_THROUGH) {
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
