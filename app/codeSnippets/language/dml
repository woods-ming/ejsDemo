--插入：
  INSERT INTO 表名(字段1, ...) vaules(值1, ...);
  INSERT INTO 表名(字段1, ...) 集合;
  
--复制表并插入数据：
  CREATE TABLE 表名 AS SELECT * FROM 某表 WHERE ...
    
--修改：
  UPDATE 表名 
     SET 字段1 = 值1
      ...
   WHERE 筛选条件;
   
--合并：
  MERGE INTO 表名
  USING (集合) T
  ON (T.字段 = 表名.字段)
  WHEN MATCHED THEN
    UPDATE SET 表名.字段1 = T.字段1, 
               ...
  WHEN NOT MATCHED THEN
    INSERT (字段1, ...)
    VALUES (T.字段1, ...)

--删除：
  DELETE FROM 表名 WHERE 筛选条件;
  TRUNCATE TABLE 表名;