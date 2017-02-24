// 子系统
import java.SubSystem;

public class Facade{
  // 隐藏原料、做菜细节

  // 仅暴露顾客关注的接口
  public String GetMenu() {}
  public void OrderFood(String foodName) {}
}


// Client  
public class Client  
{  
   public static void Main()  
   {  
      Facade restaurant = new Facade();  

      // 无需关注细节，使用起来更简单
      String menu = restaurant.GetMenu();
      restaurant.OrderFood("西红柿炒鸡蛋");
   }  
}  