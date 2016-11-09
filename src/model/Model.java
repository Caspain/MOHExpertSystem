package model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import view.Main;

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
		public static ScrollPane Trigger( String age,String gender, String name,String weight, String ethnicity, String heightInches, String heightFeet, String waistCircumference,
				boolean tog1,boolean tog2,boolean tog3,boolean tog4,String category){
		
			org.jpl7.Integer ageI = new org.jpl7.Integer(Integer.parseInt(age));
			org.jpl7.Integer weightI = new org.jpl7.Integer(Integer.parseInt(weight));
			org.jpl7.Integer waistCircumI = new org.jpl7.Integer(Integer.parseInt(waistCircumference));
			org.jpl7.Integer heightFeetI = new org.jpl7.Integer(Integer.parseInt(heightFeet));
			org.jpl7.Integer heightInchesI = new org.jpl7.Integer(Integer.parseInt(heightInches)); 
			org.jpl7.Integer categoryI = null ;
			Atom nameS,genderS, ethnicityS,ExerAmt,VegFruits,HighBP,HighBG;
			switch(category){
			case "r1":{
				categoryI = new org.jpl7.Integer(0);
				break;	
				}
			case "r2":{
				categoryI = new org.jpl7.Integer(1);
				break;	
				}
			case "r3":{
				categoryI = new org.jpl7.Integer(2);
				break;	
				}
			}
			if(tog1){
				//excercise
				ExerAmt = new Atom("yes");	
			}else{
				ExerAmt = new Atom("no");	
			}
			
			
			if(tog2){
				//vegetables
				VegFruits = new Atom("yes");
			}else{
				VegFruits = new Atom("no");
			}
			if(tog3){
				HighBG = new Atom("yes");
			}else
			{
				HighBG = new Atom("no");
			}
			if(tog4){
				HighBP = new Atom("yes");
			}else{
				HighBP = new Atom("no");
			}
			//----------------------------------------------------------------------------------------------
			nameS = new Atom(name);
			ethnicityS = new Atom(ethnicity);
			genderS = new Atom(gender);
			//contruct query
			//Name,Age,Weight,Origin,Feet,Inches,WaistCir,ExerAmt,VegFruits,HighBP,HighBG,Gender,Category
			Term query = new Compound("engine",new Term[]{nameS,ageI,weightI,ethnicityS,heightFeetI,heightInchesI,waistCircumI,ExerAmt,VegFruits,HighBP,HighBG,genderS,categoryI});
			
			Query testQuery = new Query(query);
			
			 testQuery.open();
			 if(testQuery.hasMoreElements()){
				return ParseResult(testQuery.nextElement().toString()); //parse recomendations
			 }
			 testQuery.close();
			return null;
			
					
			
			
		}
	
	public static ScrollPane ParseResult(String result){
		int index = result.indexOf(":");
		//String recomendation  = result.substring((index+1), result.length()-1);
		//recomendation = recomendation.trim();
		System.out.println(result);
		return null;//DisplayRecomendations(result);
		
	}
	public  void AlertAuthoritiesOfSpike(){
		Variable X = new Variable("Trigger");
		
		Term q2 = new Compound("generate_alert", new Term[]{new Variable("Trigger")});
		Query q3 = new Query(q2 );
		
	    q3.open();
        if (q3.hasMoreElements()) {
			Object c = q3.nextElement();
			if(c!=null)
			System.out.println((c));
		}			
	}

	
}
