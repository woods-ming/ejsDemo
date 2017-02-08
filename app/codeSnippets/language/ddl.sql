--表：
  --定义：
  CREATE TABLE 表名
    (字段 数据类型 完整性约束,
      ... ...
    )

  --修改：
  ALTER TABLE 表名
      ADD 新字段 数据类型 完整性约束
      MODIFY 字段 数据类型

  --删除：
  DROP TABLE 表名 
    

--视图：
  --定义：
  CREATE OR REPLACE VIEW 视图名 AS SELECT ...

  --删除：
  DROP VIEW 视图名

  
--索引：
  --本质：[创建列的键值, rowid]，就像个目录
  --定义：
  CREATE OR REPLACE INDEX 索引名 ON 表名(索引字段1, 索引字段2, ...)

  --删除：
  DROP INDEX 索引名
    

--序列：
  --定义：
  CREATE SEQUENCE 序列名

  --删除：
  DROP SEQUENCE 序列名

  --使用：
  序列名.NEXTVAL
    

--同义词：
  --本质：就是取个别名
  --定义：
  CREATE OR REPLACE SYNONYM 同义词名 FOR 数据库对象

  --删除：
  DROP SYNONYM 同义词名


--过程：
  --定义：
  CREATE OR REPLACE PROCEDURE 过程名(变量名 IN 数据类型, ... , 变量名 OUT 数据类型) IS 

  --调用：
  EXECUTE 过程名(参数)

  --删除：
  DROP PROCEDURE 过程名

      
--函数：
  --定义：
  CREATE OR REPLACE FUNCTION 函数名(变量名 IN 数据类型, ... ) RETURN 数据类型 IS

  --调用：类似普通表达式
  --删除：
  DROP FUNCTION 函数名


--触发器：
  （本质上是面向对象的事件机制：事件发生后，自动调用）
  --定义：
  CREATE OR REPLACE TRIGGER 触发器名
      触发条件
      触发体

  --删除：
  DROP TRIGGER 触发器名

            
--程序包：
  --（当成一个类：变量和游标当成包内的全局变量，过程和函数当成方法）
  --定义说明部分：
  CREATE OR REPLACE PACKAGE 包名 IS

  --定义包体：    
  CREATE OR REPLACE PACKAGE BODY 包名 IS
  