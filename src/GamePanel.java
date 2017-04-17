import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	public BufferedImage gameBack1;
	public Thread t;
	public Thread gameDetection;
	public Timer removeBullet;
	public ArrayList<Tank> tanks = new ArrayList<Tank>();
	public boolean isRunning;
	public final static int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static Tank player = new Tank(WIDTH/2, HEIGHT/2, 100, 100, 100, ObjectType.BLUE_TANK);
	JFrame frame;
	public GamePanel(){
		tanks.add(player);
		makePanel();
		setUpKeyBinds();
		setUpEnemys();
		new Texture();
		start();
	}
	public void setUpEnemys(){
		tanks.add(new EnemyTank((int)(Math.random()*WIDTH),(int)(Math.random()*HEIGHT),100,100,10, ObjectType.RED_TANK));
	}
	public void setUpGameDetection(){
		gameDetection = new Thread(new Runnable(){
			public void run(){
				while(isRunning){
					
					/*
					 * 
					 * Game Detection Here
					 * 
					 */
					
					for(int index = 0; index < tanks.size(); index++){
						Tank t = tanks.get(index);
						for(int index2 = 0; index2 < t.getBulletArray().size(); index2++){
							Projectile p = t.getBulletArray().get(index2);
							if(p.getX()<0||p.getX()>WIDTH||p.getY()<0||p.getY()>HEIGHT){
								t.getBulletArray().remove(p);
							}
						}
					}
					/*
					 * Bullet Hits
					 * 
					 */
					for(int index = 0; index < tanks.size(); index++){
						Tank t = tanks.get(index);
						for(int index2 = 0; index2 < t.getBulletArray().size(); index2++){
							Projectile p = t.getBulletArray().get(index2);
							for(int index3 = 0; index3 < tanks.size(); index3++){
								Tank t2 = tanks.get(index3);
								if(t2.getO().equals(p.getO())){
									continue;
								}
								if(!p.hitSomething&&p.getX()>t2.getX()&&p.getX()<t2.getX()+t2.getWidth()&&p.getY()>t2.getY()&&p.getY()<t2.getY()+t2.getHeight()){
									p.setxVelocity(0);
									p.setyVelocity(0);
									p.hitSomething();
									if(t2.fatalDamage(p.getPower())){
										((EnemyTank)t2).mind.stop();
										tanks.remove(t2);
									}
								}
							}
						}
					}
					
					/*
					 * 
					 * Check For Out Of Bounds
					 * 
					 */
					for(int index = 0; index < tanks.size(); index++){
						Tank t = tanks.get(index);
						if(t.getX()<0){
							t.setX(t.getSpeed());
						}
						if(t.getX()>WIDTH){
							t.setX(-t.getSpeed());
						}
						if(t.getY()<0){
							t.setY(t.getSpeed());
						}
						if(t.getY()>HEIGHT){
							t.setY(-t.getSpeed());
						}
					}
					try{
						Thread.sleep(1);
					}catch(Exception e){
						
					}
				}
				
				/*
				 * Bullet removal
				 * 
				 */
				
				
			}
		});
		removeBullet = new Timer(400, e->{
			for(int index = 0; index < tanks.size(); index++){
				Tank t = tanks.get(index);
				for(int index2 = 0; index2 < t.getBulletArray().size(); index2++){
					Projectile p = t.getBulletArray().get(index2);
					if(p.hitSomething){
						t.getBulletArray().remove(p);
					}
				}
			}	
		});
		gameDetection.start();
		removeBullet.start();
	}
	
	public void setUpKeyBinds(){
		this.getInputMap().put(KeyStroke.getKeyStroke("A"), "A");
		this.getInputMap().put(KeyStroke.getKeyStroke("D"), "D");
		this.getInputMap().put(KeyStroke.getKeyStroke("S"), "S");
		this.getInputMap().put(KeyStroke.getKeyStroke("W"), "W");
		this.getInputMap().put(KeyStroke.getKeyStroke("P"), "Pause");

		this.getInputMap().put(KeyStroke.getKeyStroke("released A"), "rA");
		this.getInputMap().put(KeyStroke.getKeyStroke("released D"), "rD");
		this.getInputMap().put(KeyStroke.getKeyStroke("released S"), "rS");
		this.getInputMap().put(KeyStroke.getKeyStroke("released W"), "rW");

		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "shoot");

		this.getActionMap().put("Pause", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isRunning){
					stop();
				}else
					start();
			}
		});
		this.getActionMap().put("shoot", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				player.shoot();
			}
		});
		
		this.getActionMap().put("D", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setxVelocity(player.getSpeed());
			}
		});
		this.getActionMap().put("A", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setxVelocity(-player.getSpeed());
			}
		});
		this.getActionMap().put("W", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setyVelocity(-player.getSpeed());
			}
		});
		this.getActionMap().put("S", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setyVelocity(player.getSpeed());
			}
		});

		this.getActionMap().put("rD", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setxVelocity(0);
			}
		});
		this.getActionMap().put("rA", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setxVelocity(0);
			}
		});
		this.getActionMap().put("rW", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setyVelocity(0);
			}
		});
		this.getActionMap().put("rS", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setyVelocity(0);
			}
		});
		

	}
	public void makePanel(){
		
		URL imageUrl = getClass().getResource("imgs/GameBack1.png");
		try {
			gameBack1 = ImageIO.read(imageUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		frame = new JFrame("MiniTank!");
		frame.add(this);
		this.setLayout(null);
		frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public synchronized void start(){
		t = new Thread(this);
		isRunning = true;
		t.start();
		setUpGameDetection();
	}
	public synchronized void stop(){
		try{
			isRunning = false;
			t.join();
		}catch(Exception e){
			
		}
	}
	public void run() {
		while(isRunning){
			updateScreen();
			try{
				Thread.sleep(10);
			}catch(Exception e){
				
			}
		}
	}
	
	public void updateScreen(){
		updateTankLocation();
		updateBulletLocation();
		repaint();
	}
	
	public void updateBulletLocation(){
		for(int index = 0; index < tanks.size(); index++){
			Tank t = tanks.get(index);
			for(int index2 = 0; index2 < t.getBulletArray().size(); index2++){
				Projectile p = t.getBulletArray().get(index2);
				p.setX(p.getxVelocity());
				p.setY(p.getyVelocity());
			}
		}
	}
	
	public void updateTankLocation(){
		for(int index = 0; index < tanks.size(); index++){
			Tank t = tanks.get(index);
			t.setX(t.getxVelocity());
			t.setY(t.getyVelocity());
		}
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBackDrop(g);
		drawTanks(g);
		drawBullets(g);
	}
	public void drawBullets(Graphics g){
		for(int index = 0; index < tanks.size(); index++){
			Tank t = tanks.get(index);
			for(int index2 = 0; index2 < t.getBulletArray().size(); index2++){
				Projectile p = t.getBulletArray().get(index2);
				p.draw(g,p.getX(), p.getY());
			}
		}
	}
	public void drawTanks(Graphics g){
		for(int index = 0; index < tanks.size(); index++){
			Tank t = tanks.get(index);
			t.draw(g, t.getX(), t.getY());
		}
	
	}
	public void drawBackDrop(Graphics g){
		g.drawImage(gameBack1, 0, 0,WIDTH,HEIGHT, null);
	}
}
