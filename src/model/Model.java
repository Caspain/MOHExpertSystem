/*package model;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPTermParser;

public class Model {
	public void Logic(){
		try{
		JIPEngine engine =new JIPEngine();
		JIPTerm query = null;
		 engine.consultFile("C:\\Users\\remar_000.GMACHINE\\Documents\\JavaWorkSpace\\PrologMOH\\read.pl");
		 JIPTermParser termParser = engine.getTermParser();
		 
		query =  termParser.parseTerm("main.");
		// open a synchronous query
		JIPQuery jipQuery = engine.openSynchronousQuery(query);
		JIPTerm solution;

		// Loop while there is another solution
		while (jipQuery.hasMoreChoicePoints())
		{
		    solution = jipQuery.nextSolution();
		    if(solution!=null)
		    System.out.println(solution );
		}
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
*/