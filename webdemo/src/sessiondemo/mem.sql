
select * from tab;
	
create table memtab(
  num number,
  id varchar2(10),
  pass varchar2(20),
  name varchar2(50)
);

create sequence memtab_num_seq
start with 1
increment by 1
nocache
nocycle;

insert into memtab 
  values(memtab_num_seq.nextval,'kim','1234','홍길동');

commit

select * from memtab;

select count(*) from memtab where id='kim' and pass='1234'

  
  
  
  
  
