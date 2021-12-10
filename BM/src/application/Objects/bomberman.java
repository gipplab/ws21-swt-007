package applic.objects;




public class bomberman {

	int bombanzahl;
	float speed;
	float explosion;
	int health;
	boolean dead= false ;
	Point2D.float pos;
	
	
	
	
	
	playplacingbomb(int bombanzahl,float explosion){
		

	//Bombe wird eine aktuelle Position vom Bomber gelegt
		
	bomb.pos=bomberman.pos ;
	
	//Anzahl der Bomben in der Bombentasche um 1 reduzieren
	
	bombanzahl=bombanzahl-1;
	
	}
	
	// Funktion für den Fall das der Bombercharakter stirbt
	void death() {
		if(death==true)
				
	}
	
	//Bewegung nach oben 
	void moveup() {
		pos[x][y-1];	
	}
	
	
	// Bewegung nach unten 
	void movedown() {
		pos[x][y+1];
			
	}
	
	// Bewegung nach rechts
	void moveright() {
		pos[x+1][y];
		
		
	}		
	
	//Bewegung nach links
	void moveleft() {
		pos[x-1][y];
		
		
	}
	
	//Reduktion der Gesundheit bei kollision von Bombercharakter mit der explosion
	int gethit(int health ) {
		health=health-1;
		if(health==0)
			void death();
		
	}
	
	
	
	
 
	
	
	
	
	
	
	
	
}