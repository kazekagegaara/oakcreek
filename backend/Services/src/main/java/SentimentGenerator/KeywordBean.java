package SentimentGenerator;
import java.io.Serializable;
public class KeywordBean implements Serializable{

	private String Keywordtext = null;
	private String Sentiment = null;
	private String[] SocialMediaSource = new String[3];

	// setters

	public void setKeywordtext(String Keywordtext){
		this.Keywordtext = Keywordtext;
	}
	public void setSentiment(String Sentiment){
		this.Sentiment = Sentiment;
	}
	public void setSocialMediaSource(String SocialMediaSource[]){
		this.SocialMediaSource = SocialMediaSource;
	}

	// getters

	public String getKeywordtext(){
		return this.Keywordtext;
	}
	public String getSentiment(){
		return this.Sentiment;
	}
	public String[] getSocialMediaSource(){
		return this.SocialMediaSource;
	}
}