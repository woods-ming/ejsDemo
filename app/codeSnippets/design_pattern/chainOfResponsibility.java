// 示例请求类（描述请求）  
public class Request  {  
   private int _score;        // 问题难度值  
   private string _question;  // 具体问题  

   public Request(int score, string question)  
   {  
      _score = score;  
      _question = question;  
   }  

   public int Score  {  
      get { return _intScore; }  
   }  
   public string Question  {  
      get { return _strQuestion; }  
   }  
} 

// 处理请求的接口  
interface IRequestHandler  {  
   bool handlerRequest(Request request);

   void setSuccessor(RequestHandler successor);
}  
// 职责链基类
public abstract class RequestHandler implements IRequestHandler{  
   private RequestHandler _successor = successor;

   bool handleRequest(Request request){
      bool handled = this.tryHandlerRequest(request);
      if(!handled && _successor != null){
         // 失败后有人接盘
         return _successor.handlerRequest(request);
      }

      return handled; // 成功或无人接盘
   }

   public void setSuccessor(RequestHandler successor){
      _successor = successor;
   }

   protected abstract tryHandleRequest(Request request);

}  

// 具体职责类1  
public class ImageHandler implements RequestHandler {  
   protected override void tryHandleRequest(Request request)  {  
      if(request.Score < 50) {
         System.out.println("ImageHandler处理了请求");      

         return true;
      }
       
      return false;
   }  
}
// 具体职责类2  
public class FileHandler implements RequestHandler {  
   protected override void tryHandleRequest(Request request)  {  
      if(request.Score < 90) {
         System.out.println("FileHandler处理了请求");      

         return true;
      }
       
      return false;
   }  
}

public class Client {  
   public static void Main() {  
      // 链条必须由使用者组织（因为它是零件，不可能事先知道自己的位置）
      RequestHandler handler1 = new ImageHandler();
      RequestHandler handler2 = new FileHandler();
      handler1.setSuccessor(handler2);

      Request request = new Request(40, "图片文件");
      bool handleResult = handler1.handleRequest(request);
   }  
}  

