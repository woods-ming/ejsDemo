<!-- 本地文件 -->
FileSystemXmlApplicationContext context = 
    new FileSystemXmlApplicationContext("F:/WorkSpace/appContext.xml");


<!-- ClassPath -->
String springXmlpath = "classpath*:spring-*.xml";
ClassPathXmlApplicationContext context = 
    new ClassPathXmlApplicationContext(springXmlpath.split("[,\\s]+"));


<!-- Web应用 -->
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<servlet>
    <servlet-name>context</servlet-name>
    <servlet-class>org.springframework.web.context.ContextLoaderServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>