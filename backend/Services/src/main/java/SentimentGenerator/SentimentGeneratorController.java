package SentimentGenerator;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentimentGeneratorController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/getSentiments")
    public Output getSentiments() {
    	SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
    	String returnData = test.getTwitterData();
        return new Output(counter.incrementAndGet(),returnData);
    }
}