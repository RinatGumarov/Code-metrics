CREATE TABLE scms_Roles(
scms_RID bigint unsigned NOT NULL auto_increment,
scms_RoleName varchar(30) UNIQUE NOT NULL,
scms_RoleDesc varchar(100) NOT NULL,
PRIMARY KEY(scms_RID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE scms_Roles AUTO_INCREMENT = 1001;

insert into scms_Roles(scms_RoleName,scms_RoleDesc) values ('ROOT','This group can be compared to the administrator group on most systems');
insert into scms_Roles(scms_RoleName,scms_RoleDesc) values ('USERS','This group can be compared to the users group on most systems');

CREATE TABLE scms_Users (
scms_UID bigint unsigned NOT NULL auto_increment,
scms_RID bigint unsigned NOT NULL,
scms_uName varchar(20) UNIQUE NOT NULL,
scms_uLogin varchar(128) NOT NULL,
scms_uLoginAge int NOT NULL DEFAULT 100,
scms_uInvalidLogins int DEFAULT 0 NOT NULL,
scms_PasswdRequest varchar(256) NULL DEFAULT '',
scms_PasswordRequestTime timestamp NULL,
scms_UnlocKey varchar(256) NULL DEFAULT '',
scms_uLockedTime timestamp NULL,
scms_uEmail varchar(50) NOT NULL,
PRIMARY KEY(scms_UID),
KEY scms_RID (scms_RID),
CONSTRAINT scms_Users_RID FOREIGN KEY(scms_RID) REFERENCES scms_Roles(scms_RID) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

#insert into scms_Users(scms_RID,scms_uName,scms_uLogin,scms_uEmail) values (1001,'root',AES_ENCRYPT('HackScms2010','scms_ENCRYPTIONKEY_~'),'root@scms.za.net');
#insert into scms_Users(scms_RID,scms_uName,scms_uLogin,scms_uEmail) values (1002,'demo',AES_ENCRYPT('LetMeIns1d3','scms_ENCRYPTIONKEY_~'),'demo@scms.za.net');

CREATE TABLE scms_Sessions (
scms_SID varchar(64) NOT NULL,
scms_UID bigint unsigned NULL default 0,
scms_sStatus enum('NotAuth','Active','Expired','Destroyed','Invalid') DEFAULT 'NotAuth' NOT NULL,
scms_sCreated timestamp NULL,
scms_sLastAccess timestamp NULL,
scms_sDestroyed timestamp NULL,
scms_SecureSession tinyint(1) DEFAULT 0,
scms_sIP varchar(15) NOT NULL,
scms_sX_FWD_IP varchar(15) NULL,
scms_sUserAgent varchar(200) NULL,
scms_sDATA TEXT NULL,
PRIMARY KEY(scms_SID)
)ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE scms_Logins (
scms_LID bigint unsigned NOT NULL auto_increment,
scms_SID varchar(64) NOT NULL,
scms_lTried timestamp default CURRENT_TIMESTAMP,
scms_lSuccess tinyint(1) NOT NULL DEFAULT 0,
PRIMARY KEY(scms_LID)
)ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE scms_Permissions (
scms_PermID bigint unsigned NOT NULL auto_increment,
scms_RID bigint unsigned NOT NULL,
TableName varchar(30) NOT NULL,
SelectPerm tinyint(1) NOT NULL DEFAULT 0,
InsertPerm tinyint(1) NOT NULL DEFAULT 0,
UpdatePerm tinyint(1) NOT NULL DEFAULT 0,
DeletePerm tinyint(1) NOT NULL DEFAULT 0,
PRIMARY KEY (scms_PermID),
KEY scms_RID (scms_RID),
CONSTRAINT scms_Permissions_RID FOREIGN KEY(scms_RID) REFERENCES scms_Roles(scms_RID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

insert into scms_Permissions (scms_RID,TableName,SelectPerm,InsertPerm,UpdatePerm,DeletePerm) values (1001,"scms_Roles",1,1,1,1),(1001,"scms_Users",1,1,1,1),(1001,"scms_Sessions",1,1,1,1),(1001,"scms_Logins",1,1,1,1);

#GRANT ALL ON scms.* TO 'scms'@'localhost' IDENTIFIED BY 'scms_pass';
#FLUSH PRIVILEGES;