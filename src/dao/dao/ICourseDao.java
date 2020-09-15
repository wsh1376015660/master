package dao.dao;

import domain.Course;
import java.util.List;

public interface ICourseDao {
    void save(Course var1);

    void delete(int var1);

    void update(int var1, Course var2);

    Course get(int var1);

    List<Course> getAll();
}