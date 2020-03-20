drop TABLE USER_TB;

CREATE TABLE USER_TB
(
    USER_CODE             INT AUTO_INCREMENT NOT NULL ,
    NAME                  VARCHAR(100)  NULL ,
    ID                    VARCHAR(100)  NULL ,
    PWD                   VARCHAR(200)  NULL ,
    PHONE                 VARCHAR(20)  NULL ,
    MAIL                  VARCHAR(100)  NULL DEFAULT 'N',
    REG_TIME datetime default sysdate() NULL,
    UPDATE_TIME datetime default sysdate() NULL,
    PRIMARY KEY (USER_CODE)
);


INSERT INTO USER_TB(NAME, ID, PWD, PHONE, MAIL) VALUES('노유림', 'yl','1111','010-3450-2766','yyj2766@naver.com');
INSERT INTO USER_TB(NAME, ID, PWD, PHONE) VALUES('이상욱', 'sw','2222','010-3684-4134');
INSERT INTO USER_TB(NAME, ID, PWD, PHONE) VALUES('채아담', 'ad','3333','010-9896-8245');
INSERT INTO USER_TB(NAME, ID, PWD, PHONE) VALUES('이소현', 'sh','4444','010-9443-9232');

SELECT * FROM USER_TB;