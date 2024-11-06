--DROP TABLE IDS CASCADE;
CREATE TABLE IDS  (
  TABLE_NAME varchar(20) NOT NULL,
  NEXT_ID NUMERIC(30,0) DEFAULT 0 NOT NULL ,
  CONSTRAINT IDS_PK PRIMARY KEY (TABLE_NAME)
);
--DROP TABLE LETTCCMMNCLCODE CASCADE;
CREATE TABLE LETTCCMMNCLCODE (
  CL_CODE char(3) NOT NULL,
  CL_CODE_NM varchar(60) ,
  CL_CODE_DC varchar(200) ,
  USE_AT char(1) ,
  FRST_REGIST_PNTTM TIMESTAMP ,
  FRST_REGISTER_ID varchar(20) ,
  LAST_UPDT_PNTTM TIMESTAMP ,
  LAST_UPDUSR_ID varchar(20) ,
  CONSTRAINT LETTCCMMNCLCODE_PK PRIMARY KEY (CL_CODE)
) ;
--DROP TABLE LETTCCMMNCODE CASCADE;
CREATE TABLE LETTCCMMNCODE  (
  CODE_ID varchar(6) NOT NULL,
  CODE_ID_NM varchar(60) ,
  CODE_ID_DC varchar(200) ,
  USE_AT char(1) ,
  CL_CODE char(3) ,
  FRST_REGIST_PNTTM TIMESTAMP ,
  FRST_REGISTER_ID varchar(20) ,
  LAST_UPDT_PNTTM TIMESTAMP ,
  LAST_UPDUSR_ID varchar(20) ,
  CONSTRAINT LETTCCMMNCODE_PK PRIMARY KEY (CODE_ID),
  CONSTRAINT LETTCCMMNCODE_ibfk_1 FOREIGN KEY (CL_CODE) REFERENCES LETTCCMMNCLCODE (CL_CODE)
) ;
--DROP TABLE LETTCCMMNDETAILCODE CASCADE;
CREATE TABLE LETTCCMMNDETAILCODE  (
  CODE_ID varchar(6) NOT NULL,
  CODE varchar(15) NOT NULL,
  CODE_NM varchar(60) ,
  CODE_DC varchar(200) ,
  USE_AT char(1) ,
  FRST_REGIST_PNTTM TIMESTAMP ,
  FRST_REGISTER_ID varchar(20) ,
  LAST_UPDT_PNTTM TIMESTAMP ,
  LAST_UPDUSR_ID varchar(20) ,
  CONSTRAINT LETTCCMMNDETAILCODE_PK PRIMARY KEY (CODE_ID,CODE),
  CONSTRAINT LETTCCMMNDETAILCODE_ibfk_1 FOREIGN KEY (CODE_ID) REFERENCES LETTCCMMNCODE (CODE_ID)
) ;
--DROP TABLE LETTNORGNZTINFO CASCADE;
CREATE TABLE LETTNORGNZTINFO  (
  ORGNZT_ID char(20) DEFAULT '' NOT NULL,
  ORGNZT_NM varchar(20) NOT NULL,
  ORGNZT_DC varchar(100) ,
  CONSTRAINT LETTNORGNZTINFO_PK PRIMARY KEY (ORGNZT_ID)
) ;
--DROP TABLE LETTNAUTHORGROUPINFO CASCADE;
CREATE TABLE LETTNAUTHORGROUPINFO  (
  GROUP_ID char(20) DEFAULT '' NOT NULL,
  GROUP_NM varchar(60) NOT NULL,
  GROUP_CREAT_DE TIMESTAMP NOT NULL,
  GROUP_DC varchar(100) ,
  CONSTRAINT LETTNAUTHORGROUPINFO_PK PRIMARY KEY (GROUP_ID)
) ;
--DROP TABLE LETTNEMPLYRINFO CASCADE;
CREATE TABLE LETTNEMPLYRINFO  (
  EMPLYR_ID varchar(20) NOT NULL,
  ORGNZT_ID char(20) ,
  USER_NM varchar(60) NOT NULL,
  PASSWORD varchar(200) NOT NULL,
  EMPL_NO varchar(20) ,
  IHIDNUM varchar(200) ,
  SEXDSTN_CODE char(1) ,
  BRTHDY char(20) ,
  FXNUM varchar(20) ,
  HOUSE_ADRES varchar(100) ,
  PASSWORD_HINT varchar(100) NOT NULL,
  PASSWORD_CNSR varchar(100) NOT NULL,
  HOUSE_END_TELNO varchar(4) ,
  AREA_NO varchar(4) ,
  DETAIL_ADRES varchar(100) ,
  ZIP varchar(6) ,
  OFFM_TELNO varchar(20) ,
  MBTLNUM varchar(20) ,
  EMAIL_ADRES varchar(50) ,
  OFCPS_NM varchar(60) ,
  HOUSE_MIDDLE_TELNO varchar(4) ,
  GROUP_ID char(20) ,
  PSTINST_CODE char(8) ,
  EMPLYR_STTUS_CODE varchar(15) NOT NULL,
  ESNTL_ID char(20) NOT NULL,
  CRTFC_DN_VALUE varchar(20) ,
  SBSCRB_DE TIMESTAMP ,
  CONSTRAINT LETTNEMPLYRINFO_PK PRIMARY KEY (EMPLYR_ID),
  CONSTRAINT LETTNEMPLYRINFO_ibfk_2 FOREIGN KEY (GROUP_ID) REFERENCES LETTNAUTHORGROUPINFO (GROUP_ID) ON DELETE CASCADE,
  CONSTRAINT LETTNEMPLYRINFO_ibfk_1 FOREIGN KEY (ORGNZT_ID) REFERENCES LETTNORGNZTINFO (ORGNZT_ID) ON DELETE CASCADE
) ;
--DROP TABLE LETTNBBSMASTER CASCADE;
CREATE TABLE LETTNBBSMASTER  (
  BBS_ID char(20) NOT NULL,
  BBS_NM varchar(255) NOT NULL,
  BBS_INTRCN varchar(2400) ,
  BBS_TY_CODE char(6) NOT NULL,
  BBS_ATTRB_CODE char(6) NOT NULL,
  REPLY_POSBL_AT char(1) ,
  FILE_ATCH_POSBL_AT char(1) NOT NULL,
  ATCH_POSBL_FILE_NUMBER NUMERIC(2,0) NOT NULL,
  ATCH_POSBL_FILE_SIZE NUMERIC(8,0) ,
  USE_AT char(1) NOT NULL,
  TMPLAT_ID char(20) ,
  FRST_REGISTER_ID varchar(20) NOT NULL,
  FRST_REGIST_PNTTM TIMESTAMP NOT NULL,
  LAST_UPDUSR_ID varchar(20) ,
  LAST_UPDT_PNTTM TIMESTAMP ,
  CONSTRAINT LETTNBBSMASTER_PK PRIMARY KEY (BBS_ID)
) ;
--DROP TABLE LETTNBBSUSE CASCADE;
CREATE TABLE LETTNBBSUSE  (
  BBS_ID char(20) NOT NULL,
  TRGET_ID char(20) DEFAULT '' NOT NULL,
  USE_AT char(1) NOT NULL,
  REGIST_SE_CODE char(6) ,
  FRST_REGIST_PNTTM TIMESTAMP ,
  FRST_REGISTER_ID varchar(20) NOT NULL,
  LAST_UPDT_PNTTM TIMESTAMP ,
  LAST_UPDUSR_ID varchar(20) ,
  CONSTRAINT LETTNBBSUSE_PK PRIMARY KEY (BBS_ID,TRGET_ID),
  CONSTRAINT LETTNBBSUSE_ibfk_1 FOREIGN KEY (BBS_ID) REFERENCES LETTNBBSMASTER (BBS_ID)
) ;

