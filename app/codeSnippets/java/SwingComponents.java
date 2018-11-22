import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
    static JCheckBox getJCheckbox(JPanel panel, String text, boolean selected) {
        JCheckBox checkbox = new JCheckBox(text, selected);
        checkbox.setHorizontalAlignment(SwingConstants.CENTER);
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
    static JList getJList(JPanel panel, String... items) {
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

        return list;
    }

    /**
     * 组合框（可输可选）
     * 
     * @param items
     *            选项文字
     */
    static JComboBox getJComboBox(String... items) {
        JComboBox<String> comboBox = new JComboBox<String>();

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
     * 确认消息对话框
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
        PerfectDemo demo = new PerfectDemo();
    }
}

class PerfectDemo {
    /* 外层容器 */
    JFrame mainFrame;
    JDialog dialog;

    /* 中间容器 */
    JTabbedPane tabbedPane;
    JPanel panel1, panel2, panel3, panel4, panel5, panel6;

    /* 原子组件 */
    JRadioButton panel1_radioA, panel1_radioB, panel1_radioC, panel1_radioD;
    JButton panel2_btn1, panel2_btn2, panel2_btn3, panel2_btn4, panel2_btn5;
    JLabel panel3_label_h1, panel3_label_h2, panel3_label_h3, panel3_label_r11, panel3_label_r12, panel3_label_r21,
            panel3_label_r22, panel3_label_r31, panel3_label_r32;
    JCheckBox panel3_checkbox_r13, panel3_checkbox_r23, panel3_checkbox_r33;
    JLabel panel4_label1, panel4_label2, panel4_label3, panel4_label4;
    JTextField panel4_text1, panel4_text2, panel4_text3, panel4_text4;
    JComboBox panel4_combobox1;

    public PerfectDemo() {
        this.initComponent();
        this.initLookAndFeel();
        this.initEventListener();
    }

    private void initComponent() {
        mainFrame = SwingComponents.getJFrame("主窗口", 500, 300);
        dialog = SwingComponents.getJDialog(mainFrame, "友情提示", true, 400, 100);
        this.drawDialog();

        JTabbedPane tabbedPane = SwingComponents.getJTabbedPane(mainFrame, JTabbedPane.BOTTOM);

        // 流式布局：向一个方向排列，满了就换行
        panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tabbedPane.addTab("流式布局", panel1);
        this.drawPanel1();

        // 边框布局：把元素放到“东南西北中”五个位置中
        panel2 = new JPanel(new BorderLayout());
        tabbedPane.addTab("边框布局", panel2);
        this.drawPanel2();

        // 网格布局：划分格子，将元素依次放到格子中
        panel3 = new JPanel(new GridLayout(5, 3));
        tabbedPane.addTab("网格布局", panel3);
        this.drawPanel3();

        // 网格组布局：划分格子，按坐标指定元素的位置
        panel4 = new JPanel(new GridBagLayout());
        tabbedPane.addTab("网格组布局", panel4);
        this.drawPanel4();

        // 盒子布局：把组件当成盒子，沿一个方向排列（水平/垂直）
        panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        tabbedPane.addTab("盒子布局", panel5);
        this.drawPanel5();

        // 自由布局：使用元素的像素坐标，自由定位并设置大小
        panel6 = new JPanel(null);
        tabbedPane.addTab("自由布局", panel6);
        this.drawPanel6();

    }

    private void drawDialog() {
        // dialog的内容：文字说明
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(new JLabel("本示例 包含了所有常用组件、"));
        panel1.add(new JLabel("演示了各种布局、"));
        panel1.add(new JLabel("用到了所有类型的事件"));

        dialog.add(panel1);
        dialog.setVisible(true);
    }

    private void drawPanel1() {
        // panel1的内容：单选
        panel1.add(new JLabel("这个寒假，你要去哪儿？"));
        ButtonGroup group = new ButtonGroup();
        panel1_radioA = SwingComponents.getJRadioButton(panel1, group, "A. 回家探亲", true);
        panel1_radioB = SwingComponents.getJRadioButton(panel1, group, "B. 留校学习", false);
        panel1_radioC = SwingComponents.getJRadioButton(panel1, group, "C. 出去旅游", false);
        panel1_radioD = SwingComponents.getJRadioButton(panel1, group, "D. 勤工俭学", false);
    }

    private void drawPanel2() {
        // panel2的内容：按钮
        panel2.add(panel2_btn1 = new JButton("东邪"), BorderLayout.EAST);
        panel2.add(panel2_btn2 = new JButton("西毒"), BorderLayout.WEST);
        panel2.add(panel2_btn3 = new JButton("南帝"), BorderLayout.SOUTH);
        panel2.add(panel2_btn4 = new JButton("北丐"), BorderLayout.NORTH);
        panel2.add(panel2_btn5 = new JButton("中神通"), BorderLayout.CENTER);
    }

