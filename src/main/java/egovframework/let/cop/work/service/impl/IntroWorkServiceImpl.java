package egovframework.let.cop.work.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.let.cop.work.service.IntroWorkService;
import egovframework.let.cop.work.service.IntroWorkVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 코드 종류 관리를 위한 서비스 구현 클래스
 * @author 공통 서비스 개발팀
 * @since 2024.03.14
 * @version 1.0
 */
@Slf4j
@Service("IntroWorkService")
public class IntroWorkServiceImpl extends EgovAbstractServiceImpl implements IntroWorkService {


    @Resource(name = "IntroWorkDAO")
    private IntroWorkDAO introWorkDAO;


    public int insWork(IntroWorkVO paramData) throws Exception {
       return introWorkDAO.insWork(paramData);
    }

    public List<IntroWorkVO> srchWork(String paramData) throws Exception {
        return introWorkDAO.srchWork(paramData);
     }
}