package com.phh.test.jcasbin;

import org.casbin.jcasbin.main.Enforcer;
import org.junit.Test;

import java.util.List;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/29
 */
public class Test1 {


    @Test
    public void test1() {

        String path = this.getClass().getResource("/").getPath();
        Enforcer enforcer = new Enforcer(path+"/model.conf", path+"/policy.csv");

        String sub = "alice"; // the user that wants to access a resource.
        String obj = "data1"; // the resource that is going to be accessed.
        String act = "read"; // the operation that the user performs on the resource.

        if (enforcer.enforce(sub, obj, act)) {
            System.out.println("permit alice to read data1");
        } else {
            System.out.println("deny the request");
        }

        List<String> roles = enforcer.getRolesForUser("alice");
        System.out.println(roles);
    }

}
