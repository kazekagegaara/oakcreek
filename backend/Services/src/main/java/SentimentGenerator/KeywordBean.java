package SentimentGenerator;
import java.io.Serializable;
public class KeywordBean implements Serializable{

	private String Keywordtext = null;
	private String Sentiment = null;
	private String[] SocialMediaSource = new String[3];

	// setters

	public void setKeywordtext(String value){
		this.Keywordtext = value;
	}
	public void setSentiment(String value){
		this.Sentiment = value;
	}
	public void setSocialMediaSource(String value[]){
		this.SocialMediaSource = value;
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