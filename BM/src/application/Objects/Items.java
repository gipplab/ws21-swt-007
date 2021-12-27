package application.Objects;



import application.Ressourcen;




public class Items extends TileObjects {

	public static float randomStrongItem;
	
	int poweruptype;
    
	
	
	public Items(double x, double y) {
		// TODO Auto-generated constructor stub
	        super(x,y,null);
	         
	       
	        poweruptype = (int) Math.round(Math.random() * 4);
	        switch(poweruptype) {
	        case 0:{
	        	
	        	this.img =Ressourcen.IMAGES.HERZITEM.getImage();
	        	break;
	        }
	        case 1:{
	        	
	        	this.img =Ressourcen.IMAGES.BOMBITEM.getImage();
	        	break;
	        }
	        case 2:{
 	
	        	this.img =Ressourcen.IMAGES.FLAMMEITEM.getImage();
	        	break;
	        }
	        case 3:{
 	
	        	this.img = Ressourcen.IMAGES.SPEEDITEM.getImage();
	        	break;
	        }
	        
	        
	        
	        }
	         
	    }
	
	
//	public enum Type {
//       
//        Bomb(Ressourcen.IMAGES.BOMBITEM.getImage()) {
//            @Override
//            protected void grantBonus(Character bomber) {
//                bomber.addAmount(1);
//            }
//        },
//
//        
//        Fireup(Ressourcen.IMAGES.FLAMMEITEM.getImage()) {
//            @Override
//            protected void grantBonus(Character bomber) {
//                bomber.addFirepower(1);
//            }
//        },
//
//
//        Speed(Ressourcen.IMAGES.SPEEDITEM.getImage()) {
//            @Override
//            protected void grantBonus(Character bomber) {
//                bomber.addSpeed(0.5f);
//            }
//        },
//        Heart(Ressourcen.IMAGES.HERZITEM.getImage()){
//            @Override
//            protected void grantBonus(Character bomber) {
//                bomber.addHealth(1);
//            }
//        };
//        

       

       
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean getDeath() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isPlayer() {
		// TODO Auto-generated method stub
		return false;
	}
   

    

}

