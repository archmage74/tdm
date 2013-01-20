package tdm.cam.rest.imos;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import tdm.cam.imos.db.IImosService;
import tdm.cam.imos.db.ImosServiceFactory;
import tdm.cam.model.imos.ImosProject;

public class ReadImosProject extends HttpServlet {

	private static final long serialVersionUID = 1349747310951767208L;

	public static final String SERVLET_NAME = "imos";

	protected IImosService imosService;

	protected Marshaller marshaller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> params = getParameters(req);
		if (params.size() != 1) {
			throw new RuntimeException("wrong amount of parameters");
		}
		String orderId = params.get(0);
		ImosProject project = imosService.readProject(orderId);
		if (project == null) {
			throw new ServletException("no project with orderId='" + orderId + "'");
		}
		
		resp.setContentType("text/xml");
		try {
			marshaller.marshal(project, resp.getOutputStream());
		} catch (JAXBException e) {
			throw new ServletException("unable to marshal project", e);
		}
	}

	protected List<String> getParameters(HttpServletRequest req) {
		String parameterString = req.getRequestURI().substring(req.getContextPath().length() + SERVLET_NAME.length() + 2);
		return Arrays.asList(parameterString.split("/"));
	}

	@Override
	public void init() throws ServletException {
		imosService = ImosServiceFactory.getInstance().getImosService();
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ImosProject.class);
			marshaller = jaxbContext.createMarshaller();
		} catch (JAXBException e) {
			throw new ServletException("unable to create jaxb environment", e);
		}
	}

	@Override
	public void destroy() {
		imosService = null;
	};

}
