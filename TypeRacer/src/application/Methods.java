package application;

import java.util.Scanner;



import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.effect.Reflection;

import javafx.scene.media.AudioClip;

import javafx.scene.paint.Color;

import javafx.animation.PauseTransition;
import javafx.util.Duration;


public class Methods  {
	
	
	    private final static List<String> easySentences = new ArrayList<>();
	    private final static List<String> mediumSentences = new ArrayList<>();
	    private final static List<String> hardSentences = new ArrayList<>();
	    

	    public void addSentences(String filePath) throws IOException {
	    	
	    
	        List<String> lines = Files.readAllLines(Paths.get(filePath));

	        for (String line : lines) {
	        	
	        
	            if (line.startsWith("E:")) {
	            	
	                easySentences.add(line.substring(2).trim());
	                
	            } else if (line.startsWith("M:")) {
	            	
	                mediumSentences.add(line.substring(2).trim());
	                
	            } else if (line.startsWith("H:")) {
	           
	                hardSentences.add(line.substring(2).trim());
	            }
	            
	            else {
	            	
	            	System.out.println("Unable to locate file");
	            }
	        }
	    }
	    
	    
	
	    public static String getSentenceForRound(int round) {
	        if (round <= 3) {
	        	
	            return getRandomSentence(easySentences);
	            
	        } else if (round <= 6) {
	        	
	            return getRandomSentence(mediumSentences);
	            
	        } else {
	        	
	            return getRandomSentence(hardSentences);
	        }
	    }
	
	
	
	
	public static void addgifs(ArrayList gifs) throws FileNotFoundException{
		
		File file = new File ("Gifs");
		
		Scanner scan = new Scanner(file);
		
		while (scan.hasNextLine()) {
            String newgif = scan.nextLine();
            gifs.add(newgif);
        }
        scan.close();
       
}
	
	
	
	String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d", secs);
    }
	
	
	
	public static void scaninput(TextFlow sentence,TextField input, List words, Main main, Text typingstats, int points, Text pointsText, Text numOfSentences, Text roundnum) throws InterruptedException {
		
		
		String userInput = input.getText();
		String currentsentence = main.getCurrentSentence();
		
		int lastCounted = main.getLastCountedLength();
		
		sentence.getChildren().clear();
		
		
		boolean fullyCorrect = true; 
		
		
	    for (int i = 0; i < currentsentence.length(); i++) {
	    	
	        char ch = currentsentence.charAt(i);
	        Text t = new Text(String.valueOf(ch));
	        
	        if (i < userInput.length()) {
	        	
	            if (userInput.charAt(i) == ch) { //Correctly inputted
	            	
	                t.setFill(Color.GREEN);
	                
	                if (i >= lastCounted) {
	                    main.incrementCharactersTyped(); // Only increment for *new* correct characters and correctly inputted
	                }
	                
	            } else { //Wrongly inputted
	                t.setFill(Color.RED);
	                fullyCorrect = false;
	            }
	            
	        } else { //Default color 
	            t.setFill(Color.BLACK);
	            fullyCorrect = false;
	        }

	        sentence.getChildren().add(t);
	        
	    }
	    
	    main.setLastCountedLength(userInput.length());//characters typed in length of current sentence 
	        
	        // If the sentence is completely typed out
            if (fullyCorrect && userInput.length() == currentsentence.length()) {
            	 
            	input.clear(); 
                 
                 main.increasePoints(pointsText); 
                 
                 main.displayTypingStats(typingstats);
                
     	         main.increaseSentenceNumRoundNum(numOfSentences, roundnum); 
     	         
     	         //removeSentence(words, currentsentence);
     	        
	             
              
	            // Setting a new sentence
	               try {
	            	  
	            	    	
	                   String newsentence= getSentenceForRound(main.getRoundNum());
	                   
	                   main.setCurrentSentence(newsentence);
	                   
	                   setSentenceToFlow(sentence, newsentence);
	                   
	                   main.setLastCountedLength(0);//Resetting character count for sentence
	                   
	                 
	                   
	               } catch (Exception e) {
	                   e.printStackTrace();
	               }
	               
          
            
            
            }
	      
	    }
	
	//Sending the new sentence to word node and having it be a character array
	public static void setSentenceToFlow(TextFlow word, String sentence) {
		
	    word.getChildren().clear();
	    
	    for (char c : sentence.toCharArray()) {
	    	
	        Text t = new Text(String.valueOf(c));
	        
	        t.setFill(Color.BLACK);
	        
	        word.getChildren().add(t);
	    }
	}
	
	//Getting random word from words list
	public static String getRandomSentence(List<String> words) {
		
		 Random rand = new Random();
		
		if (words.isEmpty()) {
			
            return "No sentence available.";
        }
        return words.get(rand.nextInt(words.size()));
	    
	    
	}

	
	public static void removeSentence(ArrayList words, String sentence) {
		
		
		words.remove(sentence);
	}
	

		public static Image getRandomGif(ArrayList<String> gifs) {
			
		    Random rand = new Random();
		    
		    		
		    Image image21 = new Image(gifs.get(rand.nextInt(gifs.size())));
		    
		    return image21;
		}
	
	
		


}
