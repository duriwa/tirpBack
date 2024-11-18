package egovframework.let.cop.adm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.stereotype.Service;

import egovframework.let.cop.adm.service.CodeKindService;
import egovframework.let.cop.adm.service.CodeKindVO;
import lombok.extern.slf4j.Slf4j;

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

    @Resource(name = "egovCodeKindIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    /**
     * 코드 종류 정보를 삭제한다.
     */
    public void deleteCodeKindInf(CodeKindVO codeKindVO) throws Exception {
        codeKindDAO.deleteCodeKindInf(codeKindVO);
    }

    /**
     * 신규 코드 종류 정보를 등록한다.
     */
    public String insertCodeKindInf(CodeKindVO codeKindVO) throws Exception {
        codeKindDAO.insertCodeKindInf(codeKindVO);
        return "";
    }

    /**
     * 코드 종류 정보 한 건을 상세조회한다.
     */
    public CodeKindVO selectCodeKindInf(CodeKindVO searchVO) throws Exception {
        return codeKindDAO.selectCodeKindInf(searchVO);
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
     * 코드 종류가 존재하는지 확인한다.
     */
    public boolean existCodeKind(CodeKindVO codeKindVO) throws Exception {
        int cnt = codeKindDAO.existCodeKind(codeKindVO);
        return cnt > 0;
    }
}