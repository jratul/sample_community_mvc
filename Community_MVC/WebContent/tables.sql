CREATE TABLE c_user (
	id VARCHAR2(30) PRIMARY KEY,
	pwd VARCHAR2(200) NOT NULL,
	nickname VARCHAR2(30) NOT NULL,
	email VARCHAR2(100) NOT NULL,
	pic VARCHAR2(100),
	point NUMBER DEFAULT 0,
	regdate DATE DEFAULT SYSDATE
)

CREATE TABLE c_board_free(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB,
	viewCount NUMBER DEFAULT 0,
	regdate DATE DEFAULT SYSDATE
);

CREATE TABLE c_board_free_comment(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	content CLOB,
	postNum NUMBER NOT NULL,
	parentNum NUMBER DEFAULT 0,
	depth NUMBER DEFAULT 0,
	regdate DATE DEFAULT SYSDATE,
	likeCnt NUMBER DEFAULT 0,
	dislikeCnt NUMBER DEFAULT 0
)

CREATE SEQUENCE c_board_free_comment_seq;

DROP TABLE c_board_free_comment

SELECT *   
FROM (    
SELECT result1.*, ROWNUM rnum FROM (     
SELECT    num, writer, title, content, viewCount, TO_CHAR('YYYY.MM.DD AM HH:MI:SS', regdate) regdate        
FROM c_board_free            
ORDER BY num DESC) result1)   
WHERE rnum BETWEEN 1 AND 3

SELECT num, writer, title, content, viewCount, regdate
FROM c_board_free;

SELECT *
FROM c_user;

INSERT INTO c_board_free_comment
VALUES(1, '김구라', '안녕하세요', 1, 0,0, sysdate, 0, 0);

INSERT INTO c_board_free_comment
VALUES(2, '유건열', '안녕하세요', 1, 0,0, sysdate, 0, 0);

INSERT INTO c_board_free_comment
VALUES(3, '유건열', '하이', 1, 1, 1, sysdate, 0, 0);

INSERT INTO c_board_free_comment
VALUES(4, '건열', '오오',1, 1, 1, sysdate, 0, 0)

INSERT INTO c_board_free_comment
VALUES(5, '김구라', '반갑',1, 3, 2, sysdate, 0, 0)

INSERT INTO c_board_free_comment
VALUES(6, '건열', '히히',1, 3, 2, sysdate, 0, 0)

SELECT * FROM c_board_free_comment
START WITH parentNum = 0
CONNECT BY PRIOR num = parentNum
