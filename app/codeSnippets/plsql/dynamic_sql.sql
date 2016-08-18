declare
  V_SQL VARCHAR2(2000);
begin
  V_SQL := 'Insert into Logs(:1, 常量)';
  execute immediate V_SQL
  using 变量;
end;