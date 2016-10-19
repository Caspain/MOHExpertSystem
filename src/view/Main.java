package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private GridPane Components(GridPane root){
		Text LoginText = new Text("Login");
		LoginText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
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
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn); 
		root.add(hbBtn, 1, 4);
		
		
		btn.setOnAction((event)->{
			//fake login here
		});
		return root;
	}

}
