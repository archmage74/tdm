package tdm.cam.rest.imos;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import tdm.cam.export.Exporter;
import tdm.cam.imos.db.IImosService;
import tdm.cam.imos.db.ImosServiceFactory;
import tdm.cam.model.cmd.Rotation;
import tdm.cam.model.cmd.RotationList;
import tdm.cam.model.imos.ImosProject;

public class ExportImosProjectAsTlf extends HttpServlet {

	private static final long serialVersionUID = -6371165585332482990L;

	public static final String SERVLET_NAME = "tlf";

	protected IImosService imosService;

	protected Marshaller marshaller;

	protected Unmarshaller unmarshaller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getOutputStream().println("GET not supported");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<String> params = getParameters(req);
			if (params.size() != 1) {
				throw new RuntimeException("wrong amount of parameters");
			}
			String orderId = params.get(0);
	
			Map<String, Integer> rotationMap = parseRotationMap(req.getParameter("rotations"));
	
			ImosProject project = imosService.readProject(orderId);
			if (project == null) {
				throw new ServletException("no project with orderId='" + orderId + "'");
			}
			Exporter exporter = new Exporter();
			exporter.setImosService(imosService);
			exporter.setExportPath("D:/werner/tlf");
			String warnings = exporter.export(orderId, rotationMap);
	
			resp.setContentType("text/plain");
			if (warnings == null || warnings.isEmpty()) {
				resp.getOutputStream().println("OK");
			} else {
				resp.getOutputStream().println("WARNING");
				resp.getOutputStream().println(warnings);
			}
		} catch (Exception e) {
			resp.getOutputStream().println("ERROR");
			e.printStackTrace(new PrintStream(resp.getOutputStream()));
		}
	}

	protected Map<String, Integer> parseRotationMap(String parameter) throws ServletException {
		Map<String, Integer> rotationMap = new HashMap<String, Integer>();

		RotationList rotationList;
		if (parameter != null) {
			try {
				rotationList = (RotationList) unmarshaller.unmarshal(new StringReader(parameter));
			} catch (JAXBException e) {
				throw new ServletException("unable to unmarshal rotations", e);
			}
			for (Rotation rotation : rotationList.getRotations()) {
				rotationMap.put(rotation.getBarcode(), rotation.getAngle());
			}
		}

		return rotationMap;
	}

	protected List<String> getParameters(HttpServletRequest req) {
		String parameterString = req.getRequestURI().substring(req.getContextPath().length() + SERVLET_NAME.length() + 2);
		return Arrays.asList(parameterString.split("/"));
	}

	@Override
	public void init() throws ServletException {
		imosService = ImosServiceFactory.getInstance().getImosService();
		imosService = ImosServiceFactory.getInstance().getImosService();
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ImosProject.class, RotationList.class);
			marshaller = jaxbContext.createMarshaller();
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new ServletException("unable to create jaxb environment", e);
		}
	}

	@Override
	public void destroy() {
		imosService = null;
	};

}
