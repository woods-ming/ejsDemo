/**
* 反馈能力（接口）
*/
public interface Reportable {
    void mailToCompany();
}

/**
 * 网站管理员（类）
 */
public class WebsiteAdministrator extends WebsiteUser implements Reportable {
    public WebsiteAdministrator() {
        super("Administrator", "123456");
    }

    /* 扩展数据 */
    private String secureEmail;

    /* 扩展方法 */
    public void resetPasswordForUser(String account){
        // 给其它用户，重置密码
    }
    
    /* 实现接口（方法重写） */
    public void mailToCompany() {
        // 网站管理员反馈问题的方式：给项目经理发邮件（高优先级）
    }
}