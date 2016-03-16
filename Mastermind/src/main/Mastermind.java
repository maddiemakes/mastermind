package main;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Mastermind extends Application {
	
	Group root;
	Pane gameLayer;
	ScrollPane camera;
	Scene scene;
	
	AnimationTimer gameLoop;
	
	int n = 0;
	
//	List<Row> rows = new ArrayList<>();
	
	List<Integer> S = new ArrayList<>();
	List<Integer> score = new ArrayList<>();
	
	int[] code = new int[Settings.NUM_SPACES];
	int[] guess = {1,1,2,2};
	int white, red;
	
	int r = 0;
	int w = 0;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Mastermind.fxml"));
	    scene = new Scene(root);
	    stage.initStyle(StageStyle.DECORATED);
		stage.setTitle("Mastermind");
	    stage.setWidth(Settings.SCENE_WIDTH);
	    stage.setHeight(Settings.SCENE_HEIGHT);
	    stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        createGameLoop();
        gameLoop.start();
	}
	
	private void createGameLoop() {
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long l) {  
				chooseCode();
				/*if (guess != 0) {
					makeGuess();
					guess = 0;
				}*/
		 	}
		};
	}
	
	private void createS() { //create set S of all possible guesses
		int a = 0;
		for (int i=0; i<Settings.NUM_SPACES; i++) {
			a += Math.pow(10, i);
		}
		for (; a<=Math.pow(Settings.NUM_COLORS, Settings.NUM_SPACES); a++) {
			S.add(a);
		}
	}
	
	private void makeGuess() {
		//output guess to GUI
	}
	
	private void chooseCode() {
		//allow user to input code
	}
	
	private void cut() {
		//take in last guess (int guess) and pegs
		//remove impossible solutions in set S
		for (int i=0; i<S.size(); i++) {
			//if # of white pegs that would be given = what was given
			//and if # of red pegs that would be given = what was given
			//if ()
		}
	}
	
	private void getPegs(int[] guess, int[] code) {
		//returns number of red and white pegs to global variables r and w
		int white=0, red=0;
		boolean[] flag = new boolean[Settings.NUM_SPACES];
		for (int i=0; i<Settings.NUM_SPACES; i++) {
			if (guess[i] == code[i]) {
				red++;
			} else {
				for (int j=0; j<Settings.NUM_SPACES; j++) {
					if (j!=i && guess[i] != code[j] && !flag[j]) {
						white++;
						flag[j] = true;
						break;
					}
				}
			}
		}
		w=white;
		r=red;
	}
	
}