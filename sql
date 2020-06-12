필요한 필드{
  회원가입
  아이디
  비밀번호
  가입일
}

테이블 상황
CREATE TABLE tem(
	mem_id varchar(50) primary key,
    mem_pwd varchar(50),
    mem_nick varchar(50),
    join_date date
);

insert into tem values('babo', '127', 'babu', sysdate);

select * from tem;

drop table tem;

commit;
