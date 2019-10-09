package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.FeedbackVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiFeedbackService;
import com.platform.util.ApiBaseAction;
import com.platform.vo.FeedbackRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 作者: @author oldbirdteam <br>
 * 时间: 2019-08-11 08:32<br>
 * 描述: ApiFeedbackController <br>
 */
@Api(tags = "反馈")
@RestController
@RequestMapping("/api/feedback")
public class ApiFeedbackController extends ApiBaseAction {
    @Autowired
    private ApiFeedbackService feedbackService;

    /**
     * 添加反馈
     */
    @ApiOperation(value = "添加反馈")
    @PostMapping("save")
    public Object save(@LoginUser UserVo loginUser,
                       @RequestBody @ApiParam(name="FeedbackRequest",value="添加反馈对象",required=true) FeedbackRequest feedbackRequest) {
        if (null != feedbackRequest) {
            FeedbackVo feedbackVo=new FeedbackVo();
            feedbackVo.setUserId(loginUser.getUserId().intValue());
            feedbackVo.setUserName(loginUser.getUsername());
            feedbackVo.setMobile(feedbackRequest.getMobile());
            feedbackVo.setFeedType(feedbackRequest.getIndex());
            feedbackVo.setStatus(1);
            feedbackVo.setContent(feedbackRequest.getContent());
            feedbackVo.setAddTime(new Date());
            feedbackService.save(feedbackVo);
            return super.toResponsSuccess("感谢你的反馈");
        }
        return super.toResponsFail("反馈失败");
    }
}