import java.io.*;
import java.util.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.property.*;
import javafx.stage.FileChooser;
import javafx.application.Application;

public class QuizViewer extends Application {

	static int n = 0; //number of questions
	static int ca = 0; // number of correct answers
	static String[] trueAnswers;
	static String[] userAnswers;
	public static void main(String[] args) {
		Application.launch(args);
	}
	public void start(Stage primaryStage) throws Exception {
		StackPane pane = new StackPane();

		Button bt = new Button("Load File");
		pane.getChildren().addAll(bt);

		bt.setOnAction(e1 -> {
			FileChooser fc = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
            fc.getExtensionFilters().add(extFilter);
            File file = fc.showOpenDialog(null);
			Scanner scan;
			Quiz quiz = new Quiz();
//#######################################################################################	
			if(file != null) {
				try {
					scan = new Scanner(file);
					if(!scan.hasNext()) {
						throw new InvalidQuizFormatException("The file selected does not fit the requirements for a\nstandart Quiz text file format...");
					}
					ArrayList<Question> question = quiz.loadFromFile(file.getName()).getQuestions();
					int size = question.size();
					java.util.Collections.shuffle(question);
					Stage qStage = new Stage();
					qStage.setTitle("Question Viewer");
					QuestionPane qPane = new QuestionPane();

					trueAnswers = new String[size];
					userAnswers = new String[size];

					ArrayList listOfPanes = new ArrayList();
					for(int i = 0; i < size; i++) {
						trueAnswers[i] = question.get(i).getAnswer();
						userAnswers[i] = "";
						if(question.get(i) instanceof FillIn) {
							FillInPane fPane = new FillInPane();
							listOfPanes.add(fPane);
						}
						else if(question.get(i) instanceof Test) {
							TestPane tPane = new TestPane(question.get(i));
							listOfPanes.add(tPane);
						}
					}



					if(question.get(0) instanceof FillIn) {
						qPane.ta.setText(question.get(0).getDescription().replace("{blank}", " _____ "));
   						qPane.bp2.setCenter((FillInPane)listOfPanes.get(0));
					}
					else if(question.get(0) instanceof Test) {
						qPane.ta.setText(question.get(0).getDescription());
   						qPane.bp2.setCenter((TestPane)listOfPanes.get(0));
					}
					qStage.show();

					qPane.bt1.setOnAction(e -> {
						if(question.get(n) instanceof FillIn) {
							userAnswers[n] =  ((FillInPane)listOfPanes.get(n)).tf.getText();
						}
						if(n <= 0) {
							qPane.tx.setText("Status: 1" + "/" + size + " questions\nStart of Quiz!");
						}
						else {
							n--;
							qPane.tx.setText("Status: " + (n+1) + "/" + size + " questions.");
						}
						if(question.get(n) instanceof FillIn) {
							qPane.ta.setText(question.get(n).getDescription().replace("{blank}", " _____ "));
   							qPane.bp2.setCenter((FillInPane)listOfPanes.get(n));
						}
						else if(question.get(n) instanceof Test) {
							qPane.ta.setText(question.get(n).getDescription());
   							qPane.bp2.setCenter((TestPane)listOfPanes.get(n));
   							((TestPane)listOfPanes.get(n)).rb1.selectedProperty().addListener(ex -> {
   								if(((TestPane)listOfPanes.get(n)).rb1.isSelected()) {
   									userAnswers[n] = ((TestPane)listOfPanes.get(n)).rb1.getText();
   								}
   							});
   							((TestPane)listOfPanes.get(n)).rb2.selectedProperty().addListener(ex -> {
   								if(((TestPane)listOfPanes.get(n)).rb2.isSelected()) {
   									userAnswers[n] = ((TestPane)listOfPanes.get(n)).rb2.getText();
   								}
   							});
   							((TestPane)listOfPanes.get(n)).rb3.selectedProperty().addListener(ex -> {
   								if(((TestPane)listOfPanes.get(n)).rb3.isSelected()) {
   									userAnswers[n] = ((TestPane)listOfPanes.get(n)).rb3.getText();
   								}
   							});
   							((TestPane)listOfPanes.get(n)).rb4.selectedProperty().addListener(ex -> {
   								if(((TestPane)listOfPanes.get(n)).rb4.isSelected()) {
   									userAnswers[n] = ((TestPane)listOfPanes.get(n)).rb4.getText();
   								}
   							});
						}
						qStage.show();
					});

					qPane.bt2.setOnAction(e -> {
						if(question.get(n) instanceof FillIn) {
							userAnswers[n] =  ((FillInPane)listOfPanes.get(n)).tf.getText();
						}
						if(n + 1 > size - 1) {
							qPane.tx.setText("Status: " + size + "/" + size + " questions\nEnd of Quiz!");
						}
						else {
							n++;
							qPane.tx.setText("Status: " + (n+1) + "/" + size + " questions.");
						}
						if(question.get(n) instanceof FillIn) {
							qPane.ta.setText(question.get(n).getDescription().replace("{blank}", " _____ "));
   							qPane.bp2.setCenter((FillInPane)listOfPanes.get(n));
						}
						else if(question.get(n) instanceof Test) {
							qPane.ta.setText(question.get(n).getDescription());
   							qPane.bp2.setCenter((TestPane)listOfPanes.get(n));
   							((TestPane)listOfPanes.get(n)).rb1.selectedProperty().addListener(ex -> {
   								if(((TestPane)listOfPanes.get(n)).rb1.isSelected()) {
   									userAnswers[n] = ((TestPane)listOfPanes.get(n)).rb1.getText();
   								}
   							});
   							((TestPane)listOfPanes.get(n)).rb2.selectedProperty().addListener(ex -> {
   								if(((TestPane)listOfPanes.get(n)).rb2.isSelected()) {
   									userAnswers[n] = ((TestPane)listOfPanes.get(n)).rb2.getText();
   								}
   							});
   							((TestPane)listOfPanes.get(n)).rb3.selectedProperty().addListener(ex -> {
   								if(((TestPane)listOfPanes.get(n)).rb3.isSelected()) {
   									userAnswers[n] = ((TestPane)listOfPanes.get(n)).rb3.getText();
   								}
   							});
   							((TestPane)listOfPanes.get(n)).rb4.selectedProperty().addListener(ex -> {
   								if(((TestPane)listOfPanes.get(n)).rb4.isSelected()) {
   									userAnswers[n] = ((TestPane)listOfPanes.get(n)).rb4.getText();
   								}
   							});
						}
						qStage.show();
					});

					qPane.bt3.setOnAction(e -> {
						if(question.get(n) instanceof FillIn) {
							userAnswers[n] =  ((FillInPane)listOfPanes.get(n)).tf.getText();
						}
						for(int i = 0; i < trueAnswers.length; i++) {
							if((trueAnswers[i].toLowerCase()).equals(userAnswers[i].toLowerCase())) {
								ca++;
							}
						}

						Stage resultStage = new Stage();
						ResultPane rPane = new ResultPane();

						rPane.tx1.setText("\nNumber of correct answers:" + ca + "/" + trueAnswers.length);

						rPane.getButton().setOnAction(e2 -> {
							resultStage.close();
						});

						resultStage.setTitle("Quiz Viewer: Results");
						resultStage.setScene(new Scene(rPane, 350, 180));
						resultStage.setResizable(false);
						resultStage.show();

						ca = 0;
					});
			
					qStage.setResizable(false);
					qStage.setScene(new Scene(qPane, 550, 450));
			
				}catch (Exception ex) {
					Stage errorStage = new Stage();
					ExceptionPane ePane = new ExceptionPane();

					ePane.tx2.setText(ex.getMessage());

					ePane.getButton().setOnAction(e2 -> {
						errorStage.close();
					});

					errorStage.setTitle("Quiz Viewer: Error");
					errorStage.setScene(new Scene(ePane, 375, 208));
					errorStage.setResizable(false);
					errorStage.show();
				}
			}		
//#######################################################################################
		});
		Scene primaryScene = new Scene(pane, 700, 500);
		primaryStage.setTitle("Quiz Viewer");
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
}
class ExceptionPane extends VBox {
	Text tx1;
	Text tx2;
	ImageView image;
	Button bt;

