package dao.impl;

import dao.dao.ITeacherDao;
import domain.Student;
import domain.Teacher;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDaoImpl implements ITeacherDao {
    @Override
    public Teacher get(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher var1;
        try {
            conn = JdbcUtil.getConn();
            String sql = "select * from teacher where username = ? and password = ? ";
            assert conn != null;
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            Teacher teacher = new Teacher(rs.getString("username"), rs.getString("password"), rs.getString("cID"));
            var1=teacher;
        }
        catch (Exception var) {
            var.printStackTrace();
            return null;
        }
        finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return var1;
    }
}
