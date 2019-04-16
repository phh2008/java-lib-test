package com.phh.test.cglib;

import net.sf.cglib.beans.BeanMap;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.cglib
 * @date 2019/2/25
 */
public class BeanmapTest {

    @Test
    public void testBean2Map() {
        BeanC c = new BeanC(false, "tom", 25, 165.6, new Date(), LocalDate.now());
        Map<String, Object> map = beanToMap(c);
        System.out.println(map);
    }

    @Test
    public void testMap2Bean() {
        Map<String, Object> map = new HashMap<>();
        map.put("open", true);
        map.put("birthday", new Date());
        map.put("name", "tom");
        map.put("age", 28);
        map.put("height", 170D);
        map.put("localDate", LocalDate.now());
        BeanC bean = new BeanC();
        mapToBean(map, bean);
        System.out.println(bean);
    }


    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
