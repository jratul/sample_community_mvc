CREATE TABLE c_user (
	id VARCHAR2(30) PRIMARY KEY,
	pwd VARCHAR2(200) NOT NULL,
	nickname VARCHAR2(30) NOT NULL,
	email VARCHAR2(100) NOT NULL,
	pic VARCHAR2(100),
	point NUMBER DEFAULT 0,
	regdate DATE DEFAULT SYSDATE
)