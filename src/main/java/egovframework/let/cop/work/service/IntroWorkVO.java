package egovframework.let.cop.work.service;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 게시판 속성 정보를 관리하기 위한 VO  클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  이삼섭          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Schema(description = "주간보고 VO")

public class IntroWorkVO implements Serializable {
	

	private int seq;
	private String sawonCd;
	private String part;
	private String gubun;
	private String srNo;
	private String srNm;
	private String devStDt;
	private String devEndDt;
	private int progRate;
	private String progStatus;
	private int memHour;
	private String bigo;



	private String level;
	private String hjsyyb;

	private String woldo;
	private String gubunDetail;
	private String stDt;
	private String endDt;
	private String eduNm;

	private String srchGubun;
	private String srchWrd;

	public String getSrchWrd() {
		return this.srchWrd;
	}

	public void setSrchWrd(String srchWrd) {
		this.srchWrd = srchWrd;
	}

	public String getSrchGubun() {
		return this.srchGubun;
	}

	public void setSrchGubun(String srchGubun) {
		this.srchGubun = srchGubun;
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getSawonCd() {
		return this.sawonCd;
	}

	public void setSawonCd(String sawonCd) {
		this.sawonCd = sawonCd;
	}
	public String getBigo() {
		return this.bigo;
	}

	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	public String getPart() {
		return this.part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getGubun() {
		return this.gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public String getSrNo() {
		return this.srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getSrNm() {
		return this.srNm;
	}

	public void setSrNm(String srNm) {
		this.srNm = srNm;
	}

	public String getDevStDt() {
		return this.devStDt;
	}

	public void setDevStDt(String devStDt) {
		this.devStDt = devStDt;
	}

	public String getDevEndDt() {
		return this.devEndDt;
	}

	public void setDevEndDt(String devEndDt) {
		this.devEndDt = devEndDt;
	}

	public int getProgRate() {
		return this.progRate;
	}

	public void setProgRate(int progRate) {
		this.progRate = progRate;
	}

	public String getProgStatus() {
		return this.progStatus;
	}

	public void setProgStatus(String progStatus) {
		this.progStatus = progStatus;
	}

	public int getMemHour() {
		return this.memHour;
	}

	public void setMemHour(int memHour) {
		this.memHour = memHour;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getHjsyyb() {
		return this.hjsyyb;
	}

	public void setHjsyyb(String hjsyyb) {
		this.hjsyyb = hjsyyb;
	}

	public String getWoldo() {
		return this.woldo;
	}

	public void setWoldo(String woldo) {
		this.woldo = woldo;
	}

	public String getGubunDetail() {
		return this.gubunDetail;
	}

	public void setGubunDetail(String gubunDetail) {
		this.gubunDetail = gubunDetail;
	}

	public String getStDt() {
		return this.stDt;
	}

	public void setStDt(String stDt) {
		this.stDt = stDt;
	}

	public String getEndDt() {
		return this.endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getEduNm() {
		return this.eduNm;
	}

	public void setEduNm(String eduNm) {
		this.eduNm = eduNm;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
