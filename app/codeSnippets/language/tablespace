--创建表空间：
  CREATE TABLESPACE 表空间
    datafile 'E:\oracle\oradata\student\stud01.dbf' size 100M,
         'E:\oracle\oradata\student\stud02.dbf' size 100M
    default storage(initial 10M   -- 初始大小
            next 10M              -- 初始区间填满后，第2个区间大小
            minextents 1          -- 初始分配的区间个数
            maxextents 10         -- 最大分配的区间个数
            pctincrease 20)       -- 当再填满时，按照20%的增长速率分配区间大小
    online;

--增加数据文件：
  ALTER TABLESPACE 表空间
    add datafile 'E:\oracle\oradata\student\stud03.dbf' size 100M;
  
--修改数据文件大小：
  ALTER DATABASE 数据库
    datafile 'E:\oracle\oradata\student\stud03.dbf' resize 500M;
      
--删除数据文件：
  SHUTDOWN normal;                  -- 关闭数据库
  del 'E:\oracle\oradata\student\stud03.dbf'    -- 删除数据文件


--归档历史数据并回收相应空间：
  -- 1.建个临时容器（表空间+数据文件）
  CREATE TABLESPACE 历史表空间
      datafile 'E:\oracle\oradata\student\stud_history01.dbf' size 100M
      online;           
  -- 2.把历史数据转移进来
  CREATE TABLE 历史表
    as SELECT * FROM 原数据表 
    tablespace 历史表空间;   
  DROP TABLE 原数据表;      
  -- 3.把临时容器的数据文件备份起来
  -- 4.删除临时容器（表空间+数据文件）
  