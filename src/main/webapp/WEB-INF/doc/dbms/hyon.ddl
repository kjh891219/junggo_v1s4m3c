/*************************************
 *  Table Name: 회원 
*/
drop table member_test;

CREATE TABLE member_test(
    userid                            VARCHAR2(20)     NOT NULL PRIMARY KEY,
    mno                               NUMBER(6)        NOT NULL UNIQUE,
    pwd                               VARCHAR2(30)     NOT NULL,
    name                              VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL UNIQUE,
    tel                               VARCHAR2(14)     NOT NULL,
    zipcode                           VARCHAR2(5)          NULL,
    address1                          VARCHAR2(80)         NULL,
    address2                          VARCHAR2(50)         NULL,
    email                             VARCHAR2(100)    NOT NULL UNIQUE,
    mdate                             DATE             NOT NULL,
    auth                              VARCHAR2(20)     DEFAULT 'N'   NOT NULL,
    confirm                           CHAR(1)    DEFAULT 'N'     NOT NULL, -- 이메일 링크 클릭 여부, Y:클릭, N:클릭안함
    act                               CHAR(1)    NOT NULL,  -- M: 마스터, Y: 로그인 가능, N: 로그인 불가, H: 인증 대기
    droupout                          VARCHAR2(1)    DEFAULT 'N'     NOT NULL
);


INSERT INTO member_test(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('master', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member_test), '1234', '관리자', '관리자', 'abc@mail.com', '000-0000-0000', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N')






/**********************************/
/* Table Name: 카메라 */
/**********************************/
drop table camera;

CREATE TABLE CAMERA(
		ctno                          		NUMBER(6)		 NOT NULL		 PRIMARY KEY,
		category                      		VARCHAR2(20)		 NOT NULL,
		nickname                      		VARCHAR2(20)		 NOT NULL,
		passwd                        		VARCHAR2(10)		 NOT NULL,
		deal_way                      		VARCHAR2(20)		 NOT NULL,
		deal_code                     		VARCHAR2(20)		 NOT NULL,
		product_code                  		VARCHAR2(20)		 NOT NULL,
		hprice                        		NUMBER(15)		 DEFAULT 0		 NOT NULL,
		region                        		VARCHAR2(20)		 DEFAULT ''		 NOT NULL,
		tel                           		VARCHAR2(14)		 DEFAULT ''		 NOT NULL,
		email                         		VARCHAR2(100)		 DEFAULT ''		 NOT NULL,
		quantity                      		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		title                         		VARCHAR2(200)		 DEFAULT ''		 NOT NULL,
		content                       		VARCHAR2(4000)		 NOT NULL,
		purc_date                     		VARCHAR2(20)		 DEFAULT ''		 NOT NULL,
		wdate                         		DATE		 DEFAULT sysdate		 NOT NULL,
		cnt                           		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		file1                             VARCHAR2(100)        NULL ,
    file2                             VARCHAR2(50)         NULL ,
    size2                             NUMBER(9)        DEFAULT 0       NULL,
    file3                             VARCHAR2(100)        NULL ,
    file4                             VARCHAR2(50)         NULL ,
    size4                             NUMBER(9)        DEFAULT 0       NULL
);

alter table CAMERA add (userid varchar2(20) );
ALTER TABLE CAMERA 
ADD CONSTRAINT FK_CAMERA FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE CAMERA is '카메라';
COMMENT ON COLUMN CAMERA.ctno is '글번호';
COMMENT ON COLUMN CAMERA.category is '카테고리';
COMMENT ON COLUMN CAMERA.nickname  is '닉네임';
COMMENT ON COLUMN CAMERA.passwd is '비밀번호';
COMMENT ON COLUMN CAMERA.deal_way is '거래방법';
COMMENT ON COLUMN CAMERA.deal_code is '거래구분';
COMMENT ON COLUMN CAMERA.product_code is '상품구분';
COMMENT ON COLUMN CAMERA.hprice is '희망가격';
COMMENT ON COLUMN CAMERA.region is '지역';
COMMENT ON COLUMN CAMERA.tel is '전화번호';
COMMENT ON COLUMN CAMERA.email is '이메일';
COMMENT ON COLUMN CAMERA.quantity is '수량';
COMMENT ON COLUMN CAMERA.title is '제목';
COMMENT ON COLUMN CAMERA.content is '내용';
COMMENT ON COLUMN CAMERA.purc_date is '구입일';
COMMENT ON COLUMN CAMERA.wdate is '등록일자';
COMMENT ON COLUMN CAMERA.cnt is '조회수';

drop table camera;
/**
 * 카메라 테이블 리스트
 */
SELECT ctno, deal_code, title, hprice, deal_way, region, nickname 
FROM camera
ORDER BY ctno DESC

select * from camera order by ctno desc;
/**
 * 
 * 카메라 테이블 입력(멤버 테이블의 nickname과 userid를 가져온다)
 */


insert into CAMERA(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM CAMERA),
'일반디카', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '울산', '010-2222-3333', 'kkk@kkk.com', 1, 'DSLR팔아요', 'DSLR싸게팔아요', '2016년10월', (select userid from member_test where userid='master'));

