package SentimentGenerator;
import java.io.Serializable;
public class YoutubeBean implements Serializable{

	private String videoId; // to watch a video, simply use https://www.youtube.com/watch?v=videoId
	private String videoTitle;
	private String description;
	private String channelTitle;
	private String thumbnailURL;
	private String[] tags;


	//setters

	public void setvideoId(String videoId){
		this.videoId = videoId;
	}
	public void setvideoTitle(String videoTitle){
		this.videoTitle = videoTitle;
	}
	public void setdescription(String description){
		this.description = description;
	}
	public void setchannelTitle(String channelTitle){
		this.channelTitle = channelTitle;
	}
	public void setthumbnailURL(String thumbnailURL){
		this.thumbnailURL =  thumbnailURL;
	}
	public void settags(String[] tags){
		this.tags = tags;
	}

	//getters
	public String getvideoId(){
		return this.videoId;
	}
	public String getvideoTitle(){
		return this.videoTitle;
	}
	public String getdescription(){
		return this.description;
	}
	public String getchannelTitle(){
		return this.channelTitle;
	}
	public String getthumbnailURL(){
		return this.thumbnailURL;
	}
	public String[] gettags(){
		return this.tags;
	}
}