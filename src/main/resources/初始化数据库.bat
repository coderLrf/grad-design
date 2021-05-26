::echo off ::

@echo off

echo 开始执行数据库脚本...

for %%i in (sql\*.sql) do (

echo 正在执行 %%i 请稍后...

echo set names utf8;>all.sql

echo source %%i>>all.sql

mysql -u root -p123456 --max_allowed_packet=1048576 --net_buffer_length=16384 < all.sql

echo %%i 执行完毕。

)

del all.sql

echo 所有脚本执行完毕。

pause