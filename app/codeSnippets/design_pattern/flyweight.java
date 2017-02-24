// 抽象类
public abstract class FlyWeight
{
  abstract void operation(外蕴状态 state);
}

// 享元类
public class ConcreteFlyweight extends FlyWeight
{
  protected 内蕴状态 _state;
  public ConcreteFlyweight(内蕴状态 state)
  {
    _state = state;
  }

  public override void operation(外蕴状态 state) { }
}

// 享元工厂
public class FlyWeightFactory
{
  private static Singleton uniqueInstance = new Singleton();
  private FlyWeightFactory() {}
  public static FlyWeightFactory getInstance() {
    return uniqueInstance;
  }

  private Hashtable flyweights = new Hashtable();
  public FlyWeight getFlyWeight(Object key) {
    FlyWeight flyweight = (FlyWeight) flyweights.get(key);

    if(flyweight == null) {
      flyweight = new ConcreteFlyweight();
      flyweights.put(key, flyweight);
    }
    return flyweight;
  }
}


// 客户程序
public class Client {

  public static void main(String[] args) {
    FlyWeightFactory factory = new FlyWeightFactory.getInstance();              
    
    FlyWeight flyWeight1 = factory.getFlyWeight("key1");   
    FlyWeight flyWeight2 = factory.getFlyWeight("key2");   
    FlyWeight flyWeight3 = factory.getFlyWeight("key1");   

    System.out.println(flyWeight1 == flyWeight3 ? "共享同一个对象" : "不同的对象")
  }
}