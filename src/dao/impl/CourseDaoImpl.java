package dao.impl;

import dao.dao.ICourseDao;
import domain.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.JdbcUtil;

public class CourseDaoImpl implements ICourseDao {
    public CourseDaoImpl() {
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

    public void save(Course course) {
        String sql = "insert into course(cID,cMajor,cName,cType,cStartTerm,cPeriod,cCredit) values (?,?,?,?,?,?,?)";
        this.executeUpdate(sql, course.getcID(), course.getcMajor(), course.getcName(), course.getcType(), course.getcStartTerm(), course.getcPeriod(), course.getcCredit());
    }

    public void delete(int cId) {
        String sql = "delete from course where cID = ?";
        this.executeUpdate(sql, cId);
    }

    public void update(int cId, Course course) {
        String sql = "update course set cMajor=?, cName=?, cType=?, cStartTerm=?,cPeriod=?, cCredit=? where cID =? ";
        this.executeUpdate(sql, course.getcMajor(), course.getcName(), course.getcType(), course.getcStartTerm(), course.getcPeriod(), course.getcCredit(), course.getcID());
        System.out.println(sql);
    }

    public Course get(int cId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Course var7;
        try {
            conn = JdbcUtil.getConn();
            String sql = "select * from course where cId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            Course course = new Course(rs.getString("cId"), rs.getString("cMajor"), rs.getString("cName"), rs.getString("cType"), rs.getString("cStartTerm"), rs.getString("cPeriod"), rs.getString("cCredit"));
            var7 = course;
        } catch (Exception var11) {
            var11.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }

        return var7;
    }

    public List<Course> getAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();
            String sql = "select * from course ";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            ArrayList list = new ArrayList();

            while(rs.next()) {
                Course course = new Course(rs.getString("cID"), rs.getString("cMajor"), rs.getString("cName"), rs.getString("cType"), rs.getString("cStartTerm"), rs.getString("cPeriod"), rs.getString("cCredit"));
                list.add(course);
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
