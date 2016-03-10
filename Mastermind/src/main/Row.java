package main;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Row extends HBox {
	
	Pane layer;
	
	double x, y;
	
	int[] colors = new int[4];
	Circle[] circles = new Circle[4];
	
	public Row(Pane layer, double x, double y) {
		this.layer = layer;
		this.x = x;
		this.y = y;
		
		for(int i=0; i<4; i++) {
			colors[i] = 0;
			circles[i] = new Circle();
			circles[i].setFill(Settings.colors().get(colors[i]));
			this.getChildren().add(circles[i]);
		}
	}

}
