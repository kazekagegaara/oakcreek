package SentimentGenerator;
import java.io.Serializable;


public class CallTwoObject implements Serializable {
    private final String name;
    private final String sentiment;

    public CallTwoObject(String name, String sentiment){
    	this.name = name;
    	this.sentiment =sentiment;
    }

    public String getname(){
    	return this.name;
    }

    public String getsentiment(){
    	return this.sentiment;
    }

    
}