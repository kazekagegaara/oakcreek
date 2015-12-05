package SentimentGenerator;
import java.io.Serializable;
public class EntityBean implements Serializable{

	private String EntityName = null;
	private String Description =  null;
	private String Image = null;
	private String Thumbnail = null;
	private String References = null;
	private String RdfTypes = null;
	private String Label = null;
	private String Comment = null;
	private String EntityType = null;
	private String Website = null;
	private String Sentiment = null;
	private String[] SocialMediaSource = new String[3];
	private String[] WebUrl = new String[3];
	private String LinkedDataSource = null;


	// setters

	public void setEntityName(String EntityName){
		this.EntityName = EntityName;
	}
	public void setDescription(String Description){
		this.Description = Description;
	}
	public void setImage(String Image){
		this.Image = Image;
	}
	public void setThumbnail(String Thumbnail){
		this.Thumbnail = Thumbnail;
	}
	public void setRefrences(String References){
		this.References = References;
	}
	public void setRdfTypes(String RdfTypes){
		this.RdfTypes = RdfTypes;
	}
	public void setLabel(String Label){
		this.Label = Label;
	}
	public void setComment(String Comment){
		this.Comment = Comment;
	}
	public void setEntityType(String EntityType){
		this.EntityType = EntityType;
	}
	public void setWebsite(String Website){
		this.Website = Website;
	}
	public void setSentiment(String Sentiment){
		this.Sentiment = Sentiment;
	}
	public void setSocialMediaSource(String SocialMediaSource[]){
		this.SocialMediaSource = SocialMediaSource;
	}
	public void setWebUrl(String WebUrl[]){
		this.WebUrl = WebUrl;
	}
	public void setLinkedDataSource(String LinkedDataSource){
		this.LinkedDataSource = LinkedDataSource;
	}

	//getters

	public String getEntityName(){
		return this.EntityName;
	}
	public String getDescription(){
		return this.Description;
	}
	public String getImage(){
		return this.Image;
	}
	public String getThumbnail(){
		return this.Thumbnail;
	}
	public String getRefrences(){
		return this.References;
	}
	public String getRdfTypes(){
		return this.RdfTypes;
	}
	public String getLabel(){
		return this.Label;
	}
	public String getComment(){
		return this.Comment;
	}
	public String getEntityType(){
		return this.EntityType;
	}
	public String getWebsite(){
		return this.Website;
	}
	public String getSentiment(){
		return this.Sentiment;
	}
	public String[] getSocialMediaSource(){
		return this.SocialMediaSource;
	}
	public String[] getWebUrl(){
		return this.WebUrl;
	}
	public String getLinkedDataSource(){
		return this.LinkedDataSource;
	}
}