    private void drawPanel3() {
        // panel3的内容：标签和复选框
        panel3_label_h1 = SwingComponents.getJLabel(panel3, "学号", JLabel.CENTER);
        panel3_label_h2 = SwingComponents.getJLabel(panel3, "姓名", JLabel.CENTER);
        panel3_label_h3 = SwingComponents.getJLabel(panel3, "签到", JLabel.CENTER);

        panel3_label_r11 = SwingComponents.getJLabel(panel3, "1001", JLabel.CENTER);
        panel3_label_r12 = SwingComponents.getJLabel(panel3, "张三", JLabel.CENTER);
        panel3_checkbox_r13 = SwingComponents.getJCheckbox(panel3, "", false);

        panel3_label_r21 = SwingComponents.getJLabel(panel3, "1002", JLabel.CENTER);
        panel3_label_r22 = SwingComponents.getJLabel(panel3, "李四", JLabel.CENTER);
        panel3_checkbox_r23 = SwingComponents.getJCheckbox(panel3, "", false);

        panel3_label_r31 = SwingComponents.getJLabel(panel3, "1003", JLabel.CENTER);
        panel3_label_r32 = SwingComponents.getJLabel(panel3, "王五", JLabel.CENTER);
        panel3_checkbox_r33 = SwingComponents.getJCheckbox(panel3, "", false);
    }

    private void drawPanel4() {
        // panel4的内容：（表单组件）文本输入、下列列表
        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        {
            c.gridx = 0;
            panel4.add(panel4_label1 = new JLabel("姓名：", JLabel.RIGHT), c);

            c.gridx = 1;
            panel4.add(panel4_text1 = new JTextField("woods"), c);
        }

        c.gridy = 1;
        {
            c.gridx = 0;
            panel4.add(panel4_label2 = new JLabel("年龄：", JLabel.RIGHT), c);

            c.gridx = 1;
            panel4.add(panel4_text2 = new JTextField("34"), c);
        }

        c.gridy = 2;
        {
            c.gridx = 0;
            panel4.add(panel4_label2 = new JLabel("职业：", JLabel.RIGHT), c);

            c.gridx = 1;
            panel4.add(panel4_combobox1 = SwingComponents.getJComboBox("讲师", "程序员", "项目经理", "产品经理", "运维", "实施", "测试"),
                    c);
        }

        //
        // SwingComponents.getJLabel(panel6, "自我介绍：", SwingConstants.RIGHT);
        // SwingComponents.getJTextArea(panel6,
        // "喜欢思考、善于总结，十余年程序员生涯，全栈开放工程师、软件设计师、系统架构师。\n常用的前端开发技术：html、css、js、jQuery、BootStrap。\n精通的后端开发技术：c#、Asp.netMVC、EntityFramework、Linq、java、SpringMVC、HibernateValidator、Mybatis。\n熟悉的数据库：SqlServer、Oracle、Mysql。\n常用的版本控制工具：SVN、git");
        //
        // SwingComponents.getJLabel(panel6, "户籍：", SwingConstants.RIGHT);
        // SwingComponents.getJList(panel6, "北京", "上海", "深圳", "宁夏", "内蒙", "新疆",
        // "西藏");
    }

    private void drawPanel5() {
        // TODO Auto-generated method stub

    }

    private void drawPanel6() {
        // TODO Auto-generated method stub

    }

    private void initLookAndFeel() {
        for (Component c : panel3.getComponents()) {
            c.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        }
    }

    private void initEventListener() {
        // 窗口，在关闭前，显示确认对话框
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(mainFrame, "Do you want to close this window?", "Confirmation",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (n == JOptionPane.YES_OPTION) {
                    mainFrame.dispose();
                } else {
                    mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        // panel1中的单选按钮，在切换时，变色
        Color orginColor = panel1_radioA.getBackground();
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JRadioButton radio = (JRadioButton) e.getSource();
                if (radio.isSelected()) {
                    radio.setBackground(Color.ORANGE);
                } else {
                    radio.setBackground(orginColor);
                }
            }
        };
        for (Component c : panel1.getComponents()) {
            if (c instanceof JRadioButton) {
                JRadioButton radio = (JRadioButton) c;
                radio.addItemListener(itemListener);
            }
        }

        // panel2的按钮，在点击时，弹出提示信息
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();

                String msg = "不知道";
                switch (button.getText()) {
                case "东邪":
                    msg = "黄药师";
                    break;
                case "西毒":
                    msg = "欧阳锋";
                    break;
                case "南帝":
                    msg = "段智兴";
                    break;
                case "北丐":
                    msg = "洪七公";
                    break;
                case "中神通":
                    msg = "王重阳";
                    break;
                }

                JOptionPane.showMessageDialog(mainFrame, msg);
            }
        };
        for (Component c : panel2.getComponents()) {
            if (c instanceof JButton) {
                JButton button = (JButton) c;
                button.addActionListener(actionListener);
            }
        }

    }

}
