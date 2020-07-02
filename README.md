# 飞机大战

## 游戏需求

- 用户通过鼠标操作一架英雄机，英雄机自动发射子弹，攻击自上而下飞行的敌机，击落敌机获得奖励，不同敌机奖励不同，英雄机生命归零则死亡，游戏结束。
- 界面信息：
  - 得分信息、英雄机剩余生命信息、英雄机、敌机、子弹、天空图
  - 初始界面，用户点击界面，游戏开始，鼠标移至窗口外面，游戏暂停，英雄机死亡后，显示Game Over界面，再次点击鼠标，恢复初始界面
- 敌机及奖励类型：
  - 小敌机：击落得1分
  - 大敌机：击落得3分
  - 小蜜蜂：击落奖励（英雄机获得40火力值或者1条生命）
  - 敌机被击落后，显示爆炸效果后消失
- 英雄机：
  - 初始生命值：3
  - 初始火力值：1
  - 攻击模式：单发/双发，默认单发，击落小蜜蜂后，切换为双发攻击模式
- 其它：

## 开发文档

### 需求分析

| fields/method | World | Sky  | Hero | Bullet | BigAirplane | Airplane | Bee  |
| ------------- | ----- | ---- | ---- | ------ | ----------- | -------- | ---- |
| WIDTH         | √     |      |      |        |             |          |      |
| HEIGTH        | √     |      |      |        |             |          |      |
| START         | √     |      |      |        |             |          |      |
| RUNNNING      | √     |      |      |        |             |          |      |
| PAUSE         | √     |      |      |        |             |          |      |
| GAME_OVER     | √     |      |      |        |             |          |      |
| score         | √     |      |      |        |             |          |      |
| maxScore      | √     |      |      |        |             |          |      |
| LIFE          |       |      | √    | √      | √           | √        | √    |
| DEAD          |       |      |      |        | √           | √        | √    |
| REMOVE        |       |      | √    | √      | √           | √        | √    |
| width         |       | √    | √    | √      | √           | √        | √    |
| heigth        |       | √    | √    | √      | √           | √        | √    |
| x             |       | √    | √    | √      | √           | √        | √    |
| y             |       | √    | √    | √      | √           | √        | √    |
| speed         |       | √    |      | √      | √           | √        | √    |
|               |       |      |      |        | √           | √        | √    |
| y1            |       | √    |      |        |             |          |      |
| life          |       |      | √    |        |             |          |      |
| fire          |       |      | √    |        |             |          |      |
| xspeed        |       |      |      |        |             |          | √    |
| awardType     |       |      |      |        |             |          | √    |
| getImage()    |       | √    | √    | √      | √           | √        | √    |
| 生成对象      |       | √    |      | √      | √           | √        | √    |
| 移动          |       | √    |      | √      | √           | √        | √    |
| 得分          |       |      |      |        | √           | √        |      |
| 奖励          |       |      |      |        |             |          | √    |
| 碰撞          |       |      |      |        | √           | √        |      |

### 超纲代码

#### 主窗口

```java
	/**
	 * 飞机大战，世界窗口
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400;
	public static final int HEIDHT = 800;

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

	private void action() {
		// TODO Auto-generated method stub

	}
```

#### 画界面

  ```java
  	@Override
	public void paint(Graphics g) {
		g.drawImage(sky.getImages(), sky.x, sky.y, null);// 将天空图片画到界面上
		}
	}
  ```

#### 获取图片文件

```java
public static BufferedImage readImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName));
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
```

#### 游戏入口（监听器、定时器）

  ```java
	/**
	 * 游戏入口
	 */
	public void action() {
		// 监听器
		MouseAdapter msAdapter = new MouseAdapter() {
		};
		this.addMouseListener(msAdapter);
		this.addMouseMotionListener(msAdapter);

		// 定时器
		Timer timer = new Timer();
		int intervel = 10;// 定时周期，单位毫秒
		timer.schedule(new TimerTask() {
			@Override
			public void run() {// 要运行的程序
			}
		}, intervel, intervel);
	}
  ```

