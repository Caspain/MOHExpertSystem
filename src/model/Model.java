package model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import view.Main;

public class Model {

	public void consultDb() {
		String file = (System.getProperty("user.dir") + "\\brain.pl");
		Query q1 = new Query("consult", new Term[] { new Atom(file) });

		System.out.println("consult " + (q1.hasSolution() ? "succeeded" : "failed"));
	}

	public Model() {
		consultDb();
	}

	public static HashMap<String, Object> map = null;

	public static void getData(HashMap<String, Object> n) {
		map = n;
	}

	public static String Trigger(String age, String gender, String name, String weight, String ethnicity,
			String heightInches, String heightFeet, String waistCircumference, boolean tog1, boolean tog2, boolean tog3,
			boolean tog4, String category) {

		org.jpl7.Integer ageI = new org.jpl7.Integer(Integer.parseInt(age));
		org.jpl7.Integer weightI = new org.jpl7.Integer(Integer.parseInt(weight));
		org.jpl7.Integer waistCircumI = new org.jpl7.Integer(Integer.parseInt(waistCircumference));
		org.jpl7.Integer heightFeetI = new org.jpl7.Integer(Integer.parseInt(heightFeet));
		org.jpl7.Integer heightInchesI = new org.jpl7.Integer(Integer.parseInt(heightInches));
		org.jpl7.Integer categoryI = null;
		Atom nameS, genderS, ethnicityS, ExerAmt, VegFruits, HighBP, HighBG;
		switch (category) {
		case "r1": {
			categoryI = new org.jpl7.Integer(0);
			break;
		}
		case "r2": {
			categoryI = new org.jpl7.Integer(1);
			break;
		}
		case "r3": {
			categoryI = new org.jpl7.Integer(2);
			break;
		}
		}
		if (tog1) {
			// excercise
			ExerAmt = new Atom("yes");
		} else {
			ExerAmt = new Atom("no");
		}

		if (tog2) {
			// vegetables
			VegFruits = new Atom("yes");
		} else {
			VegFruits = new Atom("no");
		}
		if (tog3) {
			HighBG = new Atom("yes");
		} else {
			HighBG = new Atom("no");
		}
		if (tog4) {
			HighBP = new Atom("yes");
		} else {
			HighBP = new Atom("no");
		}
		// ----------------------------------------------------------------------------------------------
		nameS = new Atom(name);
		ethnicityS = new Atom(ethnicity);
		genderS = new Atom(gender);
		// contruct query
		// Name,Age,Weight,Origin,Feet,Inches,WaistCir,ExerAmt,VegFruits,HighBP,HighBG,Gender,Category
		Term query = new Compound("engine", new Term[] { nameS, ageI, weightI, ethnicityS, heightFeetI, heightInchesI,
				waistCircumI, ExerAmt, VegFruits, HighBP, HighBG, genderS, categoryI });

		Query testQuery = new Query(query);
       
		if(testQuery.hasMoreElements()){
		Object c =testQuery.nextElement();
	
		}
	
		return  "";
		
	

	}


	public void AlertAuthoritiesOfSpike() {
		LoadDataBase();
		getMinimumAge();
		Variable X = new Variable("Trigger");
		Term q2 = new Compound("generate_alert", new Term[] { X});
		Query q3 = new Query(q2);
		q3.open();
		if (q3.hasMoreElements()) {
			Object c = q3.nextElement();
			if (c != null){
				 String[] args= c.toString().split("=");
				 if(args[1].charAt(args[1].length()-2) =='0'){
						
						Alert alert = new Alert(AlertType.INFORMATION);

						alert.setTitle("Query Response");
						alert.setHeaderText("returned : 0 ");
						alert.setContentText("no increase in diabetes level");
						
						Optional<ButtonType> result = alert.showAndWait();
						
						
				   	 System.out.println("no increase in diabetes level. " + args[1].charAt(args[1].length()-2));
				 }
			  else{
			    	 System.out.println("ALERT: Over 75% of database records are [HIGH/VERY HIGH] risk for Type 2 Diabetes." + args[1].charAt(args[1].length()-2));
			    		Alert alert = new Alert(AlertType.INFORMATION);

						alert.setTitle("Query Response");
						alert.setHeaderText("returned : 1 ");
						alert.setContentText("ALERT: Over 75% of database records are [HIGH/VERY HIGH] risk for Type 2 Diabetes.");
						
						Optional<ButtonType> result = alert.showAndWait();
			     }
			}
		}
	}
	
