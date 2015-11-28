package SentimentGenerator;
import java.io.Serializable;
public class FlickrBean implements Serializable{
	private String imageId;
	private String secret;
	private String server;
	private int farm;
	private String title;	

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
}