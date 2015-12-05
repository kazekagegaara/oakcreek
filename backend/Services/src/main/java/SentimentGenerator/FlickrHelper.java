package SentimentGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;
import java.util.ArrayList;


public class FlickrHelper {

 public List<FlickrBean> getFlickrTrending() throws ClientProtocolException, IOException, JSONException {
    AllKeys ak = new AllKeys();
    String key = ak.getFlickerKey();

    HttpClientHelper hch = new HttpClientHelper();
    String getURL = "https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key="+key+"&format=json&nojsoncallback=1";
    HttpResponse response = hch.serviceCall(getURL);
  	BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
  	StringBuilder result = new StringBuilder();
  	String temp = "";  	
  	while ((temp = rd.readLine()) != null) {      
	    result.append(temp);    
  	}

  	JSONObject obj = new JSONObject(result.toString());  
    JSONArray photos = obj.getJSONObject("photos").getJSONArray("photo");  

    List<FlickrBean> flickrBeans = new ArrayList<FlickrBean>();

    for (int i = 0; i < photos.length(); i++) {
      JSONObject o = photos.getJSONObject(i);

      String imageId = o.getString("id");
      String secret = o.getString("secret");
      String server = o.getString("server");
      int farm = o.getInt("farm");
      String title = o.getString("title");
      
      FlickrBean fb = new FlickrBean();
      fb.setimageId(imageId);
      fb.setsecret(secret);
      fb.setserver(server);
      fb.setfarm(farm);
      fb.settitle(title);
      fb.setflickerURL(farm,server,imageId,secret);
      flickrBeans.add(fb);
    }
    //System.out.println(flickrBeans.toString());
    return flickrBeans;
 } 
}