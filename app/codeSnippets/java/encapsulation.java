/**
* 网站用户（类）
*/
public class WebsiteUser {
    public WebsiteUser(String accountInitial, String passwordInitial){
        this.account = accountInitial;
        this.password = passwordInitial;
    }

    /* 隐藏数据 */
    protected String account;
    protected String password;

    /* 隐藏实现细节 */
    private boolean validateAccount(String accountInput){
        return account.trim().equalsIgnoreCase(accountInput);
    }
    private boolean validatePassword(String passwordInput){
        return password.equals(passwordInput)
    }

    /* 仅暴露必要的数据 */
    // 账号：能读取，不能修改
    public String getAccount(){
        return account;
    }
    // 密码：能修改，不能读取
    public String setPassword(String value){
        password = value;
    }

    /* 仅暴露必要的方法 */
    public boolean authentication(String accountInput, String passwordInput){
        return validateAccount(accountInput) && validatePassword(passwordInput);
    }
}
