package egovframework.let.cop.work.service;

import java.util.HashMap;
import java.util.List;

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
public interface IntroWorkService {

	public int insWork(IntroWorkVO paramData)
	  throws Exception;


	public List<IntroWorkVO> srchWork(HashMap<String, String> paramData)
	throws Exception;

	public int insEtc(IntroWorkVO paramData)
	throws Exception;

	public List<IntroWorkVO> srchEtc(HashMap<String, String> paramData)
	throws Exception;

}