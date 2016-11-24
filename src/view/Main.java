package view;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Model;
//import model.Model;

public class Main extends Application {
	public static Model model = new Model();
	static Stage PrimaryStage = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		PrimaryStage = primaryStage;
		primaryStage.setTitle("LoginMinistraion");

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		root.setHgap(10);
		root.setVgap(10);
		root = Components(root);
		Scene scene = new Scene(root, 300, 200);
		// get components

		primaryStage.setResizable(false);
		// call styles

		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		scene.getStylesheets().add(Main.class.getResource("Styles.css").toExternalForm());
		primaryStage.show();
	}

	private GridPane Components(GridPane root) {
		Text LoginText = new Text("Login");
		LoginText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		LoginText.setId("login");
		root.add(LoginText, 0, 0, 2, 1);

		Label UserName = new Label("User Name");
		root.add(UserName, 0, 1);

		TextField userNameField = new TextField();
		root.add(userNameField, 1, 1);

		Label password = new Label("Password");
		root.add(password, 0, 2);
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

		btn.setOnAction((event) -> {

			/*
			 * org.jpl7.Integer age = new org.jpl7.Integer(5); org.jpl7.Integer
			 * feet = new org.jpl7.Integer(5); org.jpl7.Integer inches = new
			 * org.jpl7.Integer(7);
			 * 
			 * 
			 * Term q2 = new Compound("test_user_data", new Term[]{new
			 * Atom("nicsdk"),age,new org.jpl7.Integer(150),new
			 * Atom("jamaican"),feet,inches}); Query q3 = new Query(q2 );
			 * 
			 * q3.open(); if (q3.hasMoreElements()) { Object c =
			 * q3.nextElement(); System.out.println( "query " + (c.toString()));
			 * }
			 */
			String user_password = Password.getText().toString();
			String user = userNameField.getText().toString();
			if (user.toLowerCase().equals("admin") && user_password.toLowerCase().equals("admin")) {
				Alert alert = new Alert(AlertType.CONFIRMATION);

				alert.setTitle("Login Response");
				alert.setHeaderText("Success");
				alert.setContentText("Welcome administrator");
				alert.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true); // disable
																						// cancel
																						// button
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					model.AlertAuthoritiesOfSpike();
					PrimaryStage.setTitle("Dex");
					Scenes.Indexer.SetUpComponents();
					PrimaryStage.setScene(Scenes.IndexerScene);
					Scenes.IndexerScene.getStylesheets().add(Main.class.getResource("Styles.css").toExternalForm());
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);

				alert.setTitle("Login Response");
				alert.setHeaderText("Error");
				alert.setContentText("Incorrect username | password");
				// alert.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
				// //disable cancel button
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

				}

			}

		});
		return root;
	}

	public static class Scenes {
		public static Scene QueryScene = null;
		public static Scene InputScene = null;
		public static Scene IndexerScene = null;
		public static Scene RecomendationScene = null;

		public static class Query {
			public static Text query = new Text("Query");
			public static TextField Field = new TextField();
			public static Button SubmitQuery = new Button("Submit");
			public static Button IndexQuery = new Button("Back");

			public static void SetUp() {
				FlowPane root = new FlowPane();
				root.setHgap(5);
				root.setVgap(15);
				root.setPadding(new Insets(5));

				root.getChildren().add(query);
				root.getChildren().add(Field);
				HBox queryBox = new HBox();
				queryBox.setAlignment(Pos.BOTTOM_LEFT);
				queryBox.setSpacing(7);
				queryBox.getChildren().add(SubmitQuery);
				queryBox.getChildren().add(IndexQuery);

				root.getChildren().add(queryBox);
				QueryScene = new Scene(root, 200, 69);
				root.setId("query-root");
				Field.setId("field");
				SubmitQuery.setId("submit-query");
				IndexQuery.setId("submit-query");

				SubmitQuery.setPrefWidth(65);

				SubmitQuery.setOnAction((event) -> {

					String query = getQueryText();
                     String response = "null";
					switch(query){
					 
					case "stat_min_age":{
						
						break;
					}
					case "stat_max_age":{
						
						break;
					}
					case "stat_family_history":{
						break;
					}
					case "stat_avg_age":{
						
						break;
					}
					default:
						System.out.println("invalid query parameter: " + query);
						break;
					}
					ShowQueryResponse(response);
				});
				/*
				 * go back to indexer scene
				 */
				IndexQuery.setOnAction((event) -> {

					PrimaryStage.setTitle("Dex");
					Scenes.Indexer.SetUpComponents();
					PrimaryStage.setScene(Scenes.IndexerScene);
					Scenes.IndexerScene.getStylesheets().add(Main.class.getResource("Styles.css").toExternalForm());
				});
			}

			public static String getQueryText() {
				return Field.getText().toString();
			}

			public static void ShowQueryResponse(String query) {
				Alert alert = new Alert(AlertType.INFORMATION);

				alert.setTitle("Query Response");
				alert.setHeaderText("Information Returned");
				alert.setContentText(query);
				alert.show();
			}
		}

		public static class Input {
			public static boolean toggle1 = false; // this indicates wether yes
													// or no (true | false)
			public static boolean toggle2 = false;
			public static boolean toggle3 = false;
			public static boolean toggle4 = false;
			public static String category = "r1"; // for category 1,2,3
			protected static String user_gender;

			public static void SetUpComponents() {
				GridPane root = new GridPane();

				ScrollPane scrollPane = new ScrollPane(); // scroll pane...

				scrollPane.setContent(root); // add root
				scrollPane.setFitToHeight(true);
				scrollPane.setFitToWidth(false);
				/*
				 * labels declaration
				 */
				Label HeightLabel, WeightLabel, WelcomeLabel, Questionslabel;
				/*
				 * Text declarations
				 */

				/*
				 * radio button declarations
				 */
				// A radio button with an empty string for its label
				RadioButton yesRb1 = new RadioButton("Yes");
				RadioButton NoRb1 = new RadioButton("No");

				RadioButton yesRb2 = new RadioButton("Yes");
				RadioButton NoRb2 = new RadioButton("No");

				RadioButton yesRb3 = new RadioButton("Yes");
				RadioButton NoRb3 = new RadioButton("No");

				RadioButton yesRb4 = new RadioButton("Yes");
				RadioButton NoRb4 = new RadioButton("No");

				Text NameText, AgeText, EthnicityText, HeightInFeetText, HeightIn_InchesText, WeightText;
				Text WaistCircumference, Excercise, Diet, Medication, BloodPressure;
				/*
				 * TextField declarations
				 */
				TextField Name, Age, Ethnicity, HeightInFeet, HeightIn_Inches, Weight, WaistCircumferenceInput;
				/*
				 * TextField instanciations
				 */
				Name = new TextField();
				Age = new TextField();
				;
				HeightIn_Inches = new TextField();
				HeightInFeet = new TextField();
				Weight = new TextField();
				Ethnicity = new TextField();
				WaistCircumferenceInput = new TextField();
				/*
				 * Text instanciations
				 */
				Excercise = new Text("Do you exercise atleast 30 minutes every day?");
				Diet = new Text("Do you eat vegetables or fruits every day");
				Medication = new Text("Have you ever taken medication for high blood pressure on regular basis");
				BloodPressure = new Text("Have you ever been found to have high blood glucose");

				NameText = new Text("Name");
				AgeText = new Text("Age");
				HeightInFeetText = new Text("Feet");
				EthnicityText = new Text("Ethnicity");
				HeightIn_InchesText = new Text("Inches");
				WeightText = new Text("Weight");
				WaistCircumference = new Text("Waist Circumference(cm)");

				/*
				 * Label instanciations
				 */
				HeightLabel = new Label("Height in both feet and inches");
				WeightLabel = new Label("Weight (Kg)");
				WelcomeLabel = new Label("Provide the following.");
				Questionslabel = new Label("Answer the following.");

				/*
				 * gap settings
				 */
				root.setHgap(2);
				root.setVgap(2);
				root.setPadding(new Insets(2));
				/*
				 * child additions
				 */

				// root.setGridLinesVisible(true);
				root.add(WelcomeLabel, 0, 0, 2, 1);
				root.add(NameText, 0, 2);
				root.add(Name, 1, 2);

				root.add(AgeText, 0, 3);
				root.add(Age, 1, 3);

				root.add(EthnicityText, 0, 4);
				root.add(Ethnicity, 1, 4);

				root.add(HeightLabel, 0, 5, 2, 1);
				root.add(HeightInFeetText, 0, 6);
				root.add(HeightInFeet, 1, 6);

				root.add(HeightIn_InchesText, 0, 7);
				root.add(HeightIn_Inches, 1, 7);

				/*
				 * buttons
				 */;

				Button Submit = new Button("Submit");
				Button Back = new Button("Back");

				/*
				 * button event handlers.
				 */
				Back.setOnAction((event) -> {
					// go back to select scene
					PrimaryStage.setTitle("Dex");
					Scenes.Indexer.SetUpComponents();
					PrimaryStage.setScene(Scenes.IndexerScene);
					Scenes.IndexerScene.getStylesheets().add(Main.class.getResource("Styles.css").toExternalForm());
				});

				Submit.setOnAction((event) -> {
					// store data.
					String age = Age.getText().toString();
					String name = Name.getText().toString();
					String weight = Weight.getText().toString();
					String ethnicity = Ethnicity.getText().toString();
					String heightInches = HeightIn_Inches.getText().toString();
					String heightFeet = HeightInFeet.getText().toString();
					String waistCircumference = WaistCircumferenceInput.getText().toString();

					String result = Model.Trigger(age, user_gender, name, weight, ethnicity, heightInches, heightFeet,
							waistCircumference, toggle1, toggle2, toggle3, toggle4, category); // display
																								// scene
					
					//call spike alert instead
					ShowRecomendations(result);
				});
				Submit.setId("submit-query");
				Back.setId("submit-query");

				root.add(WeightLabel, 0, 9);
				root.add(Weight, 1, 9);

				root.add(WaistCircumference, 0, 10);
				root.add(WaistCircumferenceInput, 1, 10);

				root.add(Questionslabel, 0, 11, 2, 1);

				/*
				 * Hbox for various questions
				 */
				HBox question1 = new HBox();
				question1.setSpacing(2);
				question1.setAlignment(Pos.CENTER);
				question1.setPadding(new Insets(2));

				HBox question2 = new HBox();
				question2.setSpacing(5);
				question2.setAlignment(Pos.CENTER);
				question2.setPadding(new Insets(5));

				HBox question3 = new HBox();
				question3.setSpacing(5);
				question3.setAlignment(Pos.CENTER);
				question3.setPadding(new Insets(5));

				/*
				 * question1 box with radio button
				 */

				/*
				 * define toggle group final ToggleGroup group = new
				 * ToggleGroup();
				 */
				final ToggleGroup group1 = new ToggleGroup();
				final ToggleGroup group2 = new ToggleGroup();
				final ToggleGroup group3 = new ToggleGroup();
				final ToggleGroup group4 = new ToggleGroup();

				yesRb1.setToggleGroup(group1);
				NoRb1.setToggleGroup(group1);
				NoRb1.setSelected(true);

				yesRb2.setToggleGroup(group2);
				NoRb2.setToggleGroup(group2);
				NoRb2.setSelected(true);

				yesRb3.setToggleGroup(group3);
				NoRb3.setToggleGroup(group3);
				NoRb3.setSelected(true);

				yesRb4.setToggleGroup(group4);
				NoRb4.setToggleGroup(group4);
				NoRb4.setSelected(true);
				/*
				 * adding questions
				 * 
				 */
				FlowPane f1 = new FlowPane();
				f1.setPrefWidth(90);
				f1.setHgap(5);
				f1.getChildren().add(yesRb1);
				f1.getChildren().add(NoRb1);

				FlowPane f2 = new FlowPane();
				f2.setPrefWidth(90);
				f2.setHgap(5);
				f2.getChildren().add(yesRb2);
				f2.getChildren().add(NoRb2);

				FlowPane f3 = new FlowPane();
				f3.setPrefWidth(90);
				f3.setHgap(5);
				f3.getChildren().add(yesRb3);
				f3.getChildren().add(NoRb3);

				FlowPane f4 = new FlowPane();
				f4.setPrefWidth(90);
				f4.setHgap(5);
				f4.getChildren().add(yesRb4);
				f4.getChildren().add(NoRb4);

				root.add(Excercise, 0, 13);
				root.add(f1, 1, 13);

				root.add(Diet, 0, 14);
				root.add(f2, 1, 14);

				root.add(Medication, 0, 15);
				root.add(f3, 1, 15);

				root.add(BloodPressure, 0, 16);
				root.add(f4, 1, 16);
				/*
				 * 
				 * process radio buttons toggle group
				 */

				group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
						if (group1.getSelectedToggle() != null) {
							if (group1.getSelectedToggle().equals(yesRb1)) {
								toggle1 = true;
							} else if (group1.getSelectedToggle().equals(NoRb1)) {
								toggle1 = false; // then no was checked
							}
						}

					}
				});

				group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
						if (group2.getSelectedToggle() != null) {
							if (group2.getSelectedToggle().equals(yesRb2)) {
								toggle2 = true;
							} else if (group1.getSelectedToggle().equals(NoRb2)) {
								toggle2 = false;
							}
						}
					}
				});

				group3.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
						if (group3.getSelectedToggle() != null) {
							if (group3.getSelectedToggle().equals(yesRb3)) {
								toggle3 = true;
							} else if (group3.getSelectedToggle().equals(NoRb3)) {
								toggle3 = false;
							}
						}
					}
				});

				group4.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
						if (group4.getSelectedToggle() != null) {
							if (group4.getSelectedToggle().equals(yesRb4)) {
								toggle4 = true;
							} else if (group4.getSelectedToggle().equals(NoRb4)) {
								toggle4 = false;
							}
						}
					}
				});

				FlowPane f5 = new FlowPane();
				f5.setPrefWidth(40);
				f5.setHgap(5);
				f5.getChildren().add(Submit);
				f5.getChildren().add(Back);

				// Horizontal separator
				Separator separator1 = new Separator();
				// Vertical separator

				root.add(separator1, 0, 17, 2, 1);

				root.add(new Label("Previous History"), 0, 18, 2, 1);

				root.add(new Text("Have any of the members of your immediate family or other"), 0, 19, 2, 1);
				root.add(new Text("relatives been diagnosed with diabetes (type 1 or type 2) ?"), 0, 20, 2, 1);
				root.add(new Text("Select a category from the list below:"), 0, 21, 2, 1);

				RadioButton r1, r2, r3;
				r1 = new RadioButton("No");
				r2 = new RadioButton("Yes");
				r3 = new RadioButton("Yes");

				r2.setTooltip(new Tooltip("(grandparent, aunt, uncle or cousins)"));

				r3.setTooltip(new Tooltip("(parent, sibling, child)"));

				ToggleGroup tr1;

				tr1 = new ToggleGroup();
				r1.setSelected(true);
				r1.setToggleGroup(tr1);
				r2.setToggleGroup(tr1);
				r3.setToggleGroup(tr1);

				tr1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

					@Override
					public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue,
							Toggle newValue) {
						if (tr1.getSelectedToggle() != null) {
							if (tr1.getSelectedToggle().equals(r1)) {
								category = "r1";
							} else if (tr1.getSelectedToggle().equals(r2)) {
								category = "r2";
							} else {
								// then r3
								category = "r3";
							}
						}

					}
				});
				FlowPane f6 = new FlowPane();
				// f5.setPrefWidth(100);
				f6.setHgap(10);
				f6.getChildren().addAll(r1, r2, r3);
				root.add(f6, 0, 22);
				// Horizontal separator
				Separator sep = new Separator();
				root.add(sep, 0, 23, 2, 1); // submit button and back
				root.add(f5, 0, 26, 2, 1); // submit button and back

				RadioButton male = new RadioButton("M");
				RadioButton female = new RadioButton("F");

				ToggleGroup genderGroup = new ToggleGroup();
				male.setToggleGroup(genderGroup);
				female.setToggleGroup(genderGroup);
				Label genderLabel = new Label("Gender");

				FlowPane genderFlow = new FlowPane();
				genderFlow.setPadding(new Insets(5));
				HBox GenderBox = new HBox();
				GenderBox.setSpacing(10);
				GenderBox.getChildren().addAll(genderLabel, male, female);

				// handle click for gender
				genderGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

					@Override
					public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue,
							Toggle newValue) {
						if (genderGroup.getSelectedToggle() != null) {
							if (genderGroup.getSelectedToggle().equals(male)) {
								user_gender = "male";
							} else if (genderGroup.getSelectedToggle().equals(female)) {
								user_gender = "female";
							}
						}

					}
				});
				genderFlow.getChildren().addAll(GenderBox);
				root.add(new Separator(), 0, 25, 2, 1);
				root.add(genderFlow, 0, 24, 2, 1);
				InputScene = new Scene(scrollPane, 570, 300);
				root.setId("input-root");

			}

			public static void ProcessQuestions() {

			}

			public static GridPane SetupGui(GridPane root) {

				return root;
			}
		}

		public static class Indexer {
			public static void SetUpComponents() {
				FlowPane root = new FlowPane();
				HBox container = new HBox();

				container.setAlignment(Pos.CENTER);
				container.setPadding(new Insets(5));
				container.setSpacing(10);
				root.setHgap(5);
				root.setPadding(new Insets(10));

				Button query, input;

				query = new Button("Query");
				input = new Button("Input");

				query.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						SwitchScene(QUERY_SCENE, null);

					}
				});
				input.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						SwitchScene(INPUT_SCENE, null);
					}
				});
				// -------------------------------------------------
				query.setId("sign-in");
				input.setId("sign-in");

				container.getChildren().add(query);
				container.getChildren().add(input);

				root.getChildren().add(container);

				IndexerScene = new Scene(root, 100, 50);
				root.setId("indexer-root");

			}
		}

		public static void SwitchScene(String scene, ScrollPane root) {

			/*
			 * contains the logic to switch between scenes(input,query).
			 */
			System.out.println("setting scene...");
			switch (scene) {
			case QUERY_SCENE:
				Scenes.Query.SetUp();// setup
				PrimaryStage.setTitle("Query");
				PrimaryStage.setScene(Scenes.QueryScene);
				Scenes.QueryScene.getStylesheets().add(Main.class.getResource("Styles.css").toExternalForm());
				break;

			case INPUT_SCENE:
				Scenes.Input.SetUpComponents();
				PrimaryStage.setTitle("Input");
				PrimaryStage.setScene(Scenes.InputScene);
				Scenes.InputScene.getStylesheets().add(Main.class.getResource("Styles.css").toExternalForm());
				break;

			case RECOMENDATION_SCENE:
				Scenes.RecomendationScene = new Scene(root, 300, 220);
				PrimaryStage.setTitle("Recomendations");
				PrimaryStage.setScene(Scenes.RecomendationScene);
				Scenes.RecomendationScene.getStylesheets().add(Main.class.getResource("Styles.css").toExternalForm());
				break;
			default:
				break;
			}
		}

		public static ScrollPane DisplayRecomendations(String result) {
			
			ScrollPane root = new ScrollPane();
			root.setFitToWidth(true);
			root.setFitToHeight(false);

			VBox child = new VBox();
			child.setPadding(new Insets(5));
			child.setSpacing(5);

			root.setContent(child);// set child

			child.setAlignment(Pos.CENTER_LEFT);

			Text strengthTraining = new Text();
			Text aerobic_Exercise = new Text();

			strengthTraining.setWrappingWidth(287);
			aerobic_Exercise.setWrappingWidth(287);  
			
			Label label1 = new Label("Aerobic Exercise");
			label1.setTextAlignment(TextAlignment.CENTER);
			label1.setWrapText(true);
			
			Label label2 = new Label();
			label2.setTextAlignment(TextAlignment.CENTER);
			label2.setWrapText(true);
			label2.setText("Strength Training");
			
			strengthTraining.setText("Strength training (also called resistance training) makes your body more sensitive to insulin and can lower blood glucose. It helps to maintain and build strong muscles and bones, reducing your risk for osteoporosis and bone fractures. The more muscle you have, the more calories you burn – even when your body is at rest.Preventing muscle loss by strength training is also the key to maintaining an independent lifestyle as you age. Recommended: doing some type of strength training at least 2 times per week in addition to aerobic activity.");
            aerobic_Exercise.setText("Aerobic exercise helps your body use insulin better. It makes your heart and bones strong, relieves stress, improves blood circulation, and reduces your risk for heart disease by lowering blood glucose and blood pressure and improving cholesterol levels. Recommend: Aiming for 30 minutes of moderate-to-vigorous intensity aerobic exercise at least 5 days a week or a total of 150 minutes per week. Spread your activity out over at least 3 days during the week and try not to go more than 2 days in a row without exercising");
			Button back = new Button(" < Back");
			Separator line1 = new Separator();
			Separator line2 = new Separator();

			//strength training
			child.getChildren().add(label2);
			child.getChildren().add(strengthTraining);
			child.getChildren().add(line2);
			
			//"Aerobic Exercise"
			child.getChildren().add(label1);
			child.getChildren().add(aerobic_Exercise);
			
			
			//////////----------------------------------back navigation---------------------------------
			child.getChildren().add(line1);
			child.getChildren().add(back);

			back.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// go back indexer

					PrimaryStage.setTitle("Dex");
					Scenes.Indexer.SetUpComponents();
					PrimaryStage.setScene(Scenes.IndexerScene);
					Scenes.IndexerScene.getStylesheets().add(Main.class.getResource("Styles.css").toExternalForm());
				}
			});
			SwitchScene(RECOMENDATION_SCENE, root);

			 //re consult database here
			 model.consultDb();
			return root;

		}

		public static void ShowRecomendations(String query){
		
			DisplayRecomendations("");// displays
		}
		public static boolean Recomendation_Back = false;
		public static final String QUERY_SCENE = "QUERY-SCENE";
		public static final String INPUT_SCENE = "INPUT-SCENE";
		public static final String RECOMENDATION_SCENE = "RECOMENDATION-SCENE";
	}
}
