// 原型类
public class ConcretePrototype implements Cloneable, Serializable {

    public ConcretePrototype() { }

    public Object clone(){
        Monkey temp = null;
        try {
            temp = (ConcretePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
            return temp;
        }
    }

    public  Object deepClone() throws IOException, ClassNotFoundException{
        // 将对象写到流里
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        // 从流里读回来
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        
        return ois.readObject();
    }   
}


// 客户程序
public class Client {
 
    public static void main(String[] args) {
        ConcretePrototype prototype1 = new ConcretePrototype();           
        // 对象初始化
        // ...

        ConcretePrototype prototype2 = prototype1.clone();  
        ConcretePrototype prototypeN = prototype1.deepClone(); 
    }
}