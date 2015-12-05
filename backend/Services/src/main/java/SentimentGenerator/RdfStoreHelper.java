package SentimentGenerator;


import java.io.IOException;
import java.io.InputStream;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class RdfStoreHelper {

	// static String defaultNameSpace = "http://my.asu.edu/ontologies/ser594/team8Ontology#";
	
	// Model _friends = null;
	// Model schema = null;
	// Location TDBLocation = "";
	// public Model getModel(){
	// 	// this.schema=ModelFactory.createDefaultModel();
 //  // 		schema.read("oakcreek/backend/Services/Ontologies/team8Ontology.owl");
 //  // 		File modelDirectory=new File(tdbLocation);
 //  // 		try {
 //  //   		this.model=TDBFactory.createModel(modelDirectory.getCanonicalPath());
 // 	// 	 }
 // 	// 	catch (  IOException e) {
 //  //   		e.printStackTrace();
 //  // 		}
	//  // 	Reasoner reasoner=ReasonerRegistry.getRDFSReasoner();
	//  //  	this.infModel=ModelFactory.createInfModel(reasoner,schema,model);
	// }


	// private void populateNewFriendsSchema() throws IOException 
	// {
	// 	  // InputStream inFoafInstance = FileManager.get().open("oakcreek/backend/Services/Ontologies/team8Ontology.owl");
	// 	  // schema.read(inFoafInstance,defaultNameSpace);
	// 	  // inFoafInstance.close();
	// }

}