	ExceptionPane() {
		tx1 = new Text("\nInvalidQuizFormatException");
		tx2 = new Text();
		//tx2 = new Text("The file selected does not fit the requirements for a\nstandart Quiz text file format...");
		image = new ImageView(new Image(ExceptionPane.class.getResourceAsStream("image4.jpg"), 65, 65, true, false));
		bt = new Button("OK");

		tx1.setFont(Font.font("Cambria Math", FontWeight.BOLD, FontPosture.REGULAR, 20));
		bt.setPrefWidth(90);

		BorderPane pane = new BorderPane();
		pane.setLeft(tx1);
		pane.setRight(image);
		pane.setPadding(new Insets(10, 0, 10, 0));

		HBox hbox2 = new HBox();
		hbox2.getChildren().addAll(bt);
		hbox2.setAlignment(Pos.CENTER_RIGHT);

		setPadding(new Insets(0, 10, 5, 10));

		setSpacing(30);
		getChildren().addAll(pane, tx2, hbox2);
	}
	public Button getButton() {
		return bt;
	}
}
class ResultPane extends VBox {
	Text tx1;
	Text tx2;
	ImageView image;
	Button bt;

	ResultPane() {
		tx1 = new Text("\nSo far it\'s empty =)");
		tx2 = new Text("You may try again");
		image = new ImageView(new Image(ExceptionPane.class.getResourceAsStream("note.png"), 65, 65, true, false));
		bt = new Button("OK");

		tx1.setFont(Font.font("Cambria Math", FontWeight.BOLD, FontPosture.REGULAR, 20));
		bt.setPrefWidth(90);

		BorderPane pane = new BorderPane();
		pane.setLeft(tx1);
		pane.setRight(image);
		pane.setPadding(new Insets(10, 0, 10, 0));

		HBox hbox2 = new HBox();
		hbox2.getChildren().addAll(bt);
		hbox2.setAlignment(Pos.CENTER_RIGHT);

		setPadding(new Insets(0, 10, 5, 10));

		setSpacing(25);
		getChildren().addAll(pane, tx2, hbox2);
	}
	public Button getButton() {
		return bt;
	}
}
class QuestionPane extends VBox {
	BorderPane bp1;
	BorderPane bp2;
	TextArea ta;
	Button bt1;
	Button bt2;
	Button bt3;
	HBox hbox;
	Text tx;

