import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class EndGamePanel extends JPanel {
	public BufferedImage backDrop;
	boolean win;
	public JFrame frame;
	public final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public EndGamePanel(boolean win){
		this.win = win;
		makePanel();
		putActions();
		repaint();
	}
	public void putActions(){
		
		URL imageUrl = getClass().getResource("/imgs/LaunchBack.png");
		try {
			backDrop = ImageIO.read(imageUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		JButton start = new JButton("Restart Game");
		start.setBounds((int)(WIDTH*.7), (int)(HEIGHT*.6), 200, 100);
		start.addActionListener(e->{
			frame.dispose();
			new GamePanel();
		});
		add(start);
		
		
		
	}
	public void makePanel(){
		frame = new JFrame("MiniTank Restarter");
		frame.add(this);
		this.setLayout(null);
		frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBackGround(g);
	}
	public void drawBackGround(Graphics g){
		g.drawImage(backDrop, 0, 0,WIDTH,HEIGHT, null);
		g.setFont(new Font("Arial",Font.BOLD,40));
		String prompet = (win)?"You WON!":"You LOST!";
		g.drawString(prompet, WIDTH/2, HEIGHT/2);
	}
}
