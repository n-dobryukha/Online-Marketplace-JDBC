-- USER SQL
CREATE USER MARKETPLACE IDENTIFIED BY marketplace 
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";

-- QUOTAS
ALTER USER MARKETPLACE QUOTA UNLIMITED ON USERS;

-- ROLES
GRANT "CONNECT" TO MARKETPLACE;

-- SYSTEM PRIVILEGES
GRANT DROP ANY TRIGGER TO MARKETPLACE;
GRANT CREATE TRIGGER TO MARKETPLACE;
GRANT DROP PROFILE TO MARKETPLACE;
GRANT CREATE SEQUENCE TO MARKETPLACE;
GRANT ALTER PROFILE TO MARKETPLACE;
GRANT CREATE TABLE TO MARKETPLACE;
GRANT CREATE PROCEDURE TO MARKETPLACE;
GRANT DROP ANY TABLE TO MARKETPLACE;
GRANT DROP ANY VIEW TO MARKETPLACE;
GRANT ALTER ANY PROCEDURE TO MARKETPLACE;
GRANT ALTER ANY TRIGGER TO MARKETPLACE;
GRANT ALTER ANY TABLE TO MARKETPLACE;
GRANT CREATE VIEW TO MARKETPLACE;
GRANT DROP ANY PROCEDURE TO MARKETPLACE;
GRANT CREATE SESSION TO MARKETPLACE;
GRANT DELETE ANY TABLE TO MARKETPLACE;

