package dao.dao;

import domain.Student;
import java.util.List;

public interface IStudentDao {
    void save(Student var1);

    void delete(int var1);

    void update(int var1, Student var2);

    Student get(int var1);

    List<Student> getAll();
}
