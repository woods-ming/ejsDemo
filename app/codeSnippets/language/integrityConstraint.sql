--实体完整性：
  --主键：
  ALTER TABLE 表名 ADD/DROP CONSTRAINT 约束名 PRIMARY KEY(主键字段)


--参照完整性：
  --外键：
  ALTER TABLE 表名 ADD/DROP CONSTRAINT 约束名 FOREIGN KEY(外键字段) 
                                        REFERENCES 参照表(主键字段) ON DELETE CASCADE


--域完整性：(UNIQUE、NULL、CHECK)
  --非空：
  ALTER TABLE 表名 MODIFY 字段 NOT NULL

  --唯一：
  ALTER TABLE 表名 ADD/DROP CONSTRAINT 约束名 UNIQUE(字段名)

  --检查：
  ALTER TABLE 表名 ADD/DROP CONSTRAINT 约束名 CHECK(逻辑表达式)
