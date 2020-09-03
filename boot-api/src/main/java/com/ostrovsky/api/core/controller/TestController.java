package com.ostrovsky.api.core.controller;

import com.ostrovsky.common.base.IResult;
import com.ostrovsky.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: TestController</p>
 * <p>Description: 测试</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/1 17:17
 */
@Api(tags = "测试模块")
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试接口
     *
     * @param message 消息
     * @return {@link IResult<String>} 消息
     * @author ostrovsky
     * @since 2020/9/2 14:51
     */
    @ApiOperation("测试接口")
    @GetMapping
    public IResult<String> test(@RequestParam("message") String message) {
        return new Result<String>().success(message);
    }
}
