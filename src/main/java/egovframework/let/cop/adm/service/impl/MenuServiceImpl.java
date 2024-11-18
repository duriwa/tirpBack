package egovframework.let.cop.adm.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.stereotype.Service;

import egovframework.let.cop.adm.service.MenuService;
import egovframework.let.cop.adm.service.MenuVO;
import egovframework.let.cop.com.service.EgovUserInfManageService;
import lombok.extern.slf4j.Slf4j;

/**
 * 메뉴 속성관리를 위한 서비스 구현 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.24
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.24  이삼섭          최초 생성
 *  2009.06.26	한성곤		   2단계 기능 추가 (댓글관리, 만족도조사)
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Slf4j
@Service("MenuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {

    @Resource(name = "MenuDAO")
    private MenuDAO menuDAO;

    @Resource(name = "EgovUserInfManageService")
    private EgovUserInfManageService userService;

    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    /**
     * 등록된 메뉴 속성정보를 삭제한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#deleteMenuInf(egovframework.let.cop.bbs.brd.service.Menu)
     */
    public void deleteMenuInf(MenuVO menuVO) throws Exception {
        menuDAO.deleteMenuInf(menuVO);

        MenuVO bdUseInf = new MenuVO();

        bdUseInf.setLastProcId(menuVO.getLastProcId());

        menuDAO.deleteMenuInf(bdUseInf);
    }

    /**
     * 신규 메뉴 속성정보를 생성한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#insertMenuInf(egovframework.let.cop.bbs.brd.service.Menu)
     */
    public String insertMenuInf(MenuVO menuVO) throws Exception {
        menuDAO.insertMenuInf(menuVO);

        return null;
    }


    /**
     * 메뉴 속성정보 한 건을 상세조회한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#selectMenuInf(egovframework.let.cop.adm.service.brd.service.MenuVO)
     */
    public MenuVO selectMenuInf(MenuVO searchVO) throws Exception {
	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------
	//return attrbMngDAO.selectMenuInf(searchVO);

	MenuVO result = menuDAO.selectMenuInf(searchVO);

	return result;
	////-------------------------------

    }

    /**
     * 메뉴 속성 정보의 목록을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#selectMenuInfs(egovframework.let.cop.adm.service.brd.service.MenuVO)
     */
    public Map<String, Object> selectMenuInfs(MenuVO searchVO) throws Exception {
	List<MenuVO> result = menuDAO.selectMenuInfs(searchVO);
	int cnt = menuDAO.selectMenuInfsCnt(searchVO);

	Map<String, Object> map = new HashMap<String, Object>();

	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 메뉴 속성정보를 수정한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#updateMenuInf(egovframework.let.cop.bbs.brd.service.Menu)
     */
    public void updateMenuInf(MenuVO menu) throws Exception {
		menuDAO.updateMenuInf(menu);
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#validateTemplate(egovframework.let.cop.adm.service.brd.service.MenuVO)
     */
    public void validateTemplate(MenuVO searchVO) throws Exception {
    	log.debug("validateTemplate method ignored...");
    }

    /**
     * 메뉴 정보를 확인합니다.
     *
     * @param menuVO 메뉴 정보를 담고 있는 MenuVO 객체
     * @return 존재하는 고개인지 일치 여부 (true: 일치, false: 불일치)
     * @throws Exception 데이터베이스 조회 중 발생할 수 있는 예외
     */
    public boolean isMenu(MenuVO menuVO) throws Exception {
        int matchCount = menuDAO.selectMenuCount(menuVO);
        return matchCount > 0;
    }


}
