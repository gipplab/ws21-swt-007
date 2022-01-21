package application.Objects;


import java.util.ArrayList;
import java.util.List;



public class GameObjects {

	public GameObjects() {}
	
		 public static List<List<? extends Entities>> gameObjects;

		    public static ArrayList<TileObjects> tileObjects;// Wand Kisten Item Bomb
		    public static ArrayList<Explosion> explosionObjects;
		    public static ArrayList<Explosionb> explosionObjectsbot;
		    public static ArrayList<Character> bomberObjects;

	
		    public static void init() { 
		        gameObjects = new ArrayList<>();
		        tileObjects = new ArrayList<>();
		        explosionObjects = new ArrayList<>();
		        bomberObjects = new ArrayList<>();
		        explosionObjectsbot = new ArrayList<>();

		        gameObjects.add(tileObjects);
		        gameObjects.add(explosionObjects);
		        gameObjects.add(bomberObjects);
		        gameObjects.add(explosionObjectsbot);
	
		    }

		    public static void spawn(TileObjects spawnObj) {
		        tileObjects.add(spawnObj);
		    }
		    public static void spawn(Explosion spawnObj) {
		        explosionObjects.add(spawnObj);
		    }
		    public static void spawn(Character spawnObj) {
		        bomberObjects.add(spawnObj);
		    }
		 
		    public static void spawn(Explosionb spawnObj) {
		    	explosionObjectsbot.add(spawnObj);
		    }
		    
}
