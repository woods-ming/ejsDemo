public class Assertion{
    public static void main(String[] args){
        assert 1 == 2 : "如果未通过测试：即 1!=2，就显示本条信息";
        // assert 1 == 2; // 也可以省略提示信息，只抛出Error
    }
}