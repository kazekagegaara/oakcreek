package SentimentGenerator;
import java.io.Serializable;
public class FlickrBean implements Serializable{
	private String imageId;
	private String secret;
	private String server;
	private int farm;
	private String title;	
	private String flickerURL;

	// setters

	public void setimageId(String imageId){
		this.imageId = imageId;
	}
	public void setsecret(String secret){
		this.secret = secret;
	}
	public void setserver(String server){
		this.server = server;
	}
	public void setfarm(int farm){
		this.farm = farm;
	}
	public void settitle(String title){
		this.title = title;
	}

	// https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg  
	public void setflickerURL(int farm,String server, String imageId,String secret){
		this.title = "https://farm";
		this.title += farm;
		this.title += ".staticflickr.com/";
		this.title += server;
		this.title += "/";
		this.title += imageId;
		this.title += "_";
		this.title += secret;
		this.title += ".jpg";
	}

	// getters

	public String getimageId(){
		return this.imageId;
	}
	public String getsecret(){
		return this.secret;
	}
	public String getserver(){
		return this.server;
	}
	public int  getfarm(){
		return this.farm;
	}
	public String gettitle(){
		return this.title;
	}
	public String getflickerURL(){
		return this.flickerURL;
	}


}