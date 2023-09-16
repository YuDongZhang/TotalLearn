package com.example.totallearn.testRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

/*
数据库表名：Room 使用类名作为数据库表名。或通过 @Entity 注释的 tableName 属性来设置表的名称。
列名：Room 默认使用字段名作为数据库中的列名。或通过 @ColumnInfo 注释添加到字段并设置 name 属性来修改列名。
1、定义单主键
每个 Room 实体必须定义一个主键，用于唯一标识相应数据库表中的每一行。最直接的方法是使用 @PrimaryKey 注释单个列，如上面 User 类中的 id 属性。

注意：如果你需要 Room 为实体实例分配自动 ID，请将 @PrimaryKey 的 autoGenerate 属性设置为 true

java复制代码@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
}

2、定义复合主键
如果你需要通过多个列的组合来唯一标识实体的实例，你可以通过在 @Entity 的 primaryKeys 属性中列出这些列来定义复合主键：
java复制代码@Entity(tableName = "users",primaryKeys = {"mName","mAge"})
public class User {
    public String mName;
    public int mAge;
}


 */
@Entity(tableName = "student")
public class StudentHigh {
    @ColumnInfo(name = "course")
    public String courseStudent;

    public int score;

    @Ignore      //@Ignore 此属性不在数据库生产列
    public String nickname;
}
