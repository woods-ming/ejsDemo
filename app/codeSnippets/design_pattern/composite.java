// 统一接口 
public abstract class Component  
{  
   private String _name;
   public class Component(String name)
   {
      _name = name;
   }

   abstract void operation();   
   abstract void add(Component component);  
   abstract void remove(Component component);  
   abstract Component getChild(int index);  
}  

// 元素  
public class Leaf extends Component  
{  
   public override void operation() {}  
   public override void add(Component component) 
   {
      throw new UnsupportedOperationException();
   }
   public override void remove(Component component)  
   {  
      throw new UnsupportedOperationException();
   }  
   public override Component getChild(int index) 
   {
      throw new UnsupportedOperationException();
   }  
}  

// 容器 
public class Composite extends Component  
{  
   ArrayList<Component> _components = new ArrayList<Component>();  
   public void operation()   
   {  
      foreach(Component component in _components)  
      {  
         component.operation();  
      }  
   }  
   public override void add(Component component) 
   {
      _components.add(menuComponent);
   }
   public override void remove(Component component)  
   {  
      _components.remove(menuComponent);
   }  
   public override Component getChild(int index) 
   {
      return _components.get(index);
   }
}  


// Client  
public class Client  
{  
   public static void Main()  
   {  
      Component woods = new Composite("森林");  
      Component tree1 = new Leaf("松树");  
      Component tree2 = new Leaf("柏树"); 

      woods.Add(tree1);  
      woods.Add(tree2);  
      woods.operation();   // 将容器对象的操作递归到基本对象上
   }  
}  