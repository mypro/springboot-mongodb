package org.xinhua.gk.domain.attr;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于自动生成mongodb update对象
 * 内嵌属性继承该类，在生成update对象时，如果判定属性继承自该类，则字段名设置为【属性名.子属性】
 * Created by fmy on 2017/9/1.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface BaseAttr {
}
