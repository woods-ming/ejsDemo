declare
  记录 表%ROWTYPE;
  ...
  
  cursor 无参游标 is
    select * from 表;

  cursor 有参游标(参数 类型) is
    select * from 表 where 字段 = 参数;

begin
  open 有参游标(参数值);
    -- 自动取下一条记录，进行操作
    fetch 有参游标 into 记录;
  close 有参游标;
  
  ---------------------------------

  IF NOT 无参游标%ISOPEN THEN
    open 无参游标;
  END IF;

  LOOP
    -- 自动取下一条记录，进行操作
    fetch 无参游标 into 记录;

    IF 无参游标%NOTFOUND THEN
      EXIT;

  END LOOP;

  IF 无参游标%ISOPEN THEN
    close 无参游标;
  END IF;

end;