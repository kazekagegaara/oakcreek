package SentimentGenerator;
import java.io.Serializable;

import java.util.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class CallFourObject implements Serializable{

	private String Sentiment = null;
	private String Timestamp = null;


	public CallFourObject(String Timestamp, String Sentiment){
    	this.Timestamp = Timestamp;
    	this.Sentiment =Sentiment;
    }

    public String getSentiment(){
    	return this.Sentiment;
    }

    public String getTimstamp(){
    	return this.Timestamp;
    }
}