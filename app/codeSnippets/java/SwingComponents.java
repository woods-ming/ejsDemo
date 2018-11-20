package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * 梳理常用的SwingComponents（为了防止遗漏组件的重要属性，重新做了封装）
 */
public class SwingComponents {
    /**
     * 窗口 （外层容器 ，可以最小化、最大化、关闭 ）
     * 
     * @param title
     *            标题
     * @param width
     *            宽度
     * @param height
     *            高度
     */
    static JFrame getJFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setVisible(true); // 窗口默认隐藏，让它显示出来
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口时，结束程序

        return frame;
    }

    /**
     * 对话框 （外层容器 ，只能关闭、隶属于窗口 ）
     * 
     * @param frame
     *            所属窗口
     * @param title
     *            标题
     * @param modal
     *            是否模态对话框
     * @param width
     *            宽度
     * @param height
     *            高度
     */
    static JDialog getJDialog(JFrame frame, String title, boolean modal, int width, int height) {
        JDialog dialog = new JDialog(frame, title, modal);
        dialog.setSize(width, height);
        dialog.setVisible(true);

        return dialog;
    }

    /**
     * 面板（中间容器，用于归集元素）
     * 
     * @param layout
     *            布局方式
     */
    static JPanel getJPanel(LayoutManager layout) {
        JPanel panel = new JPanel(layout);

        return panel;
    }

    /**
     * 选项卡面板（中间容器，多个标签页、每个标签页可以添加面板）
     * 
     * @param frame
     *            所属窗口
     * @param tabPlacement
     *            选项卡所在位置
     */
    static JTabbedPane getJTabbedPane(JFrame frame, int tabPlacement) {
        JTabbedPane tabbedPane = new JTabbedPane(tabPlacement);
        frame.add(tabbedPane);

        return tabbedPane;
    }

    /**
     * 菜单栏
     * 
     * @param frame
     *            所属窗口
     */
    static JMenuBar getJMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        return menuBar;
    }

    /**
     * 菜单组
     * 
     * @param menuBar
     *            所属菜单栏
     * @param text
     *            文字
     */
    static JMenu getJMenu(JMenuBar menuBar, String text) {
        JMenu menu = new JMenu(text);
        menuBar.add(menu);

        return menu;
    }

    /**
     * 菜单项
     * 
     * @param menu
     *            所属菜单组
     * @param text
     *            文字
     */
    static JMenuItem getJMenuItem(JMenu menu, String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menu.add(menuItem);

        return menuItem;
    }

    /**
     * 多选框（无需分组）
     * 
     * @param panel
     *            所属面板
     * @param text
     *            文字
     * @param selected
     *            是否选中
     */
    static JCheckBox initJCheckbox(JPanel panel, String text, boolean selected) {
        JCheckBox checkbox = new JCheckBox(text, selected);
        panel.add(checkbox);

        return checkbox;
    }

    /**
     * 单选框（必须分组）
     * 
     * @param panel
     *            所属面板
     * @param group
     *            所属分组
     * @param text
     *            文字
     * @param selected
     *            是否选中
     * @return
     */
    static JRadioButton getJRadioButton(JPanel panel, ButtonGroup group, String text, boolean selected) {
        JRadioButton radioButton = new JRadioButton(text, selected);
        panel.add(radioButton);
        group.add(radioButton);

        return radioButton;
    }

    /**
     * 列表
     * 
     * @param panel
     *            所属面板
     * @param selectedIndex
     *            默认选项（从0开始）
     * @param items
     *            选项文字
     */
    static JList getJList(JPanel panel, int selectedIndex, String... items) {
        JList<String> list = new JList<String>();

        // 分别设置水平和垂直滚动条自动出现
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);

        // 添加选项
        DefaultListModel<String> dlist = new DefaultListModel<String>();
        for (String item : items) {
            dlist.addElement(item);
        }
        list.setModel(dlist);
        list.setSelectedIndex(selectedIndex);

        return list;
    }

    /**
     * 组合框（可输可选）
     * 
     * @param panel
     *            所属面板
     * @param items
     *            选项文字
     */
    static JComboBox getJComboBox(JPanel panel, String... items) {
        JComboBox<String> comboBox = new JComboBox<String>();
        panel.add(comboBox);

        // 添加选项
        for (String item : items) {
            comboBox.addItem(item);
        }

        return comboBox;
    }

    /**
     * 按钮
     * 
     * @param panel
     *            所属面板
     * @param text
     *            文字
     */
    static JButton getJButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        panel.add(button);

        // button.setBounds(10, 20, 50, 50);
        // button.setPreferredSize(new Dimension(50, 30));
        return button;
    }

    /**
     * 标签
     * 
     * @param panel
     *            所属面板
     * @param text
     *            文字
     * @param alignment
     *            文字的位置（左/中/右 SwingConstants）
     */
    static JLabel getJLabel(JPanel panel, String text, int alignment) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(alignment);
        panel.add(label);

        return label;
    }

    /**
     * 文本域
     * 
     * @param panel
     *            所属面板
     * @param text
     *            文字
     */
    static JTextField getJTextField(JPanel panel, String text) {
        JTextField textField = new JTextField(text);
        panel.add(textField);

        return textField;
    }

    /**
     * 文本块
     * 
     * @param panel
     *            所属面板
     * @param text
     *            文字
     */
    static JTextArea getJTextArea(JPanel panel, String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true); // 启用自动换行功能
        textArea.setWrapStyleWord(true); // 启用断行不断字功能

        // 分别设置水平和垂直滚动条自动出现
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);

        return textArea;
    }

    /**
     * 
     * @param frame
     * @param message
     * @param messageType
     * @param optionType
     * @return
     */
    static JOptionPane getJOptionPane(JFrame frame, String message, int messageType, int optionType) {
        JOptionPane textArea = new JOptionPane(message, messageType, optionType);
        frame.add(textArea);

        return textArea;
    }

    public static void main(String[] args) {
        /* 外层容器 */
        JFrame mainFrame = SwingComponents.getJFrame("主窗口", 500, 300);
        // JDialog dialog = SwingComponents.getJDialog(mainFrame, "对话框", true,
        // 200,
        // 300);

        /* 中间容器 */
        // 流式布局：向一个方向排列，满了就换行
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // 边框布局：把元素放到“东南西北中”五个位置中
        JPanel panel2 = new JPanel(new BorderLayout());

        // 网格布局：划分格子，将元素依次放到格子中
        JPanel panel3 = new JPanel(new GridLayout(4, 3));

        // 网格组布局：划分格子，按坐标指定元素的位置
        JPanel panel4 = new JPanel(new GridBagLayout());

        // 盒子布局：把组件当成盒子，沿一个方向排列（水平/垂直）
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

        // 自由布局：使用元素的像素坐标，自由定位并设置大小
        JPanel panel6 = new JPanel(null);

        JTabbedPane tabbedPane = SwingComponents.getJTabbedPane(mainFrame, JTabbedPane.BOTTOM);
        tabbedPane.addTab("流式布局", panel1);
        tabbedPane.addTab("边框布局", panel2);
        tabbedPane.addTab("网格布局", panel3);
        tabbedPane.addTab("网格组布局", panel4);
        tabbedPane.addTab("盒子布局", panel5);
        tabbedPane.addTab("自由布局", panel6);

        /* 原子 */
        // panel1的内容：单选
        panel1.add(new JLabel("这个寒假，你要去哪儿？"));
        ButtonGroup group = new ButtonGroup();
        SwingComponents.getJRadioButton(panel1, group, "A. 回家探亲", true);
        SwingComponents.getJRadioButton(panel1, group, "B. 留校学习", false);
        SwingComponents.getJRadioButton(panel1, group, "C. 出去旅游", false);
        SwingComponents.getJRadioButton(panel1, group, "D. 勤工俭学", false);

        // panel2的内容：按钮
        panel2.add(new JButton("东邪"), BorderLayout.EAST);
        panel2.add(new JButton("西毒"), BorderLayout.WEST);
        panel2.add(new JButton("南帝"), BorderLayout.SOUTH);
        panel2.add(new JButton("北丐"), BorderLayout.NORTH);
        panel2.add(new JButton("中顽童"), BorderLayout.CENTER);

        // panel3的内容：按钮
        panel3.add(new JButton("1"));
        panel3.add(new JButton("2"));
        panel3.add(new JButton("3"));
        panel3.add(new JButton("4"));
        panel3.add(new JButton("5"));
        panel3.add(new JButton("6"));
        panel3.add(new JButton("7"));
        panel3.add(new JButton("8"));
        panel3.add(new JButton("9"));
        panel3.add(new JButton("*"));
        panel3.add(new JButton("0"));
        panel3.add(new JButton("#"));
        for(Component c : panel3.getComponents()){
            c.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        }

        // panel4的内容：按钮
        // panel5的内容：按钮

        // panel6的内容：（表单组件）文本输入、下列列表
        SwingComponents.getJLabel(panel6, "姓名：", SwingConstants.RIGHT);
        SwingComponents.getJTextField(panel6, "woods");

        SwingComponents.getJLabel(panel6, "年龄：", SwingConstants.RIGHT);
        SwingComponents.getJTextField(panel6, "34");

        SwingComponents.getJLabel(panel6, "职业：", SwingConstants.RIGHT);
        SwingComponents.getJComboBox(panel6, "讲师", "程序员", "项目经理", "产品经理", "运维", "实施", "测试");

        SwingComponents.getJLabel(panel6, "自我介绍：", SwingConstants.RIGHT);
        SwingComponents.getJTextArea(panel6,
                "喜欢思考、善于总结，十余年程序员生涯，全栈开放工程师、软件设计师、系统架构师。\n常用的前端开发技术：html、css、js、jQuery、BootStrap。\n精通的后端开发技术：c#、Asp.netMVC、EntityFramework、Linq、java、SpringMVC、HibernateValidator、Mybatis。\n熟悉的数据库：SqlServer、Oracle、Mysql。\n常用的版本控制工具：SVN、git");

        SwingComponents.getJLabel(panel6, "户籍：", SwingConstants.RIGHT);
        SwingComponents.getJList(panel6, 3, "北京", "上海", "深圳", "宁夏", "内蒙", "新疆", "西藏");

    }
}
