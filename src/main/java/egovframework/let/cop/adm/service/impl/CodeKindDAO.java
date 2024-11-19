package egovframework.let.cop.adm.service.impl;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.let.cop.adm.service.CodeKindVO;

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
@Repository("CodeKindDAO")
public class CodeKindDAO extends EgovAbstractMapper {

    /**
     * 등록된 코드 정보를 삭제한다.
     *
     * @param CodeKindVO
     */
    public void deleteCodeKindInf(CodeKindVO codeKindVo) throws Exception {
	delete("CodeKindDAO.deleteCodeKindInf", codeKindVo);
    }

    /**
     * 신규 공통코드 정보를 등록한다.
     *
     * @param CodeKind
     */
    public int insertCodeKindInf(CodeKindVO codeKindVo) throws Exception {
	return (int)insert("CodeKindDAO.insertCodeKindInf", codeKindVo);
    }

    /**
     * 공통코드 정보 한 건을 상세조회 한다.
     *
     * @param CodeKindVO
     */
    public CodeKindVO selectCodeKindInf(CodeKindVO codeKindVo) throws Exception {
	return (CodeKindVO)selectOne("CodeKindDAO.selectCodeKindInf", codeKindVo);
    }

    /**
     * 공통코드 정보 목록을 조회한다.
     *
     * @param CodeKindVO
     */
    @SuppressWarnings("unchecked")
    public List<CodeKindVO> selectCodeKindInfs(CodeKindVO vo) throws Exception {
        return selectList("CodeKindVO.selectCodeKindInfs", vo);
    }

    /**
     * 공통코드 정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectCodeKindInfsCnt(CodeKindVO vo) throws Exception {
	return (Integer)selectOne("CodeKindVO.selectCodeKindInfsCnt", vo);
    }

    /**
     * 공통코드 정보를 수정한다.
     *
     * @param CodeKind
     */
    public void updateCodeKindInf(CodeKindVO codeKindVo) throws Exception {
	update("CodeKindVO.updateCodeKindInf", codeKindVo);
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     *
     * @param CodeKindVO
     */
    public boolean validateTemplate(CodeKindVO vo) throws Exception {
	return true;
    }

    public int existCodeKind(CodeKindVO codeKindVO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existCodeKind'");
    }

    public List<CodeKindVO> selectCodeKindList(CodeKindVO searchVO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectCodeKindList'");
    }

    public int selectCodeKindListCnt(CodeKindVO searchVO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectCodeKindListCnt'");
    }

    



    
    
}
