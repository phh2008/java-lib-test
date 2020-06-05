package com.phh.test.designer;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/3/13
 */
public class SingleInstance {


    private volatile static SingleInstance instance;

    private SingleInstance() {
    }

    public static SingleInstance getInstance() {
        //双重检查
        if (instance == null) {
            synchronized (SingleInstance.class) {
                if (instance == null) {
                    instance = new SingleInstance();
                }
            }
        }
        return instance;
    }


}
