package tdm.cam.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class RestClient {

	protected String baseAddress;
	
	public RestClient(String baseAddress) {
		this.baseAddress = baseAddress;
	}
	
	public InputStream doGetRequest(String service, List<String> params) {
		String address = createUrlAddress(service, params);
		try {
			URL url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			return connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("could not connect to '"+address+"'", e);
		}
	}

	public InputStream doPostRequest(String service, List<String> restParams, Map<String, String> postParams) {
		System.out.println("RestClient.doPostRequest() started");
		HttpClient httpClient = new DefaultHttpClient();
		String address = createUrlAddress(service, restParams);
		HttpPost httpPost = new HttpPost(address);
			
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> postParam : postParams.entrySet()) {
			params.add(new BasicNameValuePair(postParam.getKey(), postParam.getValue()));
		}

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		HttpResponse response;
		try {
			response = httpClient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			try {
				return entity.getContent();
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		} else {
			return null;
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
