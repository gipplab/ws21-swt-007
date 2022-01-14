package application.Objects;


import application.GamePanel;
import application.Ressourcen;

public class Explosionbot
{
	
	public  int direction;
	public int radius;
	public int x, y;
	Explosionb[] explosionsbot;
	Explosionb explosions0bot;
	public Explosionbot(int x, int y) {
		explosions0bot= new Explosionb(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage());
	}
	public Explosionbot(int x, int y, int direction, int radius) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.direction=direction;
		this.radius=radius;
		explosionsbot = new Explosionb[ berechneDistance() ];
		createExplosions();
		
	}
	int berechneDistance() {
		int radius = 0;
		int x1 = this.x;
		int y1 = this.y;
		while(radius < this.radius) {
			if(this.direction == 0) y1-=GamePanel.SQUARE_SIZE;
			else if(this.direction == 1) x1+=GamePanel.SQUARE_SIZE;
			else if(this.direction == 2) y1+=GamePanel.SQUARE_SIZE;
			else if(this.direction == 3) x1-=GamePanel.SQUARE_SIZE;
			
			int k=Entities.isWall(x1, y1);
			
			if(Entities.isBomber(x1, y1)) ++radius; //Bot oder Spieler getroffen
			
			
			else if(k==1) {//Wände getroffen.
				break;
				}
			else if(k==0) {//keine Wände getroffen.
						radius++;
						break;
							}
			
			else ++radius;
		}
		return radius;
		
	}
	void createExplosions() {
		boolean last = false;
		int x =(int) this.x;
		int y =(int) this.y;
		for (int i = 0; i < explosionsbot.length; i++) {
			//last = i == explosions.length -1 ? true : false;
			
			switch (direction) {
				case 0:{ 
					y-=GamePanel.SQUARE_SIZE;
					if(!last)
						explosionsbot[i] = new Explosionb(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
					else 
						explosionsbot[i] = new Explosionb(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
				} break;
				case 1:{ 
					x+=GamePanel.SQUARE_SIZE;
					if(!last)
						explosionsbot[i] = new Explosionb(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
					else 
						explosionsbot[i] = new Explosionb(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
				} break;
				case 2:{ 
					y+=GamePanel.SQUARE_SIZE;
					if(!last)
						explosionsbot[i] = new Explosionb(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
					else 
						explosionsbot[i] = new Explosionb(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
				} break;
				case 3:{ 
					x-=GamePanel.SQUARE_SIZE;
					if(!last)
						explosionsbot[i] = new Explosionb(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
					else 
						explosionsbot[i] = new Explosionb(x, y,  Ressourcen.IMAGES.EXPLOSION.getImage() );
				}break;
			}
			
		}
	}
}
