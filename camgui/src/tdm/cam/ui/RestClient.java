package tdm.cam.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RestClient {

	protected String baseAddress;
	
	public RestClient(String baseAddress) {
		this.baseAddress = baseAddress;
	}
	
	public InputStream doRequest(String service, List<String> params) {
		String address = createUrlAddress(service, params);
		System.out.println("RestClient.doRequest(): address=" + address);
		try {
			URL url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			return connection.getInputStream();
		} catch (IOException e) {
			throw new RuntimeException("could not connect to '"+address+"'", e);
		}
	}

	private String createUrlAddress(String service, List<String> params) {
		StringBuffer address = new StringBuffer();

		address.append(baseAddress).append("/");
		address.append(service);
		
		if (params != null && params.size() > 0) {
			for (String param : params) {
				address.append("/").append(param);
			}
		}
		
		return address.toString();
	}
	
}
