package com.platform.annotation;

import java.lang.annotation.*;

/**
 * 忽略Token验证
 *
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-03-23 15:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
