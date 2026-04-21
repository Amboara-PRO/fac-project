create database "federation_db";

create user "federation_db_manager" with password '123456';

-- Grant all privileges
grant connect on database federation_db to federation_db_manager;

\c federation_db

    grant create on schema public to federation_db_manager;

alter default privileges in schema public grant select, insert, update, delete on tables to federation_db_manager;

alter default privileges in schema public grant usage, select, update on sequences to federation_db_manager;