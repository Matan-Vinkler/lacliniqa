sqlite3 db/backup.db < db/prepare-backup-db.sql

mysql -h localhost -P 3306 -u root -e "CREATE DATABASE `appdb`;"
mysql -h localhost -P 3306 -u root -p appdb < db/prepare-main-db.sql