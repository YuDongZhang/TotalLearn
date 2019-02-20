package com.example.totallearn.zhujie;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by pateo on 19-2-20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AInherited {
    String value();
}
