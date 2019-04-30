package com.phh.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.jmh
 * @date 2019/4/30
 */
public class BatchTest {


    @Benchmark
    @Warmup(iterations = 5, batchSize = 10)
    @Measurement(iterations = 5, batchSize = 10)
    @BenchmarkMode(Mode.SingleShotTime)
    public void measureRight() {
        System.out.println("abc");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BatchTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
