package com.phh.test.designer;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/3/13
 */
public class Bean2Factory implements AbstractFactory {
    @Override
    public Bean getBean() {
        return new Bean2();
    }
}
