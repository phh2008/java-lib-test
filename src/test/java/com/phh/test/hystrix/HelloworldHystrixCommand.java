package com.phh.test.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.hystrix
 * @date 2019/3/3
 */
public class HelloworldHystrixCommand extends HystrixCommand<String> {

    private String name;

    public HelloworldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("commandGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        //处理方法
        return "hello " + name + " ！";
    }


    public static class Test {
        @org.junit.Test
        public void test() {

            HelloworldHystrixCommand command = new HelloworldHystrixCommand("world");
            //同步执行
            String result = command.execute();
            System.out.println(result);
        }
    }

}
