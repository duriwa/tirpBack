package egovframework.let.cop.work.service.impl;

import java.util.HashMap;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.let.cop.work.service.IntroWorkVO;

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
@Repository("IntroWorkDAO")
public class IntroWorkDAO extends EgovAbstractMapper {

    public int insWork(IntroWorkVO paramData) throws Exception {
        return insert("IntroWorkDAO.insWork", paramData);
    }    


    @SuppressWarnings({ "deprecation", "unchecked" })
    public List<IntroWorkVO> srchWork(HashMap<String, String> paramData) throws Exception {
        System.out.println(paramData.toString());
        return (List<IntroWorkVO>)list("IntroWorkDAO.srchWork", paramData);
    }    

    public int insEtc(IntroWorkVO paramData) throws Exception {
        return insert("IntroWorkDAO.insEtc", paramData);
    }    

    @SuppressWarnings({ "deprecation", "unchecked" })
    public List<IntroWorkVO> srchEtc(HashMap<String, String> paramData) throws Exception {
        System.out.println("dao~~~~~");
        System.out.println(paramData.get("eduNm"));
        System.out.println(paramData.get("sawonCd"));
        return (List<IntroWorkVO>)list("IntroWorkDAO.srchEtc", paramData);
    }   
}
