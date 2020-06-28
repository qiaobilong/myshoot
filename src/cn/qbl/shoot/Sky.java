package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class Sky extends FlyingObject {

	private int y1;

	public Sky() {
		super(World.WIDTH, World.HEIDHT, 0, 0);
		this.y1 = -World.HEIDHT;
		setSpeed(1);
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return Images.sky;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	@Override
	public void step() {
		setY(getY() + getSpeed());
		setY1(getY1() + getSpeed());
		if (getY() >= World.HEIDHT) {
			setY(-World.HEIDHT);
		}
		if (getY1() >= World.HEIDHT) {
			setY1(-World.HEIDHT);
		}
	}

}
