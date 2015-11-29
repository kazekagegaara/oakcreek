package SentimentGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;
import java.util.ArrayList;


public class AlchemyHelper {

 public String getTextSentiment(String text) throws ClientProtocolException, IOException, JSONException {
    AllKeys ak = new AllKeys();
    String key = ak.getAlchemyKey();

    HttpClientHelper hch = new HttpClientHelper();
    String url = "http://gateway-a.watsonplatform.net/calls/text/TextGetTextSentiment";
    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    urlParameters.add(new BasicNameValuePair("apikey", key));
    urlParameters.add(new BasicNameValuePair("text", text));
    urlParameters.add(new BasicNameValuePair("outputMode", "json"));
  	HttpResponse response = hch.serviceCall(url,urlParameters);
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

    HttpClientHelper hch = new HttpClientHelper();
    String url = "http://access.alchemyapi.com/calls/text/TextGetRankedNamedEntities";
    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    urlParameters.add(new BasicNameValuePair("apikey", key));
    urlParameters.add(new BasicNameValuePair("text", text));
    urlParameters.add(new BasicNameValuePair("outputMode", "json"));
    urlParameters.add(new BasicNameValuePair("sentiment", "1"));
    HttpResponse response = hch.serviceCall(url, urlParameters);    
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

 public List<KeywordBean> getKeywords(String text) throws ClientProtocolException, IOException, JSONException {
    AllKeys ak = new AllKeys();
    String key = ak.getAlchemyKey();

    HttpClientHelper hch = new HttpClientHelper();
    String url = "http://gateway-a.watsonplatform.net/calls/text/TextGetRankedKeywords";    
    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    urlParameters.add(new BasicNameValuePair("apikey", key));
    urlParameters.add(new BasicNameValuePair("text", text));
    urlParameters.add(new BasicNameValuePair("outputMode", "json"));
    urlParameters.add(new BasicNameValuePair("sentiment", "1"));
    HttpResponse response = hch.serviceCall(url, urlParameters);    
    BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
    StringBuilder result = new StringBuilder();
    String temp = "";   
    while ((temp = rd.readLine()) != null) {      
      result.append(temp);
    }
    JSONObject obj = new JSONObject(result.toString());    
    JSONArray keywords = obj.getJSONArray("keywords");
    List<KeywordBean> keywordBeans = new ArrayList<KeywordBean>();
    for (int i = 0; i < keywords.length(); i++) {
      JSONObject o = keywords.getJSONObject(i);
      String sentiment = o.getJSONObject("sentiment").getString("type");
      String name = o.getString("text");
      KeywordBean kb = new KeywordBean();
      kb.setKeywordtext(name);
      kb.setSentiment(sentiment);

      keywordBeans.add(kb);
    }

    return keywordBeans;
 }

}
