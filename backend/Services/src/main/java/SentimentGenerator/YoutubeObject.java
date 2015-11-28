package SentimentGenerator;

public class YoutubeObject {

	private String videoId; // to watch a video, simply use https://www.youtube.com/watch?v=videoId
	private String videoTitle;
	private String description;
	private String channelTitle;
	private String thumbnailURL;
	private String[] tags;

	public YoutubeObject(String videoId, String videoTitle, String description, String channelTitle, String thumbnailURL, String[] tags) {		
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.description = description;
		this.channelTitle = channelTitle;	
		this.thumbnailURL = thumbnailURL;	
		this.tags = tags;	
	}

	public String toString(){
		return this.videoId + " " + videoTitle + " " + description + " " + channelTitle + " " + thumbnailURL + " " + tags;
	}
}