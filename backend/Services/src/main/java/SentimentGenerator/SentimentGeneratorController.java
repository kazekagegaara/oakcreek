package SentimentGenerator;
import java.io.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import java.util.List;
import java.util.ArrayList;


@RestController
public class SentimentGeneratorController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/getSentiments")
    public Output getSentiments() throws IOException {
        SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        String [] arr = test.MainFunction();
        return new Output(counter.incrementAndGet(),arr[0],arr[1]);
    }

    // @RequestMapping("/getEntitiesAndKeywords")
    // public OutputCallTwo getEntitiesAndKeywords(@RequestParam String timeStamp) {
    // 	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
    //     List<CallTwoObject> keywords = new ArrayList<CallTwoObject>();
    //     List<CallTwoObject> entities = new ArrayList<CallTwoObject>();
    //     keywords = test.MainFunction2(timeStamp); 
    //     entities = test.MainFunction3(timeStamp); 
    //     return new OutputCallTwo(counter.incrementAndGet(),keywords,entities);
    // }

    @RequestMapping("/getEntitiesAndKeywords")
    public OutputCallTwo getEntitiesAndKeywords() {
        SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        List<CallTwoObject> keywords = new ArrayList<CallTwoObject>();
        List<CallTwoObject> entities = new ArrayList<CallTwoObject>();
        keywords = test.MainFunction2("1449315281774"); 
        entities = test.MainFunction3("1449315281774"); 
        return new OutputCallTwo(counter.incrementAndGet(),keywords,entities);
    }


    // @RequestMapping("/getDetails")
    // public OutputCallThree getDetails(@RequestParam String timeStamp, String NAME, String TYPE) {
    // 	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        
    //     // String name = "BuzzFeed";
    //     // String type = "ENTITY";
    //     // String timestamp = "1449315281774";

    //     String name = NAME;
    //     String type = TYPE;
    //     String timestamp = timeStamp;


    //     if (type.equals("KEYWORD")){
    //         List<CallThreeObject>  keywordDetail = new ArrayList<CallThreeObject>();
    //         keywordDetail = test.getDetailsKeyword(name,timestamp);
    //         return new OutputCallThree(counter.incrementAndGet(),keywordDetail);
    //     }else {
    //         List<CallThreeObject>  entityDetail = new ArrayList<CallThreeObject>();
    //         entityDetail = test.getDetailsEntity(name,timestamp);
    //         return new OutputCallThree(counter.incrementAndGet(),entityDetail);
    //     }
    // }


    @RequestMapping("/getDetails")
    public OutputCallThree getDetails() {
        SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        
        String name = "Youtube";
        String type = "ENTITY";
        String timestamp = "1449328996634";

        if (type.equals("KEYWORD")){
            List<CallThreeObject>  keywordDetail = new ArrayList<CallThreeObject>();
            keywordDetail = test.getDetailsKeyword(name,timestamp);
            return new OutputCallThree(counter.incrementAndGet(),keywordDetail);
        }else {
            List<CallThreeObject>  entityDetail = new ArrayList<CallThreeObject>();
            entityDetail = test.getDetailsEntity(name,timestamp);
            return new OutputCallThree(counter.incrementAndGet(),entityDetail);
        }
    }

    @RequestMapping("/getHistory")
    public OutputCallFour getHistory() {
    	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        List<CallFourObject>  result = test.getHistoryAll();
        return new OutputCallFour(counter.incrementAndGet(),result);
    }

 
}