--DROP TABLE LETTNBBS CASCADE;
CREATE TABLE LETTNBBS (
  NTT_ID NUMERIC(20,0) NOT NULL,
  BBS_ID char(20) NOT NULL,
  NTT_NO NUMERIC(20,0) ,
  NTT_SJ varchar(2000) ,
  NTT_CN TEXT,
  ANSWER_AT char(1) ,
  PARNTSCTT_NO NUMERIC(10,0) ,
  ANSWER_LC NUMERIC(11) ,
  SORT_ORDR NUMERIC(8,0) ,
  RDCNT NUMERIC(10,0) ,
  USE_AT char(1) NOT NULL,
  NTCE_BGNDE char(20) ,
  NTCE_ENDDE char(20) ,
  NTCR_ID varchar(20) ,
  NTCR_NM varchar(20) ,
  PASSWORD varchar(200) ,
  ATCH_FILE_ID char(20) ,
  FRST_REGIST_PNTTM TIMESTAMP NOT NULL,
  FRST_REGISTER_ID varchar(20) NOT NULL,
  LAST_UPDT_PNTTM TIMESTAMP ,
  LAST_UPDUSR_ID varchar(20) ,
  CONSTRAINT LETTNBBS_PK PRIMARY KEY (NTT_ID,BBS_ID),
  CONSTRAINT LETTNBBS_ibfk_1 FOREIGN KEY (BBS_ID) REFERENCES LETTNBBSMASTER (BBS_ID)
) ;
--DROP TABLE LETTNBBSMASTEROPTN CASCADE;
CREATE TABLE LETTNBBSMASTEROPTN (
  BBS_ID char(20) DEFAULT '' NOT NULL,
  ANSWER_AT char(1) DEFAULT '' NOT NULL,
  STSFDG_AT char(1) DEFAULT '' NOT NULL,
  FRST_REGIST_PNTTM TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  LAST_UPDT_PNTTM TIMESTAMP ,
  FRST_REGISTER_ID varchar(20) DEFAULT '' NOT NULL,
  LAST_UPDUSR_ID varchar(20) ,
  CONSTRAINT LETTNBBSMASTEROPTN_PK PRIMARY KEY (BBS_ID)
) ;
--DROP TABLE LETTNENTRPRSMBER CASCADE;
CREATE TABLE LETTNENTRPRSMBER (
  ENTRPRS_MBER_ID varchar(20) DEFAULT '' NOT NULL,
  ENTRPRS_SE_CODE char(15) ,
  BIZRNO varchar(10) ,
  JURIRNO varchar(13) ,
  CMPNY_NM varchar(60) NOT NULL,
  CXFC varchar(50) ,
  ZIP varchar(6) ,
  ADRES varchar(100) ,
  ENTRPRS_MIDDLE_TELNO varchar(4) ,
  FXNUM varchar(20) ,
  INDUTY_CODE char(15) ,
  APPLCNT_NM varchar(50) NOT NULL,
  APPLCNT_IHIDNUM varchar(200) ,
  SBSCRB_DE TIMESTAMP ,
  ENTRPRS_MBER_STTUS varchar(15) ,
  ENTRPRS_MBER_PASSWORD varchar(200) NOT NULL,
  ENTRPRS_MBER_PASSWORD_HINT varchar(100) NOT NULL,
  ENTRPRS_MBER_PASSWORD_CNSR varchar(100) NOT NULL,
  GROUP_ID char(20) ,
  DETAIL_ADRES varchar(100) ,
  ENTRPRS_END_TELNO varchar(4) ,
  AREA_NO varchar(4) ,
  APPLCNT_EMAIL_ADRES varchar(50) ,
  ESNTL_ID char(20) NOT NULL,
  CONSTRAINT LETTNENTRPRSMBER_PK PRIMARY KEY (ENTRPRS_MBER_ID),
  CONSTRAINT LETTNENTRPRSMBER_ibfk_1 FOREIGN KEY (GROUP_ID) REFERENCES LETTNAUTHORGROUPINFO (GROUP_ID) ON DELETE CASCADE
) ;
--DROP TABLE LETTNFILE CASCADE;
CREATE TABLE LETTNFILE (
  ATCH_FILE_ID char(20) NOT NULL,
  CREAT_DT TIMESTAMP NOT NULL,
  USE_AT char(1) ,
  CONSTRAINT LETTNFILE_PK PRIMARY KEY (ATCH_FILE_ID)
) ;
--DROP TABLE LETTNFILEDETAIL CASCADE;
CREATE TABLE LETTNFILEDETAIL (
  ATCH_FILE_ID char(20) NOT NULL,
  FILE_SN NUMERIC(10,0) NOT NULL,
  FILE_STRE_COURS varchar(2000) NOT NULL,
  STRE_FILE_NM varchar(255) NOT NULL,
  ORIGNL_FILE_NM varchar(255) ,
  FILE_EXTSN varchar(20) NOT NULL,
  FILE_CN TEXT,
  FILE_SIZE NUMERIC(8,0) ,
  CONSTRAINT LETTNFILEDETAIL_PK PRIMARY KEY (ATCH_FILE_ID,FILE_SN),
  CONSTRAINT LETTNFILEDETAIL_ibfk_1 FOREIGN KEY (ATCH_FILE_ID) REFERENCES LETTNFILE (ATCH_FILE_ID)
) ;
--DROP TABLE LETTNGNRLMBER CASCADE;
CREATE TABLE LETTNGNRLMBER (
  MBER_ID varchar(20) DEFAULT '' NOT NULL,
  PASSWORD varchar(200) NOT NULL,
  PASSWORD_HINT varchar(100) NOT NULL,
  PASSWORD_CNSR varchar(100) NOT NULL,
  IHIDNUM varchar(200) ,
  MBER_NM varchar(50) NOT NULL,
  ZIP varchar(6) ,
  ADRES varchar(100) ,
  AREA_NO varchar(4) ,
  MBER_STTUS varchar(15) ,
  DETAIL_ADRES varchar(100) ,
  END_TELNO varchar(4) ,
  MBTLNUM varchar(20) ,
  GROUP_ID char(20) ,
  MBER_FXNUM varchar(20) ,
  MBER_EMAIL_ADRES varchar(50) ,
  MIDDLE_TELNO varchar(4) ,
  SBSCRB_DE TIMESTAMP ,
  SEXDSTN_CODE char(1) ,
  ESNTL_ID char(20) NOT NULL,
  CONSTRAINT LETTNGNRLMBER_PK PRIMARY KEY (MBER_ID),
  CONSTRAINT LETTNGNRLMBER_ibfk_1 FOREIGN KEY (GROUP_ID) REFERENCES LETTNAUTHORGROUPINFO (GROUP_ID) ON DELETE CASCADE
) ;
--DROP TABLE LETTNSCHDULINFO CASCADE;
CREATE TABLE LETTNSCHDULINFO (
  SCHDUL_ID char(20) NOT NULL,
  SCHDUL_SE char(1) ,
  SCHDUL_DEPT_ID varchar(20) ,
  SCHDUL_KND_CODE varchar(20) ,
  SCHDUL_BEGINDE char(20) ,
  SCHDUL_ENDDE char(20) ,
  SCHDUL_NM varchar(255) ,
  SCHDUL_CN varchar(2500) ,
  SCHDUL_PLACE varchar(255) ,
  SCHDUL_IPCR_CODE char(1) ,
  SCHDUL_CHARGER_ID varchar(20) ,
  ATCH_FILE_ID char(20) ,
  FRST_REGIST_PNTTM TIMESTAMP ,
  FRST_REGISTER_ID varchar(20) ,
  LAST_UPDT_PNTTM TIMESTAMP ,
  LAST_UPDUSR_ID varchar(20) ,
  REPTIT_SE_CODE char(3) ,
  CONSTRAINT LETTNSCHDULINFO_PK PRIMARY KEY (SCHDUL_ID)
) ;
CREATE OR REPLACE VIEW COMVNUSERMASTER ( ESNTL_ID,USER_ID,PASSWORD,USER_NM,USER_ZIP,USER_ADRES,USER_EMAIL,GROUP_ID, USER_SE, ORGNZT_ID ) 
AS  
        SELECT ESNTL_ID, MBER_ID,PASSWORD,MBER_NM,ZIP,ADRES,MBER_EMAIL_ADRES,' ','GNR' AS USER_SE, ' ' ORGNZT_ID
        FROM LETTNGNRLMBER
    UNION ALL
        SELECT ESNTL_ID,EMPLYR_ID,PASSWORD,USER_NM,ZIP,HOUSE_ADRES,EMAIL_ADRES,GROUP_ID ,'USR' AS USER_SE, ORGNZT_ID
        FROM LETTNEMPLYRINFO
    UNION ALL
        SELECT ESNTL_ID,ENTRPRS_MBER_ID,ENTRPRS_MBER_PASSWORD,CMPNY_NM,ZIP,ADRES,APPLCNT_EMAIL_ADRES,' ' ,'ENT' AS USER_SE, ' ' ORGNZT_ID
        FROM LETTNENTRPRSMBER 
