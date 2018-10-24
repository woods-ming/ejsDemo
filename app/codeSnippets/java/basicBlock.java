// 1）定义类
package 包;
import 引用的包;

[访问说明符] class 类名{
    // 1.1）定义成员变量
    [访问说明符] 数据类型 变量名 = 值;

    // 1.2）定义成员方法
    [访问说明符] 返回类型 方法名(参数列表) {
        // 函数体
    }

    // 1.3）定义构造函数
    [访问说明符]  类名(参数列表){
        // 对象的初始化操作
    }
}

// 2）定义接口
package 包;
import 引用的包;

[访问说明符] interface 接口名{
    // 2.1）声明方法（通常省略：public abstract）
    public abstract 返回类型 方法名(参数列表);

    // 2.2）定义静态常量（通常省略：public static final）
    public static final 数据类型 变量名 = 值;
}
