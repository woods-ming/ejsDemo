/**
 * 网站助手（类）
 */
public class WebsiteAssistant extends WebsiteUser implements reportable {
    public WebsiteAssistant(String accountInitial){
        super.account = accountInitial;
        super.password = "123456";
    }

    /* 实现接口（方法重写） */
    public void mailToCompany() {
        // 网站助手反馈问题的方式：给网站管理员发邮件（低优先级）
    }
    
    /* 方法重载 */
    public void mailToCompany(int priority) {
        // 网站助手反馈问题的方式：给网站管理员发邮件，并指定邮件优先级
    }
} 

public class Test{
    public void main(String[] args){
        /* 演示：动态多态性 */
        // admin和assistant都是网站用户
        WebsiteUser admin = new WebsiteAdministrator();
        WebsiteUser assistant = new WebsiteAssistant();
        
        // admin和assistant拥有不同的mailToCompany()行为
        admin.mailToCompany();
        assistant.mailToCompany();
        
        /* 演示：静态多态性 */
        // assistant拥有同名（但不同参数）的另一个方法
        assistant.mailToCompany(1);
    }
}