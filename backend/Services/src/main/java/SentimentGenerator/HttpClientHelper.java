package SentimentGenerator;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.NameValuePair;
import java.util.List;

public class HttpClientHelper {

	public HttpResponse serviceCall(String url) throws ClientProtocolException{
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);

		return response;
	}

	// only used by Alchemy API as of now
	public HttpResponse serviceCall(String url, List<NameValuePair> urlParameters) throws ClientProtocolException{
		HttpClient client = new DefaultHttpClient();

		HttpPost request = new HttpPost(url);
		request.setHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);

		return response;
	}
}