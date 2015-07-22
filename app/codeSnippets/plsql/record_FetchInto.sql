declare
  记录 表%ROWTYPE;
  ...
  
  cursor 游标(参数 数据类型) is
    select * from 表 where 字段1 = 参数;

begin
  open 游标(参数值);
    fetch 游标 into 记录;
  close 游标;
  
end;