package mysnake.box;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 * @author 沉默的小老头 Barry
 * QQ:191988637
 * Created on 2010-10-9, 18:18:04
 */

/**
 * 蛇类~~ 包含有关蛇的方法属性
 * 属性：
 *      蛇长、方向、颜色、是否活着
 * 方法：
 *      蛇的初始化、init()
 *      计算下一个位置、countNewSite()
 *      将蛇画出 drawMe()
 *      死亡之后重新开始 stareNewGame()
 */

//          小蛇~~
//                    /^\/^\
//                  _|__|  O|
//         \/     /~     \_/ \
//          \____|__________/  \
//                 \_______      \
//                         `\     \                 \
//                           |     |                  \
//                          /      /                    \
//                         /     /                       \\
//                       /      /                         \ \
//                      /     /                            \  \
//                    /     /             _----_            \   \
//                   /     /           _-~      ~-_         |   |
//                  (      (        _-~    _--_    ~-_     _/   |
//                   \      ~-____-~    _-~    ~-_    ~-_-~    /
//                     ~-_           _-~          ~-_       _-~
//                        ~--______-~                ~-___-~
public class Snake {

    /**常量，表示向上方向*/
    public static final int DIRECTION_UP = 1;
    /**常量，表示向下方向*/
    public static final int DIRECTION_DOWN = -1;
    /**常量，表示向左方向*/
    public static final int DIRECTION_LEFT = 2;
    /**常量，表示向右方向*/
    public static final int DIRECTION_RIGHT = -2;
    /**   蛇身子，用数据类型Linkedlist表示     */
    private LinkedList<Point> body = new LinkedList<Point>();
    /**蛇的前进方向*/
    private int direction;
    /**蛇的颜色*/
    private Color snakeColor = Color.RED;
    /** 蛇是否活着 */
    private boolean isLive = true;
    /**储存下一个节点坐标*/
    Point nextSite;
    /**蛇场的引用字段*/
    Box box;

    /**蛇Snake的构造方法*/
    public Snake(Box inBox) {
        box = inBox;
        init();
    }

    /**
     * 初始化蛇
     */
    private void init() {
        //方向初始化为向右
        setDirection(DIRECTION_RIGHT);
        /*增加初始的节点*/
        body.add(new Point(20, 60));
        body.add(new Point(40, 60));
        body.add(new Point(60, 60));
        body.add(new Point(80, 60));
        nextSite = new Point(100, 60);
    }

    /**
     * 计算蛇下一个节点的位置
     */
    public void countNewSite() {

        /*如果isLive为false了，通知死亡~~*/
        if (!isLive) {
            talkingdead();
        }

        getBody().addLast(new Point(nextSite.x, nextSite.y));//头部增加一个节点
        // ！！！ getBody().addLast(nextSite);
        // ！！！ 直接用addLast(nextSite) 原因不明 2010年10月12日
        //可能是因为直接添加变量nextSite，上下文改变nextSite的值时会导致所有节点改变
        //2010年10月14日2:24:51

        if (nextSite.equals(box.getFood())) {
            box.getFood().newMe();
        } else {
            getBody().remove();//移除最后一个节点
        }

        /*根据方向计算节点坐标*/
        switch (getDirection()) {
            case DIRECTION_UP:
                nextSite.y -= 20;
                break;
            case DIRECTION_DOWN:
                nextSite.y += 20;
                break;
            case DIRECTION_LEFT:
                nextSite.x -= 20;
                break;
            case DIRECTION_RIGHT:
                nextSite.x += 20;
                break;
        }
        /*如果下一个节点越界，则从另一侧出现*/
        if (nextSite.x < 0) {
            nextSite.x = (box.getWidth() / 20 - 1) * 20;
        } else if (nextSite.x > box.getWidth() - 20) {
            nextSite.x = 0;
        }
        if (nextSite.y < 0) {
            nextSite.y = (box.getHeight() / 20 - 1) * 20;
        } else if (nextSite.y > box.getHeight() - 20) {
            nextSite.y = 0;
        }
        /*如果蛇下一个节点在蛇身上，即吃到自身，将isLive设为false*/
        for (Point p : getBody()) {
            if (nextSite.equals(p)) {
                setIsLive(false);
            }
        }
    }

    /**
     * 将蛇画出
     * @param g
     */
    public void drawMe(Graphics g) {
        g.setColor(snakeColor);
        for (Point b : getBody()) {
            g.fill3DRect(b.x, b.y, 20, 20, true);
        }
    }


//                   .-"      "-.
//                  /            \
//                 |              |
//                 |,  .-.  .-.  ,|
//                 | )(__/  \__)( |
//                 |/     /\     \|
//       (@_       (_     ^^     _)
//  _     ) \_______\__|IIIIII|__/__________________________
// (_)@8@8{}<________|-\IIIIII/-|___________________________>
//        )_/        \          /
//       (@           `--------`

    /**
     * 通知蛇死亡~囧~
     * 由用户选择是否再来一局
     */
    private void talkingdead() {
        box.getTimer().stop();
        int yourChoice = JOptionPane.showConfirmDialog(box,
                "是否再来一局",
                "死了~~~",
                JOptionPane.YES_NO_OPTION);
        if (yourChoice == JOptionPane.YES_OPTION) {
            startNewGame();
        } else {
            System.exit(0);
        }
    }

    /**
     * 重新开始一局游戏
     */
    private void startNewGame() {
        //清除列表中所有节点
        getBody().clear();
        //重新初始化蛇
        this.init();
        //初始化食物位置
        box.getFood().init();
        //将速度设为100
        box.getTimer().setDelay(100);
        //Clock时钟置零
        box.getClock().setTime(0);
        //isLive设置为true
        setIsLive(true);
    }


    /**
     * @return the body
     */
    public LinkedList<Point> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(LinkedList<Point> body) {
        this.body = body;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the isLive
     */
    public boolean isIsLive() {
        return isLive;
    }

    /**
     * @param isLive the isLive to set
     */
    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }

    /**
     * @return the snakeColor
     */
    public Color getSnakeColor() {
        return snakeColor;
    }

    /**
     * @param snakeColor the snakeColor to set
     */
    public void setSnakeColor(Color snakeColor) {
        this.snakeColor = snakeColor;
    }
}
