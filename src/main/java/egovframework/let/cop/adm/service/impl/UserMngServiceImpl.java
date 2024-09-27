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

    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
    // @Resource(name = "BBSAddedOptionsDAO")
    // private BBSAddedOptionsDAO addedOptionsDAO;
    ////-------------------------------

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
	String bbsId = idgenService.getNextStringId();

	userMngVO.setUserId(bbsId);

	userMngDAO.insertUserMngInf(userMngVO);

	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------
	// if (userMngVO.getOption().equals("comment") || userMngVO.getOption().equals("stsfdg")) {
	//     addedOptionsDAO.insertAddedOptionsInf(userMngVO);
	// }
	////-------------------------------

	// if ("Y".equals(userMngVO.getUseAt())) {
	//     // UserMngVO userMngVO = new UserMngVO();

	//     userMngVO.setUserId(bbsId);
	//     userMngVO.setFrstRegisterId(userMngVO.getFrstRegisterId());
	//     userMngVO.setUseAt("Y");

	//     userMngDAO.insertUserMngInf(userMngVO);

	//     UserInfVO userVO = new UserInfVO();
	//     userVO.setTrgetId(userMngVO.getTrgetId());

	//     List<UserInfVO> tmpList = null;
	//     Iterator<UserInfVO> iter = null;

	//     if ("REGC05".equals(userMngVO.getRegistSeCode())) {
	// 	tmpList = userService.selectAllClubUser(userVO);
	// 	iter = tmpList.iterator();
	// 	while (iter.hasNext()) {
	// 	    bdUseInf = new UserMngInf();

	// 	    bdUseInf.setBbsId(bbsId);
	// 	    bdUseInf.setTrgetId(((UserInfVO)iter.next()).getUniqId());
	// 	    bdUseInf.setRegistSeCode("REGC07");
	// 	    bdUseInf.setUseAt("Y");
	// 	    bdUseInf.setFrstRegisterId(userMngVO.getFrstRegisterId());

	// 	    bbsUseDAO.insertBBSUseInf(bdUseInf);
	// 	}
	//     } else if ("REGC06".equals(userMngVO.getRegistSeCode())) {
	// 	tmpList = userService.selectAllCmmntyUser(userVO);
	// 	iter = tmpList.iterator();
	// 	while (iter.hasNext()) {
	// 	    bdUseInf = new UserMngInf();

	// 	    bdUseInf.setBbsId(bbsId);
	// 	    bdUseInf.setTrgetId(((UserInfVO)iter.next()).getUniqId());
	// 	    bdUseInf.setRegistSeCode("REGC07");
	// 	    bdUseInf.setUseAt("Y");
	// 	    bdUseInf.setFrstRegisterId(userMngVO.getFrstRegisterId());

	// 	    bbsUseDAO.insertBBSUseInf(bdUseInf);
	// 	}
	//     }
	// }
	return bbsId;
    }

    /**
     * 사용자 속성 정보의 목록을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#selectAllBBSMasteInf(egovframework.let.cop.adm.service.brd.service.UserMngVO)
     */
    public List<UserMngVO> selectAllUserMngInf(UserMngVO vo) throws Exception {
	return userMngDAO.selectAllUserMngInf(vo);
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

	// String flag = propertyService.getString("Globals.addedOptions");
	// if (flag != null && flag.trim().equalsIgnoreCase("true")) {
	//     UserMngVO options = addedOptionsDAO.selectAddedOptionsInf(searchVO);

	//     if (options != null) {
	// 	if (options.getCommentAt().equals("Y")) {
	// 	    result.setOption("comment");
	// 	}

	// 	if (options.getStsfdgAt().equals("Y")) {
	// 	    result.setOption("stsfdg");
	// 	}
	//     } else {
	// 	result.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
	//     }
	// }

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

	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------
	// String flag = propertyService.getString("Globals.addedOptions");
	// if (flag != null && flag.trim().equalsIgnoreCase("true")) {
	//     if (boardMaster.getOption().equals("na")) {
	// 	return;
	//     }
	//     UserMngVO options = addedOptionsDAO.selectAddedOptionsInf(boardMaster);

	//     if (options == null) {
	// 	boardMaster.setFrstRegisterId(boardMaster.getLastUpdusrId());
	// 	addedOptionsDAO.insertAddedOptionsInf(boardMaster);
	//     } else {
	// 	//수정 기능 제외 (새롭게 선택사항을 지정한 insert만 처리함)
	// 	//addedOptionsDAO.updateAddedOptionsInf(boardMaster);
	// 	log.debug("BBS Master update ignored...");
	//     }
	// }
	////-------------------------------
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
     * 사용중인 사용자 속성 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectUserMngListByTrget(UserMngVO vo) throws Exception {
	List<UserMngVO> result = userMngDAO.selectUserMngListByTrget(vo);
	int cnt = userMngDAO.selectUserMngListCntByTrget(vo);

	Map<String, Object> map = new HashMap<String, Object>();

	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 커뮤니티, 동호회에서 사용중인 사용자 속성 정보의 목록을 전체조회 한다.
     */
    public List<UserMngVO> selectAllUserMngByTrget(UserMngVO vo) throws Exception {
	return userMngDAO.selectAllUserMngByTrget(vo);
    }

    /**
     * 사용중이지 않은 사용자 속성 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectNotUsedUserMngList(UserMngVO searchVO) throws Exception {
	List<UserMngVO> result = userMngDAO.selectNotUsedUserMngList(searchVO);
	int cnt = userMngDAO.selectNotUsedUserMngListCnt(searchVO);

	Map<String, Object> map = new HashMap<String, Object>();

	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

}
