package SentimentGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.net.URLEncoder;
import java.util.*;


public class AlchemyHelper {

 private static final String key = "xxxxxxxxxxxxxxxxxx"; // put your alchemy key here

 public String getTextSentiment(String text) throws ClientProtocolException, IOException, JSONException {
  	HttpClient client = new DefaultHttpClient();
    String getURL = "http://gateway-a.watsonplatform.net/calls/text/TextGetTextSentiment?apikey="+key+"&text="+URLEncoder.encode(text, "UTF-8")+"&outputMode=json";
  	HttpGet request = new HttpGet(getURL);
  	HttpResponse response = client.execute(request);
  	BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
  	StringBuilder result = new StringBuilder();
  	String temp = "";  	
  	while ((temp = rd.readLine()) != null) {	    
	    result.append(temp);
  	}

  	JSONObject obj = new JSONObject(result.toString());
  	return obj.getJSONObject("docSentiment").getString("type");
 } 

}
