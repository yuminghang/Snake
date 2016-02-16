package mysnake.box;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * @author  沉默的小老头 Barry
 * QQ:191988637
 * Created on 2010-10-14, 18:43:36
 */

/**
 * 食物类，继承自Point类，
 * 包含一个坐标，即食物的位置x,y
 * 包含食物的颜色 foodColor
 * 包含重新产生一个食物的方法newMe()
 * 包含将食物画出的方法drawMe()
 */

//食物~~小苹果，幺兮~
//        ___  /__
//      /     `''  \
//     |            |
//      \          /
//       \___.___ /
public class Food extends Point {

    /**蛇场的引用字段*/
    Box box;
    /**食物的颜色*/
    private Color foodColor = Color.GREEN;

    public Food(Box inBox) {
        box = inBox;

        //初始食物位置
        init();
    }

    /**
     *初始食物位置
     */
    public void init() {
        this.x = 200;
        this.y = 60;
    }

    /**
     * 更新食物的坐标
     */
    public void newMe() {

        boolean isContinueCycle;
        do {
            // 如果产生的坐标在蛇身上，则重新产生
            /* 随机产生2个数，产生一个在屏幕内的坐标
             * 舍去除以20的余数*/
            int tempX = (new Random()).nextInt(box.getWidth() - 20);
            int tempY = (new Random()).nextInt(box.getHeight() - 20);
            this.x = tempX - tempX % 20;
            this.y = tempY - tempY % 20;

            isContinueCycle = false;
            for (Point p : box.getSnake().getBody()) {
                //与蛇身的节点逐个比较，
                //有坐标相同的节点则将isContinueCycle设为true, 
                //do while继续循环，产生新的食物坐标
                if (p.equals((Point) this)) {
                    isContinueCycle = true;
                }
            }
        } while (isContinueCycle);
    }

    /**画出食物*/
    public void drawMe(Graphics g) {
        g.setColor(getFoodColor());
        g.fill3DRect(box.getFood().x, box.getFood().y, 20, 20, true);
    }

    /**
     * @return the foodColor
     */
    public Color getFoodColor() {
        return foodColor;
    }

    /**
     * @param foodColor the foodColor to set
     */
    public void setFoodColor(Color foodColor) {
        this.foodColor = foodColor;
    }
}
