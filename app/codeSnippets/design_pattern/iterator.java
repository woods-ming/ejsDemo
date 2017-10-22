// jdk已经定义好的迭代器模式接口
// 1.有迭代能力（提供迭代器）
public interface Iterable<T> {
    Iterator<T> iterator();
}
// 2.迭代方式
public interface Iterator<T> {    
    boolean hasNext();    
    T next();    
    void remove();    
}   

// 家庭成员（子元素）  
public class FamilyMember  
{  
   private string _strName;  
   public FamilyMember(string strName)  
   {  
      _strName = strName;  
   }  
}  
// 家庭（集合类）  
public class Family : Iterable<FamilyMember>  
{  
   private FamilyMember[] _ary;  
   public Family(FamilyMember[] ary)  
   {  
      _ary = ary;  
   }  
   
   // 提供迭代器  
   public Iterator<FamilyMember> iterator()  
   {  
      return new Iterator<FamilyMember>(){
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        public boolean hasNext() {
            return cursor < _ary.length;
        }

        public E next() {
            return (FamilyMember) _ary[cursor++];
        }

        public void remove() {
            for(int i=cursor; i<_ary.length; i++) {
                _ary[i-1] = _ary[i];
            }
            cursor--;
        }

        };
   }  
}   
// Client  
public class App  
{  
   public static void Main()  
   {  
      // 构造家庭成员  
      FamilyMember XiaoHu = new FamilyMember("小胡");  
      FamilyMember XiaoXx = new FamilyMember("小X");  
      FamilyMember[] ary = new FamilyMember[]{XiaoHu, XiaoXx};  
      // 组成家庭  
      Family home = new Family(ary);  
      // 遍历家庭成员  
      for(FamilyMember member : Family)  
      {  
         System.out.println("家庭成员：" + member.ToString());  
      }  
   }  
}  