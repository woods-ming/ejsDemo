--基本结构：
  DECLARE
    -- 声明部分
  BEGIN
    -- 执行部分
  EXCEPTION
    -- 异常处理部分
  END


--数据类型：
  --数字：
  NUMBER BINARY_INTEGER

  --字符：
  VARCHAR2 NVARCHAR2 CHAR NCHAR

  --日期：
  DATE

  --布尔：
  BOOLEAN

  --定义：
  TYPE 类型名 IS RECORD { 字段1 ... };  --显式
  类型名 表名%ROWTYPE;                  --隐式
  TYPE 类型名 IS TABLE OF 数据类型 INDEX BY BINARY_INTEGER;  --集合类型


--变量定义：
  变量名 数据类型 := 初始值;


--游标：
  CURSOR 游标名 IS 集合  --声明游标
  OPEN 游标名            --打开游标
    LOOP
      FETCH 游标名 INTO 变量名    --提取游标
      -- 业务操作
      EXIT WHEN 游标名%NOTFOUND;
    END LOOP;
  CLOSE 游标名           --关闭游标
    

--控制结构：
  --分支1：   
  IF 条件1 THEN ...
  ELSIF 条件2 THEN ...
  ELSE ...
  END IF;
      
  --分支2：
  CASE 表达式
    WHEN 值1 THEN ...
    WHEN 值2 THEN ...
    ELSE ...
  END;
      
  --循环1：
  LOOP
    ...
    EXIT WHEN 条件
  END LOOP;

  --循环2：
  WHILE 条件 LOOP
    ...
  END LOOP;
  
  --循环3：
  FOR 变量 IN 集合 LOOP
    ...
  END LOOP;

  --抛异常：
  RAISE_APPLICATION_ERROR(-20001,'异常说明');
  RAISE 异常;


--动态SQL：
  declare
    V_SQL VARCHAR2(2000);
  begin
    V_SQL := 'Insert into Logs(:1, 常量)';
    execute immediate V_SQL
      using 变量;
  end;
S
