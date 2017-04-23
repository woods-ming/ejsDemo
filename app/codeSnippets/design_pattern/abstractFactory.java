// 产品
public abstract class ProductA { }
public class ProductA1 extends ProductA { }
public class ProductA2 extends ProductA { }

public abstract class ProductB { }
public class ProductB1 extends ProductB { }
public class ProductB2 extends ProductB { }

// 工厂
public abstract class Factory {
    abstract Product createProductA();
    abstract Product createProductB();
}
public class Factory1 extends Factory {
    public Product createProductA() {
 　　　 return new ProductA1();
    }
    public Product createProductB() {
 　　　 return new ProductB1();
    }
}
public class Factory2 extends Factory {
    public Product createProductA() {
 　　　 return new ProductA2();
    }
    public Product createProductB() {
 　　　 return new ProductB2();
    }
}


// 客户程序
public class Client {
 
    public static void main(String[] args) {
        Factory factory = new Factory2();               // 变化点
        
        ProductA productA = factory.createProductA();   // 代码稳定
        ProductB productB = factory.createProductB();  
    }
}
