package cn.qbl.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class FlyingObject {

	protected int width;
	protected int heidht;
	protected int x;
	protected int y;
	protected int speed;

	protected int blood;// 血量,add by 2020-6-29

	public static final int LIFE = 0;
	public static final int DEAD = 1;
	public static final int REMOVE = 2;

	protected int state = LIFE;

	public FlyingObject(int width, int height) {// 敌机专用
		this.width = width;
		this.heidht = height;
		Random random = new Random();
		this.x = random.nextInt(World.WIDTH - width);
		this.y = -height;
	}

	public FlyingObject(int width, int heidht, int x, int y) {
		this.width = width;
		this.heidht = heidht;
		this.x = x;
		this.y = y;
	}

	public abstract BufferedImage getImage();

	public boolean isLife() {
		return state == LIFE;
	}

	public boolean isDead() {
		return state == DEAD;
	}

	public boolean isRemove() {
		return state == REMOVE;
	}

	/**
	 * 默认自上而下移动
	 */
	public void step() {
		y += speed;
	}

	public boolean isHit(FlyingObject other) {
		// TODO 子弹和英雄机传过来的时候会发生向上转型，还能获取到other.width吗？
		int x1 = this.x - other.width;
		int x2 = this.x + this.width;
		int y1 = this.y - other.width;
		int y2 = this.y + this.heidht;
		int x = other.x;
		int y = other.y;
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}

	public void goDead() {
		state = DEAD;
	}

	public boolean isOutside() {
		return y >= World.HEIDHT;
	}

	/**
	 * 扣减blood的血量，敌机如果血量≤0则死亡
	 * @param blood
	 */
	public abstract void subtractBlood(int blood);
}
