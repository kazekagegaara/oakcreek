package SentimentGenerator;

public class Output {

    private final long id;
    private final String result;

    public Output(long id, String result) {
        this.id = id;
        this.result = result;
    }
    

    public long getId() {
        return id;
    }

    public String getResult() {
        return result;
    }
}