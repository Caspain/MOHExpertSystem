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
		
		//load db
		LoadDataBase();
		
		//retract all stored data
		retractData();
		
		//get all stored data
		assertStoredData();
		
		//show listing 
		listing();
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

	/*
	 * everytime a entry is inserted,consult databse
	 * everytime a utility method will be called, call load_database first
	 */

	public void AlertAuthoritiesOfSpike() {
	
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
	
	public void LoadDataBase(){
		Query quer = new Query("load_database");
		quer.open();
		quer.hasMoreElements();
	}

	/*
	 * get youngest person in db
	 */
	public void getMinimumAge(){
		
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
	public void getMaximumAge(){
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
	public void stat_family_history(){
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
	public void stat_avg_age(){
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
    public void stat_num_records(){
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
    
    public static void assertStoredData(){
    
		Query data = new Query("retrieve_data");
		
		if(data.hasSolution()){
			System.out.println("success.retrieving data");
		}
    }
    public static void listing(){
        
    		Query data = new Query("listing");
    		
    		if(data.hasSolution()){
    			System.out.println("success.listing");
    		}
        }
    public static void retractData(){
    
    	Query data = new Query("retract_data");
		
		if(data.hasSolution()){
			System.out.println("success. retracting data");
		}
    }
    /*
     * all data for person with name X
     */
	
    public void  stat_user_all(String name){
	Query data = new Query("stat_user_all",new Term[]{new Atom(name)});
		
		if(data.hasSolution()){
			System.out.println("success. name all");
		}
    }
    /*
     * stat_user_weight
     */
    public void  stat_user_weight(String name){
    	Query data = new Query("stat_user_weight",new Term[]{new Atom(name)});
    		
    		if(data.hasSolution()){
    			System.out.println("success. name stat_user_weight");
    		}
        }
    /*
     * stat_user_weight
     */
    public void   stat_user_height(String name){
    	Query data = new Query("stat_user_height",new Term[]{new Atom(name)});
    		
    		if(data.hasSolution()){
    			System.out.println("success. name  stat_user_height");
    		}
        }
    /*
     * stat_user_bmi
     */
    public void   stat_user_bmi(String name){
    	Query data = new Query("stat_user_bmi",new Term[]{new Atom(name)});
    		
    		if(data.hasSolution()){
    			System.out.println("success. name  stat_user_bmi");
    		}
        }
    /*
     * stat_user_bmi
     */
    public void   stat_user_age(String name){
    	Query data = new Query("stat_user_age",new Term[]{new Atom(name)});
    		
    		if(data.hasSolution()){
    			System.out.println("success. name stat_user_age");
    		}
        }
    /*
     * stat_user_ethnicity
     */
    public void  stat_user_ethnicity(String name){
    	Query data = new Query("stat_user_age",new Term[]{new Atom(name)});
    		
    		if(data.hasSolution()){
    			System.out.println("success. name stat_user_ethnicity");
    		}
        }
    //----------------------------------------------------------------------------------------filter rules
    
    /*
     * returns records of patients below a certain height
     */
    public void  stat_height_filter_below(int age){
    	Query data = new Query("stat_height_filter_below",new Term[]{(new org.jpl7.Integer((age))),new Variable("Records")});
    		if(data.hasMoreElements()){
    			System.out.println(data.nextElement().toString());
    		}
    		if(data.hasSolution()){
    			System.out.println("success. stat_height_filter_below");
    		}
        }
    
    /*
     * returns records of patients above a certain height
     */
    public void  stat_height_filter_above(int age){
    	Query data = new Query("stat_height_filter_above",new Term[]{(new org.jpl7.Integer((age))),new Variable("Records")});
		if(data.hasMoreElements()){
			System.out.println(data.nextElement().toString());
		}
		if(data.hasSolution()){
			System.out.println("success. stat_height_filter_above");
		}
        }
    
    /*
     * returns records of patients below a certain weight
     */
    public void  stat_weight_filter_below(int weight){
    	Query data = new Query("stat_weight_filter_below",new Term[]{(new org.jpl7.Integer((weight))),new Variable("Records")});
		if(data.hasMoreElements()){
			System.out.println(data.nextElement().toString());
		}
		if(data.hasSolution()){
			System.out.println("success. stat_weight_filter_below");
		}
        }
    /*
     *returns records of patients above a certain weight
     */
    public void  stat_weight_filter_above(int weight){
    	Query data = new Query("stat_weight_filter_above",new Term[]{(new org.jpl7.Integer((weight))),new Variable("Records")});
		if(data.hasMoreElements()){
			System.out.println(data.nextElement().toString());
		}
		if(data.hasSolution()){
			System.out.println("success. stat_weight_filter_above");
		}
        }
    /*
     *returns records of patients with family history of diabetes
     *values: 0,1,2
     */
    public void stat_family_history_filter(int code){
    	Query data = new Query("stat_family_history_filter",new Term[]{(new org.jpl7.Integer((code))),new Variable("Records")});
		if(data.hasMoreElements()){
			System.out.println(data.nextElement().toString());
		}
		if(data.hasSolution()){
			System.out.println("success. stat_family_history_filter");
		}
        }
    /*
     *returns records based on gender
     */
    public void stat_gender_filter(String gender){
    	Query data = new Query("stat_gender_filter",new Term[]{(new Atom((gender))),new Variable("Records")});
		if(data.hasMoreElements()){
			System.out.println(data.nextElement().toString());
		}
		if(data.hasSolution()){
			System.out.println("success. stat_gender_filter");
		}
        }
    /*
     *returns records based on risk level 
     *values : 
     */
    public void stat_risk_filter(String risk){
    	Query data = new Query("stat_risk_filter",new Term[]{(new Atom((risk))),new Variable("Records")});
		if(data.hasMoreElements()){
			System.out.println(data.nextElement().toString());
		}
		if(data.hasSolution()){
			System.out.println("success. stat_risk_filter");
		}
        }
    /*
     returns list of records with age below limit: 
     */
    public void stat_age_filter_below(int age){
    	Query data = new Query("stat_age_filter_below",new Term[]{(new org.jpl7.Integer((age))),new Variable("Records")});
		if(data.hasMoreElements()){
			System.out.println(data.nextElement().toString());
		}
		if(data.hasSolution()){
			System.out.println("success. stat_age_filter_below");
		}
        }
    /*
   finds list of records in age above limit
    */
   public void stat_age_filter_above(int age){
		Query data = new Query("stat_age_filter_above",new Term[]{(new org.jpl7.Integer((age))),new Variable("Records")});
		if(data.hasMoreElements()){
			System.out.println(data.nextElement().toString());
		}
		if(data.hasSolution()){
			System.out.println("success. stat_age_filter_above");
		}
       }
}
