package com.phh.test.spring.el;

import org.junit.Test;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.beans.ParameterDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.spring.el
 * @date 2019/4/14
 */
public class ElTest {

    private ExpressionParser parser = new SpelExpressionParser();

    private void println(Object obj) {
        System.out.println(obj);
    }

    /**
     * 文本表达式
     * 文本表达式支持: 字符串(需要用单引号声明)、日期、数字、布尔类型及null,对数字支持负数、指数及小数,
     * 默认情况下实数使用Double.parseDouble()进行表达式类型转换
     */
    @Test
    public void test1() {
        println(parser.parseExpression("'hello'").getValue(String.class)); // hello , 注意单引号
        println(parser.parseExpression("1.024E+3").getValue(Long.class));  // 1024  , 指数形式
        println(parser.parseExpression("0xFFFF").getValue(Integer.class)); // 65535 , 十六进制
        println(parser.parseExpression("true").getValue(Boolean.class));   // true
        println(parser.parseExpression("null").getValue());
    }

    /**
     * 变量
     */
    @Test
    public void test2() {
        String name = "tom";
        EvaluationContext context = new StandardEvaluationContext();
        //表达式要访问变量，要把变量放到上下文中
        context.setVariable("myName", name);
        //访问变量
        println(parser.parseExpression("#myName").getValue(context, String.class));
    }

    /**
     * 属生与方法调用
     * 属性可直接使用属性名,属性名首字母大小写均可(只有首字母可不区分大小写);
     * 数组、列表可直接通过下表形式(list[index])访问;
     * map可以直接把key当成索引来访问(map[key]);
     * 方法可以直接访问;
     */
    @Test
    public void test3() {
        Person person = new Person("李四", 21);
        List<String> list = Arrays.asList("a", "b");
        Map<String, Object> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 1);
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("person", person);
        context.setVariable("map", map);
        context.setVariable("list", list);

