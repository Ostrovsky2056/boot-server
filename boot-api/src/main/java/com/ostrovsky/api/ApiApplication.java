package com.ostrovsky.api;

import com.ostrovsky.common.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@Import(value = {SpringContextUtils.class})
public class ApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>项目启动成功！\n>>>>>>>>>>>>>>>>>>>>>>>>传入参数：" + (ArrayUtils.isEmpty(args) ? null : Arrays.toString(args)));
    }

}
