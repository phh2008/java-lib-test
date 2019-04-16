package com.phh.alibaba.tools;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.alibaba.tools
 * @date 2019/3/4
 */
public class EasyExcelTest {


    private InputStream getInputStream(String fileName) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(this.getClass().getClassLoader().getResource(fileName).getPath());
        return fileInputStream;
    }


    /**
     * @throws FileNotFoundException
     */
    @Test
    public void testExcelReaderNoModel() throws FileNotFoundException {
        InputStream in = getInputStream("group.xlsx");
        try {
            AnalysisEventListener eventListener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, eventListener, true);
            System.out.println("---------------start---------------");
            excelReader.read();
            System.out.println("---------------end---------------");
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testExcelReaderWithModel() throws FileNotFoundException {
        //测试读并映射到实体
        InputStream in = getInputStream("group.xlsx");
        try {
            AnalysisEventListener eventListener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, eventListener, true);
            System.out.println("---------------start---------------");
            excelReader.read(new Sheet(1, 1, GroupModel.class));
            System.out.println("---------------end---------------");
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<List<String>> getListString() {
        return IntStream.range(0, 10)
                .boxed()
                .map(i -> {
                    List<String> var = new ArrayList<>();
                    var.add("a " + i);
                    var.add("b " + i);
                    var.add("c " + i);
                    var.add("d " + i);
                    var.add("e " + i);
                    return var;
                }).collect(toList());
    }

    @Test
    public void testExcelWrite() throws FileNotFoundException {
        //无模型映射写excel
        OutputStream out = new FileOutputStream("F:\\temp\\testExcelWrite1.xlsx");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet");
            writer.write0(getListString(), sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<UserModel> getUsers() {
        return IntStream.range(1, 10).boxed().map(i -> {
            return new UserModel("李四", "20" + i, "10000@qq.com", "长沙", "男", "175cm", "测试" + i);
        }).collect(Collectors.toList());
    }

    @Test
    public void testExcelWrite2() throws FileNotFoundException {
        //模型映射写excel
        OutputStream out = new FileOutputStream("F:\\temp\\testExcelWrite2.xlsx");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0, UserModel.class);
            sheet1.setSheetName("第一个sheet");
            writer.write(getUsers(), sheet1);
            //多个sheet，重复上面的

            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testExcelWrite3() {
        //多级表头

    }

    @Test
    public void testExcelWrite4() throws FileNotFoundException {
        //一个sheet中有多个表格
        OutputStream out = new FileOutputStream("F:\\temp\\testExcelWrite3.xlsx");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            //写sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet");
            Table table1 = new Table(1);
            writer.write0(getListString(), sheet1, table1);

            Table table2 = new Table(2);
            table2.setClazz(UserModel.class);
            writer.write(getUsers(), sheet1, table2);

            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testExcelWrite5() throws FileNotFoundException {
        //读模版写excel
        OutputStream out = new FileOutputStream("F:\\temp\\testExcelWrite5.xlsx");
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("temp.xlsx");
            ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(inputStream, out, ExcelTypeEnum.XLSX, true);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 1);
            sheet1.setSheetName("第一个sheet");
            sheet1.setAutoWidth(true);//自适应宽度
            writer.write0(getListString(), sheet1);

            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 解析监听器，
     * 每解析一行会回调invoke()方法。
     * 整个excel解析结束会执行doAfterAllAnalysed()方法
     * 下面只是一个样例而已，可以根据自己的逻辑修改该类
     */
    public static class ExcelListener extends AnalysisEventListener {

        //自定义用于暂时存储data。
        //可以通过实例获取该值
        private List<Object> datas = new ArrayList<Object>();

        public void invoke(Object object, AnalysisContext context) {
            System.out.println("当前行：" + context.getCurrentRowNum());
            System.out.println(object);
            datas.add(object);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
            doSomething(object);//根据自己业务做处理
        }

        private void doSomething(Object object) {
            //1、入库调用接口
        }

        public void doAfterAllAnalysed(AnalysisContext context) {
            // datas.clear();//解析结束销毁不用的资源
        }

        public List<Object> getDatas() {
            return datas;
        }

        public void setDatas(List<Object> datas) {
            this.datas = datas;
        }
    }

}
