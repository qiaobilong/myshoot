package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class BigAirplane extends FlyingObject implements EnemyScore{

	private int index;

	public BigAirplane() {
		super(66, 89);
		setSpeed(2);
	}

	@Override
	public BufferedImage getImage() {
		if (isLife()) {
			return Images.bigairplanes[0];
		} else if (isDead()) {
			BufferedImage img = Images.bigairplanes[index++ % 4];
			if (index == Images.bigairplanes.length) {
				setState(REMOVE);
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
}
