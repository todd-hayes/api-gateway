-- Create Spring Security tables
-- Create database before is executed with the following commands
-- These are not needed if running database in a docker container
-- using docker compose
-- CREATE DATABASE IF NOT EXISTS api_gateway;
-- GRANT ALL ON SCHEMA public TO api_gw_role;
-- ALTER DATABASE api_gateway OWNER TO api_gw_role;

SET ROLE api_gw_role;

CREATE TABLE IF NOT EXISTS api_users(
   	user_name VARCHAR(100) UNIQUE NOT NULL,
   	email VARCHAR(100) UNIQUE NOT NULL,
   	password VARCHAR(100) NOT NULL,
	active BOOLEAN DEFAULT TRUE,
   	created_by VARCHAR(100) NOT NULL,
   	created_date TIMESTAMP NOT NULL,
   	last_modified_by VARCHAR(100),
   	last_modified_date TIMESTAMP,
   	PRIMARY KEY(user_name)
);

CREATE TABLE IF NOT EXISTS roles(
	user_name VARCHAR(100) NOT NULL,
   	role_name VARCHAR(20),
   	created_by VARCHAR(100) NOT NULL,
   	created_date TIMESTAMP NOT NULL,
   	last_modified_by VARCHAR(100),
   	last_modified_date TIMESTAMP,
   	PRIMARY KEY(user_name, role_name),
   	CONSTRAINT fk_user_name
       	FOREIGN KEY(user_name)
           	REFERENCES api_users(user_name)
);

INSERT INTO api_users(user_name, email, password, active, created_by, created_date)
VALUES
('thayes', 'thayes@secapp.com', '{noop}testpass', 'true', 'flyway', NOW()),
('admin', 'admin@secapp.com', '{noop}testpass', 'true', 'flyway', NOW()),
('root', 'root@secapp.com', '{noop}testpass', 'true', 'flyway', NOW());

INSERT INTO roles(user_name, role_name, created_by, created_date)
VALUES
('thayes', 'USER', 'flyway', NOW()),
('admin', 'USER', 'flyway', NOW()),
('admin', 'ADMIN', 'flyway', NOW()),
('root', 'USER', 'flyway', NOW()),
('root', 'ADMIN', 'flyway', NOW()),
('root', 'SUPER_ADMIN', 'flyway', NOW());
