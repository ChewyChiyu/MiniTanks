import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Timer;

public class Tank {
	ArrayList<Projectile> bulletList = new ArrayList<Projectile>();
	Timer move;
	int moveIndex = 0;
	final int speed = 1;
	public static int tankWidth;
	public static int tankHeight;
	int health;
	final int initialHealth;
	int x;
	int y;
	int xVelocity;
	int yVelocity;
	final int width;
	final int height;
	ObjectType o;
	Direction d = Direction.EAST;
	public Tank(int x, int y, int width, int height,int health, ObjectType o){
		this.x = x;
		this.y = y;
		initialHealth = health;
		this.width = width;
		this.height = height;
		this.health = health;
		tankWidth = width;
		tankHeight = height;
		this.o = o;
		
		move = new Timer(64, e->{
			if(xVelocity!=0||yVelocity!=0){
				incrementMoveIndex();
			}
		});
		move.start();
		
	}
	public int getHealth() {
		return health;
	}
	public boolean fatalDamage(int pow){
		if((health-=pow)<=0){
			return true;
		}
		return false;
	}
	public void incrementMoveIndex(){
		moveIndex++;
		if(moveIndex>=8){
			moveIndex = 0;
		}
		if(xVelocity>0&&yVelocity==0){
			d = Direction.EAST;
		}
		if(xVelocity<0&&yVelocity==0){
			d = Direction.WEST;
		}
		if(xVelocity==0&&yVelocity>0){
			d = Direction.SOUTH;
		}
		if(xVelocity==0&&yVelocity<0){
			d = Direction.NORTH;
		}
		
		if(xVelocity>0&&yVelocity>0){
			d = Direction.SOUTH_EAST;
		}
		if(xVelocity<0&&yVelocity>0){
			d = Direction.SOUTH_WEST;
		}
		if(xVelocity<0&&yVelocity<0){
			d = Direction.NORTH_WEST;
		}
		if(xVelocity>0&&yVelocity<0){
			d = Direction.NORTH_EAST;
		}
		
		
	}
	public void draw(Graphics g, int x, int y){
		switch(o){
		case BLUE_TANK : case RED_TANK :
		switch(d){
		case EAST:
			g.drawImage(Texture.blueWalkEast [moveIndex], x, y,width,height, null);
			break;
		case NORTH:
			g.drawImage(Texture.blueWalkNorth [moveIndex], x, y,width,height, null);

			break;
		case NORTH_EAST:
			g.drawImage(Texture.blueWalkNE [moveIndex], x, y,width,height, null);
			break;
		case NORTH_WEST:
			g.drawImage(Texture.blueWalkNW [moveIndex], x, y,width,height, null);
			break;
		case SOUTH:
			g.drawImage(Texture.blueWalkSouth [moveIndex], x, y,width,height, null);
			break;
		case SOUTH_EAST:
			g.drawImage(Texture.blueWalkSE [moveIndex], x, y,width,height, null);
			break;
		case SOUTH_WEST:
			g.drawImage(Texture.blueWalkSW [moveIndex], x, y,width,height, null);
			break;
		case WEST:
			g.drawImage(Texture.blueWalkWest [moveIndex], x, y,width,height, null);

			break;
		default:
			break;
		
		}
		
		
		default:
			break;
		
		
		}
		g.setColor(Color.green);
		g.fillRect(x, y+height, (int)(100*((double)health/initialHealth)), 7);
	}
	
	public void shoot(){
		bulletList.add(new Projectile(x,y,30,30,10,1,d,ObjectType.BLUE_TANK));
	}
	public ArrayList<Projectile> getBulletArray(){
		return bulletList;
	}
	
	public int getSpeed(){
		return speed;
	}
	public void setDirection(Direction d){
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
	public ObjectType getO() {
		return o;
	}
	public void setO(ObjectType o) {
		this.o = o;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
