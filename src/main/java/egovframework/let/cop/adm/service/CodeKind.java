package egovframework.let.cop.adm.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 속성정보를 담기위한 엔티티 클래스
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
@Schema(description = "사용자 속성 정보 엔티티")
@Getter
@Setter
public class CodeKind implements Serializable {

    /**
	 * serialVersion UID
	 */
	private static final long serialVersionUID = 2821358749509367821L;

    @Schema(description = "공통코드 아이디")
    private String cdKindNo;

    @Schema(description = "공통코드 이름")
    private String cdKindNm;

    @Schema(description = "공통코드 설명")
    private String cdKindDesc;

    @Schema(description = "공통코드 사용여부")
    private String useYn;

    @Schema(description = "최초 등록 일자")
    private String frstRegDt;

    @Schema(description = "최초 등록 아이디")
    private String frsRegId;

    @Schema(description = "마지막 처리 아이디")
    private String lastProcId;

    @Schema(description = "마지막 처리일자")
    private String lastProcDt;

    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
	@Schema(description = "추가 option (댓글-comment, 만족도조사-stsfdg)")
    private String option = "";

	@Schema(description = "댓글 여부")
    private String commentAt = "";

	@Schema(description = "만족도조사")
    private String stsfdgAt = "";
    ////-------------------------------

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}