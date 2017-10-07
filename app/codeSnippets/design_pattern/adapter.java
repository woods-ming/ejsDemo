// 旧接口  
interface AdapteeInterface  
{  
   void adaptedOperation();
}  
// 新接口  
interface AdapterInterface  
{  
   void operation();
}  

// 旧组件  
public class Adaptee implements AdapteeInterface
{  
   public void adaptedOperation()  
   {  
      System.out.println("adaptedOperation");       
   }  
}

// 适配器（调用已有组件，实现现有接口）  
public class Adapter implements AdapterInterface
{  
   private Adaptee _adaptee = new Adaptee();  

   public void operation()  
   {  
      _adaptee.adaptedOperation();    
      System.out.println("operation");      
   }  
}  

