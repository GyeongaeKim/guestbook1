drop table guestbook;
drop sequence seq_guestbook_no;

create table guestbook(
    no          number(10),
    name        varchar2(80) not null,
    password    varchar2(20),
    content     varchar2(100),
    reg_date    date,
    primary key (no)
);

create sequence seq_guestbook_no
increment by 1
start with 1
nocache;


select * from guestbook;

commit;

insert into guestbook
values (SEQ_GUESTBOOK_NO.nextval, '이정재', '1234', '첫번째 방명록내용\n안녕하세요', '25-jul-2017');