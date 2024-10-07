package egovframework.let.cop.adm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.ResponseCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.ResultVO;
import egovframework.let.cop.adm.service.UserMngService;
import egovframework.let.cop.adm.service.UserMngVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 사용자 속성관리를 위한 컨트롤러  클래스
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
@Tag(name="UserMngController",description = "사용자 속성관리")
public class UserMngController {


	/** UserMngService */
	@Resource(name = "UserMngService")
	private UserMngService userMngService;

	/** EgovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	/** DefaultBeanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 사용자 목록을 조회한다.
	 *
	 * @param request
	 * @param userMngVO
	 * @return resultVO
	 * @throws Exception
	 */
	@Operation(
			summary = "사용자 조회",
			description = "사용자 목록을 조회",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"UserMngController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "조회 성공"),
			@ApiResponse(responseCode = "403", description = "인가된 사용자가 아님")
	})
	@GetMapping(value = "/userMng")
	public ResultVO selectUserMngMasterInfs(HttpServletRequest request,
		@Parameter(
				in = ParameterIn.QUERY,
				schema = @Schema(type = "object",
				additionalProperties = Schema.AdditionalPropertiesValue.TRUE, 
				ref = "#/components/schemas/searchMap"),
				style = ParameterStyle.FORM,
				explode = Explode.TRUE
				) @RequestParam Map<String, Object> commandMap)
		throws Exception {

		ResultVO resultVO = new ResultVO();
		UserMngVO userMngVO = new UserMngVO();
		
		userMngVO.setSearchCnd((String)commandMap.get("searchCnd"));
		userMngVO.setSearchWrd((String)commandMap.get("searchWrd"));
		
		userMngVO.setPageUnit(propertyService.getInt("Globals.pageUnit"));
		userMngVO.setPageSize(propertyService.getInt("Globals.pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(userMngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userMngVO.getPageUnit());
		paginationInfo.setPageSize(userMngVO.getPageSize());

		userMngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userMngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userMngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> resultMap = userMngService.selectUserMngInfs(userMngVO);
		int totCnt = Integer.parseInt((String)resultMap.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		resultMap.put("paginationInfo", paginationInfo);

		resultVO.setResult(resultMap);
		resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
		resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}

	/**
	 * 사용자 상세내용을 조회한다.
	 *
	 * @param request
	 * @param searchVO
	 * @return resultVO
	 * @throws Exception
	 */
	@Operation(
			summary = "사용자 상세 조회",
			description = "사용자 상세내용을 조회",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"UserMngControllerController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "조회 성공"),
			@ApiResponse(responseCode = "403", description = "인가된 사용자가 아님")
	})
	@GetMapping(value ="/userMng/{userId}")
	public ResultVO selectUserMngMasterInf(HttpServletRequest request,
			@Parameter(name = "userId", description = "사용자 Id", in = ParameterIn.PATH, example="UserMngMSTR_AAAAAAAAAAAA") 
			@PathVariable("userId") String userId)
		throws Exception {
		ResultVO resultVO = new ResultVO();
		UserMngVO searchVO = new UserMngVO();
		
		searchVO.setUserId(userId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserMngVO vo = userMngService.selectUserMngInf(searchVO);
		resultMap.put("userMngVO", vo);
				
		resultVO.setResult(resultMap);
		resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
		resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		// return "cop/user/EgovBoardMstrUpdt";
		return resultVO;
	}

	/**
	 * 신규 사용자 정보를 등록한다.
	 *
	 * @param request
	 * @param userMngVO
	 * @param bindingResult
	 * @return resultVO
	 * @throws Exception
	 */
	@Operation(
			summary = "사용자 등록",
			description = "신규 사용자 정보를 등록",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"UserMngControllerController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "조회 성공"),
			@ApiResponse(responseCode = "403", description = "인가된 사용자가 아님"),
			@ApiResponse(responseCode = "900", description = "입력값 무결성 오류")
	})
	@PostMapping(value ="/userMng")
	public ResultVO insertUserMngMasterInf(HttpServletRequest request,
									   UserMngVO userMngVO,
									   BindingResult bindingResult,
									   @Parameter(hidden = true) @AuthenticationPrincipal LoginVO loginVO
	)
		throws Exception {
		ResultVO resultVO = new ResultVO();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		beanValidator.validate(userMngVO, bindingResult);
		if (bindingResult.hasErrors()) {

			ComDefaultCodeVO vo = new ComDefaultCodeVO();

			vo.setCodeId("COM004");

			List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);

			resultMap.put("typeList", codeResult);

			vo.setCodeId("COM009");

			codeResult = cmmUseService.selectCmmCodeDetail(vo);

			resultMap.put("attrbList", codeResult);

			resultVO.setResult(resultMap);
			resultVO.setResultCode(ResponseCode.INPUT_CHECK_ERROR.getCode());
			resultVO.setResultMessage(ResponseCode.INPUT_CHECK_ERROR.getMessage());
			
			return resultVO;
		}

		// userMngVO.setFrstRegisterId(loginVO.getUniqId());
		userMngVO.setUseAt("Y");
		// userMngVO.setTrgetId("SYSTEMDEFAULT_REGIST");

		userMngService.insertUserMngInf(userMngVO);

		resultVO.setResult(resultMap);
		resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
		resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}

	/**
	 * 사용자 정보를 수정한다.
	 *
	 * @param request
	 * @param userId
	 * @param userMngVO
	 * @param bindingResult
	 * @return resultVO
	 * @throws Exception
	 */
	@Operation(
			summary = "사용자 수정",
			description = "사용자 정보를 수정",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"UserMngControllerController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "수정 성공"),
			@ApiResponse(responseCode = "403", description = "인가된 사용자가 아님"),
			@ApiResponse(responseCode = "900", description = "입력값 무결성 오류")
	})
	@PutMapping(value ="/userMng/{userId}")
	public ResultVO updateUserMngMasterInf(HttpServletRequest request,
			@Parameter(name = "userId", description = "사용자 Id", in = ParameterIn.PATH, example="UserMngMSTR_AAAAAAAAAAAA")
				@PathVariable("userId") String userId,
				@RequestBody UserMngVO userMngVO,
				BindingResult bindingResult,
				@Parameter(hidden = true) @AuthenticationPrincipal LoginVO loginVO
			) throws Exception {
		ResultVO resultVO = new ResultVO();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		beanValidator.validate(userMngVO, bindingResult);

		if (bindingResult.hasErrors()) {
			UserMngVO vo = userMngService.selectUserMngInf(userMngVO);

			resultMap.put("UserMngVO", vo);

			resultVO.setResult(resultMap);
			resultVO.setResultCode(ResponseCode.INPUT_CHECK_ERROR.getCode());
			resultVO.setResultMessage(ResponseCode.INPUT_CHECK_ERROR.getMessage());
			return resultVO;
		}

		// userMngVO.setLastUpdusrId(loginVO.getUniqId());
		// userMngVO.setPosblAtchFileSize(propertyService.getString("posblAtchFileSize"));
		userMngService.updateUserMngInf(userMngVO);

		resultVO.setResult(resultMap);
		resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
		resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}

	/**
	 * 사용자 정보를 삭제한다.
	 *
	 * @param request
	 * @param userId
	 * @param userMngVO
	 * @return resultVO
	 * @throws Exception
	 */
	@Operation(
			summary = "사용자 삭제",
			description = "사용자 정보를 삭제",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"UserMngControllerController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "삭제 성공"),
			@ApiResponse(responseCode = "403", description = "인가된 사용자가 아님")
	})
	@PatchMapping(value ="/userMng/{userId}")
	public ResultVO deleteUserMngMasterInf(HttpServletRequest request,
		@Parameter(hidden = true) @AuthenticationPrincipal LoginVO loginVO,
		@Parameter(name = "userId", description = "사용자 Id", in = ParameterIn.PATH, example="UserMngMSTR_AAAAAAAAAAAA")
		@PathVariable("userId") String userId
		) throws Exception {
		
			ResultVO resultVO = new ResultVO();
			UserMngVO userMngVO = new UserMngVO();
			
			userMngVO.setLastProcId(loginVO.getId());
			userMngVO.setUserId(userId);
			
			userMngService.deleteUserMngInf(userMngVO);

			resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
			resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}

}
