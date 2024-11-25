package egovframework.let.cop.work.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.cmm.ResponseCode;
import egovframework.com.cmm.service.ResultVO;
import egovframework.let.cop.work.service.IntroWorkService;
import egovframework.let.cop.work.service.IntroWorkVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 메뉴 속성관리를 위한 컨트롤러  클래스
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
 *  2009.06.26	한성곤		2단계 기능 추가 (댓글관리, 만족도조사)
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@RestController
@Tag(name="InroWorkController",description = "주간보고")
public class IntroWorkController {


	@Resource(name = "IntroWorkService")
	private IntroWorkService itWorkService;
	
		/** MenuService */
	
		@Operation(
				summary = "주간보고",
				description = "주간보고",
				security = {@SecurityRequirement(name = "Authorization")},
				tags = {"inroWorkController"}
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공")
		})
		@PostMapping(value = "/insWork")
		public ResultVO insWork (@RequestBody IntroWorkVO paramData)
			throws Exception {
	
			ResultVO resultVO = new ResultVO();
			
			System.out.println("insWork ===== POST ");
	
	
			int insData = this.itWorkService.insWork(paramData);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", insData);
 
		resultVO.setResult(resultMap);
		resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
		resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}


	@PostMapping(value = "/srchWork")
		public ResultVO srchWork (HttpServletRequest request) throws Exception {
	
			ResultVO resultVO = new ResultVO();
			
			System.out.println("srchWork ===== POST");
	
			// String 사번 = "";
			List<IntroWorkVO> srchData = new ArrayList<IntroWorkVO>();

			srchData = itWorkService.srchWork("70020");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", srchData);
 
		resultVO.setResult(resultMap);
		resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
		resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}

}
