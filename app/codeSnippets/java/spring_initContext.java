<!-- 本地文件 -->
ApplicationContext context = 
    new FileSystemXmlApplicationContext("F:\\WorkSpace\\appContext.xml");
spring_demo.Boy player = (spring_demo.Boy)context.getBean("boy1");


<!-- ClassPath -->
ApplicationContext context = 
    new ClassPathXmlApplicationContext("beans/MarryMakerBean.xml");
spring_demo.Girl player = (spring_demo.Girl)context.getBean("gril1");
