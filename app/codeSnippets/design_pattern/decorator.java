public abstract class Component {
  protected String _property;

  public void operation(); 
}

// 扩展属性
public class Component1 extends Component {
  protected String _property1;
}
public class Component2 extends Component {
  protected String _property2;
}

// 扩展附加功能
public abstract class Decorator extends Component {
  protected Component _component;
  public Decorator(Component component)
  {
    _component = component;
  }

  public void operation() 
  {
    _component.operation();
  }
}
public class DecoratorA extends Decorator
{
  private void addedBehaviorA() {}
  public override void operation() 
  {
    addedBehaviorA();
    super.operation();
  }
}
public class DecoratorB extends Decorator
{
  private void addedBehaviorB() {}
  public override void operation() 
  {
    super.operation();
    addedBehaviorB();  
  }
}


// Client  
public class Client  
{  
   public static void Main()  
   {  
      // 变化点：属性
      Component car = new Component1();  
      Component bicycle = new Component2();  

      // 扩展点：附加功能
      Component 能导航定位的汽车 = new DecoratorA(car); 
      Component 有行车记录仪的自行车 = new DecoratorB(bicycle); 
   }  
}  