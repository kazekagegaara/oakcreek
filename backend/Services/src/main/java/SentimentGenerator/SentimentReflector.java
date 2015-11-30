package SentimentGenerator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.*;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SentimentReflector{

    private String youtubeString = "";
    private String flickerString = "";

    private final long id;
    private final String content;
    public SentimentReflector(long id, String content) {
        this.id = id;
        this.content = content;
        
    }
     public SentimentReflector(long id) {
        this.id = id;
        this.content = "";
    }

    public String getTwitterData() {
        TwitterHelper th = new TwitterHelper();
        List<String> trendNames = th.getTwitterTrending();
        String twitterString = "";
        for (int i = 0; i < trendNames.size(); i++) {
            twitterString += trendNames.get(i) + " ";
        }
        twitterString = twitterString.substring(0,twitterString.length()-1);

        System.out.println("--------------------------------------------------------------------------------------------------------> Twitter DATA S ");
        System.out.println(twitterString);
        System.out.println("--------------------------------------------------------------------------------------------------------> Twitter DATA E ");
        
        String sentiment = " Twitter Sentiment -> ";
        if(getSentimentFromAlchemy(twitterString) == "No sentiment returned."){
            sentiment = " Twitter -> No sentiments and entities returned. ";
        }else{
            sentiment += getSentimentFromAlchemy(twitterString); 
            sentiment += " Twitter entities -> ";
            List<String> entitysList = getEntitiesFromAlchemy(twitterString);
            for (int i = 0; i < entitysList.size(); i++) {
                sentiment += entitysList.get(i) + " , ";
            }
            
            sentiment = sentiment.substring(0,sentiment.length()-3);
        }
        return  sentiment;

    }
    public String getYouTubeData() {
        YoutubeHelper yt = new YoutubeHelper();
        try{
            List<YoutubeBean> youtubeBeans = yt.getYoutubeTrending();
            for (int i = 0; i < youtubeBeans.size(); i++) {
                YoutubeBean element = youtubeBeans.get(i);
                youtubeString += element.getvideoTitle() + " ";
                youtubeString += element.getdescription() + " " ;               
                youtubeString += element.getchannelTitle() + " ";
                String[] temp = element.gettags();
                for (int j=0; j<temp.length ;j++){
                    youtubeString += temp[j] + " ";
                }                           
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        youtubeString = youtubeString.substring(0,youtubeString.length()-1);
        System.out.println("--------------------------------------------------------------------------------------------------------> YOUTUBE DATA S ");
        System.out.println(youtubeString);
        System.out.println("--------------------------------------------------------------------------------------------------------> YOUTUBE DATA E ");

        String sentiment = " Youtube Sentiment -> ";
        
        if(getSentimentFromAlchemy(youtubeString) == "No sentiment returned."){
            sentiment = " Youtube -> No sentiments and entities returned. ";
        }else{
            sentiment += getSentimentFromAlchemy(youtubeString);
            sentiment += " Youtube entities -> ";
            List<String> entitysList = getEntitiesFromAlchemy(youtubeString);
            for (int i = 0; i < entitysList.size(); i++) {
                sentiment += entitysList.get(i) + " , ";
            }
            sentiment = sentiment.substring(0,sentiment.length()-3);
        }
        return  sentiment;
    }
    public String getFlickrData(){
        FlickrHelper flk = new FlickrHelper();
        try{
            List<FlickrBean> flickerBeans = flk.getFlickrTrending();
            for (int i = 0; i < flickerBeans.size(); i++) {
                FlickrBean element = flickerBeans.get(i);
                flickerString += element.gettitle() + " ";                            
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        flickerString = flickerString.substring(0,flickerString.length()-1);
        System.out.println("--------------------------------------------------------------------------------------------------------> Flickr DATA S ");
        System.out.println(flickerString);
        System.out.println("--------------------------------------------------------------------------------------------------------> Flickr DATA E ");
        
        String sentiment = " Flicker Sentiment -> ";
        if(getSentimentFromAlchemy(flickerString) == "No sentiment returned."){
            sentiment = " Flicker -> No sentiments and entities returned. ";
        }else{
            sentiment += getSentimentFromAlchemy(flickerString);
            sentiment += " Flicker entities -> ";
            List<String> entitysList = getEntitiesFromAlchemy(flickerString);
            for (int i = 0; i < entitysList.size(); i++) {
                sentiment += entitysList.get(i) + " , ";
            }
            sentiment = sentiment.substring(0,sentiment.length()-3);
        }
        return  sentiment;
    }
    public String getInstagramData() {
        return  "";
    }
    public String getSentimentFromAlchemy(String text) {
        AlchemyHelper ah = new AlchemyHelper();
        String sentiment = "";
        try{
            sentiment = ah.getTextSentiment(text);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return sentiment;
    }
    public List<String> getEntitiesFromAlchemy(String text) {
        AlchemyHelper ah = new AlchemyHelper();
        List<EntityBean> entityBeans = new ArrayList<EntityBean>();
        List<String> entitysList = new ArrayList<String>();
        try{
            entityBeans = ah.getEntities(text);
            for (int i = 0; i < entityBeans.size(); i++) {
                EntityBean element = entityBeans.get(i);
                entitysList.add(element.getEntityName());
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return entitysList;
    }
    public String[] getKeywordsFromAlchemy(String text) {
        return new String[]{""};
    }


}