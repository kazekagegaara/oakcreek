package SentimentGenerator;

import java.util.List;
import java.util.ArrayList;
public class OutputCallThree {

    private final long id;
    private  List<CallThreeObject> result = new ArrayList<CallThreeObject>();
    //private final String timestamp;
    
    public OutputCallThree(long id, List<CallThreeObject> result) {
        this.id = id;
        this.result = result;
        //this.timestamp = timestamp;
    }
    
    public long getId() {
        return id;
    }

    public List<CallThreeObject> getResult() {
        return this.result;
    }
    // public String getTimestamp() {
    //     return timestamp;
    // }

}