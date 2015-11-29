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
import java.util.*;


public class YoutubeHelper {

 //private static final String key = "******"; // put your youtube key here

 public List<YoutubeBean> getYoutubeTrending() throws ClientProtocolException, IOException, JSONException {
    AllKeys ak = new AllKeys();
    String key = ak.getYoutubeKey();
  	HttpClient client = new DefaultHttpClient();
  	HttpGet request = new HttpGet("https://www.googleapis.com/youtube/v3/videos?chart=mostPopular&key="+key+"&part=snippet&maxResults=10");
  	HttpResponse response = client.execute(request);
  	BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
  	StringBuilder result = new StringBuilder();
  	String temp = "";  	
  	while ((temp = rd.readLine()) != null) {	    
	    result.append(temp);
  	}

  	JSONObject obj = new JSONObject(result.toString());  
  	JSONArray items = obj.getJSONArray("items");

  	List<YoutubeBean> youtubeBeans = new ArrayList<YoutubeBean>();

  	for (int i = 0; i < items.length(); i++) {
  		JSONObject o = items.getJSONObject(i);
  		String id = "";
  		String videoTitle = "";
  		String description = "";
  		String channelTitle = "";
  		String thumbnailURL = "";
  		JSONArray tags = new JSONArray();
  		JSONObject snippet = o.getJSONObject("snippet");

  		if (o.has("id")) {
			   id = o.getString("id");
 		  }
  		if (snippet.has("title")) {
			   videoTitle = snippet.getString("title");
 		  }
 		  if (snippet.has("description")) {
			   description = snippet.getString("description");
 		  }
 		  if (snippet.has("channelTitle")) {
			   channelTitle = snippet.getString("channelTitle");
 		  }
  		if (snippet.has("thumbnails")) {
  			if (snippet.getJSONObject("thumbnails").has("maxres")) {
  				thumbnailURL = snippet.getJSONObject("thumbnails").getJSONObject("maxres").getString("url");
  			} else if (snippet.getJSONObject("thumbnails").has("standard")) {
  				thumbnailURL = snippet.getJSONObject("thumbnails").getJSONObject("standard").getString("url");
  			}
		  } 		
 		  if (snippet.has("tags")) {
  		   tags =snippet.getJSONArray("tags");
 		  }  		
  		//YoutubeObject yo = new YoutubeObject(id,videoTitle,description,channelTitle,thumbnailURL,jsonArrayToStringArray(tags));
      YoutubeBean yb = new YoutubeBean();
      yb.setvideoId(id);
      yb.setvideoTitle(videoTitle);
      yb.setdescription(description);
      yb.setchannelTitle(channelTitle);
      yb.setthumbnailURL(thumbnailURL);
      yb.settags(jsonArrayToStringArray(tags));
  		youtubeBeans.add(yb);
	  }	

	   //System.out.println(youtubeBeans.toString());
  return youtubeBeans;
 }

 private static String[] jsonArrayToStringArray(JSONArray arr) throws JSONException{ 	
 	String[] temp = new String[arr.length()];
 	for(int i=0; i<arr.length(); i++) {
 		temp[i]= arr.getString(i);
 	}
 	return temp;
 }

}