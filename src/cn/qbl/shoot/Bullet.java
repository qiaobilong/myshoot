package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject {

	public Bullet(int x, int y) {
		super(8, 20, x, y);
		speed = 3;
	}

	@Override
	public BufferedImage getImage() {
		if (isLife()) {
			return Images.bullet;
		}
		return null;
	}

	@Override
	public void step() {
		y -= speed;
	}

	@Override
	public boolean isOutside() {
		return y <= -heidht;
	}

	@Override
	public void subtractBlood(int blood) {
		// TODO Auto-generated method stub
		
	}
}
