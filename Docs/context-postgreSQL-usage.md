oracle 에서 Postgre 로 변경시 주의할 점과 마이그레이션 참고 사항

#pg admin

postgres / hanalife
tireport / tireport

#DB변경 : oracle => postgreSQL
EgovConfigAppDatasource.java

#postgreSQL migration
- DB
NUMBER => NUMERIC
VARCHAR2 => VARCHAR
DATE => TIMESTAMP
CLOB => TEXT
SYSTIMESTAMP => CURRENT_TIMESTAMP
SYSDATE => CURRENT_TIMESTAMP
ROWNUM  => row_number() over () AS rownum
NVL => COALESCE
select * from dual => SELECT ‘’;
TABLE에 모든 권한 부여. postgreSQL 툴에서. TABLE 선택후에 오른쪽 마우스 grant wizard

type numeric but expression is of type character 에러 발생하면 아래로 변경
#{fileSn} => to_number(#{fileSn},'0')



-JAVA
numeric 컬럼 null 안되어서 소스상에 기본값 세팅필요.
mybatis CLOB을 String 처리하면됨.


각 테이블 tireport 권한 주어야한다. 툴을 사용하면 일괄 부여 가능
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE LETTNEMPLYRINFO TO tireport;
grant select on table COMVNUSERMASTER to tireport