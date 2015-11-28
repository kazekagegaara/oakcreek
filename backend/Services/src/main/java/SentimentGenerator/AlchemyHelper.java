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

 public String getTextSentiment(String text) throws ClientProtocolException, IOException, JSONException {
    AllKeys ak = new AllKeys();
    String key = ak.getAlchemyKey();

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

 public List<EntityBean> getEntities(String text) throws ClientProtocolException, IOException, JSONException {
    AllKeys ak = new AllKeys();
    String key = ak.getAlchemyKey();

    HttpClient client = new DefaultHttpClient();
    String text = "ISIS bombed France. Obama isn't happy.";
    String getURL = "http://access.alchemyapi.com/calls/text/TextGetRankedNamedEntities?apikey="+key+"&text="+URLEncoder.encode(text, "UTF-8")+"&outputMode=json&sentiment=1";
    HttpGet request = new HttpGet(getURL);
    HttpResponse response = client.execute(request);
    BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
    StringBuilder result = new StringBuilder();
    String temp = "";   
    while ((temp = rd.readLine()) != null) {      
      result.append(temp);
    }
    JSONObject obj = new JSONObject(result.toString());    
    JSONArray entities = obj.getJSONArray("entities");
    List<EntityBean> entityBeans = new ArrayList<EntityBean>();
    for (int i = 0; i < entities.length(); i++) {
      JSONObject o = entities.getJSONObject(i);
      String sentiment = o.getJSONObject("sentiment").getString("type");
      String name = o.getString("text");      
      String dbpedia = "";
      if (o.has("disambiguated")) {
        if (o.getJSONObject("disambiguated").has("dbpedia")) {
          dbpedia = o.getJSONObject("disambiguated").getString("dbpedia");
        }
      }      
      EntityBean eb = new EntityBean();
      eb.setEntityName(name);
      eb.setSentiment(sentiment);
      eb.setLinkedDataSource(dbpedia);

      entityBeans.add(eb);
    }

    return entityBeans;
 }

}
