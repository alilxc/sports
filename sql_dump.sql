create table sys_project_sign(	
	id int UNSIGNED AUTO_INCREMENT,
	create_time BIGINT,
	update_time BIGINT,
	sys_user_sid VARCHAR(32),
	username VARCHAR(100),
	group_name VARCHAR(128),
	project_id VARCHAR(64),
	team_type VARCHAR(32),
	score DOUBLE(5,2),
	place VARCHAR(32),
	ext VARCHAR(1024),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



create table sys_grouping_info(
	id int UNSIGNED AUTO_INCREMENT,
	create_time BIGINT,
	update_time BIGINT,
	competition_id int,
	project_id varchar(64),
	team_type VARCHAR(32),
	competitors int,
	records int,
	printed int,
	ext VARCHAR(1024),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;