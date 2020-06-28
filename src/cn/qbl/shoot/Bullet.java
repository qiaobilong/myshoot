package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject {

	public Bullet(int x, int y) {
		super(8, 20, x, y);
		setSpeed(3);
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
		setY(getY() - getSpeed());
	}

	@Override
	public boolean isOutside() {
		return this.getY() <= -this.getHeidht();
	}
}
