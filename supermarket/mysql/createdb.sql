--createdb.sql
--Andrew Patronite
--October 20, 2013
--Creates the initial MySql database used by Hibernate
 
--cd /opt/local/lib/mysql5/bin
--./mysql -u root -prootpassword

CREATE DATABASE jama;
USE jama;
GRANT ALL ON jama.* TO 'jamauser'@'localhost' IDENTIFIED BY 'jamapassword';

--./mysql -u jamauser -pjamapassword