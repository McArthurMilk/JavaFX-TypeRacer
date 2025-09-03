package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import javafx.scene.paint.Color;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.effect.Reflection;
import javafx.application.Platform;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.animation.Timeline;

import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;


import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

public class Main extends Application {
	
	 public Text timerLabel;
     private Timeline timeline1;
     private Timeline timeline2; 
     private Timeline timeline3; 
     private Timeline timeline4;
     private int remainingSeconds = 3; // Initial countdown time in seconds
     
     private int gameSeconds = 5; 
     
     private int charactersTyped = 0;
     private long startTime; // in milliseconds
     
     private int points = 0; 
     private int round = 1; 
     private int numOfSentences = 1; 
    
     private String currentSentence; 
     
     private TextFlow sentence = new TextFlow();
     
     private int lastCountedLength = 0;
     
     boolean timepause = true; 
     
     //private String currentGif;
     private Image TypingImage2 = new Image("https://media.tenor.com/xjvmoEYtjwEAAAAM/thumbs-up-double-thumbs-up.gif");
	private ImageView TypingView2 = new ImageView(TypingImage2);
	
	
	@Override
	public void start(Stage primaryStage) throws InterruptedException, IOException {

		
		Methods method = new Methods(); 
		
		List words = new ArrayList();
		
		ArrayList gifs = new ArrayList();
		
		
			
		method.addgifs(gifs);
		
		method.addSentences("Words"); 
		
		currentSentence = method.getSentenceForRound(round);
		
        method.setSentenceToFlow(sentence,currentSentence);
	
		 
		//BUTTONS
		Button StartButton = new Button ("Start");
		Button HowToPlayButton = new Button ("How to Play");
		Button ExitButton = new Button ("Exit");
		Button ReturnButton = new Button ("Return to Home");
		Button ExitGameButton = new Button ("Exit Game");
		Button RetryButton = new Button("Retry");
		
		//PAGES/SCENES
		
		//Start Page
		HBox root = new HBox(10, StartButton, HowToPlayButton,ExitButton);
		root.setAlignment(Pos.CENTER);
		
		Image TypingImage = new Image("https://i.pinimg.com/originals/70/37/d4/7037d478852af21357f038fac2d2e9f6.gif");
		ImageView TypingView = new ImageView(TypingImage);
		
	    
		
		VBox vbox = new VBox(20, TypingView,root);
		vbox.setAlignment(Pos.CENTER);
		
		Scene startpage = new Scene(vbox, 600, 600);
		ThemeManager.applyTheme(startpage);
		
		//How To Play Page
		VBox root2 = new VBox(10);
		
		Text htpTitle = new Text(); 
		Label htp = new Label(); 
		htpTitle.setText("HOW TO PLAY");
		htp.setText("As each sentence comes onto the screen, type out the "
				+ "sentence before the time runs out! Each round consists of 5 sentences and after every 3 rounds, the sentences become "
				+ "more and more difficult! Good Luck!");
		
		htp.setTextAlignment(TextAlignment.CENTER);
		htp.setWrapText(true); 
		htp.setMaxWidth(400); 
		
	    root2.setAlignment(Pos.CENTER);
		root2.getChildren().addAll(htpTitle,htp,ReturnButton );
		Scene howtoplay = new Scene(root2, 600, 600);
		ThemeManager.applyTheme(howtoplay);
		
		//Main Game Page
		
		TextField userinput = new TextField(); 
		userinput.setMaxWidth(500);
		
		
		TypingView2.setImage(method.getRandomGif(gifs));
		//TypingView2.setFitWidth(200);
        //TypingView2.setFitHeight(150);
		TypingView2.setVisible(true);
		
		timerLabel = new Text();
		Text ready = new Text("Ready?"); 
		
		Text typingStats = new Text();
		Text numofpoints = new Text("Points: " + points); 
		Text roundnum = new Text("Round: " + round);
		Text numofSentence = new Text("Sentence #" + numOfSentences);
		
		roundnum.setTextAlignment(TextAlignment.CENTER);
		
		
		userinput.setPromptText("Start typing...");
		userinput.setStyle("-fx-background-color: #fff; -fx-border-color: #081E3F;");
		

		
		VBox root3 = new VBox(20, TypingView2,roundnum,ready,numofSentence,sentence, timerLabel,   userinput);
		root3.setAlignment(Pos.CENTER);
		
		
		sentence.setTextAlignment(TextAlignment.CENTER);
		
		BorderPane borderPane = new BorderPane();
		
		borderPane.setCenter(root3);
		
		HBox exitandretryandtyping = new HBox(RetryButton, ExitGameButton, typingStats);
		exitandretryandtyping.setMargin(RetryButton, new Insets( 20));
		exitandretryandtyping.setMargin(ExitGameButton, new Insets( 20));
		
		borderPane.setBottom(exitandretryandtyping);
		borderPane.setMargin(exitandretryandtyping, new Insets (15));
		
		
		RetryButton.setVisible(false);
	
		
		Scene maingame = new Scene(borderPane, 600, 600);
		ThemeManager.applyTheme(maingame);
		
		
		
		//BUTTON EVENTS && OTHER EVENTS
		StartButton.setOnAction(event -> {
	    	   
	    	   System.out.println("Start Button Pushed");
	    	   primaryStage.setScene(maingame);   
	    	  
	       });
		
		HowToPlayButton.setOnAction(event -> {
	    	   
	    	  System.out.println("HowToPlayButton Pushed");
	    	  primaryStage.setScene(howtoplay);
	    	  
	       });
		
		 ExitButton.setOnAction(event -> {
		       
		       System.out.println("Exit Button Pushed");
		       Platform.exit();
		        
		   });
		 
		 
		 ReturnButton.setOnAction(event -> {
	    	   
	    	   System.out.println("Return Button Pushed");
	    	   primaryStage.setScene(startpage);
	    	   
	       });
		 
		 ExitGameButton.setOnAction(e -> {
			 
			 Platform.exit(); 
		   });
		 
		 RetryButton.setOnAction(event -> {
	    	   
	    	   System.out.println("Retry Button Pushed");
	    	   timeline3.playFromStart();
       		
       			startTime = System.currentTimeMillis(); // Start tracking time
       			charactersTyped = 0;                    // Reset character count
       			
       			displayTypingStats(typingStats);
       			typingStats.setText("TYPING STATS: \nWPM: " + 0 + "\nCPM: " + 0 + "\nCharacters Typed: " + 0 +
       					"\nSentences Typed: " + 0);
       			
       			ready.setText("Ready?");
       			sentence.setVisible(false);
       			
       			timepause = true; 
       			
       			gameSeconds = 5; 
       			timeline4.stop();
       			
       			numOfSentences = 1; 
       			round = 1;  
       			
       			numofSentence.setText("Sentence #" + numOfSentences);
       			roundnum.setText("Round " + round);
       			
       			timerLabel.setText("");
       			
	            
            	userinput.setEditable(true);
            	userinput.setText("");
            	
            	RetryButton.setVisible(false);
	    	   
	       });
		 
		 //Actual Countdown for game to start
	        timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
	        	
	            remainingSeconds--;
	            timerLabel.setText(method.formatTime(remainingSeconds));
	        	

	            if (remainingSeconds < 1) {
	              
	            	timerLabel.setText("");
	            	remainingSeconds = 3; 
	            	timeline1.stop(); 
	            	timeline2.playFromStart();
	            	timepause = false; 
	            
	            	sentence.setVisible(true);
	            	timeline4.playFromStart();
	            	
	            	TypingView2.setVisible(false);
	            	TypingView2.setImage(method.getRandomGif(gifs));
	              
	            }
	        }));
	        timeline1.setCycleCount(Timeline.INDEFINITE);
	        
	        
	        
	      //5 second Countdown in game
	        timeline4 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
	        	
	            gameSeconds--;
	            timerLabel.setText(method.formatTime(gameSeconds));
	        	

	            if (gameSeconds < 1) {
	              
	            	timerLabel.setText("GAME OVER");
	            	
	            	
	            	timeline1.stop(); 
	            	timeline2.stop();
	            	timeline3.stop();
	            	
	            	timepause = true; 
	            
	            	userinput.setEditable(false);
	            	
	            	RetryButton.setVisible(true);
	            	
	            	
	            }
	        }));
	        timeline4.setCycleCount(Timeline.INDEFINITE);
	        
	        
	        //3 Second Ready text countdown
	        timeline3 = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
	        	
	        	ready.setText("");
	        	
	        	timerLabel.setText(method.formatTime(remainingSeconds));
	        	timeline1.playFromStart();
	        	//timeline2.playFromStart(); 
	        	
	      }));
	        
	        
	        
	        //Constant scanner of input, 60 times a sec
	         timeline2 = new Timeline(new KeyFrame(Duration.seconds(.01), event -> {
	            
	        	try {
					method.scaninput(sentence, userinput, words, this, typingStats, points, numofpoints, numofSentence, roundnum);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }));
	        timeline2.setCycleCount(Timeline.INDEFINITE); 
	      

	        
	      //If scene changes to main game
	        primaryStage.sceneProperty().addListener(new ChangeListener<Scene>() {
	        	
	            @Override
	            public void changed(ObservableValue<? extends Scene> observable, Scene oldScene, Scene newScene) {
	        
	                if (newScene != null && newScene == maingame) {
	                    	
	                		
	                		timeline3.playFromStart();
	                		
	                		startTime = System.currentTimeMillis(); // Start tracking time
	                	    charactersTyped = 0;                    // Reset character count
	                		displayTypingStats(typingStats);
	                		typingStats.setText("TYPING STATS: \nWPM: " + 0 + "\nCPM: " + 0 + "\nCharacters Typed: " + 0 + 
	                				"\nSentences Typed: " + 0);
	                		sentence.setVisible(false);
	                	
	                }
	            
	            }});
	     
	    startpage.getStylesheets().clear(); // remove current styles
	       
	    Font.loadFont(getClass().getResourceAsStream("/application/PressStart2P-Regular.ttf"), 12); 
	    startpage.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
	    
        primaryStage.setScene(startpage);
        primaryStage.show();
        primaryStage.setResizable(false);
        
        
    }
	
	//Other Methods
	
	public void incrementCharactersTyped() {
	    charactersTyped++;
	    
	}
	
	public void increasePoints(Text pointstext) {
		points+= 100; 
		pointstext.setText("Points: " + points);
	}
	
	//Typing Stats Info
	public void displayTypingStats(Text text) {
		
		if (timepause == false) {
	    long elapsedMillis = System.currentTimeMillis() - startTime;
	    double minutes = elapsedMillis / 60000.0;
		
	    int cpm = (int) (charactersTyped / minutes);
	    int wpm = (int) ((charactersTyped / 5.0) / minutes);
		
	    text.setText("TYPING STATS: \nWPM: " + wpm + "\nCPM: " + cpm + "\nCharacters Typed: " + charactersTyped + 
	    		"\nSentences Typed: " + numOfSentences);
		}
		
	
	}
	
	public String getCurrentSentence() {
		
		return currentSentence;
	}
	
	public void setCurrentSentence(String newsentence) {
		
		currentSentence = newsentence; 
	}
	
	public int getRoundNum() {
		
		return round; 
	}
	
	
	public void increaseSentenceNumRoundNum(Text numOfSentence, Text roundnum) {
		if (numOfSentences <= 3) {
			
		numOfSentences++; 
		numOfSentence.setText("Sentence #" + numOfSentences);
		gameSeconds = 5; 
		
	}
		if (numOfSentences == 4) {
			numOfSentences = 1; 
			round++; 
			gameSeconds = 5; 
			
			timerLabel.setText("");
			roundnum.setText("Round " + round);
			numOfSentence.setText("Sentence #" + numOfSentences);
			
			timepause = true; 
			sentence.setVisible(false);
			
			timeline4.stop();
			timeline2.stop();
			timeline1.stop();
			
			timeline3.playFromStart();
			
			
			TypingView2.setVisible(true);
			
		}
	}
	

	public int getLastCountedLength() {
    return lastCountedLength;
	}

	public void setLastCountedLength(int length) {
    this.lastCountedLength = length;
	}
	
	//Themes/Styles
	public class ThemeManager {
	    private static String currentTheme = "styles.css";

	    public static void setTheme(String themeFileName) {
	        currentTheme = themeFileName;
	    }

	    public static void applyTheme(Scene scene) {
	        scene.getStylesheets().clear();
	        scene.getStylesheets().add(ThemeManager.class.getResource(currentTheme).toExternalForm());
	    }

	    public static String getCurrentTheme() {
	        return currentTheme;
	    }
	}

	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		launch(args);
		
	}

}
