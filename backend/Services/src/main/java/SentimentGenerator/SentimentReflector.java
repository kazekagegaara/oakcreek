package SentimentGenerator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileManager;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.QuerySolution;

public class SentimentReflector{

    private String youtubeString = "";
    private String flickerString = "";

    private final long id;
    private final String content;
    public SentimentReflector(long id, String content) {
        this.id = id;
        this.content = content;
        
    }
     public SentimentReflector(long id) {
        this.id = id;
        this.content = "";
    }


    public String[] MainFunction() throws IOException{
        //SentimentReflector test = new SentimentReflector(counter.incrementAndGet());
        String returnData = "";
        //returnData += " Youtube-> ";
        returnData += getYouTubeData();
        //returnData += " Flickr-> ";
        returnData += getFlickrData();
        //returnData += " Twitter-> ";


        String wholeString = "";
        String twitterString = "";
        String youtubeString = "";
        String flickerString = "";
        //Twitter terms
        List<TwitterBean> tbeanList = getTwitterData();
        // // TwitterHelper th = new TwitterHelper();
        // tbeanList += 

        for (int i = 0; i < tbeanList.size(); i++) {
            TwitterBean tb = tbeanList.get(i);
            wholeString += tb.gettwitterTopic() + " ";
            twitterString += tb.gettwitterTopic() + " ";
        }

        //youtube terms
        // YoutubeHelper yt = new YoutubeHelper();
        List<YoutubeBean> youtubeBeans = getYouTubeData();
        for (int i = 0; i < youtubeBeans.size(); i++) {
            YoutubeBean element = youtubeBeans.get(i);
            wholeString += element.getvideoTitle() + " ";
            youtubeString += element.getvideoTitle() + " ";
            wholeString += element.getdescription() + " " ; 
            youtubeString += element.getdescription() + " " ;             
            wholeString += element.getchannelTitle() + " ";
            youtubeString += element.getchannelTitle() + " ";
            String[] temp = element.gettags();
            for (int j=0; j<temp.length ;j++){
                wholeString += temp[j] + " ";
                youtubeString += temp[j] + " ";
            }                           
        }


        //flickerTerms
        // FlickrHelper flk = new FlickrHelper();
        List<FlickrBean> flickerBeans = getFlickrData();
        for (int i = 0; i < flickerBeans.size(); i++) {
            FlickrBean element = flickerBeans.get(i);
            wholeString += element.gettitle() + " "; 
            flickerString += element.gettitle() + " ";                          
        }
    
        

        System.out.println("DATA --------------------------------------------------------------------------------------------------------------------- START");
        System.out.println(twitterString);
        System.out.println("twitter --------------------------------------------------------------------------------------------------------------------- START");
        System.out.println(flickerString);
        System.out.println("FLICKER --------------------------------------------------------------------------------------------------------------------- START");
        System.out.println(youtubeString);
        System.out.println("DATA --------------------------------------------------------------------------------------------------------------------- END");

        String cummulativeSentiment = "";
        
       
        cummulativeSentiment += getSentimentFromAlchemy(wholeString); 
        

        List<EntityBean> twitterEntitysList = getEntitiesFromAlchemy(twitterString,"Twitter");
        
        List<EntityBean> youtubeEntitysList = getEntitiesFromAlchemy(youtubeString,"YouTube");

        List<EntityBean> flickerEntitysList = getEntitiesFromAlchemy(flickerString,"Flicker");

        List<EntityBean> combinedList =  new ArrayList<EntityBean>();

        List<EntityBean> newtwitterEntitysList = new ArrayList<EntityBean>();
        List<EntityBean> newyoutubeEntitysList = new ArrayList<EntityBean>();
        List<EntityBean> newflickerEntitysList = new ArrayList<EntityBean>();

        System.out.println("ENTITY SIZE DATA--------------------------------------------------------------------------------------------------------------------------------------------------------- ENTITY SIZE DATA");
        System.out.println("No of twitter entities "+twitterEntitysList.size());
        System.out.println("No of flicker entities "+flickerEntitysList.size());
        System.out.println("No of Youtube entities "+youtubeEntitysList.size());
        System.out.println("ENTITY SIZE DATA ------------------------------------------------------------------------------------------------------------------------------------------------------- ENTITY SIZE DATA");
        
       
        if(tbeanList.size() > 0){
            if(twitterEntitysList.size()>0){
               for (int i = 0; i < twitterEntitysList.size(); i++) {
                        EntityBean tbb = new EntityBean();
                         tbb = twitterEntitysList.get(i);
                         String enttyName = tbb.getEntityName();
                       for (int j = 0; j < tbeanList.size(); j++) {
                        TwitterBean tbbb = new TwitterBean();
                        tbbb = tbeanList.get(j);
                        if(enttyName.equals(tbbb.gettwitterTopic())){
                    String url = tbbb.gettwitterURL();
                    String[] webur = {url};
                    tbb.setWebUrl(webur);
                    }
                    }
                    newtwitterEntitysList.add(tbb);
                } 
            }
        }


          if(youtubeBeans.size() > 0){
            if(youtubeEntitysList.size()>0){

                for (int i = 0; i < youtubeEntitysList.size(); i++) {
            EntityBean tbb = new EntityBean();
            tbb = youtubeEntitysList.get(i);
            String enttyName = tbb.getEntityName();
            for (int j = 0; j < youtubeBeans.size(); j++) {
                YoutubeBean tbbb = new YoutubeBean();
                tbbb = youtubeBeans.get(j);
                if(enttyName.equals(tbbb.getvideoTitle()))
                {
                    String url = tbbb.getYTUrl();
                    String[] webur = {url};
                    tbb.setWebUrl(webur);
                }else if(enttyName.equals(tbbb.getdescription()))
                {
                    String url = tbbb.getYTUrl();
                    String[] webur = {url};
                    tbb.setWebUrl(webur);

                }else if(enttyName.equals(tbbb.getchannelTitle()))
                {
                    String url = tbbb.getYTUrl();
                    String[] webur = {url};
                    tbb.setWebUrl(webur);
                }
                else{
                    String[] temp = tbbb.gettags();
                    for (int k=0; k<temp.length ;k++){
                        if(enttyName.equals(temp[k])){
                            String url = tbbb.getYTUrl();
                            String[] webur = {url};
                            tbb.setWebUrl(webur);
                        }
                    }  
                }
            }
            newyoutubeEntitysList.add(tbb);
        }

            }

        }
        


        if(flickerBeans.size() > 0){
            if(flickerEntitysList.size()>0){

                for (int i = 0; i < flickerEntitysList.size(); i++) {
            EntityBean tbb = new EntityBean();
            tbb = flickerEntitysList.get(i);
            String enttyName = tbb.getEntityName();
            for (int j = 0; j < flickerBeans.size(); j++) {
                FlickrBean tbbb = new FlickrBean();
                tbbb = flickerBeans.get(j);
                if(enttyName.equals(tbbb.gettitle()))
                {
                    String url = tbbb.getflickerURL();
                    String[] webur = {url};
                    tbb.setWebUrl(webur);
                }
            }
            newflickerEntitysList.add(tbb);
            }
            }
        }
        

        

        


        System.out.println("ENTITY SIZE DATA--------------------------------------------------------------------------------------------------------------------------------------------------------- ENTITY SIZE DATA");
        System.out.println("No of new twitter entities "+newtwitterEntitysList.size());
        System.out.println("No of new flicker entities "+newflickerEntitysList.size());
        System.out.println("No of new Youtube entities "+newyoutubeEntitysList.size());
        System.out.println("ENTITY SIZE DATA ------------------------------------------------------------------------------------------------------------------------------------------------------- ENTITY SIZE DATA");
        


        // for (int i = 0; i < twitterEntitysList.size(); i++) {
        //     combinedList.add(twitterEntitysList.get(i));
        // }
        // for (int i = 0; i < youtubeEntitysList.size(); i++) {
        //     combinedList.add(youtubeEntitysList.get(i));
        // }
        // for (int i = 0; i < flickerEntitysList.size(); i++) {
        //     combinedList.add(flickerEntitysList.get(i));
        // }






        for (int i = 0; i < newtwitterEntitysList.size(); i++) {
            combinedList.add(newtwitterEntitysList.get(i));
        }
        for (int i = 0; i < newyoutubeEntitysList.size(); i++) {
            combinedList.add(newyoutubeEntitysList.get(i));
        }
        for (int i = 0; i < newflickerEntitysList.size(); i++) {
            combinedList.add(newflickerEntitysList.get(i));
        }

        List<EntityBean> fullList = getDBpediaData(combinedList); 

        System.out.println("ENTITY FULL SIZE DATA ------------------------------------------------------------------------------------------------------------------------------------------------------- ENTITY SIZE DATA");
        System.out.println("ALL entities "+fullList.size());
        System.out.println("ENTITY FUll SIZE DATA ------------------------------------------------------------------------------------------------------------------------------------------------------- ENTITY SIZE DATA");
        



        List<KeywordBean> twitterkwb = getKeywordsFromAlchemy(twitterString,"Twitter");
        
        List<KeywordBean> youtubekwb = getKeywordsFromAlchemy(youtubeString,"YouTube");

        List<KeywordBean> flickerkwb = getKeywordsFromAlchemy(flickerString,"Flicker");

        List<KeywordBean> combinedkwb =  new ArrayList<KeywordBean>();

        System.out.println("Keyword SIZE DATA----------------------------------------------------------------------------------------------------------------------------------------------- Keyword SIZE DATA");
        System.out.println("No of twitter keywords "+twitterkwb.size());
        System.out.println("No of flicker keywords "+flickerkwb.size());
        System.out.println("No of Youtube keywords "+youtubekwb.size());
        System.out.println("Keyword SIZE DATA---------------------------------------------------------------------------------------------------------------------------------------------- Keyword SIZE DATA");

        for (int i = 0; i < twitterkwb.size(); i++) {
            combinedkwb.add(twitterkwb.get(i));
        }
        for (int i = 0; i < youtubekwb.size(); i++) {
            combinedkwb.add(youtubekwb.get(i));
        }
        for (int i = 0; i < flickerkwb.size(); i++) {
            combinedkwb.add(flickerkwb.get(i));
        }



        String timestamp = Long.toString(System.currentTimeMillis());


        GenerateRDF genRDF = new GenerateRDF();

        genRDF.generateRdf(fullList, combinedkwb, timestamp, cummulativeSentiment);

        String [] arr = {cummulativeSentiment,timestamp};
        return arr;
    }

