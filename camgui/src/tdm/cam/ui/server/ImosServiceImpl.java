package tdm.cam.ui.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import tdm.cam.model.cmd.RotationList;
import tdm.cam.model.imos.ImosProject;
import tdm.cam.ui.RestClient;
import tdm.cam.ui.RestParameters;
import tdm.cam.ui.client.ImosService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ImosServiceImpl extends RemoteServiceServlet implements ImosService {

	public static final String IMOS_SERVICE = "imos";

	private static final String EXPORT_TLF_SERVICE = "tlf";
	
	private RestClient imosClient = new RestClient("http://localhost:8080/camrest");
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public ImosServiceImpl() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ImosProject.class, RotationList.class);
			marshaller = jaxbContext.createMarshaller();
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException("Could not create jaxb environment", e);
		}
	}
	
	@Override
	public ImosProject readProject(String orderId) {
		RestParameters params = new RestParameters().addParam(orderId);
		InputStream ris = imosClient.doGetRequest(IMOS_SERVICE, params.getParams());
		
		ImosProject project;
		try {
			project = (ImosProject) unmarshaller.unmarshal(ris);
		} catch (JAXBException e) {
			// FIXME report error
			throw new RuntimeException(e);
		}
		
		return project;
	}

	@Override
	public void exportTlf(String orderId, RotationList rotationList) {
		RestParameters params = new RestParameters().addParam(orderId);
		Map<String, String> postParams = new HashMap<String, String>();
		postParams.put("rotations", createRotationMapParam(rotationList));
		InputStream ris = imosClient.doPostRequest(EXPORT_TLF_SERVICE, params.getParams(), postParams);
		try {
			ris.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String createRotationMapParam(RotationList rotationList) {
		StringWriter sw = new StringWriter();
		try {
			marshaller.marshal(rotationList, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return sw.toString();
	}
	
}
