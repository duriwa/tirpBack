package egovframework.let.cop.adm.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.stereotype.Service;

import egovframework.let.cop.adm.service.UserMngService;
import egovframework.let.cop.adm.service.UserMngVO;
import egovframework.let.cop.com.service.EgovUserInfManageService;
import lombok.extern.slf4j.Slf4j;

/**
 * 사용자 속성관리를 위한 서비스 구현 클래스
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
@Service("UserMngService")
public class UserMngServiceImpl extends EgovAbstractServiceImpl implements UserMngService {

    @Resource(name = "UserMngDAO")
    private UserMngDAO userMngDAO;

    @Resource(name = "EgovUserInfManageService")
    private EgovUserInfManageService userService;

    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    /**
     * 등록된 사용자 속성정보를 삭제한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#deleteUserMngInf(egovframework.let.cop.bbs.brd.service.UserMng)
     */
    public void deleteUserMngInf(UserMngVO userMngVO) throws Exception {
	userMngDAO.deleteUserMngInf(userMngVO);

	UserMngVO bdUseInf = new UserMngVO();

	bdUseInf.setUserId(userMngVO.getUserId());
	bdUseInf.setLastProcId(userMngVO.getLastProcId());

	userMngDAO.deleteUserMngInf(bdUseInf);
    }

    /**
     * 신규 사용자 속성정보를 생성한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#insertUserMngInf(egovframework.let.cop.bbs.brd.service.UserMng)
     */
    public String insertUserMngInf(UserMngVO userMngVO) throws Exception {
	userMngDAO.insertUserMngInf(userMngVO);

	return userMngVO.getUserId();
    }


    /**
     * 사용자 속성정보 한 건을 상세조회한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#selectUserMngInf(egovframework.let.cop.adm.service.brd.service.UserMngVO)
     */
    public UserMngVO selectUserMngInf(UserMngVO searchVO) throws Exception {
	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------
	//return attrbMngDAO.selectUserMngInf(searchVO);

	UserMngVO result = userMngDAO.selectUserMngInf(searchVO);

	return result;
	////-------------------------------

    }

    /**
     * 사용자 속성 정보의 목록을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#selectUserMngInfs(egovframework.let.cop.adm.service.brd.service.UserMngVO)
     */
    public Map<String, Object> selectUserMngInfs(UserMngVO searchVO) throws Exception {
	List<UserMngVO> result = userMngDAO.selectUserMngInfs(searchVO);
	int cnt = userMngDAO.selectUserMngInfsCnt(searchVO);

	Map<String, Object> map = new HashMap<String, Object>();

	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 사용자 속성정보를 수정한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#updateUserMngInf(egovframework.let.cop.bbs.brd.service.UserMng)
     */
    public void updateUserMngInf(UserMngVO userMng) throws Exception {
		userMngDAO.updateUserMngInf(userMng);
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#validateTemplate(egovframework.let.cop.adm.service.brd.service.UserMngVO)
     */
    public void validateTemplate(UserMngVO searchVO) throws Exception {
    	log.debug("validateTemplate method ignored...");
    }

    /**
     * 사용자 정보를 확인합니다.
     *
     * @param userMngVO 사용자 정보를 담고 있는 UserMngVO 객체
     * @return 존재하는 고개인지 일치 여부 (true: 일치, false: 불일치)
     * @throws Exception 데이터베이스 조회 중 발생할 수 있는 예외
     */
    public boolean isUserMng(UserMngVO userMngVO) throws Exception {
        int matchCount = userMngDAO.selectUserMngCount(userMngVO);
        return matchCount > 0;
    }


}
