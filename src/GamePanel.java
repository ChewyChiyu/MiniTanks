import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	public BufferedImage gameBack1;
	public Thread t;
	public boolean isRunning;
	public final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	Tank player = new Tank(WIDTH/2, HEIGHT/2, 100, 100, ObjectType.BLUE_TANK);
	JFrame frame;
	public GamePanel(){
		makePanel();
		setUpKeyBinds();
		new Texture();
		start();
	}
	public void setUpKeyBinds(){
		this.getInputMap().put(KeyStroke.getKeyStroke("A"), "A");
		this.getInputMap().put(KeyStroke.getKeyStroke("D"), "D");
		this.getInputMap().put(KeyStroke.getKeyStroke("S"), "S");
		this.getInputMap().put(KeyStroke.getKeyStroke("W"), "W");

		this.getInputMap().put(KeyStroke.getKeyStroke("released A"), "rA");
		this.getInputMap().put(KeyStroke.getKeyStroke("released D"), "rD");
		this.getInputMap().put(KeyStroke.getKeyStroke("released S"), "rS");
		this.getInputMap().put(KeyStroke.getKeyStroke("released W"), "rW");

	
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
		repaint();
	}
	public void updateTankLocation(){
		player.setX(player.getxVelocity());
		player.setY(player.getyVelocity());
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBackDrop(g);
		drawTanks(g);
	}
	public void drawTanks(Graphics g){
		player.draw(g, player.getX(), player.getY());
	}
	public void drawBackDrop(Graphics g){
		g.drawImage(gameBack1, 0, 0,WIDTH,HEIGHT, null);
	}
}
