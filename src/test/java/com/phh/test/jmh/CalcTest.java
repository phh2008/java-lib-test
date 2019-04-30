package com.phh.test.jmh;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 测试示例：
 * 计算 1 ~ n 之和，比较串行算法和并行算法的效率，看 n 在大约多少时并行算法开始超越串行算法
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.jmh
 * @date 2019/4/29
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class CalcTest {

    /**
     * `@Param` 可以用来指定某项参数的多种情况。
     * 特别适合用来测试一个函数在不同的参数输入的情况下的性能
     */
    @Param({"10000", "100000", "1000000"})
    private int length;
    private int[] numbers;
    private Calculator singleThreadCalc;
    private Calculator multiThreadCalc;

    /**
     * `@Setup` 会在执行 benchmark 之前被执行，正如其名，主要用于初始化
     */
    @Setup
    public void before() {
        numbers = IntStream.rangeClosed(1, length).toArray();
        singleThreadCalc = new SinglethreadCalculator();
        multiThreadCalc = new MultithreadCalculator(Runtime.getRuntime().availableProcessors());
    }


    //@Benchmark
    public long singleThreadBechmark() {
        return singleThreadCalc.sum(numbers);
    }

    @Benchmark
    public long multithreadBenchmark() {
        return multiThreadCalc.sum(numbers);
    }

    /**
     * 测试结果：当数据时达到1000000时多线程才比较高效
     * 多线程：
     * CalcTest.multithreadBenchmark     10000  avgt    5   22.284 ± 1.586  us/op
     * CalcTest.multithreadBenchmark    100000  avgt    5   40.404 ± 0.570  us/op
     * CalcTest.multithreadBenchmark   1000000  avgt    5  150.762 ± 3.390  us/op
     * <p>
     * 单线程：
     * CalcTest.singleThreadBechmark     10000  avgt    5    3.414 ±  0.017  us/op
     * CalcTest.singleThreadBechmark    100000  avgt    5   34.098 ±  0.228  us/op
     * CalcTest.singleThreadBechmark   1000000  avgt    5  396.948 ± 70.924  us/op
     *
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CalcTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    /**
     * `@TearDown` 和 @Setup 相对的，会在所有 benchmark 执行结束以后执行，主要用于资源的回收等。
     */
    @TearDown
    public void after() {
        singleThreadCalc.shutdown();
        multiThreadCalc.shutdown();
    }

    @Test
    public void junitTest() {
        //这里只是看下计算结果
        MultithreadCalculator calc = new MultithreadCalculator(5);
        int[] numbers = IntStream.rangeClosed(1, 100000).toArray();
        long sum = calc.sum(numbers);
        System.out.println("sum:  " + sum);

        SinglethreadCalculator calc2 = new SinglethreadCalculator();
        long sum2 = calc2.sum(numbers);
        System.out.println("sum2: " + sum2);
    }

}
