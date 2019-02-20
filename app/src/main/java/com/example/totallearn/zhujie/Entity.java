package com.example.totallearn.zhujie;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by pateo on 19-2-19.
 *
 */

@Retention(RetentionPolicy.SOURCE)
public @interface Entity {
    String tableName();
}
