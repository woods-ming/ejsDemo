// 产品
public abstract class Product { }
public class Product1 extends Product { }
public class Product2 extends Product { }

// 工厂
public abstract class Factory {
    abstract Product createProduct();
}
public class Factory1 extends Factory {
    public Product createProduct() {
 　　　 return new Product1();
    }
}
public class Factory2 extends Factory {
    public Product createProduct() {
 　　　 return new Product2();
    }
}


// 客户程序
public class Client {
 
    public static void main(String[] args) {
        Factory factory = new Factory2();           // 变化点
        
        Product product = factory.createProduct();  // 代码稳定
    }
}
