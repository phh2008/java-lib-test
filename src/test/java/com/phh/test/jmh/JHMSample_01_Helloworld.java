package com.phh.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
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
 * @date 2019/4/29
 */
public class JHMSample_01_Helloworld {


    @Benchmark
    public void wellHelloworld() {
        //this method was intentionally left blank.
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JHMSample_01_Helloworld.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
