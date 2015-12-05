package SentimentGenerator;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import java.util.List;
import java.util.ArrayList;

public class SparqlHelper {

    public List<String> getInfoFromDBPedia(String resourceURL) {
        
        String resource = resourceURL.split("/")[4];
        System.out.println(resourceURL);

        String description = "";
        String image = "";
        String thumbnail = "";
        String references = "";
        String rdfTypes = "";
        String label = "";
        String comment = "";

        ParameterizedSparqlString qs = new ParameterizedSparqlString( "" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX foaf:  <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX dbr:  <http://dbpedia.org/resource/>\n" +                
                "\n" +
                "SELECT ?description ?image ?thumbnail (group_concat(distinct ?reference;separator=';') as ?references) (group_concat(distinct ?rdfType;separator=';') as ?rdfTypes) ?label ?comment WHERE {\n" +
                "  dbr:" + resource + " dbo:abstract ?description.FILTER langMatches(lang(?description),'en') \n" +
                "  dbr:" + resource + " foaf:depiction ?image . \n" +
                "  dbr:" + resource + " dbo:thumbnail ?thumbnail . \n" +
                "  dbr:" + resource + " dbo:wikiPageExternalLink ?reference . \n" +
                "  dbr:" + resource + " a ?rdfType . \n" +
                "  dbr:" + resource + " rdfs:label ?label .FILTER langMatches(lang(?label),'en') \n" +
                "  dbr:" + resource + " rdfs:comment ?comment.FILTER langMatches(lang(?comment),'en') \n" +
                "} \n" +
                "GROUP BY ?description ?image ?thumbnail ?label ?comment ");                

System.out.println(qs.toString());
        QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
        
        ResultSet results = exec.execSelect();
        System.out.println("GOTResult");
        while ( results.hasNext() ) {        
            QuerySolution result = results.next();
            description = result.get( "description" ).toString();
            image = result.get( "image" ).toString();
            thumbnail = result.get( "thumbnail" ).toString();
            references = result.get( "references" ).toString();
            rdfTypes = result.get( "rdfTypes" ).toString();
            label = result.get( "label" ).toString();
            comment = result.get( "comment" ).toString();
        }        

        System.out.println(description);
        System.out.println(image);
        System.out.println(thumbnail);
        System.out.println(references);
        System.out.println(rdfTypes);
        System.out.println(label);
        System.out.println(comment);

        List<String> dbpediaData = new ArrayList<String>();
        dbpediaData.add(description);
        dbpediaData.add(image);
        dbpediaData.add(thumbnail);
        dbpediaData.add(references);
        dbpediaData.add(rdfTypes);
        dbpediaData.add(label);
        dbpediaData.add(comment);

        return dbpediaData;

    }
}