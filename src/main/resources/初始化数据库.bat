::echo off ::

@echo off

echo ��ʼִ�����ݿ�ű�...

for %%i in (sql\*.sql) do (

echo ����ִ�� %%i ���Ժ�...

echo set names utf8;>all.sql

echo source %%i>>all.sql

mysql -u root -p123456 --max_allowed_packet=1048576 --net_buffer_length=16384 < all.sql

echo %%i ִ����ϡ�

)

del all.sql

echo ���нű�ִ����ϡ�

pause