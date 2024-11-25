package egovframework.let.cop.adm.service;

import java.util.Map;

/**
 * 사용자 속성관리를 위한 서비스 인터페이스 클래스
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
public interface CodeKindService {

	/**
	 * 등록된 사용자 속성정보를 삭제한다.
	 * @param CodeKindVO
	 * 
	 * @param CodeKindVO
	 * @exception Exception Exception
	 */
	public void deleteCodeKindInf(CodeKindVO codeKindvo)
	  throws Exception;

	/**
	 * 신규 사용자 속성정보를 생성한다.
	 * @param CodeKindVO
	 * 
	 * @exception Exception Exception
	 */
	public String insertCodeKindInf(CodeKindVO codeKindvo)
	  throws Exception;

	/**
	 * 사용자 속성 정보의 목록을 조회 한다.
	 * @param CodeKindVO
	 * 
	 * @exception Exception Exception
	 */
	public Map<String, Object> selectCodeKindInfs(CodeKindVO codeKindvo)
	  throws Exception;


}