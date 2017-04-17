import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;

public class Spawner {
	Timer factory;
	int x;
	int y;
	final int width = 500;
	final int height = 300;
	int health = 100000;
	int initialHealth = health;
	public Spawner(int x, int y){
		this.x = x;
		this.y = y;
		factory = new Timer(5000, e->{
		GamePanel.tanks.add(new EnemyTank(x,y,100,100,10, ObjectType.RED_TANK));

		});
		factory.start();
	}
	public void draw(Graphics g, int x, int y){
		g.drawImage(Texture.spawner, x, y, width,height,null);
		g.setColor(Color.green);
		g.fillRect(x+(int)(width*.2), y+(int)(height*.2), (int)(500*((double)health/initialHealth)), 7);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	public boolean fatalDamage(int pow){
		if((health-=pow)<0){
			return true;
		}
		return false;
	}
	
}
