package SentimentGenerator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

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


    public String[] MainFunction() throws IOException{
        //SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        String returnData = "";
        //returnData += " Youtube-> ";
        returnData += getYouTubeData();
        //returnData += " Flickr-> ";
        returnData += getFlickrData();
        //returnData += " Twitter-> ";


        String wholeString = "";
        String twitterString = "";
        String youtubeString = "";
        String flickerString = "";
        //Twitter terms
        List<TwitterBean> tbeanList = getTwitterData();
        // // TwitterHelper th = new TwitterHelper();
        // tbeanList += 

        for (int i = 0; i < tbeanList.size(); i++) {
            TwitterBean tb = tbeanList.get(i);
            wholeString += tb.gettwitterTopic() + " ";
            twitterString += tb.gettwitterTopic() + " ";
        }

        //youtube terms
        // YoutubeHelper yt = new YoutubeHelper();
        List<YoutubeBean> youtubeBeans = getYouTubeData();
        for (int i = 0; i < youtubeBeans.size(); i++) {
            YoutubeBean element = youtubeBeans.get(i);
            wholeString += element.getvideoTitle() + " ";
            youtubeString += element.getvideoTitle() + " ";
            wholeString += element.getdescription() + " " ; 
            youtubeString += element.getdescription() + " " ;             
            wholeString += element.getchannelTitle() + " ";
            youtubeString += element.getchannelTitle() + " ";
            String[] temp = element.gettags();
            for (int j=0; j<temp.length ;j++){
                wholeString += temp[j] + " ";
                youtubeString += temp[j] + " ";
            }                           
        }


        //flickerTerms
        // FlickrHelper flk = new FlickrHelper();

        try{
            List<FlickrBean> flickerBeans = getFlickrData();
            for (int i = 0; i < flickerBeans.size(); i++) {
                FlickrBean element = flickerBeans.get(i);
                wholeString += element.gettitle() + " "; 
                flickerString += element.gettitle() + " ";                          
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }



        String cummulativeSentiment = "";
        
       
        cummulativeSentiment += getSentimentFromAlchemy(wholeString); 
        

        List<EntityBean> twitterEntitysList = getEntitiesFromAlchemy(twitterString,"Twitter");
        
        List<EntityBean> youtubeEntitysList = getEntitiesFromAlchemy(youtubeString,"YouTube");

        List<EntityBean> flickerEntitysList = getEntitiesFromAlchemy(flickerString,"Flicker");

        List<EntityBean> combinedList =  new ArrayList<EntityBean>();

        for (int i = 0; i < twitterEntitysList.size(); i++) {
            combinedList.add(twitterEntitysList.get(i));
        }
        for (int i = 0; i < youtubeEntitysList.size(); i++) {
            combinedList.add(youtubeEntitysList.get(i));
        }
        for (int i = 0; i < flickerEntitysList.size(); i++) {
            combinedList.add(flickerEntitysList.get(i));
        }

        List<EntityBean> fullList = getDBpediaData(combinedList); 


        List<KeywordBean> kwb = new ArrayList<KeywordBean>() ;
        




        String timestamp = Long.toString(System.currentTimeMillis());


        GenerateRDF genRDF = new GenerateRDF();

        genRDF.generateRdf(fullList, kwb, timestamp);

        String [] arr = {cummulativeSentiment,timestamp };
        return arr;
        // cummulativeSentiment = getSentimentFromAlchemy(wholeString);


        //return new Output(counter.incrementAndGet(),returnData);
    }


    public List<EntityBean> getDBpediaData (List<EntityBean> entityList){

        SparqlHelper sprk  = new SparqlHelper();
        List<EntityBean> newListEntity = new ArrayList<EntityBean>() ; 
        for (int i = 0; i < entityList.size(); i++) {
            EntityBean element = entityList.get(i);
            if ((!element.getLinkedDataSource().equals("")) && (!element.getLinkedDataSource().matches(".*\\d+.*")) && (!element.getLinkedDataSource().contains((","))) ){

            
            List<String> allList = sprk.getInfoFromDBPedia(element.getLinkedDataSource());
            element.setDescription(allList.get(0));
            element.setImage(allList.get(1));
            element.setThumbnail(allList.get(2));
            element.setRefrences(allList.get(3));
            element.setRdfTypes(allList.get(4));
            element.setLabel(allList.get(5));
            element.setComment(allList.get(6));
            }
            newListEntity.add(element);

        }

        return newListEntity;

    }

    public List<TwitterBean> getTwitterData() {
        TwitterHelper th = new TwitterHelper();
        List<TwitterBean> trendNames = th.getTwitterTrending();
        // String twitterString = "";
        // for (int i = 0; i < trendNames.size(); i++) {
        //     TwitterBean tb = trendNames.get(i);
        //     twitterString += tb.gettwitterTopic() + " ";
        // }
        // twitterString = twitterString.substring(0,twitterString.length()-1);

         
        
        // String sentiment = " Twitter Sentiment -> ";

        // if(getSentimentFromAlchemy(twitterString) == "No sentiment returned."){
        //     sentiment = " Twitter -> No sentiments and entities returned. ";
        // }else{
        //     sentiment += getSentimentFromAlchemy(twitterString); 
        //     sentiment += " Twitter entities -> ";

        //     List<String> entitysList = getEntitiesFromAlchemy(twitterString,"Twitter");


        //     for (int i = 0; i < entitysList.size(); i++) {
        //         sentiment += entitysList.get(i) + " , ";
        //     }
            
        //     sentiment = sentiment.substring(0,sentiment.length()-3);
        // }
        // return  sentiment;
        return  trendNames;
    }

    public List<YoutubeBean> getYouTubeData() {
        YoutubeHelper yt = new YoutubeHelper();
        List<YoutubeBean> youtubeBeans = new ArrayList<YoutubeBean>() ;
        try{
            youtubeBeans = yt.getYoutubeTrending();
            // for (int i = 0; i < youtubeBeans.size(); i++) {
            //     YoutubeBean element = youtubeBeans.get(i);
            //     youtubeString += element.getvideoTitle() + " ";
            //     youtubeString += element.getdescription() + " " ;               
            //     youtubeString += element.getchannelTitle() + " ";
            //     String[] temp = element.gettags();
            //     for (int j=0; j<temp.length ;j++){
            //         youtubeString += temp[j] + " ";
            //     }                           
            // }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        // youtubeString = youtubeString.substring(0,youtubeString.length()-1);
        // System.out.println("--------------------------------------------------------------------------------------------------------> YOUTUBE DATA S ");
        // System.out.println(youtubeString);
        // System.out.println("--------------------------------------------------------------------------------------------------------> YOUTUBE DATA E ");

        // String sentiment = " Youtube Sentiment -> ";
        
        // if(getSentimentFromAlchemy(youtubeString) == "No sentiment returned."){
        //     sentiment = " Youtube -> No sentiments and entities returned. ";
        // }else{
        //     sentiment += getSentimentFromAlchemy(youtubeString);
        //     sentiment += " Youtube entities -> ";
        //     List<String> entitysList = getEntitiesFromAlchemy(youtubeString);
        //     for (int i = 0; i < entitysList.size(); i++) {
        //         sentiment += entitysList.get(i) + " , ";
        //     }
        //     sentiment = sentiment.substring(0,sentiment.length()-3);
        // }
        // return  sentiment;
        return  youtubeBeans;
    }
    public List<FlickrBean> getFlickrData(){
        FlickrHelper flk = new FlickrHelper();
        List<FlickrBean> flickerBeans = new ArrayList<FlickrBean>() ; 
        try{
            flickerBeans = flk.getFlickrTrending();
            // for (int i = 0; i < flickerBeans.size(); i++) {
            //     FlickrBean element = flickerBeans.get(i);
            //     flickerString += element.gettitle() + " ";                            
            // }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        // flickerString = flickerString.substring(0,flickerString.length()-1);
        // System.out.println("--------------------------------------------------------------------------------------------------------> Flickr DATA S ");
        // System.out.println(flickerString);
        // System.out.println("--------------------------------------------------------------------------------------------------------> Flickr DATA E ");
        
        // String sentiment = " Flicker Sentiment -> ";
        // if(getSentimentFromAlchemy(flickerString) == "No sentiment returned."){
        //     sentiment = " Flicker -> No sentiments and entities returned. ";
        // }else{
        //     sentiment += getSentimentFromAlchemy(flickerString);
        //     sentiment += " Flicker entities -> ";
        //     List<String> entitysList = getEntitiesFromAlchemy(flickerString);
        //     for (int i = 0; i < entitysList.size(); i++) {
        //         sentiment += entitysList.get(i) + " , ";
        //     }
        //     sentiment = sentiment.substring(0,sentiment.length()-3);
        // }
        // return  sentiment;
        return flickerBeans;
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


    public List<EntityBean> getEntitiesFromAlchemy(String text , String source) {
        
        AlchemyHelper ah = new AlchemyHelper();
        
        List<EntityBean> entityBeans = new ArrayList<EntityBean>();
        
        List<String> entitysList = new ArrayList<String>();
        
        try{
            entityBeans = ah.getEntities(text);
            for (int i = 0; i < entityBeans.size(); i++) {
                EntityBean element = entityBeans.get(i);

                if (source == "Twitter"){
                    String [] str = {"Twitter"};
                    element.setWebsite("www.twitter.com");
                    element.setSocialMediaSource(str);
                }else if(source == "YouTube"){
                    String [] str = {"YouTube"};
                    element.setWebsite("www.youtube.com");
                    element.setSocialMediaSource(str);
                } else if (source == "Flicker") {
                    String [] str = {"Flicker"};
                    element.setWebsite("www.flicker.com");
                    element.setSocialMediaSource(str);
                }
                entitysList.add(element.getEntityName());
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return entityBeans;
    }

    public String[] getKeywordsFromAlchemy(String text) {
        return new String[]{""};
    }


}