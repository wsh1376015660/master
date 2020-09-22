package dao.dao;

import domain.Teacher;

public interface ITeacherDao {
    Teacher get(String username,String password);

    void register(Teacher var_teacher);
}
