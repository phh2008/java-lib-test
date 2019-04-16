package com.phh.test.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

/**
 * <p>
 * hystrix支持将一个请求结果缓存起来，下一个具有相同key的请求将直接从缓存中取出结果，减少请求开销。
 * 要使用hystrix cache功能，第一个要求是重写getCacheKey()，用来构造cache key；
 * 第二个要求是构建context，如果请求B要用到请求A的结果缓存，A和B必须同处一个context。
 * 通过HystrixRequestContext.initializeContext()和context.shutdown()可以构建一个context，
 * 这两条语句间的所有请求都处于同一个context
 * </p>
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.hystrix
 * @date 2019/3/3
 */
public class HystrixCommand4RequestCache extends HystrixCommand<String> {

    private String key;

    protected HystrixCommand4RequestCache(String key) {
        super(HystrixCommandGroupKey.Factory.asKey("testCacheGroup"));
        this.key = key;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("run invoke...........");
        return "mock val - " + key.hashCode();
    }

    @Override
    protected String getCacheKey() {
        return "key:" + this.key;
    }


    public static class Test {

        @org.junit.Test
        public void testWithoutCache() {
            HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
            try {
                System.out.println(new HystrixCommand4RequestCache("aa").execute());
                System.out.println(new HystrixCommand4RequestCache("bb").execute());
            } finally {
                ctx.shutdown();
            }
        }

        @org.junit.Test
        public void testWithCache() {
            HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
            try {
                System.out.println(new HystrixCommand4RequestCache("aa").execute());
                System.out.println(new HystrixCommand4RequestCache("aa").execute());
                System.out.println(new HystrixCommand4RequestCache("aa").execute());
            } finally {
                ctx.shutdown();
            }
        }

    }

}
