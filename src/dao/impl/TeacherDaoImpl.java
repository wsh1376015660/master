package dao.impl;

import dao.dao.ITeacherDao;
import domain.Student;
import domain.Teacher;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDaoImpl implements ITeacherDao {

    public int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JdbcUtil.getConn();
            assert conn != null;
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
            JdbcUtil.close(conn, ps, null);
        }

        return 0;
    }

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

    @Override
    public void register(Teacher var_teacher) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            String sql = "insert into teacher (username,password,cID) VALUES(?,?,?) ";
            this.executeUpdate(sql,var_teacher.getUsername(),var_teacher.getPassword(),"2");
        }
        catch (Exception var) {
            var.printStackTrace();
        }
        finally {
            JdbcUtil.close(conn, null, null);
        }
    }
}
