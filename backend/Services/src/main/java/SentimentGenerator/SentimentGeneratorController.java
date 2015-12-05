package SentimentGenerator;
import java.io.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SentimentGeneratorController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/getSentiments")
    public Output getSentiments() throws IOException {
    	
        SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        String returnData = "";
        //returnData += " Youtube-> ";
        // returnData += test.getYouTubeData();
        //returnData += " Flickr-> ";
        // returnData += test.getFlickrData();
        //returnData += " Twitter-> ";
        // returnData +=test.getTwitterData();
        String [] arr = test.MainFunction();
         return new Output(counter.incrementAndGet(),arr[0],arr[1]);
        
        // return arr;

    }
    // @RequestMapping("/getEntitiesAndKeywords")
    // public Output getEntitiesAndKeywords() {
    // 	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
    // 	String returnData = "testingData from getEntitiesAndKeywords";
    //     return new Output(counter.incrementAndGet(),returnData);
    // }
    // @RequestMapping("/getDetails")
    // public Output getDetails() {
    // 	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
    // 	String returnData = "testingData from getDetails";
    //     return new Output(counter.incrementAndGet(),returnData);
    // }
    // @RequestMapping("/getHistory")
    // public Output getHistory() {
    // 	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
    // 	String returnData = "testingData from getHistory";
    //     return new Output(counter.incrementAndGet(),returnData);
    // }

 //    @RequestMapping("/getFlickr")
 //    public Output getFlickr() {
 //        SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
 // // String returnData = test.getFlickrData();
 //        return new Output(counter.incrementAndGet(),returnData);
 //    }
}