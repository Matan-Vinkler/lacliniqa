#!/bin/bash

sqlite3 db/backup.db < db/prepare-db.sql
mysql -h localhost -P 3306 -u root -p appdb < db/prepare-main-db.sql