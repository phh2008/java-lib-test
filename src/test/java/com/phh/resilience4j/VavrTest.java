package com.phh.resilience4j;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.vavr.API;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Assert;
import org.junit.Test;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.API.run;
import static io.vavr.Predicates.isIn;

/**
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.resilience4j
 * @date 2019/4/16
 */
public class VavrTest {
    /**
     * io.vavr java8函数扩展库测试
     */

    /**
     * 类似于jdk的optional
     */
    @Test
    public void test() {
        Option<Object> option1 = Option.of(null);
        Option<String> option2 = Option.of("abc");

        System.out.println(option1.toString());
        System.out.println(option2.toString());

    }

    class T extends TypeReference<Tuple3<String, Boolean, Integer>> {
    }

    /**
     * 元组，可包装多反回值
     */
    @Test
    public void test2() {
        Tuple3<String, Boolean, Integer> tuple = Tuple.of("abc", true, 23);
        String var1 = tuple._1;
        Boolean var2 = tuple._2();
        Integer var3 = tuple._3;
        Assert.assertTrue("abc".equals(var1));
        Assert.assertTrue(var2);
        Assert.assertTrue(var3 == 23);

        //序列化
        String json = JSON.toJSONString(tuple);
        System.out.println("json>>>>:" + json);
        Tuple3<String, Boolean, Integer> o = JSON.parseObject(json, new T().getType());
        System.out.println(o);
    }

    /**
     * Try容器，包装可能有异常的代码
     */
    @Test
    public void test3() {
        Try<Integer> ret = Try.ofSupplier(() -> 3 / 0);

        //是否有异常
        Assert.assertTrue(ret.isFailure());

        //有异常也可给定其它值
        Assert.assertEquals(ret.getOrElse(-1).intValue(), -1);

        //也可抛出错常
        ret.getOrElseThrow(e -> new RuntimeException(" / by zero !!!!!!!"));

    }

    /**
     * 扩展多参数的Function
     */
    @Test
    public void test4() {
        Function2<Double, Double, Double> funcSum = Function2.of(Double::sum);
        Double retSum = funcSum.apply(3.0, 4.0);
        Function3<String, String, String, String> func = (a, b, c) -> a + b + c;
        Assert.assertEquals(retSum.doubleValue(), 7.0D);

        String ret = func.apply("a", "b", "c");

        Assert.assertEquals(ret, "abc");
    }

    /**
     * 集合
     */
    @Test
    public void test5() {
        List<Integer> list = List.of(1, 2, 3);
        System.out.println(list);
    }


    /**
     * Lazy容器，延迟计算
     */
    @Test
    public void test6() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        //还未调用math.random
        Assert.assertFalse(lazy.isEvaluated());

        //去get结果才去计算
        Double num1 = lazy.get();
        Assert.assertTrue(lazy.isEvaluated());

        Double num2 = lazy.get();
        Assert.assertTrue(lazy.isEvaluated());

        //两次get数据是一样的，计算结果被缓存了
        Assert.assertTrue(num1.equals(num2));
    }

    /**
     * match替换switch
     */
    @Test
    public void test7() {
        int input = 5;
        String output = Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(), "default")
        );
        System.out.println(output);
    }

    private static Either<String, Integer> computeWithEither(int marks) {
        if (marks < 85) {
            return Either.left("Marks not acceptable");
        } else {
            return Either.right(marks);
        }
    }

    @Test
    public void test8() {
        Either<String, Integer> either = computeWithEither(80);
        System.out.println(either.isLeft());
        System.out.println(either.isRight());
        System.out.println(either.getLeft());
        //System.out.println(either.right().get());
        System.out.println(either.left().get());
        System.out.println(either.contains(80));

        Either<Integer, String> either2 = either.swap();
    }
}
