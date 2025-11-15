/**
 * Author:  Brijesh K. Dhaker
 * Created: Jul 23, 2025
 */

---
--- mysql --user=root --password=paSSW0rd --host=mysqlserver.sandbox.net
---

create database IF NOT EXISTS CREATIVELIGHTS;


---
--- Add User
---
CREATE USER 'creativelights'@'%' IDENTIFIED BY 'paSSW0rd';
GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT, REFERENCES, RELOAD on *.* TO 'creativelights'@'%' WITH GRANT OPTION;
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'creativelights'@'%';
GRANT ALL PRIVILEGES ON *.* TO 'creativelights'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON CREATIVELIGHTS.* TO 'creativelights'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

SHOW GRANTS FOR 'creativelights'@'%';

---
--- Validate User
---
--- mysql --user=creativelights --password=paSSW0rd --host=mysqlserver.sandbox.net --database=CREATIVELIGHTS
---

--- Dupm database
---
--- docker exec mysqlserver sh -c 'mysqldump --user=root --password=$MYSQL_ADMIN_PASSWORD --routines --triggers --databases CREATIVELIGHTS' > sqls/CREATIVELIGHTS.sql
---

--- Restoring data from dump files
--- mysql --user=root --password="$MYSQL_ADMIN_PASSWORD"  < sqls/CREATIVELIGHTS.sql
--- docker exec -i mysqlserver sh -c 'exec mysql --user=root --password="$MYSQL_ADMIN_PASSWORD"' < sqls/CREATIVELIGHTS.sql

USE CREATIVELIGHTS;
show tables;
