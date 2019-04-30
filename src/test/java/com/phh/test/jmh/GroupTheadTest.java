package com.phh.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.jmh
 * @date 2019/4/29
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class GroupTheadTest {

    ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    String[] letters = {"d", "e", "f", "g", "h", "i", "j", "k"};

    @Setup
    public void init() {
        cache.put("a", "a letter");
        cache.put("b", "b letter");
        cache.put("c", "c letter");
    }

    @Benchmark
    @GroupThreads(5)
    public String testPut() {
        String key = letters[cache.size() % letters.length];
        return cache.put(key, key + " letter");
    }

    @Benchmark
    @GroupThreads(5)
    public String testGet() {
        return cache.get("a");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(GroupTheadTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
