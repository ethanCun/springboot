SELECT * FROM sys_permission;
SELECT * FROM user_info;
SELECT * FROM sys_role;

SELECT * FROM sys_user_role;
SELECT * FROM sys_role_permission;

UPDATE sys_user_role SET role_id =3 WHERE uid=1;

drop VIEW if EXISTS `zy`;
CREATE view `zy` as (

	SELECT p.permission as 'pp'
	FROM sys_permission p
	LEFT JOIN sys_role_permission rp on rp.permission_id=p.id 
	LEFT JOIN sys_role r on r.id=rp.role_id
	LEFT JOIN sys_user_role ur ON ur.role_id= r.id
	LEFT JOIN user_info u on u.uid= ur.uid
-- 	WHERE u.username='admin'
	WHERE r.role='vip'
)

SELECT * FROM zy;


DROP VIEW IF EXISTS allroles;
CREATE VIEW allroles as (
	
	SELECT r.* 
	FROM sys_role r 
	LEFT JOIN sys_user_role ur ON ur.role_id=r.id
	LEFT JOIN user_info u ON u.uid=ur.uid
	WHERE u.username='admin'
)

SELECT * FROM allroles;