	QuestionPane() {
		ta = new TextArea();
		ta.setPrefSize(550, 250);
		ta.setEditable(false);
		ta.setWrapText(true);
		tx = new Text("Status: 1/5 questions.");
		bt1 = new Button("<<");
		bt1.setPrefWidth(50);
		bt1.setPrefHeight(30);
		bt2 = new Button(">>");
		bt2.setPrefWidth(50);
		bt2.setPrefHeight(30);
		bt3 = new Button("Check Answers");
		bt3.setPrefWidth(120);
		bt3.setPrefHeight(30);
		bp1 = new BorderPane();
		bp2 = new BorderPane();
		hbox = new HBox(150);

		bp1.setLeft(bt1);
		bp1.setRight(bt2);
		
		hbox.getChildren().addAll(tx, bt3);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(40, 0, 0, 0));

		bp2.setBottom(hbox);
		getChildren().addAll(ta, bp1, bp2);
	}
}
class FillInPane extends StackPane {
	TextField tf;

	FillInPane() {
		tf = new TextField();
		getChildren().add(tf);
		setPadding(new Insets(75, 50, 75, 50));
	}
}
class TestPane extends VBox {
	RadioButton rb1;
	RadioButton rb2;
	RadioButton rb3;
	RadioButton rb4;
	ToggleGroup group;

	TestPane() {
		rb1 = new RadioButton();
		rb2 = new RadioButton();
		rb3 = new RadioButton();
		rb4 = new RadioButton();
		group = new ToggleGroup();

		rb1.setToggleGroup(group);
		rb2.setToggleGroup(group);
		rb3.setToggleGroup(group);
		rb4.setToggleGroup(group);

		getChildren().addAll(rb1, rb2, rb3, rb4);
		setSpacing(8);
		setPadding(new Insets(36.49, 20, 36.49, 20));
		setHeight(200);
	}
	TestPane(Question question) {
		rb1 = new RadioButton();
		rb2 = new RadioButton();
		rb3 = new RadioButton();
		rb4 = new RadioButton();
		group = new ToggleGroup();

		rb1.setToggleGroup(group);
		rb2.setToggleGroup(group);
		rb3.setToggleGroup(group);
		rb4.setToggleGroup(group);

		getChildren().addAll(rb1, rb2, rb3, rb4);
		setSpacing(8);
		setPadding(new Insets(36.49, 20, 36.49, 20));
		setHeight(200);

		ArrayList<String> labels = new ArrayList<>();
		labels.add(question.getAnswer());
		labels.add(((Test)question).getOptionAt(0));
		labels.add(((Test)question).getOptionAt(1));
		labels.add(((Test)question).getOptionAt(2));

		java.util.Collections.shuffle(labels);

		rb1.setText(labels.get(0));
		rb2.setText(labels.get(1));
		rb3.setText(labels.get(2));
		rb4.setText(labels.get(3));
	}
}
