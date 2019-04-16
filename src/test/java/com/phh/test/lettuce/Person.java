package com.phh.test.lettuce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test
 * @date 2019/2/19
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Person implements Serializable {

    private int age;
    private String name;
    private LocalDate birthday;

}
