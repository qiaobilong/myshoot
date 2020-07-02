package cn.qbl.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Bee extends FlyingObject implements EnemyAward {

	private int xspeed;
	private int awardType;
	private int index;

	public Bee() {
		super(60, 89);
		xspeed = 1;
		Random random = new Random();
		int type = random.nextInt(100);
		if (type < 30) {
			awardType = EnemyAward.LIFE;
		} else {
			awardType = EnemyAward.FIRE;
		}
		speed = 2;
		blood = 2;
	}

	@Override
	public BufferedImage getImage() {
		if (isLife()) {
			return Images.bees[0];
		} else if (isDead()) {
			BufferedImage img = Images.bees[index++ % 4];
			if (index == Images.bees.length) {
				state = REMOVE;
			}
			return img;
		}
		return null;
	}

	@Override
	public int getAward() {
		// TODO Auto-generated method stub
		return awardType;
	}

	@Override
	public void step() {
		super.step();
		x += xspeed;
		if (x >= World.WIDTH - width || x <= 0) {
			xspeed *= -1;
		}
	}

	@Override
	public void subtractBlood(int blood) {
		this.blood -= blood;
		if (this.blood <= 0) {
			state = DEAD;
			goDead();
		}
	}
}