    //will return keywords
    public List<CallTwoObject>  MainFunction2 (String timestamp){
       List<CallTwoObject> result = new ArrayList<CallTwoObject>();
        String TimeStamp = timestamp;
        // String TimeStamp = timestamp;
        result = ExecuteSparqlQueryKeyWords(TimeStamp);
        return result;
    }

    // will return entities
    public List<CallTwoObject>  MainFunction3 (String timestamp){
       List<CallTwoObject> result = new ArrayList<CallTwoObject>();
        String TimeStamp = timestamp;
        // String TimeStamp = timestamp;
        result = ExecuteSparqlQueryEntities(TimeStamp);
        return result;
    }

    // getDeatilsCalls
    public List<CallThreeObject> getDetailsEntity (String name, String timestamp){
        List<CallThreeObject> result = new ArrayList<CallThreeObject>();
        String Name = name;
        String TimeStamp = timestamp;
        
        result = getDetailsEntitiesSQL(Name, TimeStamp);
        return result;
    }

    public List<CallThreeObject>   getDetailsKeyword (String name, String timestamp){
       List<CallThreeObject>  result = new ArrayList<CallThreeObject>();
        String Name = name;
        String TimeStamp = timestamp;
        result = getDetailsKeywordsSQL(Name, TimeStamp);
        return result;
    }

