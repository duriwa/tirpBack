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
     * 코드 종류 정보를 수정한다.
     */
    public void updateCodeKindInf(CodeKindVO codeKindVO) throws Exception {
        codeKindDAO.updateCodeKindInf(codeKindVO);
    }

    // ... existing code ...
    public Map<String, Object> selectCodeKindInfs(CodeKindVO codeKindVO) throws Exception {
        List<CodeKindVO> result = codeKindDAO.selectCodeKindInfs(codeKindVO);
        Map<String, Object> map = new HashMap<>();
        map.put("resultList", result);
        return map;
    }
}