package egovframework.let.cop.adm.service.impl;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.let.cop.adm.service.MenuVO;

/**
 * 사용자 속성정보 관리를 위한 데이터 접근 클래스
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
@Repository("MenuDAO")
public class MenuDAO extends EgovAbstractMapper {

    /**
     * 등록된 사용자 속성정보를 삭제한다.
     *
     * @param MenuVO
     */
    public void deleteMenuInf(MenuVO userMngVO) throws Exception {
	delete("MenuDAO.deleteMenuInf", userMngVO);
    }

    /**
     * 신규 사용자 속성정보를 등록한다.
     *
     * @param Menu
     */
    public int insertMenuInf(MenuVO userMngVO) throws Exception {
	return (int)insert("MenuDAO.insertMenuInf", userMngVO);
    }

    /**
     * 사용자 속성정보 한 건을 상세조회 한다.
     *
     * @param MenuVO
     */
    public MenuVO selectMenuInf(MenuVO userMngVO) throws Exception {
	return (MenuVO)selectOne("MenuDAO.selectMenuInf", userMngVO);
    }

    /**
     * 사용자 속성정보 목록을 조회한다.
     *
     * @param MenuVO
     */
    @SuppressWarnings("unchecked")
    public List<MenuVO> selectMenuInfs(MenuVO vo) throws Exception {
	return (List<MenuVO>) list("MenuDAO.selectMenuInfs", vo);
    }

    /**
     * 사용자 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectMenuInfsCnt(MenuVO vo) throws Exception {
	return (Integer)selectOne("MenuDAO.selectMenuInfsCnt", vo);
    }
    /**
     * 사용자를 확인합니다.
     *
     * @param Menu
     * @return 일치하는 사용자 개수
     * @throws Exception
     */
    public int selectMenuCount(MenuVO userMngVO) throws Exception {
        return (Integer)selectOne("MenuDAO.selectMenuCount", userMngVO);
    }

    /**
     * 사용자정보를 수정한다.
     *
     * @param Menu
     */
    public void updateMenuInf(MenuVO userMngVO) throws Exception {
	update("MenuDAO.updateMenuInf", userMngVO);
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     *
     * @param MenuVO
     */
    public boolean validateTemplate(MenuVO vo) throws Exception {
	return true;
    }

    



    
    
}
