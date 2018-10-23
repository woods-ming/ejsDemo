/**
 * 两种抛异常的场景：
 * 1.主动抛异常，在代码块中 
 * 2.函数内部有异常，但函数不想处理，继续抛出
 */
public class ThrowException{
    public void throwBlockException() throws Exception{ // 2.函数不想处理，声明一下就行
        // 1.程序员 手动抛异常
        throw new Exception("心情烦躁，抛个异常");
    }

    public static void main(String[] args){
        ThrowException demo = new ThrowException();

        // 3.使用的函数可能抛异常，写个预案处理一下
        try{
            demo.throwBlockException();
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("好吧，我来安慰你");
        }
    }
}