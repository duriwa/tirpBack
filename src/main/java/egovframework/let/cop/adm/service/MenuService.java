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
public interface MenuService {

	/**
	 * 등록된 사용자 속성정보를 삭제한다.
	 * @param MenuVO
	 * 
	 * @param userMngVO
	 * @exception Exception Exception
	 */
	public void deleteMenuInf(MenuVO userMngVO)
	  throws Exception;

	/**
	 * 신규 사용자 속성정보를 생성한다.
	 * @param MenuVO
	 * 
	 * @param userMngVO
	 * @exception Exception Exception
	 */
	public String insertMenuInf(MenuVO userMngVO)
	  throws Exception;

	/**
	 * 사용자 속성정보 한 건을 상세조회한다.
	 * @param MenuVO
	 * 
	 * @param userMngVO
	 * @exception Exception Exception
	 */
	public MenuVO selectMenuInf(MenuVO userMngVO)
	  throws Exception;

	/**
	 * 사용자 속성 정보의 목록을 조회 한다.
	 * @param MenuVO
	 * 
	 * @param userMngVO
	 * @exception Exception Exception
	 */
	public Map<String, Object> selectMenuInfs(MenuVO userMngVO)
	  throws Exception;

	/**
	 * 사용자 정보를 확인한다.
	 * @param MenuVO
	 * 
	 * @param userMngVO
	 * @return boolean
	 * @exception Exception Exception
	 */
	public boolean isMenu(MenuVO userMngVO)
	  throws Exception;


	/**
	 * 사용자 속성정보를 수정한다.
	 * @param MenuVO
	 * 
	 * @param userMngVO
	 * @exception Exception Exception
	 */
	public void updateMenuInf(MenuVO userMngVO)
	  throws Exception;

	/**
	 * 템플릿의 유효여부를 점검한다.
	 * @param MenuVO
	 * 
	 * @param userMngVO
	 * @exception Exception Exception
	 */
	public void validateTemplate(MenuVO userMngVO)
	  throws Exception;

}