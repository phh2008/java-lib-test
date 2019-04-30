package com.phh.test.jmh;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.jmh
 * @date 2019/4/29
 */
public class MultithreadCalculator implements Calculator {

    private final int nThreads;
    private final ExecutorService pool;

    public MultithreadCalculator() {
        this.nThreads = Runtime.getRuntime().availableProcessors();
        this.pool = Executors.newFixedThreadPool(nThreads);
    }

    public MultithreadCalculator(int nThreads) {
        this.nThreads = nThreads;
        this.pool = Executors.newFixedThreadPool(nThreads);
    }


    public long sum(int[] numbers) {
        //计算每个线程计算数量
        int len = numbers.length;
        int chunk = len / nThreads;
        //多线程执行
        //这种流操作性比循环差
        /*List<CompletableFuture<Long>> futureList = IntStream.range(0, nThreads)
                .mapToObj(i -> Arrays.stream(numbers).skip(i * chunk).limit(chunk))
                .map(e -> CompletableFuture.supplyAsync(() -> e.asLongStream().sum(), pool))
                .collect(Collectors.toList());*/
        //内部用for较少影响性能
        List<CompletableFuture<Long>> futureList = IntStream.range(0, nThreads)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> {
                            int from = i * chunk;
                            int to = from + chunk;
                            long sum = 0L;
                            for (int a = from; a < to && a < len; a++) {
                                sum += numbers[a];
                            }
                            return sum;
                        })
                )
                .collect(Collectors.toList());
        return futureList.stream()
                .map(CompletableFuture::join)
                .reduce(0L, Long::sum);
    }

    @Override
    public void shutdown() {
        pool.shutdown();
    }


}
