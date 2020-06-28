package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {

	private int fire;
	private int life;

	public Hero() {
		super(97, 139, 140, 400);
		this.fire = 0;
		this.life = 3;
	}

	private int index;

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return Images.heros[index++ % 2];
	}

	@Override
	public void step() {
	}

	public Bullet[] getBullet() {
		int x = this.getWidth() / 4;
		int y = 20;
		if (fire > 0) {
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.getX() + 1 * x, this.getY() - y);
			bs[1] = new Bullet(this.getX() + 3 * x, this.getY() - y);
			return bs;
		} else {
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.getX() + 2 * x, this.getY() - y);
			return bs;
		}
	}

	public void moveTo(int x, int y) {
		this.setX(x - this.getWidth() / 2);
		this.setY(y - this.getHeidht() / 2);
	}

	public void addLife() {
		life++;
	}

	public void addFire() {
		fire += 40;
	}

	public void subtractLife() {
		life--;
	}

	public void clearFire() {
		fire = 0;
	}

	public int getLife() {
		return this.life;

	}
}
