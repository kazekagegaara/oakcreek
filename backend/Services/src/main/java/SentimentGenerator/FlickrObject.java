package SentimentGenerator;

public class FlickrObject {

	// https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg  
	// https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg  
	// https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{o-secret}_o.(jpg|gif|png)

	private String imageId;
	private String secret;
	private String server;
	private int farm;
	private String title;	

	public FlickrObject(String imageId, String secret, String server, int farm, String title) {		
		this.imageId = imageId;
		this.secret = secret;
		this.server = server;
		this.farm = farm;	
		this.title = title;
	}

	public String toString(){
		return this.imageId + " " + secret + " " + server + " " + farm + " " + title;
	}
}