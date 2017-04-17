import java.awt.Graphics;

public class Projectile {
	Direction d;
	boolean hitSomething = false;
	int x;
	int y;
	ObjectType t;
	int xVelocity;
	int yVelocity;
	int width;
	int height;
	int power;
	public Projectile(int x, int y,int width, int height, int speed,int power, Direction d, ObjectType t){
		this.x = x;
		this.y = y;
		this.d = d;
		this.t = t;
		this.power = power;
		this.width = width;
		this.height = height;
		switch(d){
		case EAST:
			yVelocity = 0;
			xVelocity = speed;
			break;
		case NORTH:
			yVelocity = -speed;
			xVelocity = 0;
			break;
		case NORTH_EAST:
			yVelocity = -speed;
			xVelocity = speed;
			break;
		case NORTH_WEST:
			yVelocity = -speed;
			xVelocity = -speed;
			break;
		case SOUTH:
			yVelocity = speed;
			xVelocity = 0;
			break;
		case SOUTH_EAST:
			yVelocity = speed;
			xVelocity = speed;
			break;
		case SOUTH_WEST:
			yVelocity = speed;
			xVelocity = -speed;
			break;
		case WEST:
			yVelocity = 0;
			xVelocity = -speed;
			break;
		default:
			break;

		}

	}
	public void hitSomething(){
		hitSomething = true;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public ObjectType getT() {
		return t;
	}
	public void setT(ObjectType t) {
		this.t = t;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void draw(Graphics g, int x, int y){
		if(!hitSomething){
		switch(d){
		case EAST:
			g.drawImage(Texture.redBullets[5], x+(int)(Tank.tankWidth*.7), y+(int)(Tank.tankHeight*.2), height, width, null);
			break;
		case NORTH:
			g.drawImage(Texture.redBullets[1], x+(int)(Tank.tankWidth*.7), y+(int)(Tank.tankHeight*.2), height, width, null);
			break;
		case NORTH_EAST:
			g.drawImage(Texture.redBullets[2], x+(int)(Tank.tankWidth*.7), y+(int)(Tank.tankHeight*.2), height, width, null);
			break;
		case NORTH_WEST:
			g.drawImage(Texture.redBullets[0], x+(int)(Tank.tankWidth*.7), y+(int)(Tank.tankHeight*.2), height, width, null);
			break;
		case SOUTH:
			g.drawImage(Texture.redBullets[7], x+(int)(Tank.tankWidth*.7), y+(int)(Tank.tankHeight*.2), height, width, null);
			break;
		case SOUTH_EAST:
			g.drawImage(Texture.redBullets[8], x+(int)(Tank.tankWidth*.7), y+(int)(Tank.tankHeight*.2), height, width, null);
			break;
		case SOUTH_WEST:
			g.drawImage(Texture.redBullets[6], x+(int)(Tank.tankWidth*.7), y+(int)(Tank.tankHeight*.2), height, width, null);
			break;
		case WEST:
			g.drawImage(Texture.redBullets[3], x+(int)(Tank.tankWidth*.7), y+(int)(Tank.tankHeight*.2), height, width, null);
			break;
		default:
			break;

		}
		}else{
			g.drawImage(Texture.redBulletHit, x, y,height*2,width*2, null);
			xVelocity = 0;
			yVelocity = 0;
		}
	}
	
	
	public Direction getD() {
		return d;
	}
	public void setD(Direction d) {
		this.d = d;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x += x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y += y;
	}
	public int getxVelocity() {
		return xVelocity;
	}
	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
	public int getyVelocity() {
		return yVelocity;
	}
	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	public ObjectType getO(){
		return t;
	}
}
