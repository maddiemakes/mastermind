package main;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
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
	
	List<Row> rows = new ArrayList<>();
	
	List<Integer> S = new ArrayList<>();
	List<Integer> score = new ArrayList<>();
	int guess = 1122;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		root = new Group();
		gameLayer = new Pane();
		camera = new ScrollPane(gameLayer);
	    scene = new Scene(camera);
		
		root.getChildren().add(camera);
		camera.setHmin(0);
	    camera.setVmin(0);
	    camera.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	    //camera.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	    camera.setPannable(false);
	    camera.setPrefSize(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		
	    stage.initStyle(StageStyle.DECORATED);
	    stage.setWidth(Settings.SCENE_WIDTH);
	    stage.setHeight(Settings.SCENE_HEIGHT);
	    stage.setScene(scene);
		stage.setResizable(false);
        stage.show();
        
        createGameLoop();
        //gameLoop.start();
	}
	
	private void createGameLoop() {
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long l) {  
				chooseCode();
				if (guess != 0) {
					makeGuess();
					guess = 0;
				}
		 	}
		};
	}
	
	private void createS() {
		for (int a=1; a<=8; a++) {
			for (int b=1; b<=8; b++) {
				for (int c=1; c<=8; c++) {
					for (int d=1; d<=8; d++) {
						S.add(1000*a+100*b+10*c+d);
					}
				}
			}
		}
	}
	
	private void makeGuess() {
		
	}
	
	private void chooseCode() {
		
	}
	
}
