package com.phh.test.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 协议bean
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.netty
 * @date 2019/4/23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyProtoBean {

    /**
     * 业务编号
     */
    private byte type;
    /**
     * 消息标志：0-心跳，1-超时，2-业务 ......等等
     */
    private byte flag;
    /**
     * 消息长度
     */
    private int length;
    /**
     * 消息内容
     */
    private String content;

}