        //属生
        println(parser.parseExpression("#person.name").getValue(context, String.class));
        println(parser.parseExpression("#person.Name").getValue(context, String.class));
        //map
        println(parser.parseExpression("#map[B]").getValue(context, Integer.class));
        //list
        println(parser.parseExpression("#list[0]").getValue(context, String.class));
        //方法
        println(parser.parseExpression("#person.getAge()").getValue(context, Integer.class));
    }

    /**
     * 类型
     * T操作符可以获取类型, 可以调用对象的静态方法
     */
    @Test
    public void test4() {
        //获取类型
        println(parser.parseExpression("T(java.util.Date)").getValue(Class.class));
        //访问静态成员(属性与方法)
        println(parser.parseExpression("T(Math).abs(-1)").getValue(Integer.class));
        //类型判断
        println(parser.parseExpression("'abc' instanceof T(String)").getValue(Boolean.class));
    }

    /**
     * 操作符
     * Spring EL 支持大多数的数学操作符、逻辑操作符、关系操作符.
     * <p>
     * 关系操作符, 包括: eq(==), ne(!=), lt()<, le(<=), gt(>), ge(>=)
     * 逻辑运算符, 包括: and(&&), or(||), not(!)
     * 数学操作符, 包括: 加(+), 减(-), 乘(*), 除(/), 取模(%), 幂指数(^)
     * 其他操作符, 如: 三元操作符, instanceof, 赋值(=), 正则匹配
     * 另外三元操作符有个特殊的用法, 一般用于赋默认值, 比如: parseExpression("#name?:'defaultName'"), 如果变量name为空时设置默认值.
     */
    @Test
    public void test5() {
        EvaluationContext context = new StandardEvaluationContext();
        Person person = new Person("Tom", 21);
        context.setVariable("person", person);
        println(parser.parseExpression("1 > -1").getValue(Boolean.class));         // true
        println(parser.parseExpression("1 gt -1").getValue(Boolean.class));        // true
        println(parser.parseExpression("true or true").getValue(Boolean.class));   // true
        println(parser.parseExpression("true || true").getValue(Boolean.class));   // true
        println(parser.parseExpression("2 ^ 3").getValue(Integer.class));          // 8
        println(parser.parseExpression("true ? true : false").getValue(Boolean.class)); // true
        println(parser.parseExpression("#name ?: 'default'").getValue(context, String.class)); // default
        println(parser.parseExpression("1 instanceof T(Integer)").getValue(Boolean.class)); // true
        println(parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class)); // true
        println(parser.parseExpression("#person.name").getValue(context, String.class));  // Tom , 原来的值
        println(parser.parseExpression("#person.name = 'Jim'").getValue(context, String.class)); // Jim , 赋值之后
        println(parser.parseExpression("#person.name").getValue(context, String.class));  // Jim, 赋值起了作用
    }

    /**
     * 避免空指针
     * 当访问一个对象的属性或方法时, 若该对象为null, 就会出现空指针异常.
     * 安全导航会判断对象是否为null,如果是的话, 就返回null而不是抛出空指针异常.
     * 使用方式就是在对象后面加个?. 如下:
     */
    @Test
    public void test6() {
        // 使用这种表达式可以避免抛出空指针异常
        EvaluationContext context = new StandardEvaluationContext();
        parser.parseExpression("#name?.toUpperCase()").getValue(context, String.class); // null
    }

    /**
     * #this变量
     * 有个特殊的变量#this来表示当前的对象. 常用于集合的过滤
     */
    @Test
    public void test7() {
        // this 使用示例
        println(parser.parseExpression("{1, 3, 5, 7}.?[#this > 3]").getValue()); // [5, 7]
    }

    /**
     * 集合选择
     * 可以使用选择表达式对集合进行过滤或一些操作，从而生成一个新的符合选择条件的集合, 有如下一些形式:
     * <p>
     * ?[expression]: 选择符合条件的元素
     * ^[expression]: 选择符合条件的第一个元素
     * $[expression]: 选择符合条件的最后一个元素
     * ![expression]: 可对集合中的元素挨个进行处理
     * 对于集合可以配合#this变量进行过滤, 对于map, 可分别对keySet及valueSet分别使用key和value关键字
     */
    @Test
    public void test8() {
        // 集合
        println(parser.parseExpression("{1, 3, 5, 7}.?[#this > 3]").getValue()); // [5, 7] , 选择元素
        println(parser.parseExpression("{1, 3, 5, 7}.^[#this > 3]").getValue()); // 5 , 第一个
        println(parser.parseExpression("{1, 3, 5, 7}.$[#this > 3]").getValue()); // 7 , 最后一个
        println(parser.parseExpression("{1, 3, 5, 7}.![#this + 1]").getValue()); // [2, 4, 6, 8] ,每个元素都加1
        // map
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("map", map);
        println(parser.parseExpression("#map.?[key > 3]").getValue(context));             // {4=D}
        println(parser.parseExpression("#map.?[value == 'A']").getValue(context));        // {1=A}
        println(parser.parseExpression("#map.?[key > 2 and key < 4]").getValue(context)); // {3=C}
    }

    /**
     * 模版表达式
     * 模板表达式允许文字和表达式混合使用, 一般选择使用#{}作为一个定界符:
     */
    @Test
    public void test9() {
        EvaluationContext context = new StandardEvaluationContext();
        Person person = new Person("Tom", 21);
        context.setVariable("person", person);

        println(parser.parseExpression("他的名字为#{#person.name}",
                new TemplateParserContext())
                .getValue(context)); // 他的名字为Tom

        //模板表达式，三元表达式结合使用
        println(parser.parseExpression("#{#person.name!=null?'他的名字叫'+#person.name+'先生':'不知道名字'}",
                new TemplateParserContext())
                .getValue(context));
    }


    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    /**
     * 解析方法参数
     * 可以结合aop实现类似spring cache 功能
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void test10() throws NoSuchMethodException {
        Person person = new Person("李四", 21);
        hello(person, "hello");
    }

    public void hello(Person person, String var) throws NoSuchMethodException {
        System.out.println(var);
        Method m = this.getClass().getMethod("hello", Person.class, String.class);
        EvaluationContext context = new MethodBasedEvaluationContext(null, m, new Object[]{person, var}, parameterNameDiscoverer);
        String result = parser.parseExpression("#person.Name +':'+#var+':test'").getValue(context, String.class);
        println(result);
    }

}
