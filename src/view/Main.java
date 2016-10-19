package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
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
			Scenes.Query.SetUp();// setup
			this.primaryStage.setTitle("Query");
			primaryStage.setScene(Scenes.QueryScene);
			Scenes.QueryScene.getStylesheets().add (Main.class.getResource("Styles.css").toExternalForm());
		});
		return root;
	}

 public static class Scenes {
	 public static Scene QueryScene = null;
	 public static Scene InputScene = null;
	 
	 public static class Query{
		 public static Text query = new Text("Query");
		 public static TextField Field = new TextField();
		 public static Button SubmitQuery = new Button("Submit");
				 
		 public static void SetUp(){
			 FlowPane root = new  FlowPane();
			 root.setHgap(5);
			 root.setVgap(15);
			 root.setPadding(new Insets(5));
		//	 root.setAlignment(Pos.CENTER);
			// root.setRowValignment(VPos.CENTER);
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
				  
			  });
		 }
		 public static String getQueryText(){
			 return Field.getText().toString();
		 }
	 }
	 public static class Input{
		 
	 }
 }
}
