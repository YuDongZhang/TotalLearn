package com.example.totallearn.zhujie.customzhujie;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by pateo on 19-2-21.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) //运行时注解RUNTIME
public @interface getViewTo {
    int value() default -1;
}
