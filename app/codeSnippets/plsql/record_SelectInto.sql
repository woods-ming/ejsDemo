declare
  变量 表%ROWTYPE;
begin
  select 表.字段1 into 变量.字段1 where id = 1;
  select 表.字段2 into 变量.字段2 where id = 2;
end;