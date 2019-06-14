/**
 * 项目启动
 */
public void contextInitialized(ServletContextEvent sce)  { 
    ServletContext context = sce.getServletContext();

    try {
        Context jndiContext = new InitialContext();
        DataSource ds = (DataSource) jndiContext.lookup("java:/comp/env/jdbc/sqlserver/ServletCourse");
        context.setAttribute("JNDIDataSource", ds);
    } catch (NamingException e) {
        e.printStackTrace();
    }
    
}