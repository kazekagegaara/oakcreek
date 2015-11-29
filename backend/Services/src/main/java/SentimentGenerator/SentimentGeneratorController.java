package SentimentGenerator;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SentimentGeneratorController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/getSentiments")
    public Output getSentiments() {
    	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        String returnData = test.getYouTubeData();
        returnData += " <-Flickr-> ";
        returnData += test.getFlickrData();
        returnData += " <-Twitter-> ";
        returnData +=test.getTwitterData();
           
        return new Output(counter.incrementAndGet(),returnData);
    }
    @RequestMapping("/getEntitiesAndKeywords")
    public Output getEntitiesAndKeywords() {
    	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
    	String returnData = "testingData from getEntitiesAndKeywords";
        return new Output(counter.incrementAndGet(),returnData);
    }
    @RequestMapping("/getDetails")
    public Output getDetails() {
    	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
    	String returnData = "testingData from getDetails";
        return new Output(counter.incrementAndGet(),returnData);
    }
    @RequestMapping("/getHistory")
    public Output getHistory() {
    	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
    	String returnData = "testingData from getHistory";
        return new Output(counter.incrementAndGet(),returnData);
    }

    // For mine testing
    @RequestMapping("/getFlickr")
    public Output getFlickr() {
        SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        String returnData = test.getFlickrData();
        return new Output(counter.incrementAndGet(),returnData);
    }
}