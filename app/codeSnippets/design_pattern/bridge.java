// 实现部分（一组可相互替换的实现逻辑）  
public interface Behavior  
{  
   abstract void execute();  
}  
public class Behavior1 implements Behavior  
{  
   public override void execute() {}  
}  
public class Behavior2 implements Behavior  
{  
   public override void execute() {}  
}  

// 抽象部分（扩展属性） 
public abstract class Bridge  
{  
   protected String _property1;

   private Behavior _behavior;  
   public Bridge(Behavior behavior)  
   {  
      _behavior = behavior;  
   }  
   public void do()
   {
      _behavior.execute();
   }  
}  
public class BridgeA extends Bridge  
{  
   protected String _property2;  
}  
public class BridgeB extends Bridge  
{  
   protected String _property3;
}  


// Client  
public class Client  
{  
   public static void Main()  
   {  
      Behavior behavior = new Behavior1();    // 变化点：行为
      Bridge bridge = new BridgeA(behavior);  // 变化点：属性

      bridge.do();            // 代码稳定
   }  
}  