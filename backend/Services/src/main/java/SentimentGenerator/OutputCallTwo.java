package SentimentGenerator;
import java.io.Serializable;

import java.util.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class OutputCallTwo implements Serializable{

    private  long id;
    private  List<CallTwoObject>  Keywords = new ArrayList<CallTwoObject>();
    private  List<CallTwoObject>  Entities = new ArrayList<CallTwoObject>();
    
    public OutputCallTwo(long id, List<CallTwoObject>  Keywords, List<CallTwoObject>   Entities) {
        this.id = id;
        this.Keywords = Keywords;
        this.Entities = Entities;
    }
    
    public long getId() {
        return id;
    }

    public List<CallTwoObject> getKeywords() {
        return this.Keywords;
    }
     public List<CallTwoObject> getEntities() {
        return this.Entities;
    }
}