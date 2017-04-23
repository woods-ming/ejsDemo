// 产品
public abstract class Product { }
public class Product1 extends Product { }
public class Product2 extends Product { }

// 工厂
public class Factory {
    public Product createProduct() {
 　　　 return new Product1();  // 变化点
    }
}



// 客户程序
public class Client {
 
    public static void main(String[] args) {
    	// 代码稳定
        Factory factory = new Factory();           
        Product product = factory.createProduct();  
    }
}
