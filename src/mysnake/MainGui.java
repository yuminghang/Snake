package mysnake;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import mysnake.box.Box;
import mysnake.contral.SettingListener;
import mysnake.contral.SnakeKeyListener;
import mysnake.settingground.PropertyPanel;

/**
 * @author  沉默的小老头 Barry
 * QQ:191988637
 * Created on 2010-10-9, 11:39:29
 */
/**
 * 主界面类，继承自JFrame类
 * 蛇场Box和设置面板settingPanel置于其上
 * 包括对主窗口的属性的设置、 注册监听者等
 */
public class MainGui extends JFrame {

    /**主窗口高度*/
    int windowHeight;//加数字是为了显示效果，下同
    /**主窗口宽度*/
    int windowWidth;
    /** 蛇场~~嘶嘶~~~ */
    private Box box = new Box(this);
    /**按键监听者*/
    SnakeKeyListener keyListener = new SnakeKeyListener(this);
    /**设置面板监听者*/
    SettingListener settingListener = new SettingListener(this);
    /**属性、设置面板*/
    private PropertyPanel settingPanel = new PropertyPanel();

    /**构造函数*/
    public MainGui() {

        super("贪吃蛇，~~~~沉默的小老头 1.0版本");

        //单击右上角X时退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**使窗口居中*/
        windowWidth = 880 + 17;
        windowHeight = 600 + 40;
        Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
        Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
        int screenWidth = screenSize.width;                     //获取屏幕的宽
        int screenHeight = screenSize.height;                   //获取屏幕的高
        setLocation(screenWidth / 2 - windowWidth / 2,
                screenHeight / 2 - windowHeight / 2);           //设置窗口居中显示
        setSize(windowWidth, windowHeight);

        /**将Box和seettingPanel加入界面*/
        setLayout(new BorderLayout());
        add(box, BorderLayout.CENTER);
        add(settingPanel, BorderLayout.SOUTH);

        //注册监听者
        addListener();

        //显示
        setVisible(true);

        //使Box获得焦点
        getBox().requestFocus();
    }

    /**
     * @return the box
     */
    public Box getBox() {
        return box;
    }

    /*
     * 注册监听者
     */
    private void addListener() {

        this.addKeyListener(keyListener);
        box.addKeyListener(keyListener);
        settingPanel.addKeyListener(keyListener);
        settingPanel.getStyleButton0().addActionListener(settingListener);
        settingPanel.getStyleButton1().addActionListener(settingListener);
        settingPanel.getStyleButton2().addActionListener(settingListener);
        settingPanel.getStyleButton3().addActionListener(settingListener);
        settingPanel.getBackgroundComboBox().addActionListener(settingListener);
        settingPanel.getSnakeComboBox().addActionListener(settingListener);
        settingPanel.getFoodComboBox().addActionListener(settingListener);
        settingPanel.getWebComboBox().addActionListener(settingListener);

        /**
         * 每次设置面板有动作时会重新设置焦点到Box上，
         * 因为用户可能手动点击ruleText,使之获得焦点
         * 所以对ruleText注册按键监听，使其能捕获到按键动作
         * 2010年10月18日16:54:23
         **/
        settingPanel.getRuleText().addKeyListener(keyListener);
        /**发现一种情况：
         * 点击下拉列表而不触发动作事件，
         *可以使焦点停留在下拉列表上，所以使下拉列表注册按键监听
         * 2010年10月18日18:51:48
         */
        settingPanel.backgroundComboBox.addKeyListener(keyListener);
        settingPanel.snakeComboBox.addKeyListener(keyListener);
        settingPanel.foodComboBox.addKeyListener(keyListener);
        settingPanel.webComboBox.addKeyListener(keyListener);
//        settingPanel.webComboBox.addKeyListener(keyListener);
//        settingPanel.styleButton0.addKeyListener(keyListener);
//        settingPanel.styleButton1.addKeyListener(keyListener);
//        settingPanel.styleButton2.addKeyListener(keyListener);
//        settingPanel.styleButton3.addKeyListener(keyListener);
    }

    /**
     * @return the settingPanel
     */
    public PropertyPanel getSettingPanel() {
        return settingPanel;
    }
}
