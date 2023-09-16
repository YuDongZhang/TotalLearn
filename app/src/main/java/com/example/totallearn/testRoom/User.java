package com.example.totallearn.testRoom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
/*
@Entity 注释的类 , @PrimaryKey 注释添加到正确的字段来设置主键(如：id)。默认情况会把类名作为表名 , 字段名为列名

 */
@Entity
public class User {
    @PrimaryKey
    public int id;

    public String name;
    public int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}