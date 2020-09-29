#Create Databases
CREATE DATABASE security_dev_db
CREATE DATABASE security_prod_db

#Create Database service accounts
CREATE USER 'security_dev_user'@'localhost' IDENTIFIED BY 'lesley212';
CREATE USER 'security_prod_user'@'localhost' IDENTIFIED BY 'lesley212';
CREATE USER 'security_dev_user'@'%' IDENTIFIED BY 'lesley212';
CREATE USER 'security_prod_user'@'%' IDENTIFIED BY 'lesley212';

#Database Grants
GRANT SELECT ON security_dev.* to 'security_dev_user'@'localhost';
GRANT INSERT ON security_dev.* to 'security_dev_user'@'localhost';
GRANT UPDATE ON security_dev.* to 'security_dev_user'@'localhost';
GRANT DELETE ON security_dev.* to 'security_dev_user'@'localhost';
GRANT SELECT ON security_prod.* to 'security_prod_user'@'localhost';
GRANT INSERT ON security_prod.* to 'security_prod_user'@'localhost';
GRANT UPDATE ON security_prod.* to 'security_prod_user'@'localhost';
GRANT DELETE ON security_prod.* to 'security_prod_user'@'localhost';
GRANT SELECT ON security_dev.* to 'security_dev_user'@'%';
GRANT INSERT ON security_dev.* to 'security_dev_user'@'%';
GRANT UPDATE ON security_dev.* to 'security_dev_user'@'%';
GRANT DELETE ON security_dev.* to 'security_dev_user'@'%';
GRANT SELECT ON security_prod.* to 'security_prod_user'@'%';
GRANT INSERT ON security_prod.* to 'security_prod_user'@'%';
GRANT UPDATE ON security_prod.* to 'security_prod_user'@'%';
GRANT DELETE ON security_prod.* to 'security_prod_user'@'%';