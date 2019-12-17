DROP TABLE if exists Accounts;
DROP TABLE if exists Tellers;
DROP TABLE if exists Branches;
DROP TABLE if exists History;
create table if not exists Accounts(id bigint, first_name varchar(50), last_name varchar(50), PRIMARY KEY(id)) with "backups={{BACKUPS}}";
create table if not exists Tellers(id bigint, first_name varchar(50), last_name varchar(50), PRIMARY KEY(id)) with "backups={{BACKUPS}}";
create table if not exists Branches(id bigint, first_name varchar(50), last_name varchar(50), PRIMARY KEY(id)) with "backups={{BACKUPS}}";
create table if not exists History(id bigint, first_name varchar(50), last_name varchar(50), PRIMARY KEY(id)) with "backups={{BACKUPS}}";
drop index if exists H_1;
create index H_1 on History (id, first_name) with "backups={{BACKUPS}}";