package com.phh.test.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p> fallback（降级）
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.hystrix
 * @date 2019/3/3
 */
@Slf4j
public class HystrixCommandFailcallback extends HystrixCommand<List<String>> {

    private boolean throwEx;

    protected HystrixCommandFailcallback(boolean throwEx) {
        super(HystrixCommandGroupKey.Factory.asKey("testGroup"));
        this.throwEx = throwEx;
    }

    @Override
    protected List<String> run() throws Exception {
        if (throwEx) {
            //非HystrixBadRequestException异常，则会触发getFallback
            throw new RuntimeException("error");
        }
        return Arrays.asList("hello", "world");
    }

    @Override
    protected List<String> getFallback() {
        //将级处理
        return Collections.emptyList();
    }

    public static class Test {

        @org.junit.Test
        public void testSuccess() {

            log.info("list :{}", new HystrixCommandFailcallback(false).execute());
        }

        @org.junit.Test
        public void testFail() {
            log.info("list :{}", new HystrixCommandFailcallback(true).execute());
        }

    }
}
