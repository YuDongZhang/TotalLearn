package com.example.totallearn.testRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao //这个是必须的
public interface UserDao {
    //无需编写任何 SQL 代码即可在数据库中插入、更新和删除行的便捷方法。
    //新增单个实体
    @Insert
    void insertUser(User user);
    //新增多个实体
    @Insert
    void insertUsers(List<User> users);

    //更新数据
    @Update
    void updateUser(User user);
    //删除数据
    @Delete
    void deleteUser(User user);
    
    //编写自己的 SQL 查询(query)方法
    //查询 users 表
    @Query("SELECT * FROM user")
    List<User> getAll();

    //根据name查询 users 表，将参数集合传递给查询
    @Query("SELECT * FROM user WHERE name IN (:usernames)")
    List<User> loadAllByNames(int[] usernames);

    //将简单参数传递给查询
    @Query("SELECT * FROM user WHERE age > :minAge")
    public User[] loadAllUsersOlderThan(int minAge);
}

