import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Texture {
	public BufferedImage spriteSheetBlue;
	public BufferedImage spriteSheetRed;
	public BufferedImage spriteSheetProjectile;
	public static BufferedImage[] blueWalkEast = new BufferedImage[8];
	public static BufferedImage[] blueWalkWest = new BufferedImage[8];
	public static BufferedImage[] blueWalkSouth = new BufferedImage[8];
	public static BufferedImage[] blueWalkNorth = new BufferedImage[8];
	public static BufferedImage[] blueWalkSE = new BufferedImage[8];
	public static BufferedImage[] blueWalkSW = new BufferedImage[8];
	public static BufferedImage[] blueWalkNW = new BufferedImage[8];
	public static BufferedImage[] blueWalkNE = new BufferedImage[8];
	
	public static BufferedImage[] redWalkEast = new BufferedImage[8];
	public static BufferedImage[] redWalkWest = new BufferedImage[8];
	public static BufferedImage[] redWalkSouth = new BufferedImage[8];
	public static BufferedImage[] redWalkNorth = new BufferedImage[8];
	public static BufferedImage[] redWalkSE = new BufferedImage[8];
	public static BufferedImage[] redWalkSW = new BufferedImage[8];
	public static BufferedImage[] redWalkNW = new BufferedImage[8];
	public static BufferedImage[] redWalkNE = new BufferedImage[8];
	
	public static BufferedImage[] redBullets = new BufferedImage[9];
	
	public static BufferedImage spawner;
	public static BufferedImage redBulletHit;
	
	public Texture(){
		makeTextures();
	}
	public void makeTextures(){
		URL imageUrl = getClass().getResource("/imgs/BlueTank.png");
		try {
			spriteSheetBlue = ImageIO.read(imageUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL imageUrl2 = getClass().getResource("/imgs/Projectile.png");
		try {
			spriteSheetProjectile = ImageIO.read(imageUrl2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL imageUrl3 = getClass().getResource("/imgs/RedTank.png");
		try {
			spriteSheetRed = ImageIO.read(imageUrl3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL imageUrl4 = getClass().getResource("/imgs/Spawner.png");
		try {
			spawner = ImageIO.read(imageUrl4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//84x84
		
		int xBuffer = 330;
		int yBuffer = 360;
		for(int index = 0 ; index < blueWalkEast.length; index++){
			blueWalkEast[index] = spriteSheetBlue.getSubimage(xBuffer, yBuffer, 84, 82);
			xBuffer+=84;
			if(xBuffer>=755-82){
				xBuffer = 0;
				yBuffer+=84;
			}
		}
		 xBuffer = 330;
		 yBuffer = 360;
		for(int index = 0 ; index < redWalkEast.length; index++){
			redWalkEast[index] = spriteSheetRed.getSubimage(xBuffer, yBuffer, 84, 82);
			xBuffer+=84;
			if(xBuffer>=755-82){
				xBuffer = 0;
				yBuffer+=84;
			}
		}
	xBuffer = 250;
	yBuffer = 446;
	for(int index = 0 ; index < blueWalkWest.length; index++){
		blueWalkWest[index] = spriteSheetBlue.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 250;
	yBuffer = 446;
	for(int index = 0 ; index < redWalkWest.length; index++){
		redWalkWest[index] = spriteSheetRed.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 0;
	yBuffer = 710;
	for(int index = 0 ; index < blueWalkSouth.length; index++){
		blueWalkSouth [index] = spriteSheetBlue.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
	}
	xBuffer = 0;
	yBuffer = 710;
	for(int index = 0 ; index < redWalkSouth.length; index++){
		redWalkSouth [index] = spriteSheetRed.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
	}
	xBuffer = 588;
	yBuffer = 90;
	for(int index = 0 ; index < blueWalkNorth.length; index++){
		blueWalkNorth [index] = spriteSheetBlue.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 588;
	yBuffer = 90;
	for(int index = 0 ; index < redWalkNorth.length; index++){
		redWalkNorth [index] = spriteSheetRed.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 164;
	yBuffer = 535;
	for(int index = 0 ; index < blueWalkSE.length; index++){
		blueWalkSE [index] = spriteSheetBlue.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 164;
	yBuffer = 535;
	for(int index = 0 ; index < redWalkSE.length; index++){
		redWalkSE [index] = spriteSheetRed.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 84;
	yBuffer = 625;
	for(int index = 0 ; index < blueWalkSW.length; index++){
		blueWalkSW [index] = spriteSheetBlue.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 84;
	yBuffer = 625;
	for(int index = 0 ; index < redWalkSW.length; index++){
		redWalkSW [index] = spriteSheetRed.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 420;
	yBuffer = 265;
	for(int index = 0 ; index < blueWalkNW.length; index++){
		blueWalkNW [index] = spriteSheetBlue.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 420;
	yBuffer = 265;
	for(int index = 0 ; index < redWalkNW.length; index++){
		redWalkNW [index] = spriteSheetRed.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 504;
	yBuffer = 180;
	for(int index = 0 ; index < blueWalkNE.length; index++){
		blueWalkNE [index] = spriteSheetBlue.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	xBuffer = 504;
	yBuffer = 180;
	for(int index = 0 ; index < redWalkNE.length; index++){
		redWalkNE [index] = spriteSheetRed.getSubimage(xBuffer, yBuffer, 84, 82);
		xBuffer+=84 ;
		if(xBuffer>=755-82){
			xBuffer = 0;
			yBuffer+=84;
		}
	}
	//Missile Red
	xBuffer = 8;
	yBuffer = 11;
	for(int index = 0 ; index < redBullets.length; index++){
		redBullets [index] = spriteSheetProjectile.getSubimage(xBuffer, yBuffer, 18, 17);
		xBuffer+=18 ;
		if(xBuffer>=62){
			xBuffer = 8;
			yBuffer+=17;
		}
	}
	
	
	redBulletHit = spriteSheetProjectile.getSubimage(23, 82, 25, 25);
	
	
	}
}
