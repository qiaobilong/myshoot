package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class Sky extends FlyingObject {

	private int y1;

	public Sky() {
		super(World.WIDTH, World.HEIDHT, 0, 0);
		this.y1 = -World.HEIDHT;
		speed = 1;
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return Images.sky;
	}

	public int getY1() {
		return y1;
	}

	@Override
	public void step() {
		y += speed;
		y1 += speed;
		if (y >= World.HEIDHT) {
			y = -World.HEIDHT;
		}
		if (y1 >= World.HEIDHT) {
			y1 = -World.HEIDHT;
		}
	}

	@Override
	public void subtractBlood(int blood) {
		// TODO Auto-generated method stub

	}

}
