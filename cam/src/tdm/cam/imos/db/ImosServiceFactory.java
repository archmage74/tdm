package tdm.cam.imos.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ImosServiceFactory {

	private static final String IMOS_SERVICE_PROPERTIES = "/imosService.properties";

	private static final String IMOS_SERVICE_CLASS = "imosServiceClass";

	private static ImosServiceFactory imosServiceFactory;

	private static IImosService imosService = null;

	public static ImosServiceFactory getInstance() {
		if (imosServiceFactory == null) {
			imosServiceFactory = new ImosServiceFactory();
			imosServiceFactory.init();
		}
		return imosServiceFactory;
	}

	private ImosServiceFactory() {

	}

	public IImosService getImosService() {
		return imosService;
	}
	
	private void init() {
		Properties properties = new Properties();
		InputStream propertiesStream = this.getClass().getResourceAsStream(IMOS_SERVICE_PROPERTIES);
		if (propertiesStream == null) {
			properties.put(IMOS_SERVICE_CLASS, ImosService.class.getName());
		} else {
			try {
				properties.load(propertiesStream);
			} catch (IOException e) {
				throw new RuntimeException("Error while loading " + IMOS_SERVICE_PROPERTIES, e);
			}
		}

		String className = properties.getProperty(IMOS_SERVICE_CLASS);
		try {
			imosService = (IImosService) Class.forName(className).newInstance();
			imosService.init();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Error while instantiating ImosService with class-name='" + className + "'", e);
		}
	}

}
