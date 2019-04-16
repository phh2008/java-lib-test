package com.phh.resilience4j;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import static io.github.resilience4j.retry.RetryConfig.DEFAULT_WAIT_DURATION;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.resilience4j
 * @date 2019/4/16
 */
public class RetryTest {
    RetryConfig config = RetryConfig.custom()
            //重试次数
            .maxAttempts(5)
            //重试间隔
            .waitDuration(Duration.ofMillis(1000L))
            //xx异常触发重试
            .retryExceptions(Exception.class)
            //xx异常不重试
            .ignoreExceptions(NullPointerException.class)
            .retryOnException(throwable -> throwable instanceof RuntimeException)
            //以结果触发重试
            .retryOnResult(resp -> resp.toString().contains("retry"))
            .build();

    Retry retry = Retry.of("test", config);

    @Test
    public void test() {
        AtomicLong adder = new AtomicLong(0);
        Supplier<Long> ret = Retry.decorateSupplier(retry, () -> {
            //业务处理
            if (adder.incrementAndGet() < 3) {
                System.out.println("异常，触发重试");
                throw new RuntimeException("error");
            }
            return adder.get();
        });
        System.out.println(ret.get());
    }

}
