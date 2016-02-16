package mysnake.contral;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import mysnake.MainGui;

/**
 * @author  沉默的小老头 Barry
 * QQ:191988637
 * Created on 2010-10-17, 23:53:52
 */

/**
 * 设置面板监听者
 * 当设置面板有动作发生时会通知此监听者
 * 包括下拉列表项改变、单选按钮改变
 */
public class SettingListener implements ActionListener {

    /**传递参数*/
    MainGui gui;
    public SettingListener(MainGui inGui) {
        gui = inGui;
    }

    public void actionPerformed(ActionEvent e) {

        /** 下拉列表有改动时 ，设置背景、蛇、食物颜色 */
        if ((e.getSource().equals(gui.getSettingPanel().getBackgroundComboBox()))
                || (e.getSource().equals(gui.getSettingPanel().getSnakeComboBox()))
                || (e.getSource().equals(gui.getSettingPanel().getFoodComboBox()))
                || (e.getSource().equals(gui.getSettingPanel().getWebComboBox()))) {
            gui.getBox().setBackgroundColor(
                    getComboBoxColor(gui.getSettingPanel().getBackgroundComboBox()));
            gui.getBox().getSnake().setSnakeColor(
                    getComboBoxColor(gui.getSettingPanel().getSnakeComboBox()));
            gui.getBox().getFood().setFoodColor(
                    getComboBoxColor(gui.getSettingPanel().getFoodComboBox()));
            gui.getBox().setWebColor(
                    getComboBoxColor(gui.getSettingPanel().getWebComboBox()));
        } /** 
         * 单选按钮发生动作时， 设置网格样式*/
        else if (e.getSource().equals(gui.getSettingPanel().getStyleButton0())
                || e.getSource().equals(gui.getSettingPanel().getStyleButton1())
                || e.getSource().equals(gui.getSettingPanel().getStyleButton2())
                || e.getSource().equals(gui.getSettingPanel().getStyleButton3())) {
            if (gui.getSettingPanel().getStyleButton0().isSelected()) {
                gui.getBox().setWebStyle(0);
            } else if (gui.getSettingPanel().getStyleButton1().isSelected()) {
                gui.getBox().setWebStyle(1);
            } else if (gui.getSettingPanel().getStyleButton2().isSelected()) {
                gui.getBox().setWebStyle(2);
            } else {
                gui.getBox().setWebStyle(3);
                System.out.println("3");
            }
        }
        //使Box获得焦点
        gui.getBox().requestFocus();
        //重绘一次，应用设置
        gui.getBox().repaint();
    }

    /**
     * 从下拉列表中获取颜色
     * @param comboBox 下拉列表
     * @return 一个颜色
     */
    private Color getComboBoxColor(JComboBox comboBox) {

        Color newColor;
        String newColorString = (String) comboBox.getSelectedItem();
        if (newColorString.equals("黑色")) {
            newColor = Color.BLACK;
        } else if (newColorString.equals("蓝色")) {
            newColor = Color.BLUE;
        } else if (newColorString.equals("青色")) {
            newColor = Color.CYAN;
        } else if (newColorString.equals("深灰色")) {
            newColor = Color.DARK_GRAY;
        } else if (newColorString.equals("灰色")) {
            newColor = Color.GRAY;
        } else if (newColorString.equals("绿色")) {
            newColor = Color.GREEN;
        } else if (newColorString.equals("浅灰色")) {
            newColor = Color.LIGHT_GRAY;
        } else if (newColorString.equals("洋红色")) {
            newColor = Color.MAGENTA;
        } else if (newColorString.equals("橘黄色")) {
            newColor = Color.ORANGE;
        } else if (newColorString.equals("粉红色")) {
            newColor = Color.PINK;
        } else if (newColorString.equals("红色")) {
            newColor = Color.RED;
        } else if (newColorString.equals("白色")) {
            newColor = Color.WHITE;
        } else if (newColorString.equals("黄色")) {
            newColor = Color.YELLOW;
        } else {
            newColor = Color.BLACK;
        }
        return newColor;
    }
}
