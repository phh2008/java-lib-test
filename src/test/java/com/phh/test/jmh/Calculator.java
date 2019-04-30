package com.phh.test.jmh;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.jmh
 * @date 2019/4/29
 */
public interface Calculator {

    /**
     * calculate sum of an integer array
     *
     * @param numbers
     * @return
     */
    long sum(int[] numbers);

    /**
     * shutdown pool or reclaim any related resources
     */
    void shutdown();
}
