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
        return  "";
    }
    public String getInstagramData() {
        return  "";
    }
    public String getAnalysisFromAlchemy() {
        return  "";
    }

}