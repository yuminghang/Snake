package mysnake.settingground;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * @author  沉默的小老头 Barry
 * QQ:191988637
 * Created on 2010-10-17, 21:06:02
 */

//时钟类~~
//运行一个单独的线程
//╔╗　　　╗         ╔　═══╗　
//║║╔══╠         ╭╔══╗║　
//╠╣　　　║         ║║　　║║　
//║║　╚╗║         ║║　　║║　
//║║　　╚║         ║╠══╣║　
//╚╯　╚═╯         ╚╚══╯╝

public class Clock {
    
    /*时间线程*/
    private Thread timeThread;
    /**经历的时间*/
    private int time = 0;
    /**计时器*/
    private Timer counTtimer;

    public Clock() {
        timeThread = new Thread(new Runnable() {

            public void run() {
                counTtimer = new Timer(1000, new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        setTime(getTime() + 1);
                    }
                });
                getCounTtimer().start();
            }
        });
    }

    /**
     * @return the timeThread
     */
    public Thread getTimeThread() {
        return timeThread;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the counTtimer
     */
    public Timer getCounTtimer() {
        return counTtimer;
    }
}
