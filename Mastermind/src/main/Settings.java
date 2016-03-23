package main;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Settings {
	
	public static double SCENE_WIDTH = 600;
	public static double SCENE_HEIGHT = 528;
	
	public static int NUM_COLORS = 6; //(<10)
	public static int NUM_SPACES = 4;
	public static Map<Integer, String> colors() {
		return Collections.unmodifiableMap(new HashMap<Integer, String>() {
			private static final long serialVersionUID = 1L;
			{
                put(0, "black");
                put(1, "green");
                put(2, "white");
                put(3, "red");
                put(4, "blue");
                put(5, "yellow");
                put(6, "brown");
                put(7, "purple");
                put(8, "orange");
            }
        });
	}
}
