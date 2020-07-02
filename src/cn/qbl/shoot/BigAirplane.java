package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class BigAirplane extends FlyingObject implements EnemyScore {

	private int index;

	public BigAirplane() {
		super(66, 89);
		speed = 2;
		blood = 3;
	}

	@Override
	public BufferedImage getImage() {
		if (isLife()) {
			return Images.bigairplanes[0];
		} else if (isDead()) {
			BufferedImage img = Images.bigairplanes[index++ % 4];
			if (index == Images.bigairplanes.length) {
				state = REMOVE;
			}
			return img;
		}
		return null;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 3;
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
