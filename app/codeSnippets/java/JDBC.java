/* 常用套路 */
// 1）加载驱动
Class.forName("com.mysql.jdbc.Driver");

// 2）建立数据库连接
// sqlserver的字符串格式（jdbc:sqlserver://127.0.0.1\\SQLEXPRESS;databaseName=数据库名称）
Connection conn = DriverManager.getConnection("jdbc:mysql://ip:port/数据库名称?useSSL=true", user, password);

// 3）预编译sql语句，填充参数
PreparedStatement ps= conn.prepareStatement("sql语句");
ps.setObject(参数位置[从1开始], 参数值);

// 4）发送sql语句
// 4.1）增删改（写）：
int affectedRows = ps.executeUpdate();

// 4.2）查询（读）：
ResultSet rs = ps.executeQuery();
while(rs.next()) {
    数据类型 属性 = rs.get数据类型("字段名称");
}
rs.close();

// 5）关闭数据库连接
ps.close();
conn.close();


/* 其它高级用法 */
// 1）批处理：
psDetail.addBatch();
psDetail.executeBatch();

// 2）事务
conn.setAutoCommit(false);
conn.commit();
conn.rollback();

// 3）获取自增长的主键
PreparedStatement ps= conn.prepareStatement("sql语句", Statement.RETURN_GENERATED_KEYS);
ResultSet rs = ps.getGeneratedKeys();
 