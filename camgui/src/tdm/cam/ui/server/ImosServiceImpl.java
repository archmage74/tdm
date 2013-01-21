package tdm.cam.ui.server;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import tdm.cam.model.imos.ImosProject;
import tdm.cam.ui.RestClient;
import tdm.cam.ui.RestParameters;
import tdm.cam.ui.client.ImosService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ImosServiceImpl extends RemoteServiceServlet implements ImosService {

	public static final String IMOS_SERVICE = "imos";
	
	private RestClient imosClient = new RestClient("http://localhost:8080/camrest");
	
//	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public ImosServiceImpl() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ImosProject.class);
//			marshaller = jaxbContext.createMarshaller();
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException("Could not create jaxb environment", e);
		}
	}
	
	@Override
	public ImosProject readProject(String orderId) {
		
		RestParameters params = new RestParameters().addParam(orderId);
		InputStream ris = imosClient.doRequest(IMOS_SERVICE, params.getParams());
		
		ImosProject project;
		try {
			project = (ImosProject) unmarshaller.unmarshal(ris);
		} catch (JAXBException e) {
			// FIXME report error
			throw new RuntimeException(e);
		}
		
		return project;
	}

}
