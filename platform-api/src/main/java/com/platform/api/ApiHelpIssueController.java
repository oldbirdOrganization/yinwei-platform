package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.HelpIssueVo;
import com.platform.entity.HelpTypeVo;
import com.platform.service.ApiHelpIssueService;
import com.platform.service.ApiHelpTypeService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-11-07 11:04:20
 */
@Api(tags = "常见帮助问题列表")
@RestController
@RequestMapping("/api/helpissue")
public class ApiHelpIssueController extends ApiBaseAction {
    @Autowired
    private ApiHelpIssueService helpIssueService;
    @Autowired
    private ApiHelpTypeService helpTypeService;

    /**
     * 查看帮助类型列表
     */
    @ApiOperation(value = "查看帮助类型列表")
    @IgnoreAuth
    @GetMapping("/typeList")
    public Object typeList() {
        List<HelpTypeVo> list = helpTypeService.queryList(new HashMap());
        return toResponsSuccess(list);
    }

    /**
     * 查看问题列表
     */
    @ApiOperation(value = "查看问题列表")
    @IgnoreAuth
    @GetMapping("/issueList")
    public Object issueList(Long type_id) {
        Map params = new HashMap();
        params.put("type_id", type_id);
        List<HelpIssueVo> helpIssueList = helpIssueService.queryList(params);
        return toResponsSuccess(helpIssueList);
    }
}
