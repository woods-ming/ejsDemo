// 抽象类
public abstract class Subject 
{
  abstract void request();
}

// 真实类
public class RealSubject extends Subject 
{
  protected String _password;  // 敏感参数
  public String SetPassword 
  { 
    _password = value; 
  }

  public override void request() 
  { 
    // 真正干活的代码
  }
}

// 代理类
public class Proxy extends Subject 
{
  private RealSubject _realSubject;
  public Proxy()
  {
    _realSubject = new RealSubject();
    _realSubject.SetPassword("机密");
  }

  public override void request() {
    _realSubject.request();
  }
}


// Client  
public class Client  
{  
   public static void Main()  
   {  
      Subject proxy = new Proxy();  // 直接跟代理打交道
      proxy.request();
   }  
}  