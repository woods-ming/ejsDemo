--用户：
  --创建：
  CREATE USER 用户名 IDENTIFIED BY 密码 

  --修改：
  ALTER USER 用户名 ...(属性设置)

  --删除：
  DROP USER 用户名 [cascade(同时删除该用户拥有的对象)]


--权限：
  --授予系统权限：
  GRANT 系统权限     TO 角色/用户 [WITH GRANT OPTION](传播权限)

  --授予对象权限：
  GRANT 权限 ON 对象 TO 角色/用户 (ALL PRIVILEGES：指代对象的所有权限)

  --授予角色：    
  GRANT 角色 TO 用户

  --回收权限：   
  REVOKE ... FROM ...


--事务控制：
  savepoint 保存点1;      -- 设置保存点
  ...                     -- 业务操作
  rollback to 保存点1;    -- 回滚到保存点
  ...                     -- 业务操作
  commit;                 -- 提交事务
