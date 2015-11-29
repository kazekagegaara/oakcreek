package SentimentGenerator;

import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.*;
import java.util.List;
import java.util.ArrayList;

public class TwitterHelper {

	public List<String> getTwitterTrending() {
		AllKeys ak = new AllKeys();
    	String twitterConsumerKey = ak.getTwitterConsumerKey();
    	String twitterConsumerSecret = ak.getTwitterConsumerSecret();
    	String twitterAccessToken = ak.getTwitterAccessToken();
    	String twitterAccessTokenSecret = ak.getTwitterAccessTokenSecret();
		try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true).setOAuthConsumerKey(twitterConsumerKey)
            	                    .setOAuthConsumerSecret(twitterConsumerSecret)
                	                .setOAuthAccessToken(twitterAccessToken)
                    	            .setOAuthAccessTokenSecret(twitterAccessTokenSecret);

        	TwitterFactory tf = new TwitterFactory(cb.build());
        	Twitter twitter = tf.getInstance();

        	Trends trends = twitter.getPlaceTrends(1); // 1 for global trends
        	List<String> trendNames = new ArrayList<String>();
        	for (int i = 0; i < trends.getTrends().length; i++) {
        		trendNames.add(trends.getTrends()[i].getName().toString());        		
        	}
        	return trendNames;	
	    } catch (TwitterException te) {
        	te.printStackTrace();
        	System.out.println("Failed to get trends: " + te.getMessage());
        	return new ArrayList<String>();
    	}
	}
}