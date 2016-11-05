package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main  extends Application {
	Stage primaryStage = null;
	
	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
	this.primaryStage = primaryStage;
		primaryStage.setTitle("LoginMinistraion");
		
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		root.setHgap(10);
		root.setVgap(10);
		root = Components(root);
		Scene scene  = new Scene(root,300,200);
		//get components 
	
		primaryStage.setResizable(false);
		//call styles
		
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		scene.getStylesheets().add (Main.class.getResource("Styles.css").toExternalForm());
		primaryStage.show();
	}
	
	private GridPane Components(GridPane root){
		Text LoginText = new Text("Login");
		LoginText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		LoginText.setId("login");
		root.add(LoginText, 0, 0,2,1);
		
		Label UserName = new Label("User Name");
		root.add(UserName, 0, 1);
		
		TextField userNameField = new TextField();
		root.add(userNameField, 1,1);
		
		Label password = new Label("password");
		root.add(password,0,2);
		PasswordField Password = new PasswordField();
		root.add(Password, 1, 2);
		
		Button btn = new Button("Sign in"); 
		btn.setId("sign-in");
		btn.setPrefWidth(85);
		btn.minHeight(79.9);
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn); 
		root.add(hbBtn, 1, 4);
		
		
		btn.setOnAction((event)->{
			/*Scenes.Query.SetUp();// setup
			this.primaryStage.setTitle("Query");
			primaryStage.setScene(Scenes.QueryScene);
			Scenes.QueryScene.getStylesheets().add (Main.class.getResource("Styles.css").toExternalForm());
			
			Scenes.Input.SetUpComponents();
			this.primaryStage.setTitle("Input");
			primaryStage.setScene(Scenes.InputScene);
			Scenes.InputScene.getStylesheets().add (Main.class.getResource("Styles.css").toExternalForm());
			*/
			this.primaryStage.setTitle("Indexer");
			Scenes.Indexer.SetUpComponents();
			primaryStage.setScene(Scenes.IndexerScene);
			Scenes.IndexerScene.getStylesheets().add (Main.class.getResource("Styles.css").toExternalForm());
		});
		return root;
	}

 public static class Scenes {
	 public static Scene QueryScene = null;
	 public static Scene InputScene = null;
	 public static Scene IndexerScene = null;
	 
	 public static class Query{
		 public static Text query = new Text("Query");
		 public static TextField Field = new TextField();
		 public static Button SubmitQuery = new Button("Submit");
				 
		 public static void SetUp(){
			 FlowPane root = new  FlowPane();
			 root.setHgap(5);
			 root.setVgap(15);
			 root.setPadding(new Insets(5));
	
			 root.getChildren().add(query);
			 root.getChildren().add(Field);
			HBox queryBox = new HBox();
			queryBox.setAlignment(Pos.BOTTOM_LEFT);
			queryBox.getChildren().add(SubmitQuery);
			root.getChildren().add(queryBox);
			 QueryScene = new Scene(root,200,69);
			  root.setId("query-root");
			  Field.setId("field");
			  SubmitQuery.setId("submit-query");
			  
			  
			  SubmitQuery.setPrefWidth(65);
			  
			  SubmitQuery.setOnAction((event)->{
				
				  String query = getQueryText();
				
				  
				  ShowQueryResponse(query);
			  });
		 }
		 public static String getQueryText(){
			 return Field.getText().toString();
		 }
		 public static void ShowQueryResponse(String query){
				Alert alert = new Alert(AlertType.INFORMATION);
				
				alert.setTitle("Query Response");
				alert.setHeaderText("Information Returned");
				alert.setContentText(query);
				alert.show();
		 }
	 }
	 public static class Input{
		 public static void SetUpComponents(){
			 GridPane root = new GridPane();
			 /*
			  * labels declaration
			  */
			 Label HeightLabel,WeightLabel,WelcomeLabel;
			 /*
			  * Text declarations
			  */
					 
			 Text NameText,AgeText,EthnicityText,HeightInFeetText,HeightIn_InchesText,WeightText;
			 /*
			  * TextField declarations
			  */
			 TextField Name,Age,Ethnicity,HeightInFeet,HeightIn_Inches,Weight;
			 /*
			  * TextField instanciations
			  */
			          Name = new TextField(); 
			          Age =  new TextField();;
				      HeightIn_Inches = new TextField();
					  HeightInFeet =  new TextField();Weight= new TextField(); 
					  Ethnicity = new TextField();
					  /*
					   * Text instanciations
					   */
			 NameText = new Text("Name");
			 AgeText = new Text("Age");
			 HeightInFeetText = new Text("Feet");
			 EthnicityText = new Text("Ethnicity");
			 HeightIn_InchesText = new Text("Inches");
			 WeightText = new Text("Weight");
			/*
			 * Label instanciations 
			 */
			 HeightLabel = new Label("Height in both feet and inches");
			 WeightLabel = new Label("Weight in Kilograms");	
			 WelcomeLabel = new Label("Provide the following.");
			 /*
			  * gap settings
			  */
			 root.setHgap(5);
			 root.setVgap(5);
			 root.setPadding(new Insets(8));
	/*
	 * child additions
	 */
			 
			// root.setGridLinesVisible(true); 
			 root.add(WelcomeLabel, 0, 0,1,2);
			 root.add(NameText, 0, 2);
			 root.add(Name, 1, 2); 
			 
			 root.add(AgeText, 0, 3);
			 root.add(Age, 1, 3);
			 
			 root.add(EthnicityText, 0, 4);
			 root.add(Ethnicity, 1, 4);
			 
			 
			 root.add(HeightLabel, 0, 5,2,1);
			 root.add(HeightInFeetText, 0, 6);
			 root.add(HeightInFeet, 1, 6);
			 
			 root.add(HeightIn_InchesText, 0,7);
			 root.add(HeightIn_Inches, 1, 7);
			 
		
			 /*
			  * buttons
			  */;
			  
			 Button Submit = new Button("Submit");
			 Button Back = new Button("Back");
			 /*
			  * button event handlers.
			  */
			 Back.setOnAction((event)->{
				 //go back to select scene
			 });
			 
			 Submit.setOnAction((event)->{
				 //store data.
				 String age = Age.getText().toString();
				 String name = Name.getText().toString();
				 String weight = Weight.getText().toString();
				 String ethnicity = Ethnicity.getText().toString();
				 String heightInches = HeightIn_Inches.getText().toString();
				 String heightFeet = HeightInFeet.getText().toString();
			 });
			 Submit.setId("submit-query");
			 Back.setId("submit-query");
			 root.add(new Button("Submit"), 0, 9);
			 root.add(new Button("Back"), 1, 9);
			 InputScene = new Scene(root,300,250);
			  root.setId("input-root");
			 
		 }
		 public static GridPane SetupGui(GridPane root){
			
			 return root;
		 }
	 }
	 public static class Indexer{
		 public static void SetUpComponents(){
			 FlowPane root = new FlowPane();
			 HBox container = new HBox();
			
			 container.setAlignment(Pos.CENTER);
			 container.setPadding(new Insets(5));
			 container.setSpacing(10);
			 root.setHgap(5);
			 root.setPadding(new Insets(10));
			 
			 Button query,input;
			 
			 query = new Button("Query");
			 input = new Button("Input");
			 
		     query.setId("sign-in");
		     input.setId("sign-in");
		     
			 container.getChildren().add(query);
			 container.getChildren().add(input);
			 
			 root.getChildren().add(container);
			 
			 IndexerScene = new Scene(root,100,50);
			 root.setId("indexer-root");
			
		 }
	 }
 }
}
