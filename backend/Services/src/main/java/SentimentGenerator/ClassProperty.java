package SentimentGenerator;
import java.io.Serializable;
import org.springframework.web.bind.annotation.RequestMapping;

// @Configuration
// @PropertySource("classpath:config.properties")
public class ClassProperty implements Serializable{
	//1.2.3.4
    // @Value("${mongodb.url}")
    private String mongodbUrl;

    //hello
    // @Value("${mongodb.db}")
    private String defaultDb;

    public String getmongodbUrl(){
    	return this.mongodbUrl;
    }

    public String getdefaultDb(){
    	return this.defaultDb;
    }
}