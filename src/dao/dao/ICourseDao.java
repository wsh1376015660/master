package dao.dao;

import domain.Course;
import java.util.List;

public interface ICourseDao {       //对Course类对象的增删查改的接口
    void save(Course var1);

    void delete(int var1);      //给定编号删除一个课程

    void update(int var1, Course var2);     //给定编号 和 一个完整的Course类的实例，改变给定的课程

    Course get(int var1);       //给定编号读取课程详情

    List<Course> getAll();      //获取所有课程 此处用泛型
}