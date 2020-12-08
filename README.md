# CGNTV Camera Equipment Register

CGNTV 카메라 장비 관리대장 프로그램입니다.


# 개발 환경

- Java 1.8
- Spring 3.1.1
- MyBatis
- JSP
- MariaDB 10.2.32
- Tomcat 8.0.9



# DB 세팅


## Database 생성
- create database cer;


## 관리자 USER 생성 및 권한 부여
- create user 'cer_admin'@'%' identified by '####';
- grant all privileges on cer.* to 'cer_admin'@'%';
- flush privileges;


## 테이블 생성 
