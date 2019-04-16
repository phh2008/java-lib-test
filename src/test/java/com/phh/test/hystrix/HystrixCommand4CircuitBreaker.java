package com.phh.test.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 熔断机制相当于电路的跳闸功能，
 * 举个栗子，我们可以配置熔断策略为当请求错误比例在10s内>50%时，该服务将进入熔断状态，后续请求都会进入fallback。
 * 以demo为例，我们通过withCircuitBreakerRequestVolumeThreshold配置10s内请求数超过3个时熔断器开始生效，
 * 通过withCircuitBreakerErrorThresholdPercentage配置错误比例>80%时开始熔断
 * </p>
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.hystrix
 * @date 2019/3/3
 */
public class HystrixCommand4CircuitBreaker extends HystrixCommand<String> {

    private String name;

    protected HystrixCommand4CircuitBreaker(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CircuitBreakerTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("circuitBreakerTestKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("circuitBreakerTestKey"))
                //线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties
                        .Setter()
                        .withCoreSize(200)) // 测试配置线程池里的线程数，设置足够多线程，以防未熔断却打满threadpool
                .andCommandPropertiesDefaults(HystrixCommandProperties
                                .Setter()
                                // 配置熔断器
                                .withCircuitBreakerEnabled(true)
                                .withCircuitBreakerRequestVolumeThreshold(3)
                                .withCircuitBreakerErrorThresholdPercentage(80)
                        //.withCircuitBreakerForceOpen(true)	// 置为true时，所有请求都将被拒绝，直接到fallback
                        //.withCircuitBreakerForceClosed(true)	// 置为true时，将忽略错误
                        //.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)	// 信号量隔离
                        //.withExecutionTimeoutInMilliseconds(500000)
                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        if ("CircuitBreaker".equals(name)) {
            //模拟超时，使其触发fallback
            TimeUnit.SECONDS.sleep(1000000);
        } else {

        }
        return "ok: " + name;
    }

    @Override
    protected String getFallback() {
        return "fallback ::: " + name;
    }

    public static class Test {

        @org.junit.Test
        public void test() throws IOException {
            System.out.println(new HystrixCommand4CircuitBreaker("hello").execute());
            for (int i = 0; i < 10; i++) {
                System.out.println(new HystrixCommand4CircuitBreaker("CircuitBreaker").execute());
            }
            System.out.println(new HystrixCommand4CircuitBreaker("aaa").execute());
            System.out.println(new HystrixCommand4CircuitBreaker("bbb").execute());
            System.out.println(new HystrixCommand4CircuitBreaker("ccc").execute());

        }


    }

}
