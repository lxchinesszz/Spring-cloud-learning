package org.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Package: org.smile.model
 * @Description: ${todo}
 * @author: liuxin
 * @date: 2017/12/21 下午3:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude ="version")
public class User {
    private String name;
    private int age;
    private String version;
}