     public List<CallFourObject>   getHistoryAll (){
       List<CallFourObject>  result = new ArrayList<CallFourObject>();
        
        result = getHistorySQL();
        return result;
    }


    public List<EntityBean> getDBpediaData (List<EntityBean> entityList){

        SparqlHelper sprk  = new SparqlHelper();
        List<EntityBean> newListEntity = new ArrayList<EntityBean>() ; 
        for (int i = 0; i < entityList.size(); i++) {
            EntityBean element = entityList.get(i);
            if ((!element.getLinkedDataSource().equals("doesn't have DBPEDIALINK")) && (!element.getLinkedDataSource().matches(".*\\d+.*")) && (!element.getLinkedDataSource().contains((","))) ){

            
            List<String> allList = sprk.getInfoFromDBPedia(element.getLinkedDataSource());
            element.setDescription(allList.get(0));
            element.setImage(allList.get(1));
            element.setThumbnail(allList.get(2));
            element.setRefrences(allList.get(3));
            element.setRdfTypes(allList.get(4));
            element.setLabel(allList.get(5));
            element.setComment(allList.get(6));
            }
            newListEntity.add(element);

        }

        return newListEntity;

    }

    public List<TwitterBean> getTwitterData() {
        TwitterHelper th = new TwitterHelper();
        List<TwitterBean> trendNames = th.getTwitterTrending();
    
        return  trendNames;
    }

