一、逻辑备份/恢复：（利用实用程序，将数据库信息写入DMP文件；恢复时从DMP中读取信息）
  --数据泵：
    -- 1.创建 备份/恢复 用到的文件存储路径
    CREATE DIRECTORY 路径名 AS 'E:\oracle\backup'  
    -- 2.导出
    expdp 账号/密码 [参数 ...]
    -- 3.导入
    impdp 账号/密码 [参数 ...]


二、脱机备份/恢复：（在数据库关闭状态下，拷贝数据库文件；恢复是个逆过程）
  -- 1.关闭数据库
  SHUTDOWN immediate;
  -- 2.直接拷贝物理文件
  控制文件的路径：SELECT * FROM v$controlfile
  数据文件的路径：SELECT * FROM dba_data_files
  日志文件的路径：SELECT * FROM v$logfile
  -- 3.在数据库关闭状态下，将物理文件覆盖到原目录
  

三、联机备份/恢复：（在数据库打开状态下，使用RMAN技术备份和恢复）
  -- 1.切换到归档模式
  CONNECT SYS/密码 AS SYSDBA;
  SHUTDOWN immediate;
  STARTUP mount;
  ALTER database archivelog;
  SELECT log_mode FROM v$database;

  -- 2.创建恢复目录所用的表空间
  CONNECT SYS/密码 AS SYSDBA;
  ALTER database open;
  CREATE TABLESPACE RMAN表空间
        datafile 'E:\oracle\oradata\student\rman.dbf' size 500M
        online; 

  -- 3.创建RMAN用户并授权
  CONNECT SYS/密码 AS SYSDBA;
  CREATE USER rman IDENTIFIED BY 密码 DEFAULT TABLESPACE RMAN表空间 TEMPORARY TABLESPACE TEMP;
  GRANT CONNECT, RECOVERY_CATALOG_OWNER, RESOURCE TO rman;

  -- 4.创建恢复目录
    -- 4.1打开RMAN
    cd ...\product\11.1.0\db_1\bin;
    RMAN CATALOG rman/密码 target orc;
    -- 4.2指定表空间作为恢复目录
    CREATE CATALOG TABLESPACE RMAN表空间;

  -- 5.注册目标数据库
  REGISTER DATABASE;

  -- 6.使用RMAN进行备份
  run {
    allocate channel dev1 type disk;
    backup database;
    release channel dev1;
  }

  -- 7.查看备份
  list backup;

  -- 8.使用RMAN进行恢复
  run {
    allocate channel dev1 type disk;
    restore database;
    release channel dev1;
  }
  

四、自动备份/恢复：
  （使用闪回flashback技术，实现基于磁盘的备份与恢复）