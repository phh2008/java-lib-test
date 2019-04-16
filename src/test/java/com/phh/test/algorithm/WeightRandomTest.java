package com.phh.test.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.algorithm
 * @date 2019/2/24
 */
public class WeightRandomTest {

    /**
     * 模拟奖品类
     */
    @AllArgsConstructor
    @ToString
    @Data
    public static class Gift {
        private long id;
        private String name;
        private int weight;//权重
    }

    private Random random = new Random();

    @Test
    public void test() {
        List<Gift> gifts = new ArrayList<>();
        gifts.add(new Gift(1, "1等奖", 1));
        gifts.add(new Gift(2, "2等奖", 10));
        gifts.add(new Gift(3, "3等奖", 20));
        gifts.add(new Gift(4, "4等奖", 35));
        gifts.add(new Gift(5, "5等奖", 70));
        gifts.add(new Gift(6, "6等奖", 100));
        Gift win = null;
        Map<String, Integer> record = new TreeMap<>();
        for (int i = 0; i < 1000; i++) {
            //多次抽奖
            win = lottery(gifts);
            //记录每个奖抽中次数
            record.compute(win.getName(), (k, v) -> v == null ? 1 : v + 1);
        }
        //打印
        record.forEach((k, v) -> {
            System.out.println(k + "抽中次数：" + v);
        });
    }

    /**
     * 参考dubbo负载均衡算法实现:权重随机
     *
     * @see com.alibaba.dubbo.rpc.cluster.loadbalance.RandomLoadBalance
     */
    private Gift lottery(List<Gift> gifts) {
        //总权重
        int weightTotal = gifts.stream().map(e -> e.getWeight()).reduce(0, Integer::sum);
        int offset = random.nextInt(weightTotal);
        for (Gift gift : gifts) {
            offset -= gift.weight;
            if (offset < 0) {
                return gift;
            }
        }
        //如果是相同权重的话
        return gifts.get(random.nextInt(gifts.size()));
    }

}
