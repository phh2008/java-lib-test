package com.phh.alibaba.tools;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.alibaba.tools
 * @date 2019/3/4
 */
@Data
public class GroupModel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String corName;

    @ExcelProperty(index = 1)
    private String groupName;

    @ExcelProperty(index = 2)
    private String code;

    @ExcelProperty(index = 3, format = "yyyy/MM/dd")
    private Date date;

}
