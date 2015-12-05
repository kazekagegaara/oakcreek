package SentimentGenerator;

public class Output {

    private final long id;
    private final String result;
    private final String timestamp;
    
    public Output(long id, String result, String timestamp) {
        this.id = id;
        this.result = result;
        this.timestamp = timestamp;
    }
    
    public long getId() {
        return id;
    }

    public String getResult() {
        return this.result;
    }
    public String getTimestamp() {
        return timestamp;
    }

}