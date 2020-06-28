package cn.qbl.shoot;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Images {

	public static BufferedImage sky;
	public static BufferedImage[] heros;
	public static BufferedImage bullet;
	public static BufferedImage[] bigairplanes;
	public static BufferedImage[] bees;
	public static BufferedImage[] airplanes;

	public static BufferedImage start;
	public static BufferedImage pause;
	public static BufferedImage gameOver;

	static {
		start = readImage("start.png");
		pause = readImage("pause.png");
		gameOver = readImage("gameover.png");

		sky = readImage("background.png");
		bullet = readImage("bullet.png");

		heros = new BufferedImage[2];
		for (int i = 0; i < heros.length; i++) {
			heros[i] = readImage("hero" + i + ".png");
		}

		bullet = readImage("bullet.png");

		bigairplanes = new BufferedImage[5];
		bigairplanes[0] = readImage("bigairplane.png");
		bees = new BufferedImage[5];
		bees[0] = readImage("bee.png");
		airplanes = new BufferedImage[5];
		airplanes[0] = readImage("airplane.png");
		for (int i = 1; i < 5; i++) {
			bigairplanes[i] = readImage("bom" + i + ".png");
			bees[i] = readImage("bom" + i + ".png");
			airplanes[i] = readImage("bom" + i + ".png");
		}
	}

	public static BufferedImage readImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName));
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
