# CGNTV Camera Equipment Register

CGNTV 카메라 장비 관리대장 프로그램입니다.

<br><br>
# 개발 환경

- Java 1.8
- Spring 3.1.1
- MyBatis
- JSP
- MariaDB 10.2.32
- Tomcat 8.0.9


<br><br>

# DB 세팅

## Database 생성
- create database cer;
<br>

## 관리자 USER 생성 및 권한 부여
- create user 'cer_admin'@'%' identified by '####';
- grant all privileges on cer.* to 'cer_admin'@'%';
- flush privileges;
<br>

## 테이블 생성 
- 유저 테이블<br>
CREATE TABLE 'usr' (<br>
  'usr_id' int(11) NOT NULL AUTO_INCREMENT,<br>
  'usr_email' varchar(100) NOT NULL,<br>
  'usr_pw' varchar(45) DEFAULT NULL,<br>
  'usr_grant' varchar(45) DEFAULT NULL,<br>
  'crt_dae' datetime DEFAULT CURRENT_TIMESTAMP,<br>
  'upt_dat' datetime DEFAULT CURRENT_TIMESTAMP,<br>
  'act_flg' bit(1) DEFAULT b'1',<br>
  'del_flg' bit(1) DEFAULT b'0',<br>
  PRIMARY KEY ('usr_id')<br>
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='회원 테이블';<br><br>
