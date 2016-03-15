package main;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class Settings {
	
	public static double SCENE_WIDTH = 600;
	public static double SCENE_HEIGHT = 500;
	
	public static Map<Integer, Color> colors() {
		return Collections.unmodifiableMap(new HashMap<Integer, Color>() {
			private static final long serialVersionUID = 1L;
			{
                put(0, Color.BLACK);
                put(1, Color.GREEN);
                put(2, Color.WHITE);
                put(3, Color.RED);
                put(4, Color.BLUE);
                put(5, Color.YELLOW);
                put(6, Color.BROWN);
                put(7, Color.PURPLE);
                put(8, Color.ORANGE);
            }
        });
	}
	
}
