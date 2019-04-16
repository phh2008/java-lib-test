package com.phh.alibaba.tools;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.alibaba.tools
 * @date 2019/3/4
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserModel extends BaseRowModel {
    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "年龄", index = 1)
    private String age;

    @ExcelProperty(value = "邮箱", index = 2)
    private String email;

    @ExcelProperty(value = "地址", index = 3)
    private String address;

    @ExcelProperty(value = "性别", index = 4)
    private String sax;

    @ExcelProperty(value = "高度", index = 5)
    private String heigh;

    @ExcelProperty(value = "备注", index = 6)
    private String last;
}
