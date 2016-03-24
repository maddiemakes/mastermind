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
	
	Scene scene;
	
	AnimationTimer gameLoop;
	
	static List<int[]> S = new ArrayList<>();
	static List<int[]> Sp = new ArrayList<>();
	static int[] score;

    static MastermindController controller;
	
	public static int whitePegs;
	public static int redPegs;
	
	static int r = 0;
	static int w = 0;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Mastermind.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

	    scene = new Scene(root);
	    stage.initStyle(StageStyle.DECORATED);
		stage.setTitle("Mastermind");
	    stage.setWidth(Settings.SCENE_WIDTH);
	    stage.setHeight(Settings.SCENE_HEIGHT);
	    stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
	
	public static void AI(boolean hasRun) {
        if (!hasRun) {
            createS();

            getPegs(MastermindController.guess, MastermindController.password);
            redPegs = r;
            whitePegs = w;
        }
		else {
//		while (!(redPegs == Settings.NUM_SPACES)) {
            controller.computerNextMove();
			cut();

			score();
			MastermindController.guess = pick();
            controller.setGuess();

			getPegs(MastermindController.guess, MastermindController.password);
            redPegs = r; whitePegs = w;

			for (int i=0; i<Settings.NUM_SPACES; i++) {
				if (!(MastermindController.guess[i] == 0))
					break;
				else if (i == Settings.NUM_SPACES-1)
					System.exit(0);
			}
		}
	}
	
	/*private static void printS() {
		for (int i=0; i<S.size(); i++) {
			System.out.println("i: " + i + " S(i): " + Arrays.toString(S.get(i)) + " Sp(i): " + Arrays.toString(Sp.get(i)));
		}
	}*/
	
//	private static void printPegs() {
//		System.out.println("redPegs: " + redPegs + " whitePegs: " + whitePegs);
//	}
	
	private static void createS() { //create set S and S' of all possible guesses
		/*int a = 0;
		for (int i=0; i<Settings.NUM_SPACES; i++) {
			a += Math.pow(10, i);
		}
		int b[] = new int[Settings.NUM_SPACES];
		for (; a<=Math.pow(Settings.NUM_COLORS, Settings.NUM_SPACES); a++) {
			for (int i=0; i<Settings.NUM_SPACES; i++) {
				b[i] = (int)((int)(a%Math.pow(Settings.NUM_COLORS, i+1))/(Math.pow(Settings.NUM_COLORS, i)))+1;
			}
			//System.out.println(Arrays.toString(b));
			S.add(b);
		}*/
		int b[] = new int[(int) Math.pow(Settings.NUM_COLORS, Settings.NUM_SPACES)];
		int x;
		for (int i=0; i<Settings.NUM_SPACES; i++) {
			//x = (int) Math.pow(Settings.NUM_COLORS, i);
			for (int j=0; j<b.length; j++) {
				x = ((j/((int) Math.pow(Settings.NUM_COLORS, i))+1)%8);
				b[j] += ((int) Math.pow(10, i)) * ((x!=0)?x:8);
				//b[j] += (x!=0)?x:8;
				//b[j] += (((int) Math.pow(10, i)) * ((j/((int) Math.pow(Settings.NUM_COLORS, i))+1)%8));
			}
		}
		for (int i=0; i<b.length; i++) {
			int c[] = new int[Settings.NUM_SPACES];
			for (int j=0; j < Settings.NUM_SPACES; j++) {
				c[j] = (b[i]/((int)Math.pow(10, j)))%10;
			}
			S.add(i, c);
			Sp.add(i, c);
			//System.out.println(i + " " + b[i] + " " + Arrays.toString(S.get(i)));
			//printS();
		}
	}
	
	private static void cut() {
		//take in last guess (int guess) and pegs
		//remove impossible solutions in set S
		int x = Sp.size();
		for (int i=0; i<x; i++) {
			//if # of whitePegs pegs that would be given = what was given
			//and if # of redPegs pegs that would be given = what was given
			//then it is a possible solution
			
			getPegs(MastermindController.guess, Sp.get(i));
			if (r != redPegs || w != whitePegs) {
				//System.out.println("r: " + r + " w: " + w);
				//System.out.println("rem: " + Arrays.toString(Sp.get(i)));
				Sp.remove(i);
				i--;
				x = Sp.size();
			} else {
				//System.out.println("r: " + r + " w: " + w);
				//System.out.println(Arrays.toString(Sp.get(i)));
			}
		}
	}
	
	private static int numCut(int[] guess, int red, int white) {
		int n=0;
		//System.out.println("redPegs: " + redPegs + " whitePegs: " + whitePegs);
		for (int i=0; i<Sp.size(); i++) {
			getPegs(guess, Sp.get(i));
			//System.out.println("guess: " + Arrays.toString(guess) + " code: " + Arrays.toString(Sp.get(i)) + " r: " + r + " w: " + w);
			if (r != red || w != white) {
				n++;
			}
		}
		//System.out.println(n);
		return n;
	}
	
	private static void score() {
		// score every possible solution
		score = new int[S.size()];
		for (int i=0; i<S.size(); i++) {
			score[i] = min(S.get(i));
		}
	}
	
	private static int[] pick() {
		// pick solution in S with highest score (greatest minimum possible solutions that it could remove from S)
		int s=0;
		int[] newGuess = new int[Settings.NUM_SPACES];
		for (int i=0; i<score.length; i++) {
			if (score[i] > s && Sp.contains(S.get(i))) {
				newGuess = S.get(i);
				s = score[i];
			}
		}
		return newGuess;
	}
	
	private static int min(int[] guess) {
		int min=(int) Math.pow(Settings.NUM_COLORS, Settings.NUM_SPACES);
		int r, w;
		for (w=1; w<=Settings.NUM_SPACES; w++) {
			for (r=1; r<=Settings.NUM_SPACES-w; r++) {
				min = Math.min(min, numCut(guess, r, w));
			}
		}
		return min;
	}
	
	private static void getPegs(int[] guess, int[] code) {
		//returns number of redPegs and whitePegs pegs to global variables r and w
		int white=0, red=0;
		boolean[] flag = new boolean[Settings.NUM_SPACES];
		for (int i=0; i<Settings.NUM_SPACES; i++)
			flag[i] = false;
		for (int i=0; i<Settings.NUM_SPACES; i++) { //through guess
			if (guess[i] == code[i] && !flag[i]) {
				red++;
				flag[i] = true;
			} else {
				for (int j=0; j < Settings.NUM_SPACES; j++) { //through code
					if (j!=i && guess[i] == code[j] && !flag[j] && code[j] != guess[j]) {
						white++;
						flag[j] = true;
						break;
					}
				}
			}
		}
        controller.paintPegs(red, white);
		w=white;
		r=red;

	}
	
}