package egovframework.let.cop.adm.web;

import java.util.HashMap;
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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.ResponseCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.ResultVO;
import egovframework.let.cop.adm.service.MenuService;
import egovframework.let.cop.adm.service.MenuVO;
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
@Tag(name="MenuController",description = "메뉴 속성관리")
public class MenuController {


	/** MenuService */
	@Resource(name = "MenuService")
	private MenuService menuService;

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
	 * 메뉴 목록을 조회한다.
	 *
	 * @param request
	 * @param menuVO
	 * @return resultVO
	 * @throws Exception
	 */
	@Operation(
			summary = "메뉴 조회",
			description = "메뉴 목록을 조회",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"MenuController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "조회 성공"),
			@ApiResponse(responseCode = "403", description = "인가된 메뉴가 아님")
	})
	@GetMapping(value = "/menu")
	public ResultVO selectMenuMasterInfs(HttpServletRequest request,
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
		MenuVO menuVO = new MenuVO();
		
		menuVO.setSearchCnd((String)commandMap.get("searchCnd"));
		menuVO.setSearchWrd((String)commandMap.get("searchWrd"));
		
		menuVO.setPageUnit(propertyService.getInt("Globals.pageUnit"));
		menuVO.setPageSize(propertyService.getInt("Globals.pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(menuVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(menuVO.getPageUnit());
		paginationInfo.setPageSize(menuVO.getPageSize());

		menuVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		menuVO.setLastIndex(paginationInfo.getLastRecordIndex());
		menuVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> resultMap = menuService.selectMenuInfs(menuVO);
		int totCnt = Integer.parseInt((String)resultMap.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		resultMap.put("paginationInfo", paginationInfo);

		resultVO.setResult(resultMap);
		resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
		resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}

	/**
	 * 신규 메뉴 정보를 등록한다.
	 *
	 * @param request
	 * @param menuVO
	 * @param bindingResult
	 * @return resultVO
	 * @throws Exception
	 */
	@Operation(
			summary = "메뉴 등록",
			description = "신규 메뉴 정보를 등록",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"MenuController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "조회 성공"),
			@ApiResponse(responseCode = "403", description = "인가된 메뉴가 아님"),
			@ApiResponse(responseCode = "900", description = "입력값 무결성 오류")
	})
	@PostMapping(value ="/menu")
	public ResultVO insertMenuInf(HttpServletRequest request,
									   MenuVO menuVO,
									   BindingResult bindingResult,
									   @Parameter(hidden = true) @AuthenticationPrincipal LoginVO loginVO
	)
		throws Exception {
		ResultVO resultVO = new ResultVO();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		menuVO.setFrstRegId(loginVO.getId());
		menuVO.setLastProcId(loginVO.getId());

		menuService.insertMenuInf(menuVO);

		resultVO.setResult(resultMap);
		resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
		resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}

	/**
	 * 메뉴 정보를 수정한다.
	 *
	 * @param request
	 * @param userId
	 * @param menuVO
	 * @param bindingResult
	 * @return resultVO
	 * @throws Exception
	 */
	@Operation(
			summary = "메뉴 수정",
			description = "메뉴 정보를 수정",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"MenuControllerController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "수정 성공"),
			@ApiResponse(responseCode = "403", description = "인가된 메뉴가 아님"),
			@ApiResponse(responseCode = "900", description = "입력값 무결성 오류")
	})
	@PutMapping(value ="/menu/{userId}")
	public ResultVO updateMenuMasterInf(HttpServletRequest request,
			@Parameter(name = "userId", description = "메뉴 Id", in = ParameterIn.PATH, example="MenuMSTR_AAAAAAAAAAAA")
				@PathVariable("userId") String userId,
				@RequestBody MenuVO menuVO,
				BindingResult bindingResult,
				@Parameter(hidden = true) @AuthenticationPrincipal LoginVO loginVO
			) throws Exception {
		ResultVO resultVO = new ResultVO();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		beanValidator.validate(menuVO, bindingResult);

		if (bindingResult.hasErrors()) {
			MenuVO vo = menuService.selectMenuInf(menuVO);

			resultMap.put("MenuVO", vo);

			resultVO.setResult(resultMap);
			resultVO.setResultCode(ResponseCode.INPUT_CHECK_ERROR.getCode());
			resultVO.setResultMessage(ResponseCode.INPUT_CHECK_ERROR.getMessage());
			return resultVO;
		}

		menuVO.setLastProcId(loginVO.getId());
		menuService.updateMenuInf(menuVO);

		resultVO.setResult(resultMap);
		resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
		resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}

	/**
	 * 메뉴 정보를 삭제한다.
	 *
	 * @param request
	 * @param userId
	 * @param menuVO
	 * @return resultVO
	 * @throws Exception
	 */
	@Operation(
			summary = "메뉴 삭제",
			description = "메뉴 정보를 삭제",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"MenuControllerController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "삭제 성공"),
			@ApiResponse(responseCode = "403", description = "인가된 메뉴가 아님")
	})
	@PatchMapping(value ="/menu/{userId}")
	public ResultVO deleteMenuMasterInf(HttpServletRequest request,
		@Parameter(hidden = true) @AuthenticationPrincipal LoginVO loginVO,
		@Parameter(name = "userId", description = "메뉴 Id", in = ParameterIn.PATH, example="MenuMSTR_AAAAAAAAAAAA")
		@PathVariable("userId") String userId
		) throws Exception {
		
			ResultVO resultVO = new ResultVO();
			MenuVO menuVO = new MenuVO();
			
			menuVO.setLastProcId(loginVO.getId());
			
			menuService.deleteMenuInf(menuVO);

			resultVO.setResultCode(ResponseCode.SUCCESS.getCode());
			resultVO.setResultMessage(ResponseCode.SUCCESS.getMessage());

		return resultVO;
	}

}