## 开发步骤

### 创建对象类

1. 创建World类，画窗口（复制超纲代码）
   1. import JFrame+JPanel  
   2. 设计World类继承JPanel  
   3. 复制粘贴main()方法中的所有代码  
   4. 修改frame.setSize()中的参数为常量WIDTH,HEIGHT
2. 创建了6个对象类，设计FlyingObject超类，6个对象类继承超类 
3. FlyingObject定义共用属性，6个对象定义特定属性

### 基础数据初始化

1. 给FlyingObject设计两个构造方法，6个派生类分别调用

| 对象        | 宽   | 高   | x    | y    | speed |
| ----------- | ---- | ---- | ---- | ---- | ----- |
| Sky         | 400  | 800  | 0    | 0    | 1     |
| Hero        | 97   | 139  | 140  | 400  |       |
| Bullet      | 8    | 20   | x    | y    | 3     |
| BigAirplane | 66   | 89   | 随机 | -高  | 2     |
| Bee         | 60   | 51   | 随机 | -高  | 2     |
| Airplane    | 48   | 50   | 随机 | -高  | 2     |

### 设计Images图片工具类

1. 静态块

### 画对象:  

1. 想画对象需要去获取对象的图片，每个对象都得获取图片，    意味着获取图片行为为共有行为，所以设计在超类FlyingObject中，    每个对象获取图片的行为都是不一样的，所以设计为抽象方法    ----在FlyingObject中设计抽象方法getImage()用于获取对象的图片  
2. 获取图片时需要考虑对象的状态，因为在不同状态下获取的图片是不同的，    每个对象都有状态，意味着状态为共有属性，所以设计在FlyingObject超类中，    状态一般都设计为常量，同时设计变量state来表示当前状态    ----在FlyingObject中设计LIFE、DEAD、REMOVE常量，state变量表示当前状态    有了状态常量还不够，还需要去判断对象的状态，每个对象都得判断，    意味着判断状态为共有行为，所以设计在超类FlyingObject中，    每个对象判断状态的行为都是一样的，所以设计为普通方法    ----在FlyingObject中设计isLife()、isDead()、isRemove()来判断对象的状态  
3. 重写getImage()获取对象图片: 
   1. 天空Sky: 返回sky图片即可    
   2. 子弹Bullet:        
      1. 若活着的，直接返回bullet图片即可        
      2. 若死了的，直接删除(不返回图片)    
   3. 英雄机Hero:
      1. 若活着的，返回heros[0]和heros[1]来回切换
   4. 小敌机Airplane:
      1. 若活着的，返回airs[0]即可
      2. 若死了的，依次返回airs[1]到airs[4]，4后删除
   5. 大敌机BigAirplane:
      1. 若活着的，返回bairs[0]即可
      2. 若死了的，依次返回bairs[1]到bairs[4]，4后删除
   6. 小蜜蜂Bee:
      1. 若活着的，返回bees[0]即可
      2. 若死了的，依次返回bees[1]到bees[4]，4后删除
4. 图片有了，就可以开画了，在World类中重写paint()画方法

### 英雄机随鼠标移动

1. 英雄机随着鼠标移动为英雄机的行为，所以在Hero中设计moveTo()来实现英雄机随着鼠标移动
2. 英雄机随着鼠标动为事件触发的，所以在侦听器中重写mouseMoved()鼠标移动事件    在mouseMoved()中:      获取鼠标的x和y坐标，调用Hero的moveTo()实现英雄机随着鼠标移动

### 敌机/子弹入场：enterAction()

1. 随机生成一个敌机，构建getEnemy()方法，用于生成敌机，进行延时生成处理，0.4秒生成一个
2. 对敌人数组进行扩容，并将生成的敌机插入数组中
3. <font color=red>构建getBullet()方法，用于生成子弹，根据英雄机的火力值，确认单双发，火力值＞0，说明击落过小蜜蜂，获得双发火力，延时处理，0.3秒生成一个</font>
4. <font color=red>对子弹数组进行扩容，并将子弹插入数组中</font>

