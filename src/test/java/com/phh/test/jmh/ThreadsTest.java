package com.phh.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.jmh
 * @date 2019/4/30
 */
public class ThreadsTest {


    @State(Scope.Benchmark)
    public static class VarState {
        volatile int a = 0;

        @Setup
        public void init() {
            a = 0;
        }
    }

    @Benchmark
    //@Threads(10)
    @GroupThreads(10)
    public void test(VarState state) throws InterruptedException {
        state.a = state.a + 1;
        int ret = state.a;
        Thread.sleep(50);
        System.out.println(Thread.currentThread().getName() + ":   ret:" + ret + " now:  " + state.a);

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ThreadsTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
