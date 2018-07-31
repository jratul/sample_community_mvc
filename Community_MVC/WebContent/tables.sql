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

SELECT *   
FROM (    
SELECT result1.*, ROWNUM rnum FROM (     
SELECT    num, writer, title, content, viewCount, TO_CHAR('YYYY.MM.DD AM HH:MI:SS', regdate) regdate        
FROM c_board_free            
ORDER BY num DESC) result1)   
WHERE rnum BETWEEN 1 AND 3

SELECT num, writer, title, content, viewCount, regdate
FROM c_board_free;