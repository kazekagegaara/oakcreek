package SentimentGenerator;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.IOException;

public class HttpClientHelper {

	public HttpResponse serviceCall(String url) throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);

		return response;
	}
}