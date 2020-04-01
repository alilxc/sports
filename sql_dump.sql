create table sys_project_sign(	
	id int UNSIGNED AUTO_INCREMENT,
	create_time BIGINT,
	update_time BIGINT,
	order_id int,
	sys_user_sid VARCHAR(32),
	username VARCHAR(100),
	competition_id BIGINT,
	group_name VARCHAR(128),
	project_id VARCHAR(64),
	project_name VARCHAR(128),
	team_type VARCHAR(32),
	score DOUBLE(5,2),
	place VARCHAR(32),
	ext VARCHAR(1024),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table sys_project(
	id int UNSIGNED AUTO_INCREMENT,
	create_time BIGINT,
	update_time BIGINT,
	active_start BIGINT,
	active_finish BIGINT,
	organization VARCHAR(128),
	name VARCHAR(100),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



create table sys_grouping_info(
	id int UNSIGNED AUTO_INCREMENT,
	create_time BIGINT,
	update_time BIGINT,
	competition_id int,
	project_id varchar(64),
	project_name varchar(256),
	team_type VARCHAR(32),
	competitors int,
	records int,
	printed int,
	ext VARCHAR(1024),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table sys_competition_group(
	id int UNSIGNED AUTO_INCREMENT,
	create_time BIGINT,
	update_time BIGINT,
	competition_id int,
	place VARCHAR(32),
	ext VARCHAR(1024),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table sys_user(
	id int UNSIGNED AUTO_INCREMENT,
	create_time BIGINT,
	update_time BIGINT,
	sid VARCHAR(64),
	`password` VARCHAR(64),
	type SMALLINT(6),
avatar VARCHAR(256),
frozen bit(1),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table sys_import_file(
	id int UNSIGNED AUTO_INCREMENT,
	create_time BIGINT,
	update_time BIGINT,
	competition_id BIGINT,
	file_name VARCHAR(256),
	ext VARCHAR(1024),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


delete from sys_project_sign;
delete from sys_grouping_info;
delete from sys_competition_group;


drop table sys_project_sign;
drop table sys_grouping_info;
drop table sys_competition_group;

create INDEX uk_competition_username ON
 sys_project_sign(competition_id, group_name, username);

 create UNIQUE INDEX uk_competition_place ON
 sys_competition_group(competition_id, place);

