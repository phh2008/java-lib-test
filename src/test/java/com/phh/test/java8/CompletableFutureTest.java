package com.phh.test.java8;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.java8
 * @date 2019/2/27
 */
public class CompletableFutureTest {


    @Test
    public void test1() {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world")//thenApply接收上一步结果，并返回future
                .thenApply(s -> s + " !")
                .thenAccept(s -> System.out.println(s));//接收上一步结果，并处理完结
        //.thenRun
    }

    @Test
    public void test2() {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world")
                .thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2019), (s, y) -> {
                    return s + ": " + y;
                })
                .thenAccept(r -> System.out.println(r));
        //thenCombine 是组合两个不相干的同时进行
        //thenCompose 是一个等待一个的结果才进行的，
    }

    @Test
    public void test3() {
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> "hello").thenApply(String::toUpperCase);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "world").thenApply(String::toUpperCase);
        //要全部完成,allof 反回void 拿不到结果的
        CompletableFuture.allOf(f, f2)
                .whenComplete((r, e) -> {
                    System.out.println(r);
                    System.out.println(e);
                })
                .thenAccept(e -> {
                    System.out.println(e);
                });
        System.out.println("----------------------------");
        //任一个完成，anyof 可以拿到第一个完成的结果
        CompletableFuture.anyOf(f, f2)
                .whenComplete((r, e) -> {
                    System.out.println(r);
                    System.out.println(e);
                })
                .thenAccept(e -> {
                    System.out.println(e);
                });
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            return 1 / 0;
        }).exceptionally(e -> {
            return 2;
        });
        System.out.println(f.get());
        System.out.println(f.join());
    }

    @Test
    public void test5() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> f = CompletableFuture.runAsync(() -> {
            int a = 1 / 0;
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return null;
        });
        System.out.println(f.get());
        System.out.println(f.join());
    }

}
