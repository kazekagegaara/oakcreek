package SentimentGenerator;
import java.io.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class GenerateRDF{


public void generateRdf(List<EntityBean> entitylist, List<KeywordBean> keywordlist , String timeStamp, String cSentiment) throws IOException{



    Model m = ModelFactory.createDefaultModel();

     m.read("wsa.rdf");


    m.setNsPrefix("wsa","http://my.asu.edu/ontologies/ser594/team8Ontology#");

    String timestamp = timeStamp;

    if (entitylist.size() > 0) {
        for (int i = 0; i < entitylist.size(); i++) {

            EntityBean eb = entitylist.get(i);

            m = generateEntityRDF(m, eb.getEntityName(),eb.getDescription(),eb.getImage(),eb.getThumbnail(),eb.getRefrences(),eb.getRdfTypes(),eb.getLabel(),eb.getComment(),eb.getEntityType(),eb.getWebsite(),eb.getSentiment(),eb.getSocialMediaSource(),eb.getWebUrl(),eb.getLinkedDataSource(),timestamp);

        }
        
        
    }
    if (keywordlist.size() > 0){
        for (int i = 0; i < keywordlist.size(); i++) {

            KeywordBean kb = keywordlist.get(i);

             m = generateKeywordRdf(m, kb.getKeywordtext(),kb.getSentiment(),kb.getSocialMediaSource(),timestamp);

        }
    }
    // if(cSentiment != null) {
    System.out.println("DDDDDDDDddd--------------------------------E3##_#_#_#_#_#_#_#_#_#_#_#_#__#_#_#_#_#_#_##########_________#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_");
    System.out.println(cSentiment);
    System.out.println("DDDDDDDDddd--------------------------------E3##_#_#_#_#_#_#_#_#_#_#_#_#__#_#_#_#_#_#_##########_________#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_#_");
        m = writeCummulativeSentiment(m,timestamp, cSentiment);
    // }
    String fileName = "wsa.rdf";
        FileWriter out = new FileWriter( fileName );
        try {
            m.write(out);
        }
        finally {
               try {
                   out.close();
               }
               catch (IOException closeException) {
                   // ignore
               }
        }

    System.out.println("RDF written");

}


  public Model generateKeywordRdf(Model m, String KeywordText,String Sentiment,String[] SocialMediaSource,String timeStamp){


    String customURI = "http://my.asu.edu/ontologies/ser594/team8Ontology#Keyword";

    Model model = ModelFactory.createDefaultModel();

    model.setNsPrefix("wsa","http://my.asu.edu/ontologies/ser594/team8Ontology#");

    String uuidOne = UUID.randomUUID().toString();    

    Property about = model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
    Resource aboutKeyword = model.createResource(AnonId.create(uuidOne));
     aboutKeyword.addProperty(about,customURI);

    Property hasTimeStamp = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasTimestamp");
     Resource timestamp = model.createResource(AnonId.create(uuidOne));
     timestamp.addProperty(hasTimeStamp,timeStamp);

     

      if(KeywordText != null) {
        Property hasKeywordText = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasKeywordText");
        Resource hKeywordText = model.createResource(AnonId.create(uuidOne));
        hKeywordText.addProperty(hasKeywordText,KeywordText);
    } else {
        Property hasKeywordText = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasKeywordText");
        Resource hKeywordText = model.createResource(AnonId.create(uuidOne));
        hKeywordText.addProperty(hasKeywordText," ");
    }

    if(Sentiment != null) {
        Property hasSentiment = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasSentiment");
        Resource hSentiment = model.createResource(AnonId.create(uuidOne));
        hSentiment.addProperty(hasSentiment,Sentiment); 
    } else {
        Property hasSentiment = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasSentiment");
        Resource hSentiment = model.createResource(AnonId.create(uuidOne));
        hSentiment.addProperty(hasSentiment," "); 
    }

    if(SocialMediaSource.length > 0){

        for (int i =0 ; i <SocialMediaSource.length; i++){
            Property hasSMSource = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasSMSource");
            Resource hSMSource = model.createResource(AnonId.create(uuidOne));
            if(SocialMediaSource[i] != null) {
                hSMSource.addProperty(hasSMSource,SocialMediaSource[i]); 
            } else {
                hSMSource.addProperty(hasSMSource, " "); 
            }
            
        }
    }

    m.add(model);
     // now write the model in XML form to a file
    return m;

  }
  public Model generateEntityRDF(Model m, String EntityName,String Description,String Image,String Thumbnail,String References,String RdfTypes,String Label,String Comment,String EntityType,String Website,String Sentiment,String[] SocialMediaSource,String[] WebUrl,String LinkedDataSource,String timeStamp){


    


    String customURI = "http://my.asu.edu/ontologies/ser594/team8Ontology#Entity";
    Model model = ModelFactory.createDefaultModel();

    model.setNsPrefix("wsa","http://my.asu.edu/ontologies/ser594/team8Ontology#");

    String uuidOne = UUID.randomUUID().toString();    

    Property about = model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
    Resource aboutKeyword = model.createResource(AnonId.create(uuidOne));
     aboutKeyword.addProperty(about,customURI);

    Property hasTimeStamp = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasTimestamp");
    Resource timestamp = model.createResource(AnonId.create(uuidOne));
    timestamp.addProperty(hasTimeStamp,timeStamp);

    if(EntityName != null) {
        Property hasEntityName = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasEntityName");
        Resource hEntityName = model.createResource(AnonId.create(uuidOne));
        hEntityName.addProperty(hasEntityName,EntityName);
    } else {
        Property hasEntityName = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasEntityName");
        Resource hEntityName = model.createResource(AnonId.create(uuidOne));
        hEntityName.addProperty(hasEntityName," ");
    }
    
    if(Description != null) {
        Property hasDescription = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasDescription");
        Resource hDescription = model.createResource(AnonId.create(uuidOne));
        hDescription.addProperty(hasDescription,Description); 
    } else {
        Property hasDescription = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasDescription");
        Resource hDescription = model.createResource(AnonId.create(uuidOne));
        hDescription.addProperty(hasDescription," "); 
    }
    
    if(Image != null) {
        Property hasImage = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasImage");
        Resource hImage = model.createResource(AnonId.create(uuidOne));
        hImage.addProperty(hasImage,Image); 
    } else {
        Property hasImage = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasImage");
        Resource hImage = model.createResource(AnonId.create(uuidOne));
        hImage.addProperty(hasImage," "); 
    }
    
    if(Thumbnail != null) {
        Property hasThumbnail = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasThumbnail");
        Resource hThumbnail = model.createResource(AnonId.create(uuidOne));
        hThumbnail.addProperty(hasThumbnail,Thumbnail); 
    } else {
        Property hasThumbnail = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasThumbnail");
        Resource hThumbnail = model.createResource(AnonId.create(uuidOne));
        hThumbnail.addProperty(hasThumbnail," "); 
    }
    
    if(References != null) {
        Property hasReferences = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasReferences");
        Resource hReferences = model.createResource(AnonId.create(uuidOne));
        hReferences.addProperty(hasReferences,References);
    } else{
        Property hasReferences = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasReferences");
        Resource hReferences = model.createResource(AnonId.create(uuidOne));
        hReferences.addProperty(hasReferences," ");
    }
    
    if(RdfTypes != null) {
        Property hasRDFTypes = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasRDFTypes");
        Resource hRDFTypes = model.createResource(AnonId.create(uuidOne));
        hRDFTypes.addProperty(hasRDFTypes,RdfTypes);     
    } else {
        Property hasRDFTypes = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasRDFTypes");
        Resource hRDFTypes = model.createResource(AnonId.create(uuidOne));
        hRDFTypes.addProperty(hasRDFTypes," ");     
    }
    
    if(Label != null) {
        Property hasLabel = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasLabel");
        Resource hLabel = model.createResource(AnonId.create(uuidOne));
        hLabel.addProperty(hasLabel,Label); 
    } else {
        Property hasLabel = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasLabel");
        Resource hLabel = model.createResource(AnonId.create(uuidOne));
        hLabel.addProperty(hasLabel," "); 
    }
      
    
    if(Comment != null) {
        Property hasComment = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasComment");
        Resource hComment = model.createResource(AnonId.create(uuidOne));
        hComment.addProperty(hasComment,Comment);  
    } else {
        Property hasComment = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasComment");
        Resource hComment = model.createResource(AnonId.create(uuidOne));
        hComment.addProperty(hasComment," ");  
    }
    
    if(EntityType != null) {
        Property hasEntityType = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasEntityType");
        Resource hEntityType = model.createResource(AnonId.create(uuidOne));
        hEntityType.addProperty(hasEntityType,EntityType); 
    } else {
        Property hasEntityType = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasEntityType");
        Resource hEntityType = model.createResource(AnonId.create(uuidOne));
        hEntityType.addProperty(hasEntityType," "); 
    }
    
    if(Website != null) {
        Property hasWebsite = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasWebsite");
        Resource hWebsite = model.createResource(AnonId.create(uuidOne));
        hWebsite.addProperty(hasWebsite,Website);  
    } else {
        Property hasWebsite = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasWebsite");
        Resource hWebsite = model.createResource(AnonId.create(uuidOne));
        hWebsite.addProperty(hasWebsite," ");  
    }
    
    if(Sentiment != null) {
        Property hasSentiment = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasSentiment");
        Resource hSentiment = model.createResource(AnonId.create(uuidOne));
        hSentiment.addProperty(hasSentiment,Sentiment); 
    } else {
        Property hasSentiment = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasSentiment");
        Resource hSentiment = model.createResource(AnonId.create(uuidOne));
        hSentiment.addProperty(hasSentiment," "); 
    }
    

    if(SocialMediaSource.length > 0){

        for (int i =0 ; i <SocialMediaSource.length; i++){
            Property hasSMSource = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasSMSource");
            Resource hSMSource = model.createResource(AnonId.create(uuidOne));
            if(SocialMediaSource[i] != null) {
                hSMSource.addProperty(hasSMSource,SocialMediaSource[i]); 
            } else {
                hSMSource.addProperty(hasSMSource, " "); 
            }
            
        }
    }

    if (WebUrl.length > 0){
        
        for (int i =0 ; i <WebUrl.length; i++){
            Property hasUrl = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasUrl");
            Resource hUrl = model.createResource(AnonId.create(uuidOne));
            if(WebUrl[i] != null) {
                hUrl.addProperty(hasUrl,WebUrl[i]); 
            } else {
                hUrl.addProperty(hasUrl," "); 
            }
            
        }  

    }

    if(LinkedDataSource != null) {
        Property hasLDSource = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasLDSource");
        Resource hLDSource = model.createResource(AnonId.create(uuidOne));
        hLDSource.addProperty(hasLDSource,LinkedDataSource); 
    } else {
        Property hasLDSource = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasLDSource");
        Resource hLDSource = model.createResource(AnonId.create(uuidOne));
        hLDSource.addProperty(hasLDSource," "); 
    }
    

    m.add(model);
     // now write the model in XML form to a file
    return m;
  }


 public Model writeCummulativeSentiment(Model m, String timeStamp, String Sentiment){
   

    String customURI = "http://my.asu.edu/ontologies/ser594/team8Ontology#CumulativeSentiment";

    Model model = ModelFactory.createDefaultModel();

    model.setNsPrefix("wsa","http://my.asu.edu/ontologies/ser594/team8Ontology#");

    String uuidOne = UUID.randomUUID().toString();    

    Property about = model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
    Resource aboutKeyword = model.createResource(AnonId.create(uuidOne));
     aboutKeyword.addProperty(about,customURI);

    Property hasTimeStamp = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasTimestamp");
    Resource timestamp = model.createResource(AnonId.create(uuidOne));
    timestamp.addProperty(hasTimeStamp,timeStamp);


    if(Sentiment != null) {
        Property hasSentiment = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasCSentiment");
        Resource hSentiment = model.createResource(AnonId.create(uuidOne));
        hSentiment.addProperty(hasSentiment,Sentiment); 
    } else {
        Property hasSentiment = model.createProperty("http://my.asu.edu/ontologies/ser594/team8Ontology#hasCSentiment");
        Resource hSentiment = model.createResource(AnonId.create(uuidOne));
        hSentiment.addProperty(hasSentiment," "); 
    }
    
    m.add(model);

    
    return m;
 }




}