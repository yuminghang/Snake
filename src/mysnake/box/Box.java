package mysnake.box;

import mysnake.MainGui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

import mysnake.settingground.Clock;

/**
 * @author 沉默的小老头 Barry
 * QQ:191988637
 * Created on 2010-10-9, 11:57:01
 */

/**
 * 蛇场类~~继承自JPanel
 * 额额，可能这名字不好听，没办法，就这个~~~哈哈
 * Box，蛇的活动场所，也是食物出现的地方
 * 重写了该类的paint()方法，当重绘时时会调用蛇、食物、网格的绘制方法
 * <p>
 * 由定时器Timer控制，每隔delay时间，执行：
 * 1.重新计算一下位置
 * 2.绘制新图像
 * 3.更新属性面板的显示
 * 即该游戏的主要原理
 * <p>
 * 属性面板的时间由countTimer中获取，
 * 运行在另外一个线程中，减小计时的误差
 */
public class Box extends JPanel {

    /**
     * MainGui的引用字段
     */
    MainGui gui;
    /**
     * 蛇的引用字段
     */
    private Snake snake;
    /**
     * 食物的引用字段
     */
    private Food food;
    /**
     * 定时器
     */
    private Timer timer;
    /**
     * 定时器延迟的毫秒数
     */
    private int delay = 100;
    /**
     * 背景色
     */
    private Color backgroundColor = Color.DARK_GRAY;
    /**
     * 网格颜色
     */
    private Color webColor = Color.DARK_GRAY;
    /**
     * 网格样式
     */
    private int webStyle = 1;
    /**
     * 时间记录的定时器
     */
    private Clock clock;

    /**
     * 构造方法
     */
    public Box(MainGui inGui) {
        //传递gui
        gui = inGui;
        //创建蛇
        snake = new Snake(this);
        //创建食物
        food = new Food(this);
        //设置背景色
        setBackground(backgroundColor);
        //创建主定时器，用于整个程序的主要定时变化工作

        timer = new Timer(delay, new ActionListener() {
            //匿名接口，实现间隔delay毫秒执行一次事件
            public void actionPerformed(ActionEvent e) {
                //计算新位置
                getSnake().countNewSite();
                //重新绘制界面
                repaint();
                //更新状态显示面板
                updateProperty();
            }
        });

        /**创建钟表的实例并启动线程（没有开始计时）*/
        clock = new Clock();
        clock.getTimeThread().start();
    }

    /**
     * 重写paint()方法
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        //调用父类的paint()方法，先~~
        setBackground(getBackgroundColor());
        super.paint(g);
        //画格子
        paintBackgroundRect(g, getWebStyle());
        //画蛇
        getSnake().drawMe(g);
        //画食物
        getFood().drawMe(g);
    }

    /**
     * 绘制背景网格
     *
     * @param g     传入的画笔
     * @param style 网格样式
     */
    private void paintBackgroundRect(Graphics g, int style) {
        //设置颜色
        g.setColor(getWebColor());
        /**根据参数style绘制格子*/
        switch (style) {
            case 0:
                //不画网格
                return;
            case 1:
                //用画满矩形格子的方法添加网格,凸形3D矩形
                for (int i = 0; i < getWidth(); i += 20) {
                    for (int j = 0; j < getHeight(); j += 20) {
                        g.fill3DRect(i, j, 20, 20, true);
                    }
                }
                break;
            case 2:
                //用画满矩形格子的方法添加网格，凹形3D矩形
                for (int i = 0; i < getWidth(); i += 20) {
                    for (int j = 0; j < getHeight(); j += 20) {
                        g.fill3DRect(i, j, 20, 20, false);
                    }
                }
                break;
            case 3:
                //用画线的方法画格子
                for (int i = 0; i < getWidth(); i += 20) {
                    g.drawLine(i, 0, i, getHeight());
                }
                for (int i = 0; i < getHeight(); i += 20) {
                    g.drawLine(0, i, getWidth(), i);
                }
                break;
        }
    }

    /**
     * 更新属性面板
     */
    private void updateProperty() {
        //更新蛇长
        gui.getSettingPanel().getSnakeLengthLabel().setText(String.valueOf(getSnake().getBody().size()));
        //更新方向
        switch (gui.getBox().getSnake().getDirection()) {
            case Snake.DIRECTION_DOWN:
                gui.getSettingPanel().getDirectionLabel().setText("下，↓");
                break;
            case Snake.DIRECTION_LEFT:
                gui.getSettingPanel().getDirectionLabel().setText("左，←");
                break;
            case Snake.DIRECTION_RIGHT:
                gui.getSettingPanel().getDirectionLabel().setText("右，→");
                break;
            case Snake.DIRECTION_UP:
                gui.getSettingPanel().getDirectionLabel().setText("上，↑");
                break;
        }
        //更新蛇头坐标
        gui.getSettingPanel().getSnakeSiteLabel().setText("("
                + (getSnake().getBody().getLast().x + 20) / 20
                + ","
                + (getSnake().getBody().getLast().y + 20) / 20
                + ")");
        //更新食物坐标
        gui.getSettingPanel().getFoodSiteLabel().setText("("
                + (getFood().x + 20) / 20
                + ","
                + (getFood().y + 20) / 20
                + ")");
        //更新时间显示
        gui.getSettingPanel().getTimeLabel().setText(getClock().getTime() + "秒");
        //更新速度显示
        gui.getSettingPanel().getSpeedLabel().setText("格 /" + getTimer().getDelay() + "毫秒");
    }

    /**
     * @return the snake
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * @param snake the snake to set
     */
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    /**
     * @return the counTtimer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @param counTtimer the counTtimer to set
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * @return the food
     */
    public Food getFood() {
        return food;
    }

    /**
     * @param food the food to set
     */
    public void setFood(Food food) {
        this.food = food;
    }

    /**
     * @return the clock
     */
    public Clock getClock() {
        return clock;
    }

    /**
     * @param clock the clock to set
     */
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    /**
     * @return the backgroundColor
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @return the webStyle
     */
    public int getWebStyle() {
        return webStyle;
    }

    /**
     * @param webStyle the webStyle to set
     */
    public void setWebStyle(int webStyle) {
        this.webStyle = webStyle;
    }

    /**
     * @return the webColor
     */
    public Color getWebColor() {
        return webColor;
    }

    /**
     * @param webColor the webColor to set
     */
    public void setWebColor(Color webColor) {
        this.webColor = webColor;
    }
}
