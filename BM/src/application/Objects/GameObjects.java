package application.Objects;


import java.util.ArrayList;
import java.util.List;



public class GameObjects {

	public GameObjects() {}
	
		 public static List<List<? extends Entities>> gameObjects;

		    public static ArrayList<TileObjects> tileObjects;// Wand Kisten Item Bomb
		    public static ArrayList<Explosion> explosionObjects;//Explosion
		    public static ArrayList<Character> bomberObjects;//Spieler und Bot

		    //Listen für verschiedene Objekttypen erstellen.
		    public static void init() {
		        gameObjects = new ArrayList<>();
		        tileObjects = new ArrayList<>();
		        explosionObjects = new ArrayList<>();
		        bomberObjects = new ArrayList<>();

		        gameObjects.add(tileObjects);//tileObjects zur gameObjekts Liste hinzufügen.
		        gameObjects.add(explosionObjects);//explosionObjects zur gameObjekts Liste hinzufügen.
		        gameObjects.add(bomberObjects);//bomberObjects zur gameObjekts Liste hinzufügen.
	
		    }
		  //hier werden alle Game Objekte gezeichnet.
		    public static void spawn(TileObjects spawnObj) {
		        tileObjects.add(spawnObj);
		    }
		    public static void spawn(Explosion spawnObj) {
		        explosionObjects.add(spawnObj);
		    }
		    public static void spawn(Character spawnObj) {
		        bomberObjects.add(spawnObj);
		    }
		

}
