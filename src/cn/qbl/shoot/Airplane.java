package cn.qbl.shoot;

import java.awt.image.BufferedImage;

public class Airplane extends FlyingObject implements EnemyScore {

	private int index;

	public Airplane() {
		super(48, 50);
		setSpeed(2);
	}

	@Override
	public BufferedImage getImage() {
		if (isLife()) {
			return Images.airplanes[0];
		} else if (isDead()) {
			BufferedImage img = Images.airplanes[index++ % 4];
			if (index == Images.airplanes.length) {
				setState(REMOVE);
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

}
