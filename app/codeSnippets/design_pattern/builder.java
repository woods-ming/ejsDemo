// 产品
public class Product {
    private String part1;
    private String part2;

    public String getPart1() {
        return part1;
    }
    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }
    public void setPart2(String part2) {
        this.part2 = part2;
    }
}

// 零件工厂
public abstract class Builder {
    public void buildPart1();
    public void buildPart2();
}
public class ConcreteBuilder1 extends Builder {
    public void buildPart1() {
 　　　 return "编号：11";
    }

    public void buildPart2() {
 　　　 return "名称：12";
    }
}
public class ConcreteBuilder2 extends Builder {
    public void buildPart1() {
 　　　 return "编号：21";
    }

    public void buildPart2() {
 　　　 return "名称：22";
    }
}

// 组装者
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct() {
        Product product = new Product();
        product.setPart1(builder.buildPart1());
        product.setPart2(builder.buildPart2());

        return product;
    }
}


// 客户程序
public class Client {
 
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder2();   // 变化点       
        Director director = new Director(builder);
        Product product = director.construct();     // 代码稳定
    }
}