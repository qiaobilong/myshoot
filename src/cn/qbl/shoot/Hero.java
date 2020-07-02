package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {

	private int fire;
	private int life;

	public Hero() {
		super(97, 139, 140, 400);
		fire = 0;
		life = 3;
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
		int x = width / 4;
		int y = 20;
		if (fire > 0) {
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.x + 1 * x, this.y - y);
			bs[1] = new Bullet(this.x + 3 * x, this.y - y);
			return bs;
		} else {
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x + 2 * x, this.y - y);
			return bs;
		}
	}

	public void moveTo(int x, int y) {
		this.x = x - width / 2;
		this.y = y - heidht / 2;
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
		return life;

	}

	public int getFire() {
		return fire;
	}

	@Override
	public void subtractBlood(int blood) {
		// TODO Auto-generated method stub

	}
}
