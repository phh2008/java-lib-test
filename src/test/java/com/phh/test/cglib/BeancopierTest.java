package com.phh.test.cglib;

import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.cglib
 * @date 2019/2/25
 */
public class BeancopierTest {


    @Test
    public void test1() {
        //此对象一般要做缓存
        BeanCopier copier = BeanCopier.create(BeanA.class, BeanB.class, false);

        BeanA a = new BeanA(true, "tom", 23, 165.5, new Date(), "男", "", BeanA.Op.B, LocalDate.of(2018, 5, 12), LocalDateTime.now());
        System.out.println(a);
        BeanB b = new BeanB();
        copier.copy(a, b, null);
        System.out.println(b);
    }





}
