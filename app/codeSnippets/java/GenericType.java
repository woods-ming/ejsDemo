// 1）定义泛型类（T只能是引用类型）
class 泛型类<T> {}

// 2）定义泛型方法
<T> 返回类型 泛型方法(形参) {}

// 3）使用 泛型类/泛型方法（在 构造函数/方法 后指定具体类型）
泛型类 对象 = new 泛型类<具体类型>();
xxx.泛型方法<具体类型>();

// 4）使用泛型通配符（参数赋值时，不指定具体类型，只给一个范围）
// T是类A的子类 / T实现了接口1
? extends 类A/接口1  
// T是类B的父类
? super 类B         

// 5）类型自动推断（偷懒）
// 特殊情况下，对于3）可以不指定具体类型；
// 这种情况是：构造函数/泛型方法 的参数中包含了所有定义的泛型参数（当我们给定实参时，编译器可以推断出实参类型，该类型就是泛型参数的值）