insert into CAMERA(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM CAMERA),
'DSLR', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '부산', '010-2222-3333', 'kkk@kkk.com', 1, 'DSLR팔아요', 'DSLR싸게팔아요', '2016년10월', (select userid from member_test where userid='master'));

insert into CAMERA(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM CAMERA),
'필름카메라', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '인천', '010-2222-3333', 'kkk@kkk.com', 1, 'DSLR팔아요', 'DSLR싸게팔아요', '2016년10월', (select userid from member_test where userid='master'));


insert into CAMERA(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid, file1, file2, size2) 
   values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM CAMERA),
'필름카메라', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '인천', '010-2222-3333', 'kkk@kkk.com', 1, 'DSLR팔아요', 'DSLR싸게팔아요', '2016년10월', (select userid from member_test where userid='master'), 'ddd.jpg', 'ddd.jpg', 10);



select * from camera;



/**
 * 카메라 테이블 글조회
 */
select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from camera
where ctno = 1

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from camera
where category = 'DSLR'
/**
 * 
 * 조회수 증가
 */
update camera 
set cnt = cnt + 1
where ctno = 1

/**수정
 * 
 */
update camera
set category='일반디카', nickname='ㅋㅋㅋ', passwd='1234', deal_way='택배', deal_code='팝니다', product_code='신상품', hprice='500000', region='인천', tel='010-5555-6666', email='kkk@kkk.com', quantity='5', title='일반디카팔아요', content='ㄱㄴㄷㄼㅁㄴㅇ', purc_date='2016년1월' 
where ctno = 1;

/*   삭제      */
delete camera
where ctno = 1;


/* 검색 */

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt, file1, file2, size2, file3, file4, size4
from camera
where title like '%디카%'
order by ctno desc;

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt, file1, file2, size2, file3, file4, size4
from camera
where content like '%dfdf%'
order by ctno desc;




SELECT COUNT(*) as cnt
FROM camera
where title like '%디카%'

-- step 1
 SELECT ctno, deal_code, title, hprice, deal_way, region, nickname, wdate,cnt, file1, file2, size2  
   FROM camera
   ORDER BY ctno DESC    
-- step 2
SELECT ctno, deal_code, title, hprice, deal_way, region, nickname, wdate,cnt, file1, file2, size2
from (
SELECT ctno, deal_code, title, hprice, deal_way, region, nickname, wdate,cnt, file1, file2, size2  
   FROM camera
   ORDER BY ctno DESC
)
-- step 3



select * from camera;
/**********************************/
/* Table Name: 생활용품 */
/**********************************/
drop table Living

CREATE TABLE Living(
    ctno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    category                          VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(10)     NOT NULL,
    deal_way                          VARCHAR2(20)     NOT NULL,
    deal_code                         VARCHAR2(20)     NOT NULL,
    product_code                      VARCHAR2(20)     NOT NULL,
    hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
    region                            VARCHAR2(20)     DEFAULT ''    NOT NULL,
    tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
    email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
    quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
    title                             VARCHAR2(200)    DEFAULT ''    NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    purc_date                         VARCHAR2(20)     DEFAULT ''    NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL
);

alter table Living add (userid varchar2(20) );
ALTER TABLE Living 
ADD CONSTRAINT FK_Living FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE Living is '생활용품';
COMMENT ON COLUMN Living.ctno is '글번호';
COMMENT ON COLUMN Living.category is '카테고리';
COMMENT ON COLUMN Living.nickname  is '닉네임';
COMMENT ON COLUMN Living.passwd is '비밀번호';
COMMENT ON COLUMN Living.deal_way is '거래방법';
COMMENT ON COLUMN Living.deal_code is '거래구분';
COMMENT ON COLUMN Living.product_code is '상품구분';
COMMENT ON COLUMN Living.hprice is '희망가격';
COMMENT ON COLUMN Living.region is '지역';
COMMENT ON COLUMN Living.tel is '전화번호';
COMMENT ON COLUMN Living.email is '이메일';
COMMENT ON COLUMN Living.quantity is '수량';
COMMENT ON COLUMN Living.title is '제목';
COMMENT ON COLUMN Living.content is '내용';
COMMENT ON COLUMN Living.purc_date is '구입일';
COMMENT ON COLUMN Living.wdate is '등록일자';
COMMENT ON COLUMN Living.cnt is '조회수';

/**
 * 
 * 생활용품 테이블 입력(멤버 테이블의 nickname과 userid를 가져온다)
 */


