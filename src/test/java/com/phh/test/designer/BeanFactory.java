package com.phh.test.designer;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/3/13
 */
public class BeanFactory {

    //普通工厂方法
    public Bean getBean(String args) {
        if ("bean1".equals(args)) {
            return new Bean1();
        } else {
            return new Bean2();
        }
    }

    //静态工厂方法
    public static Bean getBean1() {
        return new Bean1();
    }

    public static Bean getBean2() {
        return new Bean2();
    }
}
