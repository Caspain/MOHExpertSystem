package model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

public class Model {

	public void consultDb(){
		String file = (System.getProperty("user.dir")+"\\brain.pl");
		Query q1 = 
			    new Query( 
			        "consult", 
			        new Term[] {new Atom(file)} 
			    );
		
		System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
	}
	public Model(){
		consultDb();
	}

		public static HashMap<String, Object> map=null;
		
		public static void getData(HashMap<String, Object> n){
			map = n;
		}
		public static void Trigger( String age,String gender, String name,String weight, String ethnicity, String heightInches, String heightFeet, String waistCircumference,
				boolean tog1,boolean tog2,boolean tog3,boolean tog4,String category){
		
			
		}
	
	
	public  void AlertAuthoritiesOfSpike(){
		Variable X = new Variable("Trigger");
		Term q2 = new Compound("generate_alert", new Term[]{new Variable("Trigger")});
		Query q3 = new Query(q2 );
		
	    q3.open();
        if (q3.hasMoreElements()) {
			Object c = q3.nextElement();
			System.out.println(  (c));
		}
      
				
	}
	
}
