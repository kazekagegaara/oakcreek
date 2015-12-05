package SentimentGenerator;

import java.util.List;
import java.util.ArrayList;
public class OutputCallFour {

    private final long id;
    private List<CallFourObject> result = new ArrayList<CallFourObject>();
    //private final String timestamp;
    
    public OutputCallFour(long id, List<CallFourObject> result) {
        this.id = id;
        this.result = result;
        //this.timestamp = timestamp;
    }
    
    public long getId() {
        return id;
    }

    public List<CallFourObject> getResult() {
        return this.result;
    }
    // public String getTimestamp() {
    //     return timestamp;
    // }

}