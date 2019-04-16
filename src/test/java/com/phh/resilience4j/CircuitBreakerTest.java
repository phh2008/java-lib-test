package com.phh.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import org.junit.Test;

import java.util.function.Supplier;

/**
 * <p> 容断器测试
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.resilience4j
 * @date 2019/3/3
 */
public class CircuitBreakerTest {


    @Test
    public void test() {
        //获取默认配置的实例
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendName");
        //装饰方法
        Supplier<String> restrictedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
            return "test value";
        });

        Try<String> ret = Try.ofSupplier(restrictedSupplier).recover(ex -> {
            //有异常，常试恢复
            return "fail value";
        });

        System.out.println(ret.get());
    }


}
