package mysnake.contral;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import mysnake.MainGui;
import mysnake.box.Snake;

/**
 * @author  沉默的小老头 Barry
 * QQ:191988637
 * Created on 2010-10-12, 11:03:08
 */

/**
 * 按键监听
 * 根据按键改变蛇的方向、速度等设置
 */
public class SnakeKeyListener implements KeyListener {

    MainGui gui;

    public SnakeKeyListener(MainGui inGui) {
        gui = inGui;
    }

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            //向下键、S键改变方向向下
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                //按下键
                if (gui.getBox().getSnake().getDirection() == Snake.DIRECTION_UP) {
                    return;
                }
                gui.getBox().getSnake().setDirection(Snake.DIRECTION_DOWN);
                break;

            //向上方向键、W键改变方向向下
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (gui.getBox().getSnake().getDirection() == Snake.DIRECTION_DOWN) {
                    return;
                }
                gui.getBox().getSnake().setDirection(Snake.DIRECTION_UP);
                break;

            //向左方向键、A键改变方向向下
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (gui.getBox().getSnake().getDirection() == Snake.DIRECTION_RIGHT) {
                    return;
                }
                gui.getBox().getSnake().setDirection(Snake.DIRECTION_LEFT);
                break;

            //向右方向键、D键改变方向向右
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (gui.getBox().getSnake().getDirection() == Snake.DIRECTION_LEFT) {
                    return;
                }
                gui.getBox().getSnake().setDirection(Snake.DIRECTION_RIGHT);
                break;

            //回车键开始、暂停游戏
            case KeyEvent.VK_ENTER:
                if (gui.getBox().getTimer().isRunning()) {
                    //主定时器暂停
                    gui.getBox().getTimer().stop();
                    //时钟暂停
                    gui.getBox().getClock().getCounTtimer().stop();
                } else {
                    //主定时器开始
                    gui.getBox().getTimer().start();
                    //时钟走动
                    gui.getBox().getClock().getCounTtimer().start();
                }
                break;

            //F11、Z键减速
            case KeyEvent.VK_Z:
            case KeyEvent.VK_F11:
                int tempDelay2 = gui.getBox().getTimer().getDelay();

                if ((tempDelay2 * 3 / 2) > 1000) {
                    return;
                }
                gui.getBox().getTimer().setDelay(tempDelay2 * 3 / 2);
                break;

            //F12、Z键加速
            case KeyEvent.VK_X:
            case KeyEvent.VK_F12:
                int tempDelay1 = gui.getBox().getTimer().getDelay();
                if ((tempDelay1 * 2 / 3) < 10) {
                    return;
                }
                gui.getBox().getTimer().setDelay(tempDelay1 * 2 / 3);
                break;
                
            default:
                System.out.println("其他键");
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
//　▅▅▅▅▅▅▅　　　▅▅▅▅▅　　　　▅
//　　▅▅█▅▅　　　　█　▅　█　　　　█
//　　█▅█▅█　　　　█　█　█　　　　█
//　▅█▅█▅█▅　　　　◢◤▋　▅　　　▼
//　　█　　▅█　　　██◤　███　　　●