;

-- Table: public.tb_cm_user

DROP TABLE IF EXISTS public.tb_cm_user;

CREATE TABLE IF NOT EXISTS public.tb_cm_user
(
    user_id character varying(10) COLLATE pg_catalog."default" NOT NULL,
    user_nm character varying(100) COLLATE pg_catalog."default",
    user_pw character varying(100) COLLATE pg_catalog."default",
    frst_reg_dt date,
    frst_reg_id character varying(10) COLLATE pg_catalog."default",
    last_proc_dt date,
    last_proc_id character varying(10) COLLATE pg_catalog."default",
    user_seq integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( CYCLE INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99999999 CACHE 1 ),
    use_at character(1) COLLATE pg_catalog."default",
    CONSTRAINT tb_cm_user_pkey PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_cm_user
    OWNER to tireport;

COMMENT ON TABLE public.tb_cm_user
    IS '사용자 관리';

COMMENT ON COLUMN public.tb_cm_user.user_id
    IS '사용자 id';

COMMENT ON COLUMN public.tb_cm_user.user_nm
    IS '사용자 명';

COMMENT ON COLUMN public.tb_cm_user.user_pw
    IS '사용자 암호';

COMMENT ON COLUMN public.tb_cm_user.frst_reg_dt
    IS '최초 등록 일시';

COMMENT ON COLUMN public.tb_cm_user.frst_reg_id
    IS '최초 등록자';

COMMENT ON COLUMN public.tb_cm_user.last_proc_dt
    IS '최종 변경 일시';

COMMENT ON COLUMN public.tb_cm_user.last_proc_id
    IS '최종 변경자';

COMMENT ON COLUMN public.tb_cm_user.user_seq
    IS '사용자번호 (유니크키)';


drop table IF EXISTS public.tb_cm_menu;

CREATE TABLE IF NOT EXISTS public.tb_cm_menu
(
    menu_id character varying(10) COLLATE pg_catalog."default" NOT NULL,
    menu_nm character varying(50) COLLATE pg_catalog."default",
    menu_level integer,
    menu_order integer,
    use_yn character(1) COLLATE pg_catalog."default",
    pgm_nm character varying COLLATE pg_catalog."default",
    frst_reg_dt date,
    frst_reg_id character varying(10) COLLATE pg_catalog."default",
    last_proc_dt date,
    last_proc_id character varying COLLATE pg_catalog."default",
    CONSTRAINT "TB_CM_MENU_pkey" PRIMARY KEY (menu_id)
);


ALTER TABLE IF EXISTS public.tb_cm_menu
    OWNER to tireport;

COMMENT ON TABLE public.tb_cm_menu
    IS '메뉴 관리';

COMMENT ON COLUMN public.tb_cm_menu.menu_id
    IS '메뉴 ID';

COMMENT ON COLUMN public.tb_cm_menu.menu_nm
    IS '메뉴 명';

COMMENT ON COLUMN public.tb_cm_menu.menu_level
    IS '메뉴 레벨';

COMMENT ON COLUMN public.tb_cm_menu.menu_order
    IS '메뉴 정렬순서';

COMMENT ON COLUMN public.tb_cm_menu.use_yn
    IS '메뉴 사용여부';

COMMENT ON COLUMN public.tb_cm_menu.pgm_nm
    IS '메뉴프로그램이름';

COMMENT ON COLUMN public.tb_cm_menu.frst_reg_dt
    IS '최초 등록 일시';

COMMENT ON COLUMN public.tb_cm_menu.frst_reg_id
    IS '최초 등록자';

COMMENT ON COLUMN public.tb_cm_menu.last_proc_dt
    IS '최종 변경 일시';

COMMENT ON COLUMN public.tb_cm_menu.last_proc_id
    IS '최종 변경자';


DROP TABLE IF EXISTS public.tb_cm_cd_kind;

CREATE TABLE IF NOT EXISTS public.tb_cm_cd_kind
(
    cd_kind_no character varying(10) COLLATE pg_catalog."default" NOT NULL,
    cd_kind_nm character varying(200) COLLATE pg_catalog."default",
    cd_kind_desc character varying(500) COLLATE pg_catalog."default",
    use_yn character varying(1) COLLATE pg_catalog."default",
    frst_reg_dt date,
    frst_reg_id character varying(10) COLLATE pg_catalog."default",
    last_proc_dt date,
    last_proc_id character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT tb_cm_cd_kind_pkey PRIMARY KEY (cd_kind_no)
);    

ALTER TABLE IF EXISTS public.tb_cm_cd_kind
    OWNER to tireport;

COMMENT ON TABLE public.tb_cm_cd_kind
    IS '코드 관리';

COMMENT ON COLUMN public.tb_cm_cd_kind.cd_kind_no
    IS '코드 ID';

COMMENT ON COLUMN public.tb_cm_cd_kind.cd_kind_nm
    IS '코드 명';

COMMENT ON COLUMN public.tb_cm_cd_kind.cd_kind_desc
    IS '코드설명';

COMMENT ON COLUMN public.tb_cm_cd_kind.use_yn
    IS '코드사용여부';

COMMENT ON COLUMN public.tb_cm_cd_kind.frst_reg_dt
    IS '최초 등록 일시';

COMMENT ON COLUMN public.tb_cm_cd_kind.frst_reg_id
    IS '최초 등록자';

COMMENT ON COLUMN public.tb_cm_cd_kind.last_proc_dt
    IS '최종 변경 일시';

COMMENT ON COLUMN public.tb_cm_cd_kind.last_proc_id
    IS '최종 변경자';


DROP TABLE IF EXISTS public.tb_cm_cd_desc;


CREATE TABLE IF NOT EXISTS public.tb_cm_cd_desc
(
    cd_kind_no character varying(10) COLLATE pg_catalog."default" NOT NULL,
    cd_desc_no character varying(10) COLLATE pg_catalog."default" NOT NULL,
    cd_desc_nm character varying(200) COLLATE pg_catalog."default",
    use_yn character varying(1) COLLATE pg_catalog."default",
    frst_reg_dt date,
    frst_reg_id character varying(10) COLLATE pg_catalog."default",
    last_proc_dt date,
    last_proc_id character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT tb_cm_cd_desc_pkey PRIMARY KEY (cd_desc_no)
);    

ALTER TABLE  public.tb_cm_cd_desc    OWNER to tireport;

COMMENT ON TABLE public.tb_cm_cd_desc   IS '코드 상세 관리';

COMMENT ON COLUMN public.tb_cm_cd_desc.cd_kind_no    IS '코드 ID';

COMMENT ON COLUMN public.tb_cm_cd_desc.cd_desc_no    IS '코드상세 ID';    

COMMENT ON COLUMN public.tb_cm_cd_desc.cd_desc_nm    IS '코드상세 명';

COMMENT ON COLUMN public.tb_cm_cd_desc.use_yn    IS '코드사용여부';

COMMENT ON COLUMN public.tb_cm_cd_desc.frst_reg_dt    IS '최초 등록 일시';

COMMENT ON COLUMN public.tb_cm_cd_desc.frst_reg_id    IS '최초 등록자';

COMMENT ON COLUMN public.tb_cm_cd_desc.last_proc_dt    IS '최종 변경 일시';

COMMENT ON COLUMN public.tb_cm_cd_desc.last_proc_id    IS '최종 변경자';
