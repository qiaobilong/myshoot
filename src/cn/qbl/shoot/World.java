package cn.qbl.shoot;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel {
	/**
	 * 飞机大战，世界窗口
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400;
	public static final int HEIDHT = 800;

	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	private int usestatus = START;

	private static int maxScore;

	private Sky sky = new Sky();
	private Hero hero = new Hero();
	private Bullet[] bullets = {};
	private FlyingObject[] enemies = {};

	private int score;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		World world = new World();
		frame.add(world);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIDHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		world.action();// 运行游戏入口
	}

	/**
	 * 游戏入口
	 */
	public void action() {
		// 监听器
		MouseAdapter msAdapter = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (usestatus == RUNNING) {
					hero.moveTo(e.getX(), e.getY());
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (usestatus == PAUSE) {
					usestatus = RUNNING;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (usestatus == RUNNING) {
					usestatus = PAUSE;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (usestatus == START) {
					usestatus = RUNNING;
				}
				if (usestatus == GAME_OVER) {
					if (score > maxScore) {
						maxScore = score;
					}
					sky = new Sky();
					hero = new Hero();
					bullets = new Bullet[0];
					enemies = new FlyingObject[0];
					score = 0;
					usestatus = START;
				}
			}
		};
		this.addMouseListener(msAdapter);
		this.addMouseMotionListener(msAdapter);

		// 定时器
		Timer timer = new Timer();
		int intervel = 10;// 定时周期，单位毫秒
		timer.schedule(new TimerTask() {
			@Override
			public void run() {// 要运行的程序
				if (usestatus == RUNNING) {
					enterAction();
					stepAction();
					checkHitAction();
					deleteAction();
					checkGameOver();
				}
				repaint();
			}
		}, intervel, intervel);
	}

	protected void checkGameOver() {
		if (hero.getLife() <= 0) {
			usestatus = GAME_OVER;
		}
	}

	protected void deleteAction() {
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i].isRemove() || bullets[i].isOutside()) {
				bullets[i] = bullets[bullets.length - 1];
				bullets = Arrays.copyOf(bullets, bullets.length - 1);
			}
		}
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i].isRemove() || enemies[i].isOutside()) {
				enemies[i] = enemies[enemies.length - 1];
				enemies = Arrays.copyOf(enemies, enemies.length - 1);
			}
		}
	}

	protected void checkHitAction() {
		// 子弹与敌机碰撞
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			for (int j = 0; j < enemies.length; j++) {
				FlyingObject e = enemies[j];
				if (b.isLife() && e.isLife() && e.isHit(b)) {
					b.goDead();
					e.subtractBlood(1);
					if (!e.isLife()) {
						if (e instanceof EnemyScore) {
							score += ((EnemyScore) e).getScore();
						}
						if (e instanceof EnemyAward) {
							int type = ((EnemyAward) e).getAward();
							if (type == EnemyAward.LIFE) {
								hero.addLife();
							}
							if (type == EnemyAward.FIRE) {
								hero.addFire();
							}
						}
					}

				}
			}
		}
		// 英雄机与敌机碰撞
		for (int i = 0; i < enemies.length; i++) {
			if (hero.isLife() && enemies[i].isLife() && enemies[i].isHit(hero)) {
				enemies[i].goDead();
				hero.subtractLife();
				hero.clearFire();
			}
		}
	}

	protected void stepAction() {
		sky.step();
		for (int i = 0; i < enemies.length; i++) {
			enemies[i].step();
		}
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].step();
		}
	}

	int enterIndex = 0;

	/**
	 * 敌机和子弹入场
	 */
	protected void enterAction() {
		enterIndex++;
		// 敌机入场
		if (enterIndex % 40 == 0) {
			FlyingObject enemy = getEnemy();
			enemies = Arrays.copyOf(enemies, enemies.length + 1);
			enemies[enemies.length - 1] = enemy;
		}
		if (enterIndex % 30 == 0) {
			Bullet[] bs = hero.getBullet();
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);
		}
	}

	private FlyingObject getEnemy() {
		int num = new Random().nextInt(10);
		if (num < 2) {
			return new Bee();
		} else if (num < 7) {
			return new Airplane();
		} else {
			return new BigAirplane();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(sky.getImage(), sky.x, sky.y, null);
		g.drawImage(sky.getImage(), sky.x, sky.getY1(), null);
		g.drawImage(hero.getImage(), hero.x, hero.y, null);
		for (int i = 0; i < bullets.length; i++) {
			g.drawImage(bullets[i].getImage(), bullets[i].x, bullets[i].y, null);
		}
		for (int i = 0; i < enemies.length; i++) {
			g.drawImage(enemies[i].getImage(), enemies[i].x, enemies[i].y, null);
		}
		g.drawString("MaxScore: " + maxScore, 10, 20);
		g.drawString("Score: " + score, 10, 40);
		g.drawString("Life: " + hero.getLife(), 10, 60);
		if (usestatus == START) {
			g.drawImage(Images.start, 0, 0, null);
		}
		if (usestatus == PAUSE) {
			g.drawImage(Images.pause, 0, 0, null);
		}
		if (usestatus == GAME_OVER) {
			g.drawImage(Images.gameOver, 0, 0, null);
		}
	}
}
