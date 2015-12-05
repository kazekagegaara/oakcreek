package SentimentGenerator;
import java.io.Serializable;
public class TwitterBean implements Serializable{

	private String twitterTopic;
	private String twitterURL;

	//getters
	public String gettwitterTopic(){
		return this.twitterTopic;
	}
	public String gettwitterURL(){
		return this.twitterURL;
	}

	//setters
	public void settwitterTopic(String twitterTopic){
		this.twitterTopic = twitterTopic;
	}
	public void settwitterURL(String twitterURL){
		this.twitterURL = twitterURL;
	}
}