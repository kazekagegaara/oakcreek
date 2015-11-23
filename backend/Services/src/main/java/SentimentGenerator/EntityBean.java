package SentimentGenerator;

public class EntityBean implements java.io.Serializable{

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
	private String SocialMediaSource = null;
	private String WebUrl = null;
	private String LinkedDataSource = null;


	// setters

	public void setEntityName(String value){
		this.EntityName = value;
	}
	public void setDescription(String value){
		this.Description = value;
	}
	public void setImage(String value){
		this.Image = value;
	}
	public void setThumbnail(String value){
		this.Thumbnail = value;
	}
	public void setRefrences(String value){
		this.References = value;
	}
	public void setRdfTypes(String value){
		this.RdfTypes = value;
	}
	public void setLabel(String value){
		this.Label = value;
	}
	public void setComment(String value){
		this.Comment = value;
	}
	public void setEntityType(String value){
		this.EntityType = value;
	}
	public void setWebsite(String value){
		this.Website = value;
	}
	public void setSentiment(String value){
		this.Sentiment = value;
	}
	public void setSocialMediaSource(String value){
		this.SocialMediaSource = value;
	}
	public void setWebUrl(String value){
		this.WebUrl = value;
	}
	public void setLinkedDataSource(String value){
		this.LinkedDataSource = value;
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
	public String getSocialMediaSource(){
		return this.SocialMediaSource;
	}
	public String getWebUrl(){
		return this.WebUrl;
	}
	public String getLinkedDataSource(){
		return this.LinkedDataSource;
	}
}