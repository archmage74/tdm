package tdm.cam.tlf;

import java.util.HashMap;
import java.util.Map;

import tdm.cam.export.NamedDecimalRenderer;
import tdm.cam.export.NamedIntRenderer;
import tdm.cam.util.TextFileReader;

import com.floreysoft.jmte.Engine;

public class TlfEngine {

	private TextFileReader textFileReader = new TextFileReader();
	
	private Map<String, String> templates = new HashMap<String, String>();
	
	private Engine engine;
	
	public TlfEngine() {
		engine = new Engine();
		engine.registerNamedRenderer(new NamedIntRenderer());
		engine.registerNamedRenderer(new NamedDecimalRenderer());
	}
	
	public String transform(String templateName, Map<String, Object> model) {
		String template = getTemplateByName(templateName);
		return engine.transform(template, model);
	}
	
	private String getTemplateByName(String templateName) {
		String template = templates.get(templateName);
		if (template == null) {
			template = textFileReader.readTemplate(templateName);
			templates.put(templateName, template);
		}
		return template;
	}
	
}