insert into Living(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM Living),
'소형가전', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '울산', '010-2222-3333', 'kkk@kkk.com', 1, '밥솥 팔아요', '밥솥 싸게팔아요', '2016년10월', (select userid from member_test where userid='master'));

insert into Living(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM Living),
'사무용품', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '부산', '010-2222-3333', 'kkk@kkk.com', 1, '복사기 팔아요', '복사기 싸게팔아요', '2016년10월', (select userid from member_test where userid='master'));

insert into Living(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM Living),
'대형가전', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '부산', '010-2222-3333', 'kkk@kkk.com', 1, 'TV 팔아요', 'TV 싸게팔아요', '2016년10월', (select userid from member_test where userid='master'));




select count(ctno) from living
where title like '%TV%'

select count(ctno) from camera
where title like '%TV%'



/**
 * 생활 테이블 글조회
 */
select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from living
where ctno = 1

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from living
where category = '소형가전'


/**********************************/
/* Table Name: 스포츠 */
/**********************************/
drop table SPORTS

CREATE TABLE SPORTS(
    ctno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    category                          VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(10)     NOT NULL,
    deal_way                          VARCHAR2(20)     NOT NULL,
    deal_code                         VARCHAR2(20)     NOT NULL,
    product_code                      VARCHAR2(20)     NOT NULL,
    hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
    region                            VARCHAR2(20)     DEFAULT ''    NOT NULL,
    tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
    email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
    quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
    title                             VARCHAR2(200)    DEFAULT ''    NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    purc_date                         VARCHAR2(20)     DEFAULT ''    NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL
);

alter table SPORTS add (userid varchar2(20) );
ALTER TABLE SPORTS 
ADD CONSTRAINT FK_SPORTS FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE SPORTS is '생활용품';
COMMENT ON COLUMN SPORTS.ctno is '글번호';
COMMENT ON COLUMN SPORTS.category is '카테고리';
COMMENT ON COLUMN SPORTS.nickname  is '닉네임';
COMMENT ON COLUMN SPORTS.passwd is '비밀번호';
COMMENT ON COLUMN SPORTS.deal_way is '거래방법';
COMMENT ON COLUMN SPORTS.deal_code is '거래구분';
COMMENT ON COLUMN SPORTS.product_code is '상품구분';
COMMENT ON COLUMN SPORTS.hprice is '희망가격';
COMMENT ON COLUMN SPORTS.region is '지역';
COMMENT ON COLUMN SPORTS.tel is '전화번호';
COMMENT ON COLUMN SPORTS.email is '이메일';
COMMENT ON COLUMN SPORTS.quantity is '수량';
COMMENT ON COLUMN SPORTS.title is '제목';
COMMENT ON COLUMN SPORTS.content is '내용';
COMMENT ON COLUMN SPORTS.purc_date is '구입일';
COMMENT ON COLUMN SPORTS.wdate is '등록일자';
COMMENT ON COLUMN SPORTS.cnt is '조회수';

/**
 * 
 * 생활용품 테이블 입력(멤버 테이블의 nickname과 userid를 가져온다)
 */


insert into SPORTS(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM SPORTS),
'축구/야구/농구', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '울산', '010-2222-3333', 'kkk@kkk.com', 1, '야구공 팔아요', '야구공 싸게팔아요', '2016년10월', (select userid from member_test where userid='master'));

insert into SPORTS(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM SPORTS),
'등산용품', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '부산', '010-2222-3333', 'kkk@kkk.com', 1, '등산복 팔아요', '등산복 싸게팔아요', '2016년10월', (select userid from member_test where userid='master'));

insert into SPORTS(ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, userid) 
values ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM SPORTS),
'기타스포츠', (select nickname from member_test where userid='master'), '1234', '직거래', '팝니다', '중고품', 100000, '부산', '010-2222-3333', 'kkk@kkk.com', 1, '배트민턴라켓 팔아요', '배트민턴라켓 싸게팔아요', '2016년10월', (select userid from member_test where userid='master'));

/**
 * 스포츠 테이블 글조회
 */
select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from sports
where ctno = 1

select ctno ,category, nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, cnt
from sports
where category = '등산용품'

/**********************************/
/* Table Name: 카메라 댓글 */
/**********************************/
drop table CAMERA_REPLY;

CREATE TABLE CAMERA_REPLY(
    rno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    nickname                         VARCHAR2(20) NOT NULL,
    passwd                           VARCHAR2(20) NOT NULL,
    rcomment                         VARCHAR2(1000) NOT NULL
);

alter table CAMERA_REPLY add(ctno number(6));
alter table CAMERA_REPLY
add constraint FK_CAMERA_REPLY FOREIGN KEY(ctno)
REFERENCES CAMERA(ctno);

