package com.example.totallearn.testRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/*
定义一个 AppDatabase 类来保存数据库。AppDatabase 定义数据库配置并充当应用程序对持久数据的主要访问点。数据库类必须满足以下条件：

该类必须使用 @Database 注释进行注释，该注释包括一个实体数组，该数组列出了与数据库关联的所有数据实体。
该类必须是扩展 RoomDatabase 的抽象类。
对于与数据库关联的每个 DAO 类，数据库类必须定义一个具有零参数并返回 DAO 类实例的抽象方法。

 */
@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

