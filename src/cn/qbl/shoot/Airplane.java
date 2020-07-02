package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class Airplane extends FlyingObject implements EnemyScore {

	private int index;

	public Airplane() {
		super(48, 50);
		speed = 2;
		blood = 1;
	}

	@Override
	public BufferedImage getImage() {
		if (isLife()) {
			return Images.airplanes[0];
		} else if (isDead()) {
			BufferedImage img = Images.airplanes[index++ % 4];
			if (index == Images.airplanes.length) {
				state = REMOVE;
			}
			return img;
		}
		return null;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 1;
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