alter table CAMERA_REPLY add(userid varchar2(20));
alter table CAMERA_REPLY
add constraint FK_CAMERA_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE CAMERA_REPLY is '카메라 댓글';
COMMENT ON COLUMN CAMERA_REPLY.rno is '댓글번호';
COMMENT ON COLUMN CAMERA_REPLY.nickname is '닉네임';
COMMENT ON COLUMN CAMERA_REPLY.passwd  is '비밀번호';
COMMENT ON COLUMN CAMERA_REPLY.rcomment is '댓글내용';
COMMENT ON COLUMN CAMERA_REPLY.ctno is '글번호(외래키)';

/**
 * 
 * 카메라 댓글(멤버 테이블의 nickname과 userid를 가져온다)
 */


insert into CAMERA_REPLY(rno ,nickname, passwd, rcomment, ctno, userid) 
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM CAMERA_REPLY),
(select nickname from member_test where userid='master'), '1234', '사고싶어요', 1, (select userid from member_test where userid='master'));

drop table camera_reply
/**
 * 카메라 댓글 조회
 */
select cr.rno, cr.nickname, cr.rcomment
from camera_reply cr, camera c
where cr.ctno = c.ctno;


/**********************************/
/* Table Name: 생활용품 댓글 */
/**********************************/
drop table LIVING_REPLY

CREATE TABLE LIVING_REPLY(
    rno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    nickname                         VARCHAR2(20) NOT NULL,
    passwd                           VARCHAR2(20) NOT NULL,
    rcomment                         VARCHAR2(1000) NOT NULL
);

alter table LIVING_REPLY add(ctno number(6));
alter table LIVING_REPLY
add constraint FK_LIVING_REPLY FOREIGN KEY(ctno)
REFERENCES LIVING(ctno);

alter table LIVING_REPLY add(userid varchar2(20));
alter table LIVING_REPLY
add constraint FK_LIVING_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE CAMERA_REPLY is '생활용품 댓글';
COMMENT ON COLUMN CAMERA_REPLY.rno is '댓글번호';
COMMENT ON COLUMN CAMERA_REPLY.nickname is '닉네임';
COMMENT ON COLUMN CAMERA_REPLY.passwd  is '비밀번호';
COMMENT ON COLUMN CAMERA_REPLY.rcomment is '댓글내용';
COMMENT ON COLUMN CAMERA_REPLY.ctno is '글번호(외래키)';


/**
 * 
 *  생활용품 댓글(멤버 테이블의 nickname과 userid를 가져온다)
 */


insert into LIVING_REPLY(rno ,nickname, passwd, rcomment, ctno, userid) 
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM LIVING_REPLY),
(select nickname from member_test where userid='master'), '1234', '사고싶어요', 1, (select userid from member_test where userid='master'));


/**
 * 생활용품 댓글 조회
 */
select lr.rno, lr.nickname, lr.rcomment
from LIVING_REPLY lr, camera c
where lr.ctno = c.ctno;




/**********************************/
/* Table Name: 스포츠 댓글 */
/**********************************/
drop table SPORTS_REPLY

CREATE TABLE SPORTS_REPLY(
    rno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    nickname                         VARCHAR2(20) NOT NULL,
    passwd                           VARCHAR2(20) NOT NULL,
    rcomment                         VARCHAR2(1000) NOT NULL
);

alter table SPORTS_REPLY add(ctno number(6));
alter table SPORTS_REPLY
add constraint FK_SPORTS_REPLY FOREIGN KEY(ctno)
REFERENCES SPORTS(ctno);

alter table SPORTS_REPLY add(userid varchar2(20));
alter table SPORTS_REPLY
add constraint FK_SPORTS_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member_test(userid);

COMMENT ON TABLE CAMERA_REPLY is '스포츠 댓글';
COMMENT ON COLUMN CAMERA_REPLY.rno is '댓글번호';
COMMENT ON COLUMN CAMERA_REPLY.nickname is '닉네임';
COMMENT ON COLUMN CAMERA_REPLY.passwd  is '비밀번호';
COMMENT ON COLUMN CAMERA_REPLY.rcomment is '댓글내용';
COMMENT ON COLUMN CAMERA_REPLY.ctno is '글번호(외래키)';

/**
 * 
 *  스포츠 댓글(멤버 테이블의 nickname과 userid를 가져온다)
 */


insert into SPORTS_REPLY(rno ,nickname, passwd, rcomment, ctno, userid) 
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM SPORTS_REPLY),
(select nickname from member_test where userid='master'), '1234', '사고싶어요', 1, (select userid from member_test where userid='master'));


/**
 * 생활용품 댓글 조회
 */
select sr.rno, sr.nickname, sr.rcomment
from SPORTS_REPLY sr, camera c
where sr.ctno = c.ctno;

