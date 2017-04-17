import javax.swing.Timer;

public class EnemyTank extends Tank {
	Timer mind;
	Timer attackMind;
	int primedForFire = 0;
	public EnemyTank(int x, int y, int width, int height, int health, ObjectType o) {
		super(x, y, width, height, health, o);
		switch((int)(Math.random()*4)){

		case 0:
			xVelocity = speed;
			yVelocity = speed;
			break;
		case 1:
			xVelocity = -speed;
			yVelocity = speed;
			break;
		case 2:
			xVelocity = speed;
			yVelocity = -speed;
			break;
		case 3:
			xVelocity = -speed;
			yVelocity = -speed;
			break;
		}
		attackMind = new Timer(100, e->{
		        double deltaX = GamePanel.player.getX()- getX(); 
		        double deltaY = GamePanel.player.getY()- getY();
		        double angle = Math.atan(Math.abs(deltaY/deltaX));
		        angle = Math.toDegrees(angle);
		        if((deltaX>0)&&(deltaY>0)){
		            angle =360 - angle;
		        }
		        if((deltaX>0)&&(deltaY<0)){
		        }
		        if((deltaX<0)&&(deltaY>0)){
		            angle+=180;
		        }
		        if((deltaX<0)&&(deltaY<0)){
		            angle = 180 - angle;
		            
		        }
		        angle = (int)(angle);
		        if(angle<30&&angle>330){
		        	xVelocity = speed;
		        	yVelocity = 0;
		        }
		        if(angle>30&&angle<60){
		        	xVelocity = speed;
		        	yVelocity = -speed;
		        }
		        if(angle<210&&angle>150){
		        	xVelocity = -speed;
		        	yVelocity = 0;
		        }
		        if(angle>120&&angle<150){
		        	xVelocity = -speed;
		        	yVelocity = -speed;
		        }
		        if(angle<120&&angle>60){
		        	xVelocity = 0;
		        	yVelocity = -speed;
		        }
		        if(angle>300&&angle<330){
		        	xVelocity = speed;
		        	yVelocity = speed;
		        }
		        if(angle<300&&angle>240){
		        	xVelocity = 0;
		        	yVelocity = speed;
		        }
		        
		        if(primedForFire++>=5){
		        	shoot();
		        	primedForFire = 0;
		        }
		});
		attackMind.start();
		think();
	}
	public void think(){
		mind = new Timer(10,e->{
			if(justHit){
				Direction d = this.d;
				for(int index = 0; index < GamePanel.player.getBulletArray().size(); index++){
					Projectile p = GamePanel.player.getBulletArray().get(index);
					if(p.hitSomething){
						d = p.d;
						break;
					}	
				}
				switch(d){
				case EAST: case NORTH: case WEST: case SOUTH:
					switch((int)(Math.random()*4)){
					case 0:
						xVelocity = speed;
						yVelocity = speed;
						break;
					case 1:
						xVelocity = -speed;
						yVelocity = speed;
						break;
					case 2:
						xVelocity = speed;
						yVelocity = -speed;
						break;
					case 3:
						xVelocity = -speed;
						yVelocity = -speed;
						break;
					}
					break;
				case NORTH_WEST: case NORTH_EAST: case SOUTH_EAST: case SOUTH_WEST:
					switch((int)(Math.random()*4)){
					case 0:
						xVelocity = 0;
						yVelocity = speed;
						break;
					case 1:
						xVelocity = 0;
						yVelocity = -speed;
						break;
					case 2:
						xVelocity = speed;
						yVelocity = 0;
						break;
					case 3:
						xVelocity = -speed;
						yVelocity = 0;
						break;
					}
					break;
				default:
					break;

				}
				justHit = false;
			}
		});
		mind.start();

	}

}