    public List<YoutubeBean> getYouTubeData() {
        YoutubeHelper yt = new YoutubeHelper();
        List<YoutubeBean> youtubeBeans = new ArrayList<YoutubeBean>() ;
        try{
            youtubeBeans = yt.getYoutubeTrending();
           
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
       
        return  youtubeBeans;
    }
    public List<FlickrBean> getFlickrData(){
        FlickrHelper flk = new FlickrHelper();
        List<FlickrBean> flickerBeans = new ArrayList<FlickrBean>() ; 
        try{
            flickerBeans = flk.getFlickrTrending();
        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
       
        return flickerBeans;
    }


   


    public String getSentimentFromAlchemy(String text) {
        AlchemyHelper ah = new AlchemyHelper();
        String sentiment = "";
        try{
            sentiment = ah.getTextSentiment(text);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return sentiment;
    }


    public List<EntityBean> getEntitiesFromAlchemy(String text , String source) {
        
        AlchemyHelper ah = new AlchemyHelper();
        
        List<EntityBean> entityBeans = new ArrayList<EntityBean>();
        
        List<EntityBean> newEntitysList = new ArrayList<EntityBean>();
        
        try{
            entityBeans = ah.getEntities(text);
            for (int i = 0; i < entityBeans.size(); i++) {
                EntityBean element = entityBeans.get(i);

                if (source == "Twitter"){
                    String [] str = {"Twitter"};
                    element.setWebsite("www.twitter.com");
                    element.setSocialMediaSource(str);
                }else if(source == "YouTube"){
                    String [] str = {"YouTube"};
                    element.setWebsite("www.youtube.com");
                    element.setSocialMediaSource(str);
                } else if (source == "Flicker") {
                    String [] str = {"Flicker"};
                    element.setWebsite("www.flicker.com");
                    element.setSocialMediaSource(str);
                }
                newEntitysList.add(element);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return newEntitysList;
    }

    public List<KeywordBean> getKeywordsFromAlchemy(String text  , String source) {
        AlchemyHelper ah = new AlchemyHelper();
        List<KeywordBean> keywordBeans = new ArrayList<KeywordBean>();
        List<KeywordBean> newKeywordBeans = new ArrayList<KeywordBean>();
        try{
            keywordBeans = ah.getKeywords(text);
            for (int i = 0; i < keywordBeans.size(); i++) {
                KeywordBean keywordELE = keywordBeans.get(i);
                if (source == "Twitter"){
                    String [] str = {"Twitter"};
                    keywordELE.setSocialMediaSource(str);
                }else if(source == "YouTube"){
                    String [] str = {"YouTube"};
                    keywordELE.setSocialMediaSource(str);
                } else if (source == "Flicker") {
                    String [] str = {"Flicker"};
                    keywordELE.setSocialMediaSource(str);
                }
                newKeywordBeans.add(keywordELE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return newKeywordBeans;
    }


    public List<CallTwoObject> ExecuteSparqlQueryKeyWords(String timestamp){

        List<CallTwoObject> list = new ArrayList<CallTwoObject>();

        Model m = ModelFactory.createDefaultModel();
        m.read("wsa.rdf");

        String queryString = "" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +            
        "PREFIX wsa:  <http://my.asu.edu/ontologies/ser594/team8Ontology#>\n" +                
        "\n" +
        "SELECT ?hasSentiment ?hasKeywordText WHERE {\n" +        
        " ?x wsa:hasTimestamp '"+ timestamp +"' . \n" +
        " ?x wsa:hasKeywordText ?hasKeywordText .\n" +
        " ?x wsa:hasSentiment ?hasSentiment \n" +
        "}";                

        System.out.println(queryString);

        Query query = QueryFactory.create(queryString);
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        // ResultSetFormatter.out(System.out, results, query);

        while (results.hasNext()){
            QuerySolution soln = results.nextSolution();
            RDFNode name = soln.get("?hasKeywordText");
            String keyword = " ";
            String sentiment = " ";
            
            if( name != null ){
                System.out.println( "Keyword TEXT is " + name.toString());
                keyword = name.toString();
            }
            else{
                System.out.println("NO Keyword TEXT.");
            }
            RDFNode senti = soln.get("?hasSentiment");
            if( senti != null ){
                System.out.println( "Sentiment TEXT is " + senti.toString());
                sentiment = senti.toString();
            }
            else{
                System.out.println("NO Sentiment TEXT.");
            }
            CallTwoObject clt = new CallTwoObject(keyword,sentiment);
            list.add(clt);
        }
        System.out.println("RESULTSETOUT");
        return list;
    }

    public List<CallTwoObject> ExecuteSparqlQueryEntities(String timestamp){

        List<CallTwoObject> list = new ArrayList<CallTwoObject>();

        Model m = ModelFactory.createDefaultModel();
        m.read("wsa.rdf");

        // String timestamp = "1449277455000";

        String queryString = "" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +            
        "PREFIX wsa:  <http://my.asu.edu/ontologies/ser594/team8Ontology#>\n" +                
        "\n" +
        "SELECT ?hasSentiment ?hasEntityName WHERE {\n" +        
        " ?x wsa:hasTimestamp '"+ timestamp +"' . \n" +
        " ?x wsa:hasEntityName ?hasEntityName .\n" +
        " ?x wsa:hasSentiment ?hasSentiment \n" +
        "}";                

        System.out.println(queryString);

        Query query = QueryFactory.create(queryString);
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        // ResultSetFormatter.out(System.out, results, query);

        while (results.hasNext()){
            QuerySolution soln = results.nextSolution();
            RDFNode name = soln.get("?hasEntityName");
            String entityname = " ";
            String sentiment = " ";
            
            if( name != null ){
                System.out.println( "Entities TEXT is " + name.toString());
                entityname = name.toString();
            }
            else{
                System.out.println("NO Entities TEXT.");
            }
            RDFNode senti = soln.get("?hasSentiment");
            if( senti != null ){
                System.out.println( "Entities Sentiment is " + senti.toString());
                sentiment = senti.toString();
            }
            else{
                System.out.println("Entities NO Sentiment TEXT.");
            }
            CallTwoObject clt = new CallTwoObject(entityname,sentiment);
            list.add(clt);
        }
        System.out.println("RESULTSETOUT");
        return list;
    }

    public List<CallThreeObject> getDetailsEntitiesSQL(String name, String timestamp){

        List<CallThreeObject> list = new ArrayList<CallThreeObject>();

        Model m = ModelFactory.createDefaultModel();
        m.read("wsa.rdf");

        // String timestamp = "1449277455000";

        String queryString = "" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +            
        "PREFIX wsa:  <http://my.asu.edu/ontologies/ser594/team8Ontology#>\n" +                
        "\n" +
        "SELECT ?hasSentiment ?hasEntityName ?hasDescription ?hasComment ?hasLabel ?hasSMSource ?hasLDSource ?hasWebsite ?hasReferences ?hasEntityType ?hasImage ?hasThumbnail ?hasRDFTypes ?hasUrl WHERE {\n" +        
        " ?x wsa:hasTimestamp '"+ timestamp +"' . \n" +
        " ?x wsa:hasEntityName '"+ name +"' . \n" +
        " ?x wsa:hasEntityName ?hasEntityName .\n" +
        " ?x wsa:hasDescription ?hasDescription .\n" +
        " ?x wsa:hasComment ?hasComment .\n" +
        " ?x wsa:hasLabel ?hasLabel .\n" +
        " ?x wsa:hasSMSource ?hasSMSource .\n" +
        " ?x wsa:hasLDSource ?hasLDSource .\n" +
        " ?x wsa:hasWebsite ?hasWebsite .\n" +
        " ?x wsa:hasReferences ?hasReferences .\n" +
        " ?x wsa:hasEntityType ?hasEntityType .\n" +
        " ?x wsa:hasImage ?hasImage .\n" +
        " ?x wsa:hasThumbnail ?hasThumbnail .\n" +
        " ?x wsa:hasRDFTypes ?hasRDFTypes .\n" +
        " ?x wsa:hasSentiment ?hasSentiment .\n" +
        " ?x wsa:hasUrl ?hasUrl \n" +
        "}";                

        System.out.println(queryString);

        Query query = QueryFactory.create(queryString);
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        // ResultSetFormatter.out(System.out, results, query);

        while (results.hasNext()){

            QuerySolution soln = results.nextSolution();
            CallThreeObject eb = new CallThreeObject();
            eb.setEntityName(soln.get("?hasEntityName").toString());
            eb.setDescription(soln.get("?hasDescription").toString());
            eb.setImage(soln.get("?hasImage").toString());
            eb.setThumbnail(soln.get("?hasThumbnail").toString());
            eb.setRefrences(soln.get("?hasReferences").toString());
            eb.setRdfTypes(soln.get("?hasRDFTypes").toString());
            eb.setLabel(soln.get("?hasLabel").toString());
            eb.setComment(soln.get("?hasComment").toString());
            eb.setEntityType(soln.get("?hasEntityType").toString());
            eb.setWebsite(soln.get("?hasWebsite").toString());
            eb.setSentiment(soln.get("?hasSentiment").toString());
            String [] SMS = {soln.get("?hasSMSource").toString()};
            eb.setSocialMediaSource(SMS);
            String [] wburl = {soln.get("?hasUrl").toString()};
            eb.setWebUrl(wburl);
            eb.setLinkedDataSource(soln.get("?hasLDSource").toString());
            list.add(eb);
        }
        System.out.println("RESULTSETOUT");
        return list;
    }


    public List<CallThreeObject> getDetailsKeywordsSQL(String name, String timestamp){

        List<CallThreeObject> list = new ArrayList<CallThreeObject>();

        Model m = ModelFactory.createDefaultModel();
        m.read("wsa.rdf");

        String queryString = "" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +            
        "PREFIX wsa:  <http://my.asu.edu/ontologies/ser594/team8Ontology#>\n" +                
        "\n" +
        "SELECT ?hasSentiment ?hasKeywordText ?hasSMSource WHERE {\n" +        
        " ?x wsa:hasTimestamp '"+ timestamp +"' . \n" +
        " ?x wsa:hasKeywordText '"+ name +"' . \n" +
        " ?x wsa:hasSMSource ?hasSMSource .\n" +
        " ?x wsa:hasKeywordText ?hasKeywordText .\n" +
        " ?x wsa:hasSentiment ?hasSentiment \n" +
        "}";                

        System.out.println(queryString);

        Query query = QueryFactory.create(queryString);
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        // ResultSetFormatter.out(System.out, results, query);

        while (results.hasNext()){

            QuerySolution soln = results.nextSolution();
            CallThreeObject kb = new CallThreeObject();
            String keywordText = "";
            String keywordSentiment = "";
            String [] keyworkdSource = {""};
            
            RDFNode keyText = soln.get("?hasKeywordText");
            if( keyText != null ){
                keywordText = keyText.toString();
            }else{
                keywordText = " ";
            }

            RDFNode keyTextSentiment = soln.get("?hasSentiment");
            if( keyTextSentiment != null ){
                keywordSentiment = keyTextSentiment.toString();
            }else{
                keywordSentiment = " ";
            }

            RDFNode keyTextSource = soln.get("?hasSMSource");
            if( keyTextSource != null ){
                String [] SMS = {keyTextSource.toString()};
                keyworkdSource = SMS;
            }else{
                keyworkdSource = new String[0];
            }

            kb.setEntityName(keywordText);
            kb.setSentiment(keywordSentiment);
            kb.setSocialMediaSource(keyworkdSource);
            kb.setDescription(" ");
            kb.setImage(" ");
            kb.setThumbnail(" ");
            kb.setRefrences(" ");
            kb.setRdfTypes(" ");
            kb.setLabel(" ");
            kb.setComment(" ");
            kb.setEntityType(" ");
            kb.setWebsite(" ");
            String [] wburl = {""};
            kb.setWebUrl(wburl);
            kb.setLinkedDataSource(" ");
            list.add(kb);
        }
        System.out.println("RESULTSETOUT");
        return list;
    }



    public List<CallFourObject>  getHistorySQL(){

        List<CallFourObject>  list = new ArrayList<CallFourObject>();

        Model m = ModelFactory.createDefaultModel();
        m.read("wsa.rdf");

        String queryString = "" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +            
        "PREFIX wsa:  <http://my.asu.edu/ontologies/ser594/team8Ontology#>\n" +                
        "\n" +
        "SELECT ?hasCSentiment ?hasTimestamp WHERE {\n" +        
        " ?x wsa:hasCSentiment ?hasCSentiment .\n" +
        " ?x wsa:hasTimestamp ?hasTimestamp \n" +
        "}";                

        System.out.println(queryString);

        Query query = QueryFactory.create(queryString);
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, m);
        ResultSet results = qe.execSelect();
        // ResultSetFormatter.out(System.out, results, query);

        while (results.hasNext()){

            QuerySolution soln = results.nextSolution();
            CallFourObject kb = new CallFourObject(soln.get("?hasTimestamp").toString(),soln.get("?hasCSentiment").toString());
            list.add(kb);
        }
        System.out.println("RESULTSETOUT");
        return list;
    }


}