### 敌机和子弹移动：stepAction()

1. 飞行物移动为派生类所共有的行为，所以在FlyingObject类中设计step()实现飞行物移动，默认自上而下移动，派生类不同的自行重写
2. 飞行物移动为定时发生的，所以在run()中调用stepAction()实现飞行物移动    在stepAction()中:天空动，遍历敌人敌人动，遍历子弹子弹动   

### 画分和画命:

1. 在Hero中设计getLife()获取英雄机的命数
2. 在World类的paint()中: 画分和画命

### 碰撞检测：checkHitAction()

1. 设计EnemyScore得分接口，Airplane和BigAirplane实现接口  设计EnemyAward奖励接口，Bee实现接口
2. 在FlyingObject中设计isHit()检测碰撞、goDead()飞行物去死，在Hero中设计addLife()增命、addFire()增火力，在Hero中设计subtractLife()英雄机减命、clearFire()清空火力值
3. 子弹与敌人的碰撞：
   1. 遍历子弹得子弹，遍历敌人得敌人，判断若都活着并且撞上了:敌人去死、子弹去死 判断若敌人能得分，则强转为得分接口，   则玩家得分 判断若敌人为奖励，则强转为奖励接口，   则获取奖励类型，最后根据不同的奖励类型来获取不同的奖励
4. 英雄机与敌人的碰撞
   1. 遍历敌人得敌人，判断若都活着并且撞上了:敌人去死、英雄机减命、英雄机清空火力值

### 删除越界和remove对象：deleteAction()

1. 在FlyingObject中设计isOutside()检测敌机是否越界    在Bullet中重写isOutside()检测子弹是否越界
2. 遍历所有敌人/子弹，判断若敌人/子弹越界了或者处于remove状态，则将最后一个元素赋值到越界位置，缩容

###  检测游戏结束：checkGameOverAction()

1. 判断若英雄机的命数<=0，则表示游戏结束，......

### 画游戏状态控制

1. 在World中设计START、RUNNING、PAUSE、GAME_OVER状态常量，usestatus变量表示当前状态    在Images中设计start、pause、gameover状态图片，静态块中初始化    在World的paint()中，设置在不同的状态下画不同的状态图
2. 设置那一堆action为仅在运行状态下执行    设置英雄机随着鼠标移动为仅在运行状态下执行
3. 重写mouseClicked()鼠标点击事件:      启动时变运行，游戏结束时先清理现场再变启动    重写mouseExited()鼠标移出事件:      运行变暂停    重写mouseEntered()鼠标移入事件:      暂停变运行

## 需求列表

| 需求ID | 需求分类 | 处理状态 | 需求说明                                                     |
| ------ | -------- | -------- | ------------------------------------------------------------ |
| 1      | 新功能   | 完成     | 记录并显示历史最大得分                                       |
| 2      | 优化     | 完成     | 降低子弹生成频率                                             |
| 3      | bug      | 完成     | 修复子弹碰撞不消失的bug                                      |
| 4      | 优化     | 完成     | 小蜜蜂生成概率调低（小蜜蜂:小敌机:大敌机为2:5:3）            |
| 5      | 新功能   | 完成     | 增加奖励类型生成概率控制功能（火力:生命为7:3）               |
| 6      | 新功能   | 完成     | 增加血量概念，小敌机一滴血，小蜜蜂2滴血，大敌机3滴血         |
| 7      | 新功能   |          | 增加一个空投奖励，英雄机获取后，消灭屏幕所有敌人             |
| 8      | 新功能   |          | 每获得500分，出现一个boss，血量10<br />击杀boss获得50分，boss出现同时清除其它敌机 |
| 9      | 新功能   |          | 将每次游戏得分记录到文件中，保留最近20次的历史记录           |

