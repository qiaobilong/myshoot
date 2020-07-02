package cn.qbl.shoot;

import java.awt.image.BufferedImage;

/**
 * 空投奖励，英雄机获得后，界面所有敌人自爆，并获得相应奖励
 */
public class Parachute extends FlyingObject {

	public Parachute() {
		super(48, 48);
		speed = 2;
		blood = 1;
	}

	@Override
	public BufferedImage getImage() {
		if (isLife()) {
			return Images.parachute;
		}
		return null;
	}

	@Override
	public void subtractBlood(int blood) {
		// TODO Auto-generated method stub

	}
	
}
