package cn.qbl.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class FlyingObject {

	private int width;
	private int heidht;
	private int x;
	private int y;
	private int speed;

	public static final int LIFE = 0;
	public static final int DEAD = 1;
	public static final int REMOVE = 2;

	private int state = LIFE;

	public FlyingObject() {
	}

	public FlyingObject(int width, int height) {
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeidht() {
		return heidht;
	}

	public void setHeidht(int heidht) {
		this.heidht = heidht;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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
		this.y += this.speed;
	}

	public boolean isHit(FlyingObject other) {
		// TODO 子弹和英雄机传过来的时候会发生向上转型，还能获取到other.width吗？
		int x1 = this.x - other.getWidth();
		int x2 = this.x + this.width;
		int y1 = this.y - other.getWidth();
		int y2 = this.y + this.heidht;
		int x = other.getX();
		int y = other.getY();
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}

	public void goDead() {
		this.state = DEAD;
	}

	public boolean isOutside() {
		return this.y >= World.HEIDHT;
	}
}
