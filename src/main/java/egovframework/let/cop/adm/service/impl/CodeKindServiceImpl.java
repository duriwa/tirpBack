package egovframework.let.cop.adm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import egovframework.let.cop.adm.service.CodeKindService;
import egovframework.let.cop.adm.service.CodeKindVO;
import egovframework.let.cop.com.service.EgovUserInfManageService;


/**
 * 코드 종류 관리를 위한 서비스 구현 클래스
 * @author 공통 서비스 개발팀
 * @since 2024.03.14
 * @version 1.0
 */
@Slf4j
@Service("CodeKindService")
public class CodeKindServiceImpl extends EgovAbstractServiceImpl implements CodeKindService {

    @Resource(name = "CodeKindDAO")
    private CodeKindDAO codeKindDAO;

    @Resource(name = "EgovUserInfManageService")
    private EgovUserInfManageService userService;

    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    /**
     * 코드 종류 정보를 삭제한다.
     */
    public void deleteCodeKindInf(CodeKindVO codeKindVO) throws Exception {
        codeKindDAO.deleteCodeKindInf(codeKindVO);

        CodeKindVO cKindVO = new CodeKindVO();

        cKindVO.setLastProcId(codeKindVO.getCdKindNo());

        codeKindDAO.deleteCodeKindInf(cKindVO);
    }

    /**
     * 신규 코드 종류 정보를 등록한다.
     */
    public String insertCodeKindInf(CodeKindVO codeKindVO) throws Exception {
        codeKindDAO.insertCodeKindInf(codeKindVO);
        return null;
    }

    /**
     * 메뉴 속성정보 한 건을 상세조회한다.
     *
     * @see egovframework.let.cop.bbs.brd.service.EgovBBSAttributeManageService#selectMenuInf(egovframework.let.cop.adm.service.brd.service.MenuVO)
    */
    public CodeKindVO selectCodeKindInf(CodeKindVO searchVO) throws Exception {

	CodeKindVO result = codeKindDAO.selectCodeKindInf(searchVO);
	return result;
	////-------------------------------

    }

    /**
     * 코드 종류 정보 한 건을 상세조회한다.
     */
    public Map<String, Object> selectCodeKindInfs(CodeKindVO searchVO) throws Exception {
        List<CodeKindVO> result = codeKindDAO.selectCodeKindInfs(searchVO);
        int cnt = codeKindDAO.selectCodeKindInfsCnt(searchVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 코드 종류 정보의 목록을 조회한다.
     */
    public Map<String, Object> selectCodeKindList(CodeKindVO searchVO) throws Exception {
        List<CodeKindVO> result = codeKindDAO.selectCodeKindList(searchVO);
        int cnt = codeKindDAO.selectCodeKindListCnt(searchVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

      /**
     * 코드 종류 정보를 수정한다.
     */
    public void updateCodeKindInf(CodeKindVO codeKindVO) throws Exception {
        codeKindDAO.updateCodeKindInf(codeKindVO);
    }

    /**
     * 코드 종류가 유효한지 확인한다.
     */
    public boolean isCode(CodeKindVO codeKindVO) throws Exception {
        return codeKindDAO.validateTemplate(codeKindVO);
    }

    public void validateTemplate(CodeKindVO codeKindVO) throws Exception {
        codeKindDAO.validateTemplate(codeKindVO);
    }

}