	private void LoadDataBase(){
		Query quer = new Query("load_database");
		quer.open();
		quer.hasMoreElements();
	}

	/*
	 * get youngest person in db
	 */
	private void getMinimumAge(){
		
		Variable age = new Variable("MinAge");
		Query ageTerm = new Query("stat_min_age",new Term[]{age});
		
		
		System.out.println("getting age min");
		if(ageTerm.hasMoreElements()){
		
			Object c = ageTerm.nextElement();
			if(c!=null){
				{
					 //append a curly bracket to it
					 
					 System.out.println(c);
					 Alert alert = new Alert(AlertType.INFORMATION);

						alert.setTitle("Query Response");
						alert.setHeaderText("returned : Min Age ");
						alert.setContentText(c.toString());
						
						Optional<ButtonType> result = alert.showAndWait();
				 }
			}
		}
		
	}

	/*
	 * get oldest person in db
	 */
	private void getMaximumAge(){
		Variable age = new Variable("MaxAge");
		Query ageTerm = new Query("stat_max_age",new Term[]{age});
		
		
		System.out.println("getting age max");
		if(ageTerm.hasMoreElements()){
		
			Object c = ageTerm.nextElement();
			if(c!=null){
				{
					 //append a curly bracket to it
					 
					 System.out.println(c);
					 Alert alert = new Alert(AlertType.INFORMATION);

						alert.setTitle("Query Response");
						alert.setHeaderText("returned : Min Age ");
						alert.setContentText(c.toString());
						
						Optional<ButtonType> result = alert.showAndWait();
				 }
			}
		}
	}

	/*
	 * finds records of patients with a family history of diabetes
	 */
	private void stat_family_history(){
		Variable age = new Variable("HistoryCount");
		Query ageTerm = new Query("stat_family_history",new Term[]{age});
		
		
		System.out.println("getting family history count");
		if(ageTerm.hasMoreElements()){
		
			Object c = ageTerm.nextElement();
			if(c!=null){
				{
					 //append a curly bracket to it
					 
					 System.out.println(c);
					 Alert alert = new Alert(AlertType.INFORMATION);

						alert.setTitle("Query Response");
						alert.setHeaderText("returned : Family history count ");
						alert.setContentText(c.toString());
						
						Optional<ButtonType> result = alert.showAndWait();
				 }
			}
		}
	}
	
	/*
	 * Calculates average age of all patient records
	 */
	private void stat_avg_age(){
		Variable age = new Variable("AverageAge");
		Query ageTerm = new Query("stat_avg_age",new Term[]{age});
		
		
		System.out.println("getting average age");
		if(ageTerm.hasMoreElements()){
		
			Object c = ageTerm.nextElement();
			if(c!=null){
				{
					 //append a curly bracket to it
					 
					 System.out.println(c);
					 Alert alert = new Alert(AlertType.INFORMATION);

						alert.setTitle("Query Response");
						alert.setHeaderText("returned : Average Age ");
						alert.setContentText(c.toString());
						
						Optional<ButtonType> result = alert.showAndWait();
				 }
			}
		}
	}
	

	
    /*
     * Counts the number of High risk or Very High risk records
     */
    private void stat_num_records(){
    	Variable age = new Variable("Count");
		Query ageTerm = new Query("stat_num_high_risk",new Term[]{age});
		
		
		System.out.println("getting count of high risk");
		if(ageTerm.hasMoreElements()){
		
			Object c = ageTerm.nextElement();
			if(c!=null){
				{
					 //append a curly bracket to it
					 
					 System.out.println(c);
					 Alert alert = new Alert(AlertType.INFORMATION);

						alert.setTitle("Query Response");
						alert.setHeaderText("returned : Number of High risk or Very High risk records");
						alert.setContentText(c.toString());
						
						Optional<ButtonType> result = alert.showAndWait();
				 }
			}
		}
    }
	
}
