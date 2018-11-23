import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

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
    static JDialog getJDialog(JFrame frame, String title, boolean modal,
            int width, int height) {
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
    static JRadioButton getJRadioButton(JPanel panel, ButtonGroup group,
            String text, boolean selected) {
        JRadioButton radioButton = new JRadioButton(text, selected);
        panel.add(radioButton);
        group.add(radioButton);

        return radioButton;
    }

    /**
     * 列表
     * 
     * @param selectedIndex
     *            默认选项（从0开始）
     * @param items
     *            选项文字
     */
    static JList<String> getJList(String... items) {
        JList<String> list = new JList<String>();

        // 分别设置水平和垂直滚动条自动出现
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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
    static JComboBox<String> getJComboBox(String... items) {
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
    static JTextArea getJTextArea(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true); // 启用自动换行功能
        textArea.setWrapStyleWord(true); // 启用断行不断字功能

        // 分别设置水平和垂直滚动条自动出现
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        return textArea;
    }

    public static void main(String[] args) {
        new PerfectDemo();
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

    JLabel panel3_label_h1, panel3_label_h2, panel3_label_h3, panel3_label_r11,
            panel3_label_r12, panel3_label_r21, panel3_label_r22,
            panel3_label_r31, panel3_label_r32;
    JCheckBox panel3_checkbox_r13, panel3_checkbox_r23, panel3_checkbox_r33;

    JLabel panel4_label00, panel4_label10, panel4_label20, panel4_label30,
            panel4_label40;
    JTextField panel4_text01, panel4_text11;
    JComboBox<String> panel4_combobox21;
    JTextArea panel4_textArea31;
    JList<String> panel4_list41;
    JButton panel4_button51;

    JLabel panel5_label1, panel5_label2, panel5_label3, panel5_label4,
            panel5_label5;

    JPanel panel6_drawer;
    JLabel panel6_label_x, panel6_label_y, panel6_label_start, panel6_label_end,
            panel6_label_description;

    public PerfectDemo() {
        this.initComponent();
        this.initLookAndFeel();
        this.initEventListener();
    }

    /**
     * 排列组件
     */
    private void initComponent() {
        mainFrame = SwingComponents.getJFrame("主窗口", 500, 600);

        dialog = SwingComponents.getJDialog(mainFrame, "友情提示", true, 400, 200);
        this.drawDialog();

        tabbedPane = SwingComponents.getJTabbedPane(mainFrame,
                JTabbedPane.BOTTOM);

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
        panel1.add(new JLabel("本示例 包含了所有常用组件"));
        panel1.add(new JLabel("演示了各种布局"));
        panel1.add(new JLabel("用到了所有类型的事件"));

        for (Component c : panel1.getComponents()) {
            c.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
            c.setForeground(Color.ORANGE);
        }

        dialog.add(panel1);
        dialog.setVisible(true);
    }

    private void drawPanel1() {
        // panel1的内容：单选
        panel1.add(new JLabel("这个寒假，你要去哪儿？"));
        ButtonGroup group = new ButtonGroup();
        panel1_radioA = SwingComponents.getJRadioButton(panel1, group,
                "A. 回家探亲", false);
        panel1_radioB = SwingComponents.getJRadioButton(panel1, group,
                "B. 留校学习", false);
        panel1_radioC = SwingComponents.getJRadioButton(panel1, group,
                "C. 出去旅游", false);
        panel1_radioD = SwingComponents.getJRadioButton(panel1, group,
                "D. 勤工俭学", false);
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
        panel3_label_h1 = SwingComponents.getJLabel(panel3, "学号",
                JLabel.CENTER);
        panel3_label_h2 = SwingComponents.getJLabel(panel3, "姓名",
                JLabel.CENTER);
        panel3_label_h3 = SwingComponents.getJLabel(panel3, "签到",
                JLabel.CENTER);

        panel3_label_r11 = SwingComponents.getJLabel(panel3, "1001",
                JLabel.CENTER);
        panel3_label_r12 = SwingComponents.getJLabel(panel3, "张三",
                JLabel.CENTER);
        panel3_checkbox_r13 = SwingComponents.getJCheckbox(panel3, "", false);

        panel3_label_r21 = SwingComponents.getJLabel(panel3, "1002",
                JLabel.CENTER);
        panel3_label_r22 = SwingComponents.getJLabel(panel3, "李四",
                JLabel.CENTER);
        panel3_checkbox_r23 = SwingComponents.getJCheckbox(panel3, "", false);

        panel3_label_r31 = SwingComponents.getJLabel(panel3, "1003",
                JLabel.CENTER);
        panel3_label_r32 = SwingComponents.getJLabel(panel3, "王五",
                JLabel.CENTER);
        panel3_checkbox_r33 = SwingComponents.getJCheckbox(panel3, "", false);
    }

    private void drawPanel4() {
        // panel4的内容：（表单组件）文本输入、下列列表
        panel4.setBackground(Color.ORANGE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5); // 单元格的内边距
        c.fill = GridBagConstraints.BOTH; // 组件充满单元格（相当于设置大小）

        c.gridy = 0;
        {
            c.gridx = 0;
            c.ipadx = 60;
            panel4.add(panel4_label00 = new JLabel("姓名：", JLabel.RIGHT), c);

            c.gridx = 1;
            c.ipadx = 200;
            panel4.add(panel4_text01 = new JTextField("woods"), c);
        }

        c.gridy = 1;
        {
            c.gridx = 0;
            c.ipadx = 60;
            panel4.add(panel4_label10 = new JLabel("年龄：", JLabel.RIGHT), c);

            c.gridx = 1;
            c.ipadx = 200;
            panel4.add(panel4_text11 = new JTextField("34"), c);
        }

        c.gridy = 2;
        {
            c.gridx = 0;
            c.ipadx = 60;
            panel4.add(panel4_label20 = new JLabel("职业：", JLabel.RIGHT), c);

            c.gridx = 1;
            c.ipadx = 200;
            panel4.add(panel4_combobox21 = SwingComponents.getJComboBox("讲师",
                    "程序员", "项目经理", "产品经理", "运维", "实施", "测试"), c);
            panel4_combobox21.setSelectedIndex(0);
        }

        c.gridy = 3;
        {
            c.gridx = 0;
            c.ipadx = 60;
            panel4.add(panel4_label30 = new JLabel("自我介绍：", JLabel.RIGHT), c);

            c.gridx = 1;
            c.ipadx = 0;
            panel4.add(panel4_textArea31 = new JTextArea(
                    "喜欢思考、善于总结，十余年程序员生涯，\n全栈开发工程师、软件设计师、系统架构师。"), c);
        }

        c.gridy = 4;
        {
            c.gridx = 0;
            c.ipadx = 60;
            panel4.add(panel4_label40 = new JLabel("家乡：", JLabel.RIGHT), c);

            c.gridx = 1;
            c.ipadx = 200;
            panel4.add(panel4_list41 = SwingComponents.getJList("北京", "上海",
                    "深圳", "宁夏", "内蒙", "新疆", "西藏"), c);
            panel4_list41.setSelectedIndex(3);
        }

        c.gridy = 5;
        {
            c.gridx = 1;
            c.ipadx = 0;
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.WEST;
            panel4.add(panel4_button51 = new JButton("保存（Ctrl + S）"), c);
        }

    }

    private void drawPanel5() {
        panel5.add(panel5_label1 = new JLabel());
        panel5.add(panel5_label2 = new JLabel());
        panel5.add(panel5_label3 = new JLabel());
        panel5.add(panel5_label4 = new JLabel());
        panel5.add(panel5_label5 = new JLabel());

        this.redrawPanel5();
    }

    private void redrawPanel5() {
        panel5_label1
                .setText(panel4_label00.getText() + panel4_text01.getText());
        panel5_label2
                .setText(panel4_label10.getText() + panel4_text11.getText());
        panel5_label3.setText(panel4_label20.getText()
                + panel4_combobox21.getSelectedItem().toString());
        panel5_label4.setText(
                panel4_label30.getText() + panel4_textArea31.getText());
        panel5_label5.setText(
                panel4_label40.getText() + panel4_list41.getSelectedValue());
    }

    private void drawPanel6() {
        panel6_label_x = SwingComponents.getJLabel(panel6, "X：", JLabel.LEFT);
        panel6_label_y = SwingComponents.getJLabel(panel6, "Y：", JLabel.LEFT);
        panel6_label_start = SwingComponents.getJLabel(panel6, "起点：(x , y)",
                JLabel.LEFT);
        panel6_label_end = SwingComponents.getJLabel(panel6, "终点：(x , y)",
                JLabel.LEFT);
        panel6_label_description = SwingComponents.getJLabel(panel6,
                "画直线：鼠标按下确定起点，鼠标松开确定终点", JLabel.CENTER);
        panel6.add(panel6_drawer = new JPanel());

        panel6_label_x.setBounds(5, 5, 50, 10);
        panel6_label_y.setBounds(5, 25, 50, 10);
        panel6_drawer.setBounds(50, 50, 400, 400);
        panel6_label_start.setBounds(50, 460, 100, 20);
        panel6_label_end.setBounds(350, 460, 100, 20);
        panel6_label_description.setBounds(50, 20, 400, 20);
    }

    /**
     * 雕琢外观
     */
    private void initLookAndFeel() {
        // 主窗口，设置最小范围
        mainFrame.setMinimumSize(new Dimension(500, 600));

        // panel3中，统一设置组件的字体
        for (Component c : panel3.getComponents()) {
            c.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        }
        panel3_label_h1.setFont(new Font(Font.SERIF, Font.BOLD, 26));
        panel3_label_h2.setFont(new Font(Font.SERIF, Font.BOLD, 26));
        panel3_label_h3.setFont(new Font(Font.SERIF, Font.BOLD, 26));

        // panel5中，统一设置组件的字体
        for (Component c : panel5.getComponents()) {
            c.setFont(new Font("宋体", Font.BOLD, 26));
        }

        // panel6中，设置组件的颜色
        panel6_drawer.setBackground(Color.WHITE);
        panel6_label_start.setForeground(Color.GREEN);
        panel6_label_end.setForeground(Color.RED);
        panel6_label_description.setForeground(Color.BLUE);
        panel6_label_description.setFont(new Font("楷体", Font.ITALIC, 14));
    }

    /**
     * 互动
     */
    private void initEventListener() {
        // 窗口，在关闭前，显示确认对话框
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(mainFrame,
                        "Do you want to close this window?", "Confirmation",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (n == JOptionPane.YES_OPTION) {
                    mainFrame.dispose();
                } else {
                    mainFrame.setDefaultCloseOperation(
                            WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        // tabbedPane，在切换时，在标签页内做操作
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedComponent() == panel4) {
                    // 在切换到panel4时，获取文本框的焦点
                    panel4_text01.requestFocusInWindow();
                } else if (tabbedPane.getSelectedComponent() == panel5) {
                    // 在切换到panel5时，重写计算组件的值
                    redrawPanel5();
                }
            }
        });

        // panel1中的单选按钮，在切换时，变色
        Color orginBackgroundColor = panel1_radioA.getBackground();
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JRadioButton radio = (JRadioButton) e.getSource();
                if (radio.isSelected()) {
                    radio.setBackground(Color.ORANGE);
                } else {
                    radio.setBackground(orginBackgroundColor);
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

        // panel4的保存按钮，在点击后，弹出确认对话框并进入panel5
        panel4_button51.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(mainFrame,
                        "确定要保存吗？");
                if (JOptionPane.YES_OPTION == option) {
                    tabbedPane.setSelectedIndex(4);
                }
            }
        });

        // panel4的所有组件，在按下Ctrl+s后，触发保存按钮
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
                    panel4_button51.doClick();
                }
            }
        };
        for (Component c : panel4.getComponents()) {
            c.addKeyListener(keyListener);
        }

        // panel4的文本框，在焦点获取或丢失后，改变外观
        Color orginForegroundColor = panel4_text01.getForeground();
        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // 在获取焦点后，改变字的颜色
                Component c = e.getComponent();
                c.setForeground(Color.RED);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // 在丢失焦点后，恢复原样
                Component c = e.getComponent();
                c.setForeground(orginForegroundColor);
            }
        };
        for (Component c : panel4.getComponents()) {
            if (c instanceof JTextComponent) {
                JTextComponent text = (JTextComponent) c;
                text.addFocusListener(focusListener);
            }
        }

        // panel6的画板，当鼠标按下、松开时，画直线
        panel6_drawer.addMouseListener(new MouseAdapter() {
            private int start_x, start_y;

            @Override
            public void mouseEntered(MouseEvent e) {
                // 改变鼠标形状
                panel6_drawer.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 鼠标按下时，记录坐标值，作为起点
                start_x = e.getX();
                start_y = e.getY();
                panel6_label_start.setText(
                        "起点：(x , y)".replaceAll("x", String.valueOf(start_x))
                                .replaceAll("y", String.valueOf(start_y)));

                // 清除终点的坐标值
                panel6_label_end.setText("终点：(x , y)");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // 鼠标松开时，记录坐标值，作为终点
                panel6_label_end.setText(
                        "终点：(x , y)".replaceAll("x", String.valueOf(e.getX()))
                                .replaceAll("y", String.valueOf(e.getY())));

                // 画直线，连接(起点, 终点)
                Graphics g = panel6_drawer.getGraphics();
                g.setColor(Color.ORANGE);
                g.drawLine(start_x, start_y, e.getX(), e.getY());
            }
        });

        // panel6的画板，当鼠标在其中移动时，记录坐标
        panel6_drawer.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                panel6_label_x.setText("X：" + e.getX());
                panel6_label_y.setText("Y：" + e.getY());
            }
        });
    }
}
