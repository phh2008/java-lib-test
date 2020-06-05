package com.phh.test.designer;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/3/13
 */
public class SingleInstance2 {

    private SingleInstance2() {
    }


    //利用类的初始化机制来涎迟初始化实例
    public static class SingleInstanceFactory {
        private static SingleInstance2 instance2 = new SingleInstance2();
    }

    public static SingleInstance2 getInstance() {
        return SingleInstanceFactory.instance2;
    }

}
