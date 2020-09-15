package dao.impl;

import dao.dao.IStudentDao;
import domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {
    public StudentDaoImpl() {
    }

    public int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);

            int i;
            for(i = 0; i < params.length; ++i) {
                ps.setObject(i + 1, params[i]);
            }

            i = ps.executeUpdate();
            return i;
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, (ResultSet)null);
        }

        return 0;
    }

    public void save(Student stu) {
        String sql = "insert into student(stuID,stuClass,stuName,stuSex,stuBirth,stuMajor) values (?,?,?,?,?,?)";
        this.executeUpdate(sql, stu.getStuID(), stu.getStuName(), stu.getStuClass(), stu.getStuSex(), stu.getStuBirth(), stu.getStuMajor());
    }

    public void delete(int sId) {
        String sql = "delete from student where stuID = ?";
        this.executeUpdate(sql, sId);
    }

    public void update(int sId, Student stu) {
        String sql = "update student set stuClass=?, stuName=?, stuSex=?,stuBirth=?,stuMajor=? where stuId =? ";
        this.executeUpdate(sql, stu.getStuClass(), stu.getStuName(), stu.getStuSex(), stu.getStuBirth(), stu.getStuMajor(), sId);
    }

    public Student get(int sId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Student var7;
        try {
            conn = JdbcUtil.getConn();
            String sql = "select * from student where stuID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sId);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            Student stu = new Student(rs.getString("stuID"), rs.getString("stuClass"), rs.getString("stuName"), rs.getString("stuSex"), rs.getString("stuBirth"), rs.getString("stuMajor"));
            var7 = stu;
        } catch (Exception var11) {
            var11.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }

        return var7;
    }

    public List<Student> getAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();
            String sql = "select * from student ";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            ArrayList list = new ArrayList();

            while(rs.next()) {
                Student stu = new Student(rs.getString("stuID"), rs.getString("stuClass"), rs.getString("stuName"), rs.getString("stuSex"), rs.getString("stuBirth"), rs.getString("stuMajor"));
                list.add(stu);
            }

            ArrayList var12 = list;
            return var12;
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, rs);
        }

        return null;
    }
}
