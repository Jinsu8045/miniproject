-- CONNECTION: url=jdbc:oracle:thin:@//localhost:1521/XE
-- New script in localhost.
-- Connection Type: dev 
-- Url: jdbc:oracle:thin:@//localhost:1521/XE
-- workspace : C:\workspace\multi\03_DB
-- Date: 2024. 5. 12.
-- Time: 오후 6:22:03

CREATE USER MINI IDENTIFIED BY mini;
GRANT CONNECT, RESOURCE, DBA TO MINI;

-- TABLE 생성

CREATE TABLE MEMBERS(
    MEMBER_NUM VARCHAR2(100)  PRIMARY KEY,
    ID VARCHAR2(100) NOT NULL,
    PW VARCHAR2(100) NOT NULL,
    NAME VARCHAR2(100),
    EMAIL VARCHAR2(100),
    ADMIN NUMBER
    );
   
--DROP TABLE MEMBERS ;

CREATE TABLE CARS(
	CAR_NUM VARCHAR2(100) PRIMARY KEY,
	CAR_NAME VARCHAR2(100) NOT NULL,
	CAR_CATEGORY VARCHAR2(100) NOT NULL,
	CAR_FEATURE VARCHAR2(100) NOT NULL,
	CAR_PREF_COMFORT NUMBER NOT NULL,
	CAR_PREF_WEIGHT NUMBER NOT NULL,
	CAR_PREF_PASSENGER NUMBER NOT NULL
--	(SELECT REVIEW_NUM FROM REVIEWS WHERE  )AS 별점 총합/리뷰개수 추가하기!
	);

CREATE TABLE PRODUCTS(
	PRODUCT_NUM VARCHAR2(100) PRIMARY KEY,
	CAR_NUM VARCHAR2(100) REFERENCES CARS(CAR_NUM),
	PRODUCT_STATUS VARCHAR2(100),
	PRODUCT_PRICE NUMBER NOT NULL,
	PRODUCT_AVAILABLE NUMBER NOT NULL
	);

CREATE TABLE ORDERS(
	ORDER_NUM VARCHAR2(100) PRIMARY KEY,
	MEMBER_NUM VARCHAR2(100) REFERENCES MEMBERS(MEMBER_NUM),
	CAR_NUM VARCHAR2(100) REFERENCES CARS(CAR_NUM),
	ORDER_STATUS NUMBER NOT NULL,
	ORDER_REFUND_REQUEST NUMBER NOT NULL,
	ORDER_REFUND_COMPLETE NUMBER NOT NULL
	);

CREATE TABLE REVIEWS(
    REVIEW_NUM NUMBER PRIMARY KEY,
    ORDER_NUM VARCHAR(100) REFERENCES ORDERS(ORDER_NUM),

--   	SELECT CAR_NAME FROM ORDERS //보완필요할지도?


    RATING NUMBER NOT NULL,
    TITLE VARCHAR2(100) NOT NULL,
    CONTENTS VARCHAR2(1000) NOT NULL
    );

CREATE TABLE PRESETS(
	PRESET_NUM VARCHAR2(100) PRIMARY KEY,
	MEMBER_NUM VARCHAR2(100) REFERENCES MEMBERS(MEMBER_NUM),
	PRESET_COMFORT NUMBER NOT NULL,
	PRESET_WEIGHT NUMBER NOT NULL,
	PRESET_PASSENGER NUMBER NOT NULL
	);


-- SEQUENCE 생성

CREATE SEQUENCE MEMBER_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE REVIEW_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE CAR_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE PRODUCT_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE PRESET_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE ORDER_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


INSERT INTO MEMBERS values ('M'||MEMBER_NUM_SEQ.NEXTVAL,'admin','admin','관리자','admin@gmail.com',1);

COMMIT;







