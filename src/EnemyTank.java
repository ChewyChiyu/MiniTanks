import javax.swing.Timer;

public class EnemyTank extends Tank {
	Timer mind;
	Timer randMind;
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
		randMind = new Timer(5000, e->{
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
		});
		randMind.start();
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
