package SentimentGenerator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SentimentReflector{

    

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
        return  "";
    }
    public String getYouTubeData() {
        YoutubeHelper yt = new YoutubeHelper();
        try{
        System.out.println(yt.getYoutubeTrending());
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }

        return  "";
    }
    public String getInstagramData() {
        return  "";
    }
    public String getAnalysisFromAlchemy() {
        return  "";
    }
    public String getFlickrData(){
        String temp = "";
        try {
             URL url = new URL("http://flickr.interestingness.getList");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            if (responseCode == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String strTemp = "";
                while (null != (strTemp = br.readLine())) {
                    temp += strTemp;
                }
                
            }
            System.out.println("Flickr Output is "+ temp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  temp;
    }

}