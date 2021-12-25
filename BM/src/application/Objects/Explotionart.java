package application.Objects;


import application.GamePanel;
import application.Ressourcen;

public class Explotionart //extends Entities 
{
	
	public  int direction;
	public int radius;
	public int x, y;
	Explosion[] explosions;
	Explosion explosions0;
	public Explotionart(int x, int y) {
		explosions0= new Explosion(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage());
	}
	public Explotionart(int x, int y, int direction, int radius) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.direction=direction;
		this.radius=radius;
		explosions = new Explosion[ berechneDistance() ];
		createExplosions();
		
	}
	int berechneDistance() {
		int radius = 0;
		int x1 = this.x;
		int y1 = this.y;
		while(radius < this.radius) {
			if(this.direction == 0) y1-=GamePanel.SQUARE_SIZE;
			if(this.direction == 1) x1+=GamePanel.SQUARE_SIZE;
			if(this.direction == 2) y1+=GamePanel.SQUARE_SIZE;
			if(this.direction == 3) x1-=GamePanel.SQUARE_SIZE;
			
			
			
			if(Entities.isBomber(x1, y1)) ++radius; //Bot oder Spieler getroffen
			
			int k=Entities.isWall(x1, y1);
			if(k==1) {
				break;
				}
			else if(k==0) {
						radius++;
						break;
							}
			
			++radius;
		}
		return radius;
		
	}
	void createExplosions() {
		boolean last = false;
		int x =(int) this.x;
		int y =(int) this.y;
		for (int i = 0; i < explosions.length; i++) {
			//last = i == explosions.length -1 ? true : false;
			
			switch (direction) {
				case 0:{ 
					y-=GamePanel.SQUARE_SIZE;
					if(!last)
						explosions[i] = new Explosion(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
					else 
						explosions[i] = new Explosion(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
				} break;
				case 1:{ 
					x+=GamePanel.SQUARE_SIZE;
					if(!last)
						explosions[i] = new Explosion(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
					else 
						explosions[i] = new Explosion(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
				} break;
				case 2:{ 
					y+=GamePanel.SQUARE_SIZE;
					if(!last)
						explosions[i] = new Explosion(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
					else 
						explosions[i] = new Explosion(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
				} break;
				case 3:{ 
					x-=GamePanel.SQUARE_SIZE;
					if(!last)
					explosions[i] = new Explosion(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
					else 
						explosions[i] = new Explosion(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
				}break;
			}
			
		}
	}
}
