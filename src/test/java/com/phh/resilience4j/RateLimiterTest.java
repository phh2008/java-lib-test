package com.phh.resilience4j;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.vavr.control.Try;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * <p> 限流测试
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.resilience4j
 * @date 2019/3/3
 */
public class RateLimiterTest {

    /**
     * 限制方法每次请求不高于1次/秒
     */
    @Test
    public void test() {
        //配置
        RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(100))
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(1)
                .build();
        //创建实例
        RateLimiter rateLimiter = RateLimiter.of("backendName", config);
        //装饰调用方法
        Supplier<String> restrictedSupplier = RateLimiter.decorateSupplier(rateLimiter, () -> {
            return "this is test value";
        });
        Try<String> firstTry = Try.ofSupplier(restrictedSupplier);
        Assert.assertTrue(firstTry.isSuccess());
        System.out.println(firstTry.get());
        Try<String> secondTry = Try.ofSupplier(restrictedSupplier);
        Assert.assertTrue(secondTry.isFailure());
        Assert.assertTrue(secondTry.getCause() instanceof RequestNotPermitted);
    }

}
