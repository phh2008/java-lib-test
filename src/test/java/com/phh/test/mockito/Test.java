package com.phh.test.mockito;

import org.junit.Assert;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.mockito
 * @date 2019/4/18
 */
public class Test {

    @org.junit.Test
    public void test() {

        List list = mock(List.class);

        //when thenReturn来设定 Mock 对象某个方法调用时的返回值
        when(list.add("a")).thenReturn(true);
        when(list.size()).thenReturn(1);

        Assert.assertTrue(list.add("a"));
        Assert.assertTrue(list.size() == 1);
        Assert.assertFalse(list.add("b"));

        Iterator ite = mock(Iterator.class);

        when(ite.next()).thenReturn("hello,").thenReturn("world!");

        String ret = ite.next() + "" + ite.next();

        Assert.assertEquals("hello,world!", ret);
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void test2() {
        Iterator ite = mock(Iterator.class);
        when(ite.next()).thenReturn("hello,").thenReturn("world!");
        String ret = ite.next() + "" + ite.next();
        Assert.assertEquals("hello,world!", ret);
        //当再次调用.next抛出指定异常
        doThrow(NoSuchElementException.class).when(ite).next();
        ite.next();

    }

    @org.junit.Test
    public void test3() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //至少被调用了 1 次(atLeastOnce)
        verify(mockedList, atLeastOnce()).add("one");
        //被调用了 1 次(times(1))
        verify(mockedList, times(1)).add("two");
        verify(mockedList, times(3)).add("three times");
        //从未被调用(never)
        verify(mockedList, never()).isEmpty();
    }

    @org.junit.Test
    public void test4() {
        List list = new ArrayList();
        //spy对真实对象包装
        List spy = spy(list);

        when(spy.size()).thenReturn(100);
        spy.add("one");
        spy.add("two");

        //没有对get(0),get(1)定制，获取真实数据
        Assert.assertEquals(spy.get(0), "one");
        Assert.assertEquals(spy.get(1), "two");

        Assert.assertEquals(spy.size(), 100);
        //因类监控的是真实对象，get(2)不存在，抛出IndexOutOfBoundsException
        //when(spy.get(2)).thenReturn("three");
        //可以使用doReturn、Answer、Throw()函数组来进行打桩
        doReturn("three").when(spy).get(2);
        Assert.assertEquals(spy.get(2), "three");
    }

    @org.junit.Test
    public void test5() {
        List<String> list = Arrays.asList("1", "2");
        List mockedList = mock(List.class);
        ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
        mockedList.addAll(list);

        //通过 verify(mockedList).addAll(argument.capture()) 语句来获取 mockedList.addAll 方法所传递的实参 list
        verify(mockedList).addAll(argCaptor.capture());

        Assert.assertEquals(argCaptor.getValue().size(), 2);
        Assert.assertEquals(argCaptor.getValue(), list);

    }

}
