package cn.qbl.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Bee extends FlyingObject implements EnemyAward {

	private int xspeed;
	private int awardType;
	private int index;

	public Bee() {
		super(60, 89);
		this.xspeed = 1;
		Random random = new Random();
		this.awardType = random.nextInt(2);
		setSpeed(2);
	}

	@Override
	public BufferedImage getImage() {
		if (isLife()) {
			return Images.bees[0];
		} else if (isDead()) {
			BufferedImage img = Images.bees[index++ % 4];
			if (index == Images.bees.length) {
				setState(REMOVE);
			}
			return img;
		}
		return null;
	}

	@Override
	public int getAward() {
		// TODO Auto-generated method stub
		return this.awardType;
	}

	@Override
	public void step() {
		super.step();
		this.setX(this.getX() + this.xspeed);
		if (this.getX() >= World.WIDTH - this.getWidth() || this.getX() <= 0) {
			this.xspeed *= -1;
		}